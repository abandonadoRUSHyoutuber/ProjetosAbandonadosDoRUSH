package me.leitinhooow.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.md_5.bungee.api.ChatColor;

public class ChatF implements Listener{
	
	public void Tags(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		e.setFormat(ChatColor.GRAY + p.getDisplayName() + "§a§l: §7" + e.getMessage());
	}

}
