import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class $ extends JavaPlugin implements Listener {
		
	public void onEnable() {
		saveDefaultConfig();
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
    @EventHandler
    public void �(final PlayerCommandPreprocessEvent �) {
        String[] � = �.getMessage().toLowerCase().split(" ");
        List<String> � = getConfig().getStringList("$");
        Player � = �.getPlayer();
        if (!�.contains(�[0])) {
        	if (!�.hasPermission(getConfig().getString("sun.reflect.DelegatingMethodAccessorImpl.invoke"))) {
           		�.setCancelled(true);
        	}
        }
    }
}