package rush.contkill.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import rush.contkill.Main;

public class PlayerDeath implements Listener {
	
	private static final List<String> Worlds = Main.get().getConfig().getStringList("Mundos-Habilitados");
	private static final List<Integer> Ids = Main.get().getConfig().getIntegerList("Ids-Habilitados");
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
		if (p.getKiller() instanceof Player) {
			Player k = (Player) p.getKiller();
			String mundo = p.getLocation().getWorld().getName();
			if (Worlds.contains(mundo)) {
				Integer id = k.getItemInHand().getTypeId();
				if (Ids.contains(id)) 
					changeLore(k);
			}
		}
	}
	
	private void changeLore(Player p) {
		ItemStack item = p.getItemInHand();
		ItemMeta meta = item.getItemMeta(); 
		int a = getIndexLore(item);
		List<String> lore;
		if (a == -1) {
			lore = new ArrayList<>();
			lore.add("񗉲");
			lore.add("Kills: 1");
		} else if (a == -2) {
			lore = meta.getLore();
			lore.add("񗉲");
			lore.add("Kills: 1");
		} else {
			lore = meta.getLore();
			int kills = Integer.valueOf(lore.get(a).split("Kills: ")[1].trim()) + 1;
			lore.set(a, ("Kills: " + kills));
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
	}
	
	private int getIndexLore(ItemStack item) {
		if (item == null || !item.hasItemMeta()) return -1;
		ItemMeta meta = item.getItemMeta();
		if (!meta.hasLore()) return -1;
		for (int i = 0; i < meta.getLore().size(); i++) 
			if (meta.getLore().get(i).startsWith("Kills: ")) return i;
		return -2;
	}
	
}
