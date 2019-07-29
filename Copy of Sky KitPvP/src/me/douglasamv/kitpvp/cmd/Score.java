package me.douglasamv.kitpvp.cmd;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.utils.Inventarios;

public class Score implements CommandExecutor, Listener {

	public static ArrayList<String> player = new ArrayList<>();

	@EventHandler
	void sair(PlayerQuitEvent e) {
		if (player.contains(e.getPlayer().getName())) {
			player.remove(e.getPlayer().getName());
		}
	}

	@EventHandler
	void kick(PlayerQuitEvent e) {
		if (player.contains(e.getPlayer().getName())) {
			player.remove(e.getPlayer().getName());
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;

		if (player.contains(p.getName())) {
			player.remove(p.getName());
			p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("score.Enabled")));
			Inventarios.upDateScore(p);
		} else {
			player.add(p.getName());
			p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("score.Disabled")));
			p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		}

		return false;
	}
}
