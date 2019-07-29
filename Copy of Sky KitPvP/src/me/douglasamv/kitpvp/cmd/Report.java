package me.douglasamv.kitpvp.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;

public class Report implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!(args.length >= 2)) {
			p.sendMessage("§cUse /report <nick> <reason>");
			return true;
		}
		Player target = p.getServer().getPlayer(args[0]);
		if (target == null) {
			p.sendMessage(Mensagens.offlinePlayer);
			return true;
		}
		StringBuilder msg = new StringBuilder();
		for (int i = 1; i < args.length; i++) {
			msg.append(args[i]).append(" ");
		}
		p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("ReportSucess")));
		for(Player p2:Bukkit.getOnlinePlayers()) {
			if(p2.hasPermission("kitpvp.report")) {
				if(Main.pl.getConfig().getBoolean("ReportSound")) {
					p2.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 10, 30);
				}
				p2.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("ReportFormat").replace("$line$", "\n")
						.replace("$who$", p.getName()).replace("$reported$", target.getName()).replace("$reason$", msg)));
			}
		}
		return false;
	}

}
