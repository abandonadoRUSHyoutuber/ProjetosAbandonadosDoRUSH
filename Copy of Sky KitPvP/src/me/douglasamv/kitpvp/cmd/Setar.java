package me.douglasamv.kitpvp.cmd;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;

public class Setar implements CommandExecutor {

	// 45 57 -287

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("kitpvp.cmd.setar")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("§cUse /set <spawn/fps/main/lava>");
			return true;
		}
		if (args[0].equalsIgnoreCase("spawn")) {
			Location loc = p.getLocation();
			Bukkit.getWorld("world").setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
			Main.cfg_warps.set("warps.spawn.x", loc.getX());
			Main.cfg_warps.set("warps.spawn.y", loc.getY());
			Main.cfg_warps.set("warps.spawn.z", loc.getZ());
			try {
				Main.cfg_warps.save(Main.file_warps);
				Main.cfg_warps.load(Main.file_warps);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
			p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("Setted")));
			return true;
		} else if (args[0].equalsIgnoreCase("fps")) {
			Location loc = p.getLocation();
			Main.cfg_warps.set("warps.fps.x", loc.getX());
			Main.cfg_warps.set("warps.fps.y", loc.getY());
			Main.cfg_warps.set("warps.fps.z", loc.getZ());
			try {
				Main.cfg_warps.save(Main.file_warps);
				Main.cfg_warps.load(Main.file_warps);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
			p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("Setted")));
			return true;
		} else if (args[0].equalsIgnoreCase("main")) {
			Location loc = p.getLocation();
			Main.cfg_warps.set("warps.main.x", loc.getX());
			Main.cfg_warps.set("warps.main.y", loc.getY());
			Main.cfg_warps.set("warps.main.z", loc.getZ());
			try {
				Main.cfg_warps.save(Main.file_warps);
				Main.cfg_warps.load(Main.file_warps);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
			p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("Setted")));
			return true;
		} else if (args[0].equalsIgnoreCase("lava")) {
			Location loc = p.getLocation();
			Main.cfg_warps.set("warps.lava.x", loc.getX());
			Main.cfg_warps.set("warps.lava.y", loc.getY());
			Main.cfg_warps.set("warps.lava.z", loc.getZ());
			try {
				Main.cfg_warps.save(Main.file_warps);
				Main.cfg_warps.load(Main.file_warps);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
			p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("Setted")));
			return true;
		} else if (args[0].equalsIgnoreCase("knock")) {
			Location loc = p.getLocation();
			Main.cfg_warps.set("warps.knock.x", loc.getX());
			Main.cfg_warps.set("warps.knock.y", loc.getY());
			Main.cfg_warps.set("warps.knock.z", loc.getZ());
			try {
				Main.cfg_warps.save(Main.file_warps);
				Main.cfg_warps.load(Main.file_warps);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
			p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("Setted")));
			return true;
		} else {
			p.sendMessage("§cUse /set <spawn/fps/main/lava/knock>");

		}
		return false;
	}
}
