package me.leitinho.cmd;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

@SuppressWarnings("deprecation")
public class Chat_Lindu
  implements Listener
{
  @EventHandler(priority=EventPriority.NORMAL)
  public void onColorandChangeChat(PlayerChatEvent e)
  {
    Player p = e.getPlayer();
    if (!p.hasPermission("chat.color")) {
      e.setFormat(ChatColor.GRAY + p.getDisplayName() + " §7§l» §r§f " + e.getMessage().replaceAll("&", "§"));
    } else {
      e.setFormat("§7" + p.getDisplayName() + " §f§l»§r §f" + e.getMessage().replaceAll("&", "§"));
    }
  }
}
