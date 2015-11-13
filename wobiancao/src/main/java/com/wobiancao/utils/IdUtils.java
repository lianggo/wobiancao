package com.wobiancao.utils;

import java.util.Random;

public class IdUtils {

	private final static Random random = new Random();
	private static final char[] ALPHA_NUMERIC;
	static {
		StringBuilder sb = new StringBuilder();
		for (char ch = '0'; ch <= '9'; ++ch) {
			sb.append(ch);
		}
		for (char ch = 'a'; ch <= 'z'; ch++) {
			sb.append(ch);
		}
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			sb.append(ch);
		}
		ALPHA_NUMERIC = sb.toString().toCharArray();
	}

	public static int MODE_NUMERIC_ONLY = 0;
	public static int MODE_NUMERIC_ALPHABET = 1;

	public static String generateRandomId(int length) {
		String result = generateRandomId(length, MODE_NUMERIC_ALPHABET);
		return result;
	}

	public static String generateRandomId(int length, int mode) {
		if (length < 1) {
			throw new IllegalArgumentException("length < 1: " + length);
		}
		int range = 0;
		if (mode == MODE_NUMERIC_ONLY) {
			range = 10;
		} else if (mode == MODE_NUMERIC_ALPHABET) {
			range = ALPHA_NUMERIC.length;
		} else {
			range = ALPHA_NUMERIC.length;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(ALPHA_NUMERIC[random.nextInt(range)]);
		}
		return sb.toString();
	}

}
