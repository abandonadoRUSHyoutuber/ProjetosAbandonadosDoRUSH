package me.leitinhooow.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
@SuppressWarnings("unused")
public class Build
implements Listener, CommandExecutor
{
public static List<String> build = new ArrayList<String>();
public static HashMap<String, ItemStack[]> saveinv = new HashMap<String, ItemStack[]>();
public static HashMap<String, ItemStack[]> armadura = new HashMap<String, ItemStack[]>();

public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
  if ((sender instanceof Player)) {
    Player p = (Player)sender;
    if ((label.equalsIgnoreCase("build")) && 
      (p.hasPermission("kitpvp.cmd.build"))) {
      if (build.contains(p.getName())) {
        build.remove(p.getName());
        p.getInventory().clear();
        p.setGameMode(GameMode.SURVIVAL);
        p.getInventory().clear();
        p.getInventory().setContents((ItemStack[])saveinv.get(p.getName()));
        p.getInventory().setArmorContents((ItemStack[])armadura.get(p.getName()));
        saveinv.remove(p.getName());
        armadura.remove(p.getName());
        p.sendMessage("�cVoce saiu do modo: �7Build");
        p.playSound(p.getLocation(), Sound.ANVIL_USE, 4.0F, 4.0F);
        return true;
      }
      build.add(p.getName());
      saveinv.put(p.getName(), p.getInventory().getContents());
      armadura.put(p.getName(), p.getInventory().getArmorContents());
      p.getInventory().clear();
      p.getInventory().setArmorContents(null);
      p.setGameMode(GameMode.CREATIVE);
      p.sendMessage("�cVoce entrou no modo: �7Build.");
      p.playSound(p.getLocation(), Sound.ANVIL_USE, 4.0F, 4.0F);
    }
  }
  return false;
}

@EventHandler
public void aoColocarBlocos(BlockPlaceEvent e) {
  Player p = e.getPlayer();
  if (p.getGameMode() != GameMode.CREATIVE)
    e.setCancelled(true);
}

@EventHandler
public void aoQuebrarBlocos(BlockBreakEvent e)
{
  Player p = e.getPlayer();
  if (p.getGameMode() != GameMode.CREATIVE)
    e.setCancelled(true);
}
}




