package com.bms.eai.common.security.encryption;

/**
 * @author kul_sudhakar
 *
 */
public class EncryptionToken {

	private byte[] encrypted;
	private byte[] salt;
	private int iteration;

	public EncryptionToken(byte[] encrypted, byte[] salt, int iteration) {
		this.encrypted = encrypted;
		this.salt = salt;
		this.iteration = iteration;
	}

	public byte[] getEncrypted() {
		return encrypted;
	}

	public byte[] getSalt() {
		return salt;
	}

	public int getIteration() {
		return iteration;
	}

}
