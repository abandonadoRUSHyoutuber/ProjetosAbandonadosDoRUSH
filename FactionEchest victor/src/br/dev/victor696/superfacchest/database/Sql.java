package br.dev.victor696.superfacchest.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import br.dev.victor696.superfacchest.SuperFacChest;

public class Sql {

	public Connection conexao;

	public void criarTabelas() {
		executarUpdate("CREATE TABLE IF NOT EXISTS superfacchest (Faccao VARCHAR, Base64 VARCHAR)");
	}

	public synchronized void abrirConexao() {
		try {
			File file = new File(SuperFacChest.getInstance().getDataFolder(), "superfacchest.db");
			String URL = "jdbc:sqlite:" + file;
			Class.forName("org.sqlite.JDBC");
			conexao = DriverManager.getConnection(URL);
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("" + e);
			Bukkit.getPluginManager().disablePlugin(SuperFacChest.getInstance());
		}
	}

	public PreparedStatement prepareStatement(String statement) {
		try {
			return conexao.prepareStatement(statement);
		} catch (SQLException ex) {
			Bukkit.getConsoleSender().sendMessage("" + ex);
			return null;
		}
	}

	public void executarUpdate(String update) {
		try {
			conexao.createStatement().executeUpdate(update);
		} catch (SQLException ex) {
			Bukkit.getConsoleSender().sendMessage("" + ex);
		}
	}

	public void execute(String update) {
		try {
			conexao.createStatement().execute(update);
		} catch (SQLException ex) {
			Bukkit.getConsoleSender().sendMessage("" + ex);
		}
	}

	public void fecharConexao() {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (Exception ex) {
				Bukkit.getConsoleSender().sendMessage("" + ex);
			}
		}
	}

}
