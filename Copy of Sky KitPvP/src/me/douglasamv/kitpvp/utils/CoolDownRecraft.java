package me.douglasamv.kitpvp.utils;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class CoolDownRecraft implements Listener {
	
	 private static HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();
	 
	 @EventHandler
	 public void quit(PlayerQuitEvent e) {
		 removeCooldown(e.getPlayer());
	 }
	 
	 @EventHandler
	 public void kick(PlayerKickEvent e) {
		 removeCooldown(e.getPlayer());
	 }
	 
	 @EventHandler
	 public void respawn(PlayerRespawnEvent e) {
		 removeCooldown(e.getPlayer());
	 }
	 
	 public static void putCooldown(Player p, int Seconds) {
		 cooldown.put(p.getUniqueId(), System.currentTimeMillis() + (Seconds * 1000L));
	 }
	 
	 
	 public static int getCooldown(Player p) {
		 if (cooldown.containsKey(p.getUniqueId())) {
			 return Long.valueOf(((System.currentTimeMillis() - cooldown.get(p.getUniqueId())) / 1000)).intValue();
		 }
		 else {
			 return 0;
		 }
	 }
	 
	 public static boolean isOnCooldown(Player p) {
		 if (cooldown.containsKey(p.getUniqueId()) && cooldown.get(p.getUniqueId()) > System.currentTimeMillis()) {
			 return true;
		 }
		 else {
			 return false;
		 }
	 }
	 
	 public static void removeCooldown(Player p) {
		 if (cooldown.containsKey(p.getUniqueId())) {
			 cooldown.remove(p.getUniqueId());
		 }
	 }

}
