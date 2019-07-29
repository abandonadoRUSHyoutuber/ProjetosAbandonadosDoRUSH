package me.leitinho.cmd;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class aTAG
  implements Listener, CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("tag"))
    {
      Player p = (Player)sender;
      if (args.length == 0)
      {
        p.sendMessage("§7Tags NORMAL VIP MVP PRO YT YT+ TRIAL MOD ADMIN DONO ");

        return false;
      }
      if (args[0].equalsIgnoreCase("normal"))
      {
        if (p.hasPermission("tag.normal"))
        {
          p.sendMessage(ChatColor.AQUA + "Tag Alterada Para: §e Normal");
          p.setDisplayName("§7 " + p.getName() + ChatColor.WHITE);
          p.setPlayerListName("§7" + p.getName() + ChatColor.WHITE);
        }
        else
        {
        	if (!p.hasPermission("tag.normal"))
          p.sendMessage(ChatColor.RED + "Voce nao tem essa Tag");
        }
      }
      else if (args[0].equalsIgnoreCase("Vip"))
      {
        if (p.hasPermission("tag.Vip"))
        {
          p.sendMessage(ChatColor.AQUA + "Tag Alterada Para: §e Vip");
          p.setDisplayName("§a§lVIP§a " + p.getName() + ChatColor.WHITE);
          p.setPlayerListName("§a" + getShortStr(p.getName()) + ChatColor.WHITE);
        }
        else
        {
        	if (!p.hasPermission("tag.vip"))
          p.sendMessage(ChatColor.RED + "Voce nao tem essa Tag");
        }
      }
      else if (args[0].equalsIgnoreCase("Mvp"))
      {
        if (p.hasPermission("tag.mvp"))
        {
          p.sendMessage(ChatColor.AQUA + "Tag Alterada Para: §9 MVP");
          p.setDisplayName("§9§lMVP§a " + p.getName() + ChatColor.WHITE);
          p.setPlayerListName("§e" + getShortStr(p.getName()) + ChatColor.WHITE);
        }
        else
        {
        	if (!p.hasPermission("tag.mvp"))
          p.sendMessage(ChatColor.RED + "Voce nao tem essa Tag");
        }
      }
      else if (args[0].equalsIgnoreCase("pro"))
      {
        if (p.hasPermission("tag.pro"))
        {
          p.sendMessage(ChatColor.AQUA + "Tag Alterada Para: §e Pro");
          p.setDisplayName("§6§lPRO§6 " + p.getName() + ChatColor.WHITE);
          p.setPlayerListName("§6" + getShortStr(p.getName()) + ChatColor.WHITE);
        }
        else
        {
        	if (!p.hasPermission("tag.pro"))
          p.sendMessage(ChatColor.RED + "Voce nao tem essa Tag");
        }
      }
        
        p.sendMessage(ChatColor.RED + "vc n tem essa tag");
      }
      else if (args[0].equalsIgnoreCase("youtuber"))
      {
    	  Player p = (Player)sender;
        if (p.hasPermission("tag.youtuber"))
        {
          p.sendMessage(ChatColor.AQUA + "Tag Alterada Para: §e Youtuber");
          p.setDisplayName("§b§lYT §b " + p.getName() + ChatColor.WHITE);
          p.setPlayerListName("§b" + getShortStr(p.getName()) + ChatColor.WHITE);
        }
        else
        {
        	if (!p.hasPermission("tag.youtuber"))
          p.sendMessage(ChatColor.RED + "Voce nao tem essa Tag");
        }
      }
      else if (args[0].equalsIgnoreCase("youtuber+"))
      {
    	  Player p = (Player)sender;
        if (p.hasPermission("tag.youtuber+"))
        {
          p.sendMessage(ChatColor.AQUA + "Tag Alterada Para: §e Youtuber+");
          p.setDisplayName("§3§lYT+ §3 " + p.getName() + ChatColor.WHITE);
          p.setPlayerListName("§3" + getShortStr(p.getName()) + ChatColor.WHITE);
        }
        else
        {
        	if (!p.hasPermission("tag.youtuber+"))
          p.sendMessage(ChatColor.RED + "Voce nao tem essa Tag");
        }
      }
      else if (args[0].equalsIgnoreCase("trial"))
      {
    	  Player p = (Player)sender;
        if (p.hasPermission("tag.trial"))
        {
          p.sendMessage(ChatColor.AQUA + "Tag Alterada Para: §e TrialMod");
          p.setDisplayName("§d§lTRIAL§d " + p.getName() + ChatColor.WHITE);
          p.setPlayerListName("§d" + getShortStr(p.getName()) + ChatColor.WHITE);
        }
        else
        {
        	if (!p.hasPermission("tag.trial"))
          p.sendMessage(ChatColor.RED + "Voce nao tem essa Tag");
        }
      }
      else if (args[0].equalsIgnoreCase("mod"))
      {
    	  Player p = (Player)sender;
        if (p.hasPermission("tag.mod"))
        {
          p.sendMessage(ChatColor.AQUA + "Tag Alterada Para: §e Mod");
          p.setDisplayName("§5§lMOD§5 " + p.getName() + ChatColor.WHITE);
          p.setPlayerListName("§5" + getShortStr(p.getName()) + ChatColor.WHITE);
        }
        else
        {
        	if (!p.hasPermission("tag.mod"))
          p.sendMessage(ChatColor.RED + "Voce nao tem essa Tag");
        }
      }
      else if (args[0].equalsIgnoreCase("admin"))
      {
    	  Player p = (Player)sender;
        if (p.hasPermission("tag.admin"))
        {
          p.sendMessage(ChatColor.AQUA + "Tag Alterada Para: §e Admin");
          p.setDisplayName("§c§lADMIN§c " + p.getName() + ChatColor.WHITE);
          p.setPlayerListName("§c" + getShortStr(p.getName()) + ChatColor.WHITE);
        }
        else
        {
        	if (!p.hasPermission("tag.admin"))
          p.sendMessage(ChatColor.RED + "Voce nao tem essa Tag");
        }
      }
      if (args[0].equalsIgnoreCase("dono"))
      {
    	  Player p = (Player)sender;
        if (p.hasPermission("tag.dono"))
        {
          p.sendMessage(ChatColor.GRAY + "Utilizando Tag : " + ChatColor.DARK_RED + "Dono");
          p.setDisplayName("§4§lDONO§4 " + p.getName() + ChatColor.WHITE);
          p.setPlayerListName("§4" + getShortStr(p.getName()) + ChatColor.WHITE);
          return true;
        }
    	if (!p.hasPermission("tag.dono"))
        p.sendMessage(ChatColor.RED + "Voce nao tem permissao !");
      }
    
    return false;
  }
  
  public static String getShortStr(String s)
  {
    if (s.length() == 16)
    {
      String shorts = s.substring(0, s.length() - 6);
      return shorts;
    }
    if (s.length() == 15)
    {
      String shorts = s.substring(0, s.length() - 5);
      return shorts;
    }
    if (s.length() == 14)
    {
      String shorts = s.substring(0, s.length() - 4);
      return shorts;
    }
    if (s.length() == 13)
    {
      String shorts = s.substring(0, s.length() - 4);
      return shorts;
    }
    if (s.length() == 12)
    {
      String shorts = s.substring(0, s.length() - 2);
      return shorts;
    }
    if (s.length() == 11)
    {
      String shorts = s.substring(0, s.length() - 1);
      return shorts;
    }
    return s;
  }
  
  @SuppressWarnings("unused")
@EventHandler
  public void click(PlayerInteractEvent e)
  {
    Player player = e.getPlayer();
    Action a = e.getAction();
    if (a.name().contains("RIGHT")) {
      if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
        (e.getPlayer().getItemInHand().getType() == Material.DIAMOND)) {
        e.getPlayer().chat("/tags");
      }
    }
  }
}
