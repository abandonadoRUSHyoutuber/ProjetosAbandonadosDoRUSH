package rush.antiprotectspawners.config;

import java.util.List;

import rush.antiprotectspawners.Main;

public class Config {
	
	public static List<Integer> IDS;
	public static Integer DST;
	public static String MSG1;
	public static String MSG2;
	
	public static void loadConfig() {
		IDS = Main.get().getConfig().getIntegerList("Blocos-De-Protecao");
		DST = Main.get().getConfig().getInt("Distancia-Minima-Do-Spawner");
		MSG1 = Main.get().getConfig().getString("Protecao-Com-Spawner-Perto").replace('&', '§');
		MSG2 = Main.get().getConfig().getString("Spawner-Com-Protecao-Perto").replace('&', '§');
	}
}