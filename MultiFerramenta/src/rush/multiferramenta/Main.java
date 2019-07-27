package rush.multiferramenta;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import rush.multiferramenta.commands.CommandGivemulti;
import rush.multiferramenta.config.Config;
import rush.multiferramenta.listener.BlockDamage;

public class Main extends JavaPlugin {

	private static Main aqui;

	@Override
	public void onEnable() {
		aqui = this;
		saveDefaultConfig();
		getCommand("givemulti").setExecutor(new CommandGivemulti());
		Bukkit.getServer().getPluginManager().registerEvents(new BlockDamage(), this);
	}

	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
	}
	
	@Override
	public void saveDefaultConfig() {
		super.saveDefaultConfig();
		new Config(this);
	}

	public static Main get() {
		return aqui;
	}

}