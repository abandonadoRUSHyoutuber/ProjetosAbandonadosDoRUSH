package me.douglasamv.kitpvp.cmd;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;

public class Gm implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("kitpvp.cmd.gm")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		if (args.length == 0) {
			if (p.getGameMode().equals(GameMode.CREATIVE)) {
				p.setGameMode(GameMode.SURVIVAL);
				p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("survival")));
				return true;
			} else {
				p.setGameMode(GameMode.CREATIVE);
				p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("creative")));
				return true;
			}
		}
		else {
			if(args[0].equalsIgnoreCase("0")) {
				p.setGameMode(GameMode.SURVIVAL);
				p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("survival")));
				return true;
			}
			else if(args[0].equalsIgnoreCase("1")) {
				p.setGameMode(GameMode.CREATIVE);
				p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("creative")));
				return true;
			}
			else {
				p.sendMessage("§cUse /gm <0/1>");
			}
		}
		return false;
	}

}
