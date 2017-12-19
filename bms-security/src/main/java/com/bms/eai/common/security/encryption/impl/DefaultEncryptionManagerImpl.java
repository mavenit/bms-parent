package com.bms.eai.common.security.encryption.impl;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bms.eai.common.security.encryption.EncryptionException;
import com.bms.eai.common.security.encryption.EncryptionManager;
import com.bms.eai.common.security.encryption.EncryptionToken;
import com.bms.eai.portal.security.lib.RandomUtils;

/**
 * @author kul_sudhakar
 *
 */
@Component
public class DefaultEncryptionManagerImpl implements EncryptionManager {

	private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

	@Autowired
	private EncryptionProperties props = null;

	public void setProps(EncryptionProperties props) {
		this.props = props;
	}

	@Override
	public EncryptionToken encrypt(String unencrypted) throws EncryptionException {
		try {
			int saltLen = RandomUtils.randInt(props.getMinSaltLength(), props.getMaxSaltLength());
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[saltLen];
			random.nextBytes(salt);

			int iterations = RandomUtils.randInt(props.getMinIterations(), props.getMaxIterations());

			byte[] encrypted = computeHash(unencrypted.toCharArray(), salt, iterations, props.getHashLength());
			return new EncryptionToken(encrypted, salt, iterations);
		} catch (GeneralSecurityException | UnsupportedEncodingException e) {
			throw new EncryptionException(e);
		}
	}

	@Override
	public boolean equals(String unencrypted, EncryptionToken encrypted) {
		try {
			byte[] testToken = computeHash(unencrypted.toCharArray(), encrypted.getSalt(), encrypted.getIteration(),
					encrypted.getEncrypted().length);
			return slowEquals(encrypted.getEncrypted(), testToken);
		} catch (GeneralSecurityException | UnsupportedEncodingException e) {
			return false;
		}
	}

	private byte[] computeHash(char[] password, byte[] salt, int iterations, int hashLen)
			throws GeneralSecurityException, UnsupportedEncodingException {
		PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, hashLen * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
		return skf.generateSecret(spec).getEncoded();
	}

	private static boolean slowEquals(byte[] a, byte[] b) {
		int diff = a.length ^ b.length;
		for (int i = 0; i < a.length && i < b.length; i++)
			diff |= a[i] ^ b[i];
		return diff == 0;
	}
}
