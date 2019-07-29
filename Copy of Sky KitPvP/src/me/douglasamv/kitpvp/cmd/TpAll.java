package me.douglasamv.kitpvp.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;

public class TpAll implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("kitpvp.cmd.tpall")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		for(Player p2:Bukkit.getOnlinePlayers()) {
			p2.teleport(p.getLocation());
			p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("tpall")));
		}
		return false;
	}
}