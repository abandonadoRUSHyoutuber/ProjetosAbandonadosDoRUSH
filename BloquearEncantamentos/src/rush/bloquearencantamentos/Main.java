package rush.bloquearencantamentos;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import rush.bloquearencantamentos.core.RandomEnchanter;
import rush.bloquearencantamentos.listener.EnchantEvent;
import rush.bloquearencantamentos.listener.InventoryClick;

public class Main extends JavaPlugin {

	private RandomEnchanter randomEnchanter;
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(new InventoryClick(this), this);
		Bukkit.getPluginManager().registerEvents(new EnchantEvent(this), this);
		this.randomEnchanter = new RandomEnchanter(this);
	}
	
	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
	}
	
	public RandomEnchanter randomEnchanter() {
		return randomEnchanter;
	}
	
}