package rush.contkill;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import rush.contkill.listener.PlayerDeath;

public class Main extends JavaPlugin {

	private static Main main;

	@Override
	public void onEnable() {
		main = this;
		saveDefaultConfig();
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
	}

	@Override
	public void onDisable() {
		HandlerList.unregisterAll((Listener) this);
	}

	public static Main get() {
		return main;
	}

}