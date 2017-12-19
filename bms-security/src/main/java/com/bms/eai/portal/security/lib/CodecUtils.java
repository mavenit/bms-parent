package com.bms.eai.portal.security.lib;

import org.apache.commons.codec.binary.Base64;

/**
 * @author kul_sudhakar
 *
 */
public class CodecUtils {

	public static String encodeBsae64(byte[] binary) {
		return Base64.encodeBase64String(binary);
	}

	public static byte[] decodeBase64(String base64String) {
		return Base64.decodeBase64(base64String);
	}

}
