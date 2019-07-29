package br.itensespeciais.funcionalidades;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import br.itensespeciais.itemstack.ItemManager;
public class RaioMestre implements Listener {

	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(p.getItemInHand().isSimilar(ItemManager.getRaioMestre())) {
				ItemManager.removerItemMao(p);
				e.setCancelled(true);
				
				e.getClickedBlock().getWorld().strikeLightning(e.getClickedBlock().getLocation());
				p.sendMessage("§aVocê usou um raio mestre.");
				return;
			}
		}
	}
	
	@EventHandler
	public void onClickEntity(PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		if(p.getItemInHand().isSimilar(ItemManager.getRaioMestre())) {
			ItemManager.removerItemMao(p);
			e.setCancelled(true);
			
			e.getRightClicked().getWorld().strikeLightning(e.getRightClicked().getLocation());
			p.sendMessage("§aVocê usou um raio mestre.");
			return;
		} return;
	}
}
