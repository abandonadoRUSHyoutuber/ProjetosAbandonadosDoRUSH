package me.douglasamv.kitpvp.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Lag implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		double tps = me.douglasamv.kitpvp.utils.Lag.getTPS();
		double lag = Math.round((1.0D - tps / 20.0D) * 100.0D);
		sender.sendMessage("§cServer running at " + tps + " tps");
		sender.sendMessage("§cLag approximate " + lag + "%");
		return false;
	}
}
