package br.upf.ads.gestordebolsas.util;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;

public final class Senha {

	private static final Charset CHARSET = Charset.forName("UTF-8");
	private static final String ALGORITHM = "MD5";

	public static String Criptografar(String senhaDigitada) {
		try {
			MessageDigest m = MessageDigest.getInstance(ALGORITHM);
			byte[] input = senhaDigitada.getBytes(CHARSET);
			byte[] result = m.digest(input);
			return new BigInteger(1, result).toString(16);
		} catch (Throwable e) {
			e.printStackTrace();
			return "";
		}
	}

	public static boolean Validar(String senhaDoBancoDeDados, String senhaDigitada) {
		return senhaDoBancoDeDados.equals(Criptografar(senhaDigitada));
	}

}