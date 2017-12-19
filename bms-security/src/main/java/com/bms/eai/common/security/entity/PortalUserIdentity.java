package com.bms.eai.common.security.entity;

import java.util.Locale;

import com.bms.eai.portal.security.entity.Account;
import com.bms.eai.portal.security.lib.CodecUtils;

/**
 * @author kul_sudhakar
 *
 */
public class PortalUserIdentity implements UserIdentity<Long> {

	private Account account;

	public PortalUserIdentity(Account account) {
		this.account = account;
	}

	@Override
	public Long getUserId() {
		return account.getId();
	}

	@Override
	public String getUserIdString() {
		return Long.toString(getUserId());
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

	@Override
	public byte[] getPassword() {
		return CodecUtils.decodeBase64(account.getPassword());
	}

	@Override
	public byte[] getSalt() {
		return CodecUtils.decodeBase64(account.getSalt());
	}

	@Override
	public int getIteration() {
		return account.getIteration();
	}

	@Override
	public String getPrefLanguageCode() {
		return Locale.ENGLISH.getLanguage();
	}

	public Account getAccount() {
		return account;
	}

}
