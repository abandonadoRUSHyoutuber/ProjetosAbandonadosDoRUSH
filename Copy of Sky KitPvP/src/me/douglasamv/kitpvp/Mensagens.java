package me.douglasamv.kitpvp;

public class Mensagens {

	public static String cor(String s) {
		return s.replace("&", "§");
	}

	public static String consolePlayer = cor(Main.pl.getConfig().getString("ConsolePlayer"));
	
	public static String offlinePlayer = cor(Main.pl.getConfig().getString("OffLinePlayer"));
	
	public static String noPerm = cor(Main.pl.getConfig().getString("NoPerm"));

	public static String entrarModoAdmin = cor(Main.pl.getConfig().getString("adminMSG.enter"));
	public static String sairModoAdmin = cor(Main.pl.getConfig().getString("adminMSG.exit"));
	public static String trocaRapida = cor(Main.pl.getConfig().getString("adminMSG.quickchange"));
	public static String autoSoupResult1 = cor(Main.pl.getConfig().getString("adminMSG.autosoupUse"));
	public static String autoSoupResult2 = cor(Main.pl.getConfig().getString("adminMSG.autosoupNoUse"));

}
