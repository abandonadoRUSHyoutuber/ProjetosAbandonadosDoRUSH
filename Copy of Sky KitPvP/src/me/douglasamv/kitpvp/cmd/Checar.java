package me.douglasamv.kitpvp.cmd;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;

public class Checar implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("kitpvp.cmd.check")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("§cUse /check <nick>");
			return true;
		}
		Player checado = Bukkit.getPlayer(args[0]);
		if (checado == null) {
			p.sendMessage("§cPlayer offline!");
			return true;
		}
		p.sendMessage("§cPlayer: §7" + checado.getName());
		p.sendMessage("§cKit:§7 " + HabilidadeApi.verHB(checado).replace("nenhum", Main.cfg_kits.getString("kits.none.ability")));
		p.sendMessage("§cGamemode:§7 " + checado.getGameMode());
		p.sendMessage("§cLife:§7 " + checado.getHealth());
		p.sendMessage("§cLocation:§7 X:" + checado.getLocation().getBlockX() + " Y:" + checado.getLocation().getBlockY()
				+ " Z:" + checado.getLocation().getBlockZ());
		p.sendMessage("§cIp:§7 " + checado.getAddress().getHostString());
		try {
			ResultSet rs;
			rs = Main.getMysql().conectar().createStatement()
					.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + checado.getName() + "';");
			if (rs.next()) {
				p.sendMessage("§cKills:§7 " + rs.getInt("kill"));
				p.sendMessage("§cDeaths:§7 " + rs.getInt("death"));
				p.sendMessage("§cRank:§7 " + Inventarios.rank(rs.getInt("kill")));
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
		}
		return false;
	}
}
