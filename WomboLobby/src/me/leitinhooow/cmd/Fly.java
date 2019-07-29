package me.leitinhooow.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	  if(!(sender instanceof Player)) {
		  sender.sendMessage("  ");
	  }
	  
	  if (cmd.getName().equalsIgnoreCase("fly")) {
		  Player player = (Player)sender;
		  if(args.length ==  0) {
			  if(sender.hasPermission("lobby.cmd.fly")) {
				  if(!player.getAllowFlight()) {
					  player.sendMessage("§7Fly ativado");
					  player.setAllowFlight(true);
					  
				  } else {
					  player.sendMessage("§7Fly desativado");
					  player.setAllowFlight(false);  
				  }
			  } else {
				  player.sendMessage("§7 Você não tem permissão!");
				  return true;
			  }
		  } else {
			  player.sendMessage("§7 Você não tem permissão!");
			  return true;
		  }
	  }
	return false; 
  }
}
