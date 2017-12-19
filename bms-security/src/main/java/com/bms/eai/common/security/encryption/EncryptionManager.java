package com.bms.eai.common.security.encryption;

/**
 * @author kul_sudhakar
 *
 */
public interface EncryptionManager {

	EncryptionToken encrypt(String unencrypted) throws EncryptionException;

	boolean equals(String unencrypted, EncryptionToken encrypted);

}
