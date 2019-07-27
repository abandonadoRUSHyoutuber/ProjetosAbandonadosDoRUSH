package rush.antiprotectspawners.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;

import rush.antiprotectspawners.config.Config;

@SuppressWarnings("deprecation")
public class PistonExtend implements Listener {

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onExtend(BlockPistonExtendEvent e) {
		for (Block b : e.getBlocks()) {
			if (Config.IDS.contains(b.getTypeId())) {
				Location l = getCorrectLocation(e.getBlock(), b.getLocation());
				for (double x = l.getX() - Config.DST; x <= l.getX() + Config.DST; x++) {
					for (double y = l.getY() - Config.DST; y <= l.getY() + Config.DST; y++) {
						for (double z = l.getZ() - Config.DST; z <= l.getZ() + Config.DST; z++) {
							Material block = new Location(l.getWorld(), x, y, z).getBlock().getType();
							if (block == Material.MOB_SPAWNER) {
								e.setCancelled(true);
								return;
							}
						}
					}
				}
			}
		}
	}
	
	private Location getCorrectLocation(Block block, Location l) {
		byte b = block.getData();
		switch (b) {
			case 0:
				return l.add(0d, -1d, 0d);
			case 1:
				return l.add(0d, 1d, 0d);
			case 2:
				return l.add(0d, 0d, -1d);
			case 3:
				return l.add(0d, 0d, 1d);
			case 4:
				return l.add(-1d, 0d, 0d);
			case 5:
				return l.add(1d, 0d, 0d);
			default: 
				return l;
		}
	}
	
}
