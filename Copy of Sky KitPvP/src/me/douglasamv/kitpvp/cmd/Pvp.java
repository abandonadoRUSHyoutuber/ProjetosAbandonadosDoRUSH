package me.douglasamv.kitpvp.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.douglasamv.kitpvp.Mensagens;

public class Pvp implements CommandExecutor, Listener {
	
	@EventHandler
	void dano(EntityDamageByEntityEvent e) {
		if (pvp) {
			if (e.getEntity() instanceof Player) {
				e.setCancelled(true);
			}
		}
	}

	public static boolean pvp = false;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("kitpvp.cmd.pvp")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("§cUse /pvp <on/off>");
			return true;
		}
		if (args[0].equalsIgnoreCase("off")) {
			p.sendMessage("§c§lPvP Off!");
			pvp = true;
			return true;
		} else if (args[0].equalsIgnoreCase("on")) {
			p.sendMessage("§c§lPvP On!");
			pvp = false;
			return true;
		} else {
			p.sendMessage("§cUse /pvp <on/off>");
		}
		return false;
	}
}
