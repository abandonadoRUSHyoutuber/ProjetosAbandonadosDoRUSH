package br.itensespeciais.funcionalidades;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scheduler.BukkitRunnable;

import br.itensespeciais.itemstack.ItemManager;
import br.itensespeciais.main.Main;

public class Capsula implements Listener {

	public List<Player> capsuladamage = new ArrayList<Player>();
	
	@EventHandler
	public void onThrowPearl(ProjectileLaunchEvent e) {
		if(e.getEntityType() == EntityType.ENDER_PEARL) {
			if(e.getEntity().getShooter() instanceof Player) {
				Player p = (Player)e.getEntity().getShooter();
				if(p.getItemInHand().isSimilar(ItemManager.getCapsula())) {
					EnderPearl ep = (EnderPearl) e.getEntity();
					new BukkitRunnable() {
						int count = 0;
						@Override
						public void run() {
							count++;
							if(count == 1) {
								ep.setPassenger((Player)e.getEntity().getShooter());
								if(!capsuladamage.contains(p)) {
									capsuladamage.add(p);
								}
							} 
							
							if(count == 16) {
								p.teleport(p.getLocation());
								this.cancel();
							}
						}
					}.runTaskTimerAsynchronously(Main.get(), 0L, 5L);
				}
			}
		}
	}
	
	@EventHandler
	public void capsulaDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			if(e.getCause() == DamageCause.FALL) {
				Player p = (Player)e.getEntity();
				if(capsuladamage.contains(p)) {
					e.setCancelled(true);
				}
			}
		}
	}
}
