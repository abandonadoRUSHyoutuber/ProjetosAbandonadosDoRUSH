import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

public class Teste {
	
	private static final Random random = new Random();

	static String a;
	static Integer s;
	
	static void teste1(Map<String, Map<Integer, Integer>> map) {
		for (double i = 0D; i < 10000; i++) {
			String a = i + "";
		}
	}
	
	static void teste2(Map<String, Map<Integer, Integer>> map) {
		for (double i = 0D; i < 10000; i++) {
			String a = String.valueOf(i);
		}
	}
	
	static void teste3(Map<String, Map<Integer, Integer>> map) {
		for (Double i = 0D; i < 10000; i++) {
			String a = i.toString();
		}
	}
	
	public static String getFullStringByContains(List<String> input, String contains) {
		for (String element : input) {
			if (element.contains(contains)) return element;
		}
		return null;
	}
	
	private static int getIndexLore() {
		//if (!item.hasItemMeta() || !item.getItemMeta().hasLore()) return -1;
		//String kills = Settings.Main_String_Lore_Cont_Kill.replace("%kills%", "");
		//for (int i = 0; i < meta.getLore().size(); i++) 
			//if (meta.getLore().get(i).contains(kills)) return i;
		return -2;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		String faa = "abcdefghijklmnopqrstuvwxyz0123456789".toUpperCase();
		int ifz = 0;
		for (Character c : faa.toCharArray()) {
			System.out.println("banners.put('"+  c + "', banner" + c + ");");
			ifz++;
		}
		System.out.println(ifz);
		
		String ax = " oi0 %player% oi1";
		String bx = "%player% oi1";
		String cx = " oi1";
		
		System.out.println(ax.split("%player%")[0].isEmpty() ? ax.replace("%player%", "") : ax.split("%player%")[0]);
		System.out.println(bx.split("%player%")[0].isEmpty() ? bx.replace("%player%", "") : bx.split("%player%")[0]);
		System.out.println(cx.split("%player%")[0].isEmpty() ? cx.replace("%player%", "") : cx.split("%player%")[0]);

		
		// Metodo 1, caso você estiver usando tipos primitivos
		double d1 = 2.5;
		int i1 = (int) d1;
		
		// Metodo 2, caso você estiver usando objetos
		Double d2 = 2.5;
		Integer i2 = d2.intValue();
		
		
		// Metodo 1, caso você estiver usando tipos primitivos (não precisa fazer nada kkk)
		int i3 = 2;
		double d3 = i3;
		
		// Metodo 1, caso você estiver usando objetos
		Integer i4 = 2;
		Double d4 = i4.doubleValue();
		
		
		int _i = i1 + i2;

		Set<String> players = new HashSet<>();
		players.add("a");
		players.add(null);
		players.add(null);
		
		System.out.println(players.size());
		
		players.add("a");
		players.add(null);
		players.add("a");
		System.out.println(players.size());
		
		players.remove(null);
		
		System.out.println(players.size());
		
		System.out.println("a");
		
		if (Boolean.TRUE) return;
		
		Class<Double> a = Double.TYPE;
		Class<Double> b = Double.class;
		Class<Double> c = double.class;
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		
		if (Boolean.TRUE) return;	
		
		List<String> lore = Arrays.asList(" &c ", "&cKills: %kills%", " &c &c");
		String mainLore = getFullStringByContains(lore, "%kills%");
		System.out.print("1) Main Lore detectada: ");
		System.err.println(mainLore);
		
		
		System.out.println("\n2) Lore 1: " + lore.get(1));
		lore.set(1, lore.get(1).replace("%kills%", "1"));
		System.out.println("\n3) Lore 1: " + lore.get(1));
		
		String set = lore.get(1).replace(mainLore.replace("%kills%", ""), "");
		System.out.println("\n4) Set: " + set);
		
		int contKills = Integer.valueOf(set) + 1;
		System.out.println("\n5) ContKill: " + contKills);
		
		lore.set(1, mainLore.replace("%kills%", String.valueOf(contKills)));
		System.out.println("\n6) Lore 1: " + lore.get(1));
		
		
		
		set = lore.get(1).replace(mainLore.replace("%kills%", ""), "");
		System.out.println("\n7) Set: " + set);
		
		contKills = Integer.valueOf(set) + 1;
		System.out.println("\n8) ContKill: " + contKills);
		
		lore.set(1, mainLore.replace("%kills%", String.valueOf(contKills)));
		System.out.println("\n9) Lore 1: " + lore.get(1));
		
		if (Boolean.TRUE) return;
		
		int nMsg = 0;
		List<String> lista = new ArrayList<>();
		lista.add("1");
		lista.add("2");
		lista.add("3");
		lista.add("4");
		lista.add("5");
		int size = lista.size() - 1;
		
		System.out.println(lista.get(nMsg));
		System.out.println("nmsg " + nMsg + " - size " + size +  " -- " + (nMsg == size));
		nMsg = nMsg == size ? 0 : nMsg + 1;
		
		System.out.println(lista.get(nMsg));
		System.out.println("nmsg " + nMsg + " - size " + size +  " -- " + (nMsg == size));
		nMsg = nMsg == size ? 0 : nMsg + 1;
		
		System.out.println(lista.get(nMsg));
		System.out.println("nmsg " + nMsg + " - size " + size +  " -- " + (nMsg == size));
		nMsg = nMsg == size ? 0 : nMsg + 1;
		
		System.out.println(lista.get(nMsg));
		System.out.println("nmsg " + nMsg + " - size " + size +  " -- " + (nMsg == size));
		nMsg = nMsg == size ? 0 : nMsg + 1;
		
		System.out.println(lista.get(nMsg));
		System.out.println("nmsg " + nMsg + " - size " + size +  " -- " + (nMsg == size));
		nMsg = nMsg == size ? 0 : nMsg + 1;
		
		System.out.println(lista.get(nMsg));
		System.out.println("nmsg " + nMsg + " - size " + size +  " -- " + (nMsg == size));
		nMsg = nMsg == size ? 0 : nMsg + 1;
		
		System.out.println(lista.get(nMsg));
		System.out.println("nmsg " + nMsg + " - size " + size +  " -- " + (nMsg == size));
		nMsg = nMsg == size ? 0 : nMsg + 1;
		
		System.out.println(lista.get(nMsg));
		System.out.println("nmsg " + nMsg + " - size " + size +  " -- " + (nMsg == size));
		nMsg = nMsg == size ? 0 : nMsg + 1;
		
		System.out.println(lista.get(nMsg));
		System.out.println("nmsg " + nMsg + " - size " + size +  " -- " + (nMsg == size));
		nMsg = nMsg == size ? 0 : nMsg + 1;
		
		//saveProps();
		//getProps();
		
		/**
		for (Field m : TesteReflection.class.getFields()) {
			System.out.println(m.getName());
		}
		
		System.out.println("===");

		for (Field m : TesteReflection.class.getDeclaredFields()) {
			System.out.println(m.getName());
		}
		*/
		
			if (true) return;
			
		double o4 = 05D;
		double o3 = 10D;
		double o2 = 10D;
		double o1 = 15D;
		
		System.out.println(Double.compare(o2,o1));
		System.out.println(Double.compare(o2,o3));
		System.out.println(Double.compare(o2,o4));
		
		System.out.println("\n\n");
		
		Map<String, Map<Integer, Integer>> map = new HashMap<>();
		
		Map<Integer, Integer> map1 = new HashMap<>();
		Map<Integer, Integer> map2 = new HashMap<>();
		Map<Integer, Integer> map3 = new HashMap<>();

		for (int i = 0; i < 5; i++) {
			map1.put(i, i);
			map2.put(i, i);
			map3.put(i, i);
		}
		
		map.put("1", map1);
		//map.put("2", map2);
		//map.put("3", map3);


		long ti1, tf1, ti2, tf2, ti3, tf3;
		
		ti1 = System.nanoTime();
		teste1(map);
		tf1 = System.nanoTime();
		
		ti2 = System.nanoTime();
		teste2(map);
		tf2 = System.nanoTime();
		
		ti3 = System.nanoTime();
		teste3(map);
		tf3 = System.nanoTime();
		
		System.out.println(" ");
		System.out.println("Somando: " + (tf1 - ti1));
		System.out.println("valueOf: " + (tf2 - ti2));
		System.out.println("toStrin: " + (tf3 - ti3));

		
	//Thread.setDefaultUncaughtExceptionHandler(new FinalExceptionHandler());
		
		//a.equals("dsadsa");
		//"sadas".equals(a);
		
		//int b = s;
		
		//System.out.println("ewiiii");

		/*
		Collection<a> players = new ArrayList<>();
		
		for (int i = 0; i < 20000; i++) {
			players.add(new a(i,i,i,i,i,i,i,i,i));
		}
		
		System.out.println("\n\n\n\n");
		long t1, t2;
		
		for (int i = 0 ; i < 15; i++) {
			t1 = System.currentTimeMillis();
			a[] playerz = players.toArray(new a[players.size()]);
			t2 = System.currentTimeMillis();
			System.out.println(i + " - Sem Stream Demorou: " + (t2 - t1));
		}
		
		System.out.println("\n\n");

		for (int i = 0 ; i < 15; i++) {
			t1 = System.currentTimeMillis();
			a[] playerz = players.stream().toArray(a[]::new);
			t2 = System.currentTimeMillis();
			System.out.println(i + " - Com Stream Demorou: " + (t2 - t1));
		}
		*/
		
		/**
		
		
		
		String str = "Oi%n%nova linha!%n%Outra linha";
		
		String[] lines = str.replace('&', '§').split("%n%");
		
		System.out.println(lines.length);
		
		for (String line : lines) {
			System.out.println(line);
		}
		
		File file = new File("D:\\prop.properties");
		System.out.println(file.length());
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(file);

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println(prop.getProperty("database"));
			System.out.println(prop.getProperty("dbuser"));
			System.out.println(prop.getProperty("dbpassword"));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}*/

	}

	private static void getProps() throws IOException {
		Properties prop = new Properties();
		File file = new File("D:\\prop.properties");
		
		InputStreamReader reader = new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8"));		
		prop.load(reader);
		
		System.out.println(prop.getProperty("host"));
		System.out.println(prop.getProperty("user"));
		System.out.println(prop.getProperty("password"));
		System.out.println(prop.getProperty("teste"));
		
		reader.close();
	}

	private static void saveProps() throws IOException {
		Properties prop = new Properties();
		File file = new File("D:\\prop.properties");
		
		OutputStream out = new FileOutputStream(file);
		
		prop.setProperty("host", "localhost");
		prop.setProperty("user", "master");
		prop.setProperty("password", "masterkey");
		prop.setProperty("teste", "não § a Óÿ é í");
		
		prop.store(out, null);
		out.close();
	}
}