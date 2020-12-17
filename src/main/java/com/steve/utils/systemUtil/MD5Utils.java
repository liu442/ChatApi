package com.steve.utils.systemUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;

public class MD5Utils {

	/**
	 * MD532加密
	 *
	 * @param readyEncryptStr
	 * @param saltKey
	 * @return
	 */
	public static String MD532(String readyEncryptStr, String saltKey) {
		if (StringUtils.isNotEmpty(readyEncryptStr) && saltKey != null) {
			readyEncryptStr = readyEncryptStr + saltKey;
			//Get MD5 digest algorithm's MessageDigest's instance.
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("MD5");
				//Use specified byte update digest.
				md.update(readyEncryptStr.getBytes());
				//Get cipher text
				byte[] b = md.digest();
				//The cipher text converted to hexadecimal string
				StringBuilder su = new StringBuilder();
				//byte array switch hexadecimal number.
				for (byte aB : b) {
					String haxHex = Integer.toHexString(aB & 0xFF);
					if (haxHex.length() < 2) {
						su.append("0");
					}
					su.append(haxHex);
				}
				return su.toString();
			} catch (NoSuchAlgorithmException e) {
				return "";
			}
		} else {
			return "";
		}
	}

	public static String toMd5(String value) {
		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(value.getBytes("UTF-8"));
			byte[] arr = mdInst.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < arr.length; ++i) {
				sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,
						3));
			}
			return sb.toString();
		} catch (Exception e) {
			return e.toString();
		}

	}

	public static final String getMD532(String str) {
		try {
			if (str != null) {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(str.getBytes());
				byte[] b = md.digest();
				StringBuilder su = new StringBuilder();
				int offset = 0;
				for (int bLen = b.length; offset < bLen; ++offset) {
					String haxHex = Integer.toHexString(b[offset] & 255);
					if (haxHex.length() < 2) {
						su.append("0");
					}
					su.append(haxHex);
				}
				return su.toString();
			} else {
				return "";
			}
		} catch (Exception e) {
			return "";
		}
	}

	public static void main(String[] args) {
		try {
			String md5 = UUIDUtils.getUUID32();
			System.out.println(md5);
			System.err.println(MD5Utils.MD532("123456",md5));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
