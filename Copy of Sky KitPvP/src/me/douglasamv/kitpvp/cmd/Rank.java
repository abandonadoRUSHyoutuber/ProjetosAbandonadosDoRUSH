package me.douglasamv.kitpvp.cmd;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.utils.Inventarios;

public class Rank implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return false;
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			p.sendMessage("§cUse /rank <nick>");
			return true;
		}
		String checado = args[0];
		try {
			ResultSet rs;
			rs = Main.getMysql().conectar().createStatement()
					.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + checado + "';");
			if (rs.next()) {
				p.sendMessage("§cRank:§7 " + Inventarios.rank(rs.getInt("kill")));
				rs.getStatement().getConnection().close();
			} else {
				p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("RankNoPlayer")));
				rs.getStatement().getConnection().close();
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
		}
		return false;
	}

}
