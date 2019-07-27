package rush.multiferramenta.listener;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import rush.multiferramenta.config.Config;

public class BlockDamage implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onDamage(BlockDamageEvent e) {
		if (is(e.getItemInHand())) {
			e.setCancelled(true);
			Block b = e.getBlock();
			Player p = e.getPlayer();
			BlockBreakEvent event = new BlockBreakEvent(b, p);
			Bukkit.getPluginManager().callEvent(event);
			if (!event.isCancelled()) {
				changeLore(p);
				if (Config.DROPAR) {
					b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 1);
					b.breakNaturally();
				} else {
					b.setType(Material.AIR);
				}
			}
		}
	}
	
	private boolean is(ItemStack item) {
		return    item != null 
			   && item.getType() == Material.SHEARS 
		       && item.hasItemMeta() 
			   && item.getItemMeta().hasLore()
			   && item.getItemMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS);
	}
	
	private void changeLore(Player p) {
		ItemStack hand = p.getItemInHand();
		ItemMeta meta = hand.getItemMeta();
		List<String> lore = meta.getLore();
		Integer durability = Integer.parseInt(lore.get(3).replace("§fDurabilidade: ", "")) - 1;
		if (durability < 1) {
			p.setItemInHand(new ItemStack(Material.AIR));
		} else  {
			lore.set(3, "§fDurabilidade: " + durability);
			meta.setLore(lore);
			hand.setItemMeta(meta);
		}
	}
	
}
