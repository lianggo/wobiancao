package com.wobiancao.utils;

import java.io.UnsupportedEncodingException;

public class StringTest {

	public static void main(String[] args) {
		String s = "æ¹äº®";
		try {
			String b = new String(s.getBytes("ISO-8859-1"), "UTF-8");
			System.out.println(b);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
