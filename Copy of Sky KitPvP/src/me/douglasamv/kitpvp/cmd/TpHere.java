
package me.douglasamv.kitpvp.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.douglasamv.kitpvp.Mensagens;

public class TpHere implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("kitpvp.cmd.tp")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("§cUse /s <nick>");
			return true;
		}
		
		if (args.length == 1) {
			Player t = Bukkit.getPlayer(args[0]);
			if (t == null) {
				p.sendMessage(Mensagens.offlinePlayer);
				return true;
			}
			t.teleport(p);
			p.sendMessage("§cTeleported!");
		}
		return false;
	}
}
