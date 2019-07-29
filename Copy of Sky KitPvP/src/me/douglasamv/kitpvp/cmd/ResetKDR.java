package me.douglasamv.kitpvp.cmd;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;

public class ResetKDR implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if (!p.hasPermission("kitpvp.cmd.resetkdr")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("§cUse /resetkdr nick");
			return true;
		}
		String check = args[0];
		try {
			ResultSet rs;
			rs = Main.getMysql().conectar().createStatement()
					.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + check + "';");
			if (rs.next()) {
				Main.getMysql().conectar().createStatement()
						.executeUpdate("UPDATE `pvp` SET `kill`='0' WHERE `nick`='" + check + "';");
				Main.getMysql().conectar().createStatement()
						.executeUpdate("UPDATE `pvp` SET `death`='0' WHERE `nick`='" + check + "';");
				rs.getStatement().getConnection().close();

				p.sendMessage("§cReseted!");
			} else {
				p.sendMessage("§cThis player not listed in the database");
				rs.getStatement().getConnection().close();
			}
		} catch (SQLException e) {
		}
		return false;
	}

}
