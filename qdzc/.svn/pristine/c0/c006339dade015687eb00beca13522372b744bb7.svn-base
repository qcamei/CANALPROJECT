package com.cqupt.pub.util;

import java.io.IOException;

public class DeEncode {

	public static String encodeString(String str) {
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		String encodedStr = new String(encoder.encodeBuffer(str.getBytes()));
		return encodedStr.trim();
	}

	public static String decodeString(String str) throws IOException {
		sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
		String value = new String(dec.decodeBuffer(str));

		return value;
	}

	public static void main(String[] args) {
		String s = "lzadmin@sctel.com.cn";
		String enc = encodeString(s);
		String dec = "";
		try {
			dec = decodeString(enc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(enc);
		System.out.println(dec);
	}
}