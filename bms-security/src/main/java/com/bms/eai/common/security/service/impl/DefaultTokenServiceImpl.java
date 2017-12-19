package com.bms.eai.common.security.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.bms.eai.common.security.entity.UserIdentity;
import com.bms.eai.common.security.service.TokenService;
import com.bms.eai.common.security.service.UserSessionService;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

/**
 * @author kul_sudhakar
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
@Service
public class DefaultTokenServiceImpl implements TokenService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final Cache tokenCache = CacheManager.getInstance().getCache("tokenCache");

	@Value("${bms.authentication.token.encryption.algorithm:SHA-1}")
	private String tokenEncryptionAlgorithm;

	@Value("${bms.authentication.token.encryption.salt:bms}")
	private String tokenEncryptionSalt;

	@Autowired
	private CacheEventListener cacheEventListener;

	@Autowired
	private UserSessionService userSessionService;

	@PostConstruct
	public void init() {
		tokenCache.getCacheEventNotificationService().registerListener(cacheEventListener);
	}

	@PreDestroy
	public void destroy() {
		tokenCache.getCacheManager().shutdown();
	}

	/**
	 * Happens every 1 minute. Token expiration will be configured in ehcache
	 * settings.
	 */
	@Scheduled(fixedRate = 60000)
	public void evictExpiredTokens() {
		logger.info("Evicting expired tokens.");
		tokenCache.evictExpiredElements();
		tokenCache.flush();
	}

	@Override
	public String generateNewToken() {
		String token;
		do {
			// Loop to make sure that encrypted token will not clash with
			// existing tokens.
			token = encrypt(UUID.randomUUID().toString());
		} while (tokenCache.isKeyInCache(token));
		return token;
	}

	@Override
	public void store(String token, Authentication authentication) {
		tokenCache.put(new Element(token, authentication));
	}

	@Override
	public boolean contains(String token) {
		Element element = tokenCache.get(token);
		return element != null && !element.isExpired();
	}

	@Override
	public Authentication retrieve(String token) {
		Element element = tokenCache.get(token);
		if (element != null && !element.isExpired()) {
			return (Authentication) element.getObjectValue();
		} else {
			return null;
		}
	}

	@Override
	public void remove(String token) {
		tokenCache.remove(token);
	}

	@Override
	public void removeByUsername(String username) {
		List<String> keys = tokenCache.getKeysWithExpiryCheck();
		for (String key : keys) {
			Element element = tokenCache.getQuiet(key);
			if (element != null && !element.isExpired()) {
				Authentication auth = (Authentication) element.getObjectValue();
				UserIdentity identity = userSessionService.getUserIdentity(auth);
				if (identity.getUsername().equals(username)) {
					tokenCache.remove(key);
				}
			}
		}
	}

	private String encrypt(String plainToken) {
		MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder(tokenEncryptionAlgorithm);
		return encoder.encodePassword(plainToken, tokenEncryptionSalt);
	}

}
