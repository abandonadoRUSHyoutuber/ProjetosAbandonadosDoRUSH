package br.itensespeciais.funcionalidades;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;

import br.itensespeciais.itemstack.ItemManager;

public class Lancador implements Listener{

	public List<Player> lancadordamage = new ArrayList<Player>();
	
		@EventHandler
		public void onClick(PlayerInteractEvent e) {
			Player p = e.getPlayer();
			if(e.getAction() == Action.RIGHT_CLICK_AIR) {
				if(p.getItemInHand().isSimilar(ItemManager.getLancador())) {
					ItemManager.removerItemMao(p);
					e.setCancelled(true);
					
					org.bukkit.util.Vector vel = p.getVelocity();
					vel.setY(4.0);
					p.setVelocity(vel);
					lancadordamage.add(p);
				}
			} else if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(p.getItemInHand().isSimilar(ItemManager.getLancador())) {
					ItemManager.removerItemMao(p);
					e.setCancelled(true);
					
					org.bukkit.util.Vector vel = p.getVelocity();
					vel.setY(4.0);
					p.setVelocity(vel);
					lancadordamage.add(p);
				}
			}
		}
			
		@EventHandler
		public void danoQueda(EntityDamageEvent e) {
			if(e.getEntity() instanceof Player) {
				if(e.getCause() == DamageCause.FALL) {
					if(lancadordamage.contains(e.getEntity())) {
						e.setCancelled(true);
						lancadordamage.remove(e.getEntity());
					}
				}
			}	
		}
	}
