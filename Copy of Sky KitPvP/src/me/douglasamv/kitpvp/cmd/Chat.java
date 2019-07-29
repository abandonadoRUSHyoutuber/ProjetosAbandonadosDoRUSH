package me.douglasamv.kitpvp.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;

public class Chat implements CommandExecutor {

	public static boolean chat = true;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("kitpvp.cmd.chat")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("§cUse /chat <on/off>");
			return true;
		}
		if (args[0].equalsIgnoreCase("on")) {
			Bukkit.broadcastMessage(Main.pl.getConfig().getString("ChatOn").replace("&", "§"));
			chat = true;
			return true;
		} else if (args[0].equalsIgnoreCase("off")) {
			Bukkit.broadcastMessage(Main.pl.getConfig().getString("ChatOff").replace("&", "§"));
			chat = false;
			return true;
		} else {
			p.sendMessage("§cUse /chat <on/off>");
		}
		return false;
	}
}
