package me.douglasamv.kitpvp.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.douglasamv.kitpvp.Mensagens;

public class InvSee implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("kitpvp.cmd.invsee")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		if(args.length == 0) {
			p.sendMessage("§cUse /invesse <nick>");
			return true;
		}
		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
			p.sendMessage(Mensagens.offlinePlayer);
			return true;
		}
		p.openInventory(target.getInventory());
		return false;
	}
}
