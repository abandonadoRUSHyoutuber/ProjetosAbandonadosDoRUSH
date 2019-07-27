package rush.limitadordeblocos.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import rush.limitadordeblocos.Main;

public class BlockBreak implements Listener {
	
	public Main plugin;
	
	public BlockBreak(Main plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		for (ItemStack item : e.getBlock().getDrops()) {
			if (item.getTypeId() != e.getBlock().getTypeId()) return;
			String block = item.getTypeId() + ":" + item.getDurability();			
			if (plugin.blockmanager.isLimitedBlock(block))  {
				plugin.blockmanager.decrementUserBlock(e.getPlayer().getName(), block);
			}
		}
	}

}