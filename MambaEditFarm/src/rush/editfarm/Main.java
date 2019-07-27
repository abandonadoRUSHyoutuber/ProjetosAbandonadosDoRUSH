package rush.editfarm;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import rush.editfarm.listener.EntityChangeBlock;
import rush.editfarm.listener.EntityDamage;

public class Main extends JavaPlugin {
 
	@Override
	public void onEnable() {
		registrarEventos();
	}

	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
	}
	
	private void registrarEventos() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new EntityDamage(), this);
		pm.registerEvents(new EntityChangeBlock(), this);
	}

}