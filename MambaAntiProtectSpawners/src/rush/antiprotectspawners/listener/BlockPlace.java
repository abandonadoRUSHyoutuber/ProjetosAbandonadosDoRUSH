package rush.antiprotectspawners.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import rush.antiprotectspawners.config.Config;

@SuppressWarnings("deprecation")
public class BlockPlace implements Listener {

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onPlace(BlockPlaceEvent e) {
		Block b = e.getBlock();
		if (b.getType() == Material.MOB_SPAWNER) {
			Location l = b.getLocation();
			for (double x = l.getX() - Config.DST; x <= l.getX() + Config.DST; x++) {
				for (double y = l.getY() - Config.DST; y <= l.getY() + Config.DST; y++) {
					for (double z = l.getZ() - Config.DST; z <= l.getZ() + Config.DST; z++) {
						int block = new Location(l.getWorld(), x, y, z).getBlock().getTypeId();
						if (Config.IDS.contains(block)) {
							e.getPlayer().sendMessage(Config.MSG2);
							e.setCancelled(true);
							return;
						}
					}
				}
			}
		}
		
		if (Config.IDS.contains(b.getTypeId())) {
			Location l = b.getLocation();
			for (double x = l.getX() - Config.DST; x <= l.getX() + Config.DST; x++) {
				for (double y = l.getY() - Config.DST; y <= l.getY() + Config.DST; y++) {
					for (double z = l.getZ() - Config.DST; z <= l.getZ() + Config.DST; z++) {
						Material block = new Location(l.getWorld(), x, y, z).getBlock().getType();
						if (block == Material.MOB_SPAWNER) {
							e.getPlayer().sendMessage(Config.MSG1);
							e.setCancelled(true);
							return;
						}
					}
				}
			}
		}
	}
	
}