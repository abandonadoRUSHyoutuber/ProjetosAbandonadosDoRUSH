package rush.warpapbr.config;

import org.bukkit.configuration.file.FileConfiguration;

import rush.warpapbr.utils.ConfigManager;

public abstract class Config {

	protected static String CONSOLE_ERRO, TELEPORTADO, INCORRETO, CRIADA, DELETADA, ERRO;
	
	public static void loadConfig() {
		FileConfiguration config = ConfigManager.getConfig("config");
		CONSOLE_ERRO = config.getString("Console-Nao-Pode").replace('&', '§');
		TELEPORTADO = config.getString("Teleporte-Concluido").replace('&', '§');
		INCORRETO = config.getString("Admin-Comando-Incorreto").replace('&', '§');
		CRIADA = config.getString("Posicao-Definida").replace('&', '§');
		DELETADA = config.getString("Posicao-Deletada").replace('&', '§');
		ERRO = config.getString("Ainda-Nao-Definido").replace('&', '§');
	}
	
}