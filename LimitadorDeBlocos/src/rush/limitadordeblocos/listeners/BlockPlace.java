package rush.limitadordeblocos.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import rush.limitadordeblocos.Main;

public class BlockPlace implements Listener {
	
	private Main plugin;
	
	public BlockPlace(Main plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onPlace(BlockPlaceEvent e) {
		
		Player p = e.getPlayer();
		ItemStack item = p.getItemInHand();
		String block = item.getTypeId() + ":" + item.getDurability();
		
		if (plugin.blockmanager.isLimitedBlock(block)) {
			if (!plugin.blockmanager.canPlaceBlock(p, block)) {
				e.setBuild(false);
				e.setCancelled(true);
			}
		}
	}

}