package me.ep.extratags.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import me.ep.extratags.ExtraTags;

public class LegendChat implements Listener {		
	
	ExtraTags pl;
	
	public LegendChat(ExtraTags pl) {
		this.pl = pl;
		Bukkit.getPluginManager().registerEvents(this, pl);
	}
	
	
	@EventHandler
	public void chat(ChatMessageEvent e){
		
		Player p = e.getSender();
		
		for(String s : pl.tags.keySet()){
			String perm = pl.getConfig().getString("Tags." + s + ".permissao");
			String valor = pl.getConfig().getString("Tags." + s + ".valor");
			if (e.getTags().contains(s) && p.hasPermission(perm)){
				e.setTagValue(s, ChatColor.translateAlternateColorCodes('&', valor));
			}
		}							
	}

}
