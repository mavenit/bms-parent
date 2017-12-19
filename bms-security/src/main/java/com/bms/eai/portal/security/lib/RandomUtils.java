package com.bms.eai.portal.security.lib;

import java.util.Random;

/**
 * @author kul_sudhakar
 *
 */
public class RandomUtils {

	private static final String ALPHA_NUMERIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static int randInt(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}

	public static String randomNumeric(int length) {
		int start = (int) Math.pow(10, length);
		int remaining = 9 * start;
		Random rnd = new Random();
		return Integer.toString(start + rnd.nextInt(remaining));
	}

	public static String randomAlphaNumeric(int length) {
		char[] buf = new char[length];
		Random random = new Random();
		for (int i = 0; i < buf.length; i++) {
			buf[i] = ALPHA_NUMERIC.charAt(random.nextInt(ALPHA_NUMERIC.length()));
		}
		return new String(buf);
	}

}
