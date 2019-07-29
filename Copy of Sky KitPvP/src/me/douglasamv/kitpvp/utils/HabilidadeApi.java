package me.douglasamv.kitpvp.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class HabilidadeApi implements Listener {
	
	public static HashMap<Player, String> hb = new HashMap<>();
	
	@EventHandler
	void quit(PlayerQuitEvent e) {
		remover(e.getPlayer());
	}
	
	@EventHandler
	void kick(PlayerQuitEvent e) {
		remover(e.getPlayer());
	}
	
	public static String verHB(Player p) {
		if(hb.containsKey(p)) {
			return hb.get(p);
		}
		else {
			return "nenhum";
		}
	}
	
	public static boolean semHB(Player p) {
		if(verHB(p).equalsIgnoreCase("nenhum")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void remover(Player p) {
		if(hb.containsKey(p)) {
			hb.remove(p);
		}
	}
	
	public static void setarHB(Player p, String hb) {
		remover(p);
		HabilidadeApi.hb.put(p, hb);
	}

}
