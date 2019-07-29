package me.douglasamv.kitpvp.utils;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Nerf implements Listener {
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void NerfSwords(EntityDamageByEntityEvent event) {
		if ((event.getDamager() instanceof Player)){
			Player player = (Player)event.getDamager();
			if (event.getDamage() > 1.0D) {
				event.setDamage(event.getDamage() - 1.0D);
			}
			if ((event.getDamager() instanceof Player)) {
				if ((player.getFallDistance() > 0.0F) && (!((CraftPlayer)player).isOnGround()) && (!player.hasPotionEffect(PotionEffectType.BLINDNESS))){
					int NewDamage = (int)(event.getDamage() * 1.5D) - (int)event.getDamage();
					if (event.getDamage() > 1.0D) {
						event.setDamage(event.getDamage() - NewDamage);
					}
				}
				if (player.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
					for (PotionEffect Effect : player.getActivePotionEffects()) {
						if (Effect.getType().equals(PotionEffectType.INCREASE_DAMAGE)) {
							double Division = (Effect.getAmplifier() + 2) * 1.3D + 1.0D;
							int NewDamage;
							if (event.getDamage() / Division <= 1.0D) {
								NewDamage = (Effect.getAmplifier() + 2) * 3 + 3;
							} else {
								NewDamage = (int)(event.getDamage() / Division);
							}
							event.setDamage(NewDamage);
							break;
						}
					}
				}
				if (player.getItemInHand().getType() == Material.AIR) {
					event.setDamage(0.5D);
				}
				if (player.getItemInHand().getType() == Material.WOOD_SWORD) {
					event.setDamage(2.0D);
				}
				if (player.getItemInHand().getType() == Material.STONE_SWORD) {
					event.setDamage(3.0D);
				}
				if (player.getItemInHand().getType() == Material.GOLD_SWORD) {
					event.setDamage(4.0D);
				}
				if (player.getItemInHand().getType() == Material.IRON_SWORD) {
					event.setDamage(4.0D);
				}
				if (player.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					event.setDamage(event.getDamage() - 1.5D);
				}
				if (player.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
					for (PotionEffect Effect : player.getActivePotionEffects()) {
						if ((Effect.getType().equals(PotionEffectType.INCREASE_DAMAGE)) && (player.getItemInHand() != null) && (player.getItemInHand().getType().name().contains("SWORD"))) {
							Random r = new Random();
								if (r.nextInt(3) == 0) {
									event.setDamage(event.getDamage() + 2.0D);
									break;
								}
								event.setDamage(event.getDamage() + 1.5D);
							}
						}
					}
					if ((player.getFallDistance() > 0.0F) && (!((CraftPlayer)player).isOnGround()) && (!player.hasPotionEffect(PotionEffectType.BLINDNESS))) {
						if (player.getItemInHand().getType() == Material.AIR) {
							event.setDamage(0.5D);
						}
						if (player.getItemInHand().getType() == Material.WOOD_SWORD) {
							event.setDamage(event.getDamage() + 1.0D);
						}
						if (player.getItemInHand().getType() == Material.STONE_SWORD) {
							event.setDamage(event.getDamage() + 1.0D);
						}
						if (player.getItemInHand().getType() == Material.GOLD_SWORD) {
							event.setDamage(event.getDamage() + 1.5D);
						}
						if (player.getItemInHand().getType() == Material.IRON_SWORD) {
							event.setDamage(event.getDamage() + 1.0D);
						}
						if (player.getItemInHand().getType() == Material.DIAMOND_SWORD) {
							event.setDamage(event.getDamage() + 1.0D);
						}
					}
				}
	    	}
	  }
	  

}
