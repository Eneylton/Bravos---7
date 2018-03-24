package com.java.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SenhaUtil {

	public static String criptografar(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String senhaOriginal = senha + "senh@S1st3m@";
		String senhaCriptografada;
		
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(senhaOriginal.getBytes("UTF-8"));
		 
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
		  hexString.append(String.format("%02X", 0xFF & b));
		}
		
		senhaCriptografada = hexString.toString();
		
		return senhaCriptografada;
		
	}
	
}
