package me.douglasamv.kitpvp.cmd;

import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;

public class X1 implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("kitpvp.cmd.1v1")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		Location loc = p.getLocation();
		Double x = loc.getX();
		Double y = loc.getY();
		Double z = loc.getZ();
		Float yaw = loc.getYaw();
		Float pitch = loc.getPitch();

		if (args.length == 0) {
			p.sendMessage("�cUse /1v1 <spawn/loc1/loc2>");
			return true;
		}
		if (args[0].equalsIgnoreCase("spawn")) {
			Main.cfg_x1.set("x1.coords.spawn.x", x);
			Main.cfg_x1.set("x1.coords.spawn.y", y);
			Main.cfg_x1.set("x1.coords.spawn.z", z);
			Main.cfg_x1.set("x1.coords.spawn.yaw", yaw);
			Main.cfg_x1.set("x1.coords.spawn.pitch", pitch);
			try {
				Main.cfg_x1.save(Main.file_x1);
				Main.cfg_x1.load(Main.file_x1);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
			p.sendMessage(Main.pl.getConfig().getString("Setted"));
			return true;
		} else if (args[0].equalsIgnoreCase("loc1")) {
			Main.cfg_x1.set("x1.coords.loc_1.x", x);
			Main.cfg_x1.set("x1.coords.loc_1.y", y);
			Main.cfg_x1.set("x1.coords.loc_1.z", z);
			Main.cfg_x1.set("x1.coords.loc_1.yaw", yaw);
			Main.cfg_x1.set("x1.coords.loc_1.pitch", pitch);
			try {
				Main.cfg_x1.save(Main.file_x1);
				Main.cfg_x1.load(Main.file_x1);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
			p.sendMessage(Main.pl.getConfig().getString("Setted"));
			return true;
		} else if (args[0].equalsIgnoreCase("loc2")) {
			Main.cfg_x1.set("x1.coords.loc_2.x", x);
			Main.cfg_x1.set("x1.coords.loc_2.y", y);
			Main.cfg_x1.set("x1.coords.loc_2.z", z);
			Main.cfg_x1.set("x1.coords.loc_2.yaw", yaw);
			Main.cfg_x1.set("x1.coords.loc_2.pitch", pitch);
			try {
				Main.cfg_x1.save(Main.file_x1);
				Main.cfg_x1.load(Main.file_x1);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
			p.sendMessage(Main.pl.getConfig().getString("Setted"));
			return true;
		} else {
			p.sendMessage("�cUse /1v1 <spawn/loc1/loc2>");
		}

		return false;
	}

}
