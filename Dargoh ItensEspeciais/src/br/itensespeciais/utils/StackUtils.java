package br.itensespeciais.utils;
 
import java.util.Random;
 
public final class StackUtils {
	public static final Random RANDOM;
	private static final char[] APPEND;
 
	public static String randomString() {
		StackUtils.APPEND[1] = (char) (48 + StackUtils.RANDOM.nextInt(10));
		StackUtils.APPEND[3] = (char) (48 + StackUtils.RANDOM.nextInt(10));
		StackUtils.APPEND[5] = (char) (48 + StackUtils.RANDOM.nextInt(10));
		StackUtils.APPEND[7] = (char) (48 + StackUtils.RANDOM.nextInt(10));
	return new String(StackUtils.APPEND);
	}
 
	static {
		RANDOM = new Random();
		APPEND = new char[] { '§', '\0', '§', '\0', '§', '\0', '§', '\0' };
	}
}