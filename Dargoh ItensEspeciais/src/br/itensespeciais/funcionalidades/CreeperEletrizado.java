package br.itensespeciais.funcionalidades;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import br.itensespeciais.itemstack.ItemManager;

public class CreeperEletrizado implements Listener{

	@EventHandler
	public void spawnEletricCreeper(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			if(p.getItemInHand().isSimilar(ItemManager.getCreeperEletrico())) {
				e.setCancelled(true);
				ItemManager.removerItemMao(p);
				Block b = e.getClickedBlock();
				Location loc = b.getLocation();
				loc.setY(b.getLocation().getY() + 1);
				
				Creeper cc = (Creeper)loc.getWorld().spawnEntity(loc, EntityType.CREEPER);
				cc.setPowered(true);
			}
		}
	}
}
