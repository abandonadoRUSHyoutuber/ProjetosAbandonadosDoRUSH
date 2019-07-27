package rush.mior.teste;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

public class A {

	private static ObjectInputStream input;
	private static ObjectOutputStream output;
	private static Socket socket;

	public static void startConnection(String IP) throws ConnectException, IOException {
		socket = new Socket(IP, 80);
		output = new ObjectOutputStream(socket.getOutputStream());
		input = new ObjectInputStream(socket.getInputStream());
	}

	final static String IP = "8.8.8.8";

	public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		for (Class<?> clazz : getAllClasses("rush"))  System.out.println(clazz.getName());
	}

	public static List<Class<?>> getAllClasses(String pckg) throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<>();
		File directory = new File(Thread.currentThread().getContextClassLoader().getResource(pckg).getFile());
		for (File file : directory.listFiles()) {
			if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(pckg.replace('/', '.') + '.' + file.getName().replace(".class", "")));
			} else {
				if (file.isDirectory()) {
					classes.addAll(getAllClasses(pckg + "/" + file.getName()));
				}
			}
		}
		return classes;
	}

	public static Class<?>[] getClasses(String packageName) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class<?>> classes = new ArrayList<>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	private static Collection<? extends Class<?>> findClasses(File directory, String packageName)
			throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(
						Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}

	static void a() {
		try {
			startConnection(IP);
		} catch (ConnectException e) {
			System.out.println("Deu connection exception!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String encondeIp(String IP) {
		return IP.toUpperCase().replace('0', 'X').replace('1', 'A').replace('2', 'E').replace('3', 'H')
				.replace('4', 'J').replace('5', 'L').replace('6', 'N').replace('7', 'P').replace('8', 'R')
				.replace('9', 'T').replace('.', '-');
	}

	public static boolean validateIp(String IP) {
		return IP.matches("((1?\\d{1,2}|2([0-4]\\d|5[0-5]))\\.){3}(1?\\d{1,2}|2([0-4]\\d|5[0-5]))");
	}

	private static String decodeIp(String IP) {
		return IP.toUpperCase().replace('X', '0').replace('A', '1').replace('E', '2').replace('H', '3')
				.replace('J', '4').replace('L', '5').replace('N', '6').replace('P', '7').replace('R', '8')
				.replace('T', '9').replace('-', '.');
	}

}