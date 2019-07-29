package me.leitinhooow.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.leitinhooow.central.Main;


public class ChatOff implements CommandExecutor{
	
	boolean Chatoff = false;
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if (!(sender instanceof Player)) {
			
			sender.sendMessage(Main.prefix + "§7 » Você presisa ser um player para executar este comando!");
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("pchat")) {
			Player jogador = (Player) sender;
			if (jogador.hasPermission("lobby.cmd.pchat")) {
				if (Chatoff == false) {
					Chatoff = true;
					jogador.sendMessage(Main.prefix + "§7 » Chat foi pausado.");
				}
				else if (Chatoff == true) {
					Chatoff = false;
					jogador.sendMessage(Main.prefix  + "§7 » O Chat foi despausado.");
				} else {
					jogador.sendMessage( Main.prefix + " §7» Você não tem a permissão.");
				}
			}
		}
		return false;
		
	}

}
