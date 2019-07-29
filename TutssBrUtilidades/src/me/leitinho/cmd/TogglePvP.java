package me.leitinho.cmd;

import org.bukkit.command.*;
import org.bukkit.entity.*;

import org.bukkit.*;

public class TogglePvP implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("pvp")) {
            if (p.hasPermission("cmd.pvp")) {
                if (args.length == 0) {
                    p.sendMessage("Use: pvp on ou off");
                    return true;
                }
                if (args[0].equalsIgnoreCase("on")) {
                    Bukkit.getWorld("world").setPVP(true);
                    Bukkit.broadcastMessage("§a§lPVP FOI ATIVADO");
                }
                if (args[0].equalsIgnoreCase("off")) {
                    Bukkit.getWorld("world").setPVP(false);
                    Bukkit.broadcastMessage("§a§lPVP FOI DESATIVADO");
                }
            }
            else {
                p.sendMessage("Sem permissão!");
            }
        }
        return false;
    }
}
