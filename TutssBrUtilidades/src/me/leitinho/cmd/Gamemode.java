package me.leitinho.cmd;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.leitinho.Main;

public class Gamemode implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Main.prefix + " §cVoce nao é um Player!");
			return true;
		}
		if (label.equalsIgnoreCase("gm")) {
			if (args.length == 0) {
				Player p = (Player) sender;
				if (!p.hasPermission("cmd.gm")) {
					p.sendMessage(Main.prefix + " §cVoce Nao Pode Fazer Isso");
					return true;
				}
				p.sendMessage(Main.prefix + " §7Use o  comando /gm <1> ou <0>");
				return true;
			}
			if (args[0].equalsIgnoreCase("1")) {
				Player p = (Player) sender;
				p.sendMessage(Main.prefix + " §aVoce Alterou Seu Gamemode Para Creative");
				p.setGameMode(GameMode.CREATIVE);
			} else {
				if (args[0].equalsIgnoreCase("0")) {
					Player p = (Player) sender;
					p.sendMessage(Main.prefix + " §aVoce Alterou Seu Gamemode Para Sulvival");
					p.setGameMode(GameMode.SURVIVAL);
				}
			}
		}
		return false;
	}

}
