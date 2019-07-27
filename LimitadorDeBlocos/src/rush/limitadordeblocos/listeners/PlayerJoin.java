package rush.limitadordeblocos.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import rush.limitadordeblocos.Main;

public class PlayerJoin implements Listener {
	
	private Main plugin;
	
	public PlayerJoin(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		plugin.blockmanager.savePlayer(e.getPlayer().getName());
	}
	
}