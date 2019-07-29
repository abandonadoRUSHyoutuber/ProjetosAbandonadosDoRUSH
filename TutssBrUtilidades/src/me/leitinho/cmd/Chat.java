package me.leitinho.cmd;

import org.bukkit.event.*;
import org.bukkit.event.player.*;

import org.bukkit.command.*;
import org.bukkit.entity.*;

import java.util.HashMap;

import org.bukkit.*;

public class Chat implements Listener, CommandExecutor
{
    public static boolean chat1;
    public static HashMap<Player, Boolean> Avisos = new HashMap<>();
    
    static {
        Chat.chat1 = true;
    }
    
    @EventHandler
    public void onPlayerChat(final AsyncPlayerChatEvent e) {
        if (!e.getPlayer().hasPermission("api.falarchat") && !Chat.chat1) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("&4&lERRO &7 chat desabilitado.");
        }
    }
    
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onPlayerChat1(final PlayerChatEvent e) {
        if (!e.getPlayer().hasPermission("api.falarchat") && !Chat.chat1) {
            e.setCancelled(true);
        }
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        if (sender.hasPermission("api.chat")) {
            if (args.length > 0) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("off")) {
                        Chat.chat1 = false;
                        Bukkit.broadcastMessage("&4&l &7 chat desabilitado.");
                    }
                    else if (args[0].equalsIgnoreCase("on")) {
                        Chat.chat1 = true;
                        Bukkit.broadcastMessage("&4&l &7 chat habilitado.");
                    }
                    if(args.length == 1) {
                    	if(args[0].equalsIgnoreCase("clear")) {
                            for (int i = 0; i < 100; ++i) {
                                Bukkit.broadcastMessage(" ");
                            }
                            Bukkit.broadcastMessage("§e§lO CHAT FOI LIMPO");
                    	}
                    }
                }
                else {
                    sender.sendMessage("ERRO: USE CHAT !ON!OFF!CLEAR!");
                }
            }
            else {
                sender.sendMessage("ERRO: USE CHAT !ON!OFF!CLEAR!");
            }
        }

        return true;
    }
}
