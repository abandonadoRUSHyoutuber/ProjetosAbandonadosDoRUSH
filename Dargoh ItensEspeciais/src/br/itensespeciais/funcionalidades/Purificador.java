package br.itensespeciais.funcionalidades;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.potion.PotionEffect;

import br.itensespeciais.main.Main;

public class Purificador implements Listener{

	@EventHandler
	public void onThrowPurificador(ProjectileLaunchEvent e) {
		if(e.getEntityType() == EntityType.SPLASH_POTION) {
			if(e.getEntity().getShooter() instanceof Player) {
				Player p = (Player)e.getEntity().getShooter();
				if(p.getItemInHand().isSimilar(Main.purificador)){
					e.getEntity().setMetadata("purificador", (MetadataValue)new FixedMetadataValue(Main.get(), true));
				}
			}
		}
	}
	
	@EventHandler
	public void funcionar(PotionSplashEvent e) {
		Entity a = (Entity)e.getEntity();
		if(a.hasMetadata("purificador")) {
			e.setCancelled(true);
			for(LivingEntity en : e.getAffectedEntities()) {
				if(en.getType() == EntityType.PLAYER) {
					for(PotionEffect pot : en.getActivePotionEffects()) {
						en.removePotionEffect(pot.getType());
					}
					en.sendMessage("§cVocê foi atingido por um purificador e seus efeitos de poções foram removidos.");
				}
			}
		}
	}
	
}
