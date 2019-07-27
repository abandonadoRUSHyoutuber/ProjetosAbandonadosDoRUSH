package rush.warpapbr;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import rush.warpapbr.command.Command;
import rush.warpapbr.config.Config;
import rush.warpapbr.utils.ConfigManager;
import rush.warpapbr.utils.DataManager;

public class Main extends JavaPlugin {
	
	private static Main main;
	
	@Override
	public void onEnable() {
		main = this;
		createConfig();
		registreCommands();
	}

	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
	}

	private void createConfig() {
		ConfigManager.createConfig("config");
		DataManager.createFolder("locations");
		Config.loadConfig();
	}
	
	private void registreCommands() {
		for (String cmd : ConfigManager.getConfig("config").getConfigurationSection("Comandos").getKeys(false)) {
			new Command(cmd);
		}
	}

	public static Main get() {
		return main;
	}

}