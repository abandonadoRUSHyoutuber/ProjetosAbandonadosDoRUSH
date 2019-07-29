package me.douglasamv.kitpvp.cmd;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;

public class Money implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if (!p.hasPermission("kitpvp.cmd.money")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("§cUse /money <set/add> <amount> nick");
			return true;
		}
		if (args.length == 1) {
			p.sendMessage("§cUse /money <set/add> <amount> nick");
			return true;
		}
		if (args.length == 2) {
			p.sendMessage("§cUse /money <set/add> <amount> nick");
			return true;
		}
		if (args[0].equalsIgnoreCase("set")) {
			String check = args[2];

			try {
				ResultSet rs;
				rs = Main.getMysql().conectar().createStatement()
						.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + check + "';");
				if (rs.next()) {
					Main.getMysql().conectar().createStatement()
							.executeUpdate("UPDATE `pvp` SET `money`='" + args[1] + "' WHERE `nick`='" + check + "';");
					rs.getStatement().getConnection().close();
					p.sendMessage("§cMoney seted!");
				} else {
					p.sendMessage("§cThis player not listed in the database");
					rs.getStatement().getConnection().close();
				}
			} catch (SQLException | NumberFormatException e) {
				p.sendMessage("§cUse /money <set/add> <amount> nick");
			}
			return true;
		} else if (args[0].equalsIgnoreCase("add")) {
			String check = args[2];

			try {
				ResultSet rs;
				rs = Main.getMysql().conectar().createStatement()
						.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + check + "';");
				if (rs.next()) {
					int add = rs.getInt("money") + Integer.valueOf(args[1]);
					Main.getMysql().conectar().createStatement()
							.executeUpdate("UPDATE `pvp` SET `money`='" + add + "' WHERE `nick`='" + check + "';");
					rs.getStatement().getConnection().close();
					p.sendMessage("§cMoney added!");
				} else {
					p.sendMessage("§cThis player not listed in the database");
					rs.getStatement().getConnection().close();
				}
			} catch (SQLException | NumberFormatException e) {
				p.sendMessage("§cUse /money <set/add> <amount> nick");
			}
			return true;
		} else {
			p.sendMessage("§cUse /money <set/add> <amount> nick");

		}
		return false;
	}

}
