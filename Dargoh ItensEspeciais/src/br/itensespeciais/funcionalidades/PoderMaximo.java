package br.itensespeciais.funcionalidades;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import com.massivecraft.factions.entity.MPlayer;

import br.itensespeciais.itemstack.ItemManager;
import br.itensespeciais.main.Main;

public class PoderMaximo implements Listener{

	@EventHandler
	public void usarPoder(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_AIR) {
			Player p = e.getPlayer();
			if(p.getItemInHand().isSimilar(Main.podermaximo)) {
				e.setCancelled(true);
				MPlayer mp = MPlayer.get(p);
				
				int maximo = mp.getPowerMaxRounded();
				if(maximo >= 20) {
					p.sendMessage("§cVocê já está com seu poder no máximo (Máx. 20)");
					return;
				}
				
				ItemManager.removerItemMao(p);
				
				mp.setPowerBoost(mp.getPowerBoost() + 1.0);
				Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
				FireworkMeta fm = fw.getFireworkMeta();
				fm.setPower(5);
				p.sendMessage("§aPoder máximo aumentado em 1. Total: " + mp.getPowerMax());
			}
		}
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			if(p.getItemInHand().isSimilar(Main.podermaximo)) {
				e.setCancelled(true);
				MPlayer mp = MPlayer.get(p);
				
				int maximo = mp.getPowerMaxRounded();
				if(maximo >= 20) {
					p.sendMessage("§cVocê já está com seu poder no máximo (Máx. 20)");
					return;
				}
				
				ItemManager.removerItemMao(p);
				
				mp.setPowerBoost(mp.getPowerBoost() + 1.0);
				Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
				FireworkMeta fm = fw.getFireworkMeta();
				fm.setPower(5);
				p.sendMessage("§aPoder máximo aumentado em 1. Total: " + mp.getPowerMax());
			}
		}
	}
}
