package me.douglasamv.kitpvp.cmd;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;

public class StaffChat implements CommandExecutor, Listener {

	public static ArrayList<Player> player = new ArrayList<>();

	@EventHandler(priority = EventPriority.HIGHEST)
	void chat(AsyncPlayerChatEvent e) {
		if (player.contains(e.getPlayer())) {
			e.setCancelled(true);
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.hasPermission("kitpvp.cmd.staffchat")) {
					p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("SFFormat")
							.replace("$player$", e.getPlayer().getName()).replace("$msg$", e.getMessage())));
				}
			}
		}
	}

	@EventHandler
	void sair(PlayerQuitEvent e) {
		if (player.contains(e.getPlayer())) {
			player.remove(e.getPlayer());
		}
	}

	@EventHandler
	void kick(PlayerKickEvent e) {
		if (player.contains(e.getPlayer())) {
			player.remove(e.getPlayer());
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("kitpvp.cmd.staffchat")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		if (player.contains(p)) {
			player.remove(p);
			p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("SFExit")));
			return true;
		}
		player.add(p);
		p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("SFEnter")));
		return false;
	}
}
