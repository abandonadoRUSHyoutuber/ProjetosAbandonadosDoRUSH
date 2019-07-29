package me.douglasamv.kitpvp.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;

public class ComprarKit {

	public static void comprar(Player p, String permission, int Valor, String noMoney, String comprou) {
		p.closeInventory();
		int money = 0;
		try {
			ResultSet rs;
			rs = Main.getMysql().conectar().createStatement()
					.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + p.getName() + "';");
			if (rs.next()) {
				money = rs.getInt("money");
				rs.getStatement().getConnection().close();
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
		}
		if (money >= Valor) {
			Bukkit.dispatchCommand(Main.pl.getServer().getConsoleSender(), permission.replace("$player$", p.getName()));
			p.sendMessage(Mensagens.cor(comprou));
			try {
				ResultSet rs;
				rs = Main.getMysql().conectar().createStatement()
						.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + p.getName() + "';");
				if (rs.next()) {
					Main.getMysql().conectar().createStatement().executeUpdate("UPDATE `pvp` SET `money`='"
							+ String.valueOf((rs.getInt("money") - Valor)) + "' WHERE `nick`='" + p.getName() + "';");
				}
				rs.getStatement().getConnection().close();

			} catch (SQLException e) {
			}
		} else {
			p.sendMessage(Mensagens.cor(noMoney));
		}
		Inventarios.upDateScore(p);
	}

}
