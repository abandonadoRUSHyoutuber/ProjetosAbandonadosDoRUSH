package rush.antiprotectspawners;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import rush.antiprotectspawners.config.Config;
import rush.antiprotectspawners.listener.BlockPlace;
import rush.antiprotectspawners.listener.PistonExtend;

public class Main extends JavaPlugin {

	private static Main main;

	@Override
	public void onEnable() {
		main = this;
		registrarEventos();
		gerarConfigs();
	}

	@Override
	public void onDisable() {
		HandlerList.unregisterAll((Listener) this);
	}

	private void gerarConfigs() {
		saveDefaultConfig();
		Config.loadConfig();
	}

	private void registrarEventos() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new BlockPlace(), this);
		pm.registerEvents(new PistonExtend(), this);
	}

	public static Main get() {
		return main;
	}

}