package me.douglasamv.kitpvp.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;

public class Fly implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("kitpvp.cmd.fly")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		if (args.length == 0) {
			if (p.getAllowFlight()) {
				p.setAllowFlight(false);
				p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("fly.Disabled")));
			} else {
				p.setAllowFlight(true);
				p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("fly.Enabled")));
			}
		} else {
			if (!p.hasPermission("kitpvp.cmd.admin")) {
				p.sendMessage(Mensagens.noPerm);
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				p.sendMessage(Mensagens.offlinePlayer);
				return true;
			}
			if (target.getAllowFlight()) {
				target.setAllowFlight(false);
				target.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("fly.Disabled")));
				// p.sendMessage("§cYou turned off the flight player " +
				// target.getName());
			} else {
				target.setAllowFlight(true);
				target.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("fly.Enabled")));
				// p.sendMessage("§cYou turned on the flight player " +
				// target.getName());
			}
		}
		return false;
	}
}
