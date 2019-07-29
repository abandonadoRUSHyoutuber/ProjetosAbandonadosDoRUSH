package me.leitinhooow.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.leitinhooow.central.Main;



public class TpGeral implements CommandExecutor{
	
    @SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tpall") && sender.hasPermission("lobby.cmd.tpall")) {
            Player s = (Player)sender;
            Player[] arrayOfPlayer = Bukkit.getServer().getOnlinePlayers();
            int j = arrayOfPlayer.length;
            int i = 0;
            while (i < j) {
                Player player = arrayOfPlayer[i];
                player.teleport(s.getLocation());
                ++i;
            }
            Bukkit.getServer().broadcastMessage(Main.prefix + "§7Um §5MODERADOR§7 puxou todos a sua localização!");
            return true;
        }
        return false;
    }

}
