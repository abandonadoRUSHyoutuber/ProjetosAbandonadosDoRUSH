package br.dev.victor696.superfacchest.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.dev.victor696.superfacchest.SuperFacChest;

public class SqlManager {
	
	public static void deleteFaccao(String faccao) {
		try {
			PreparedStatement stm = SuperFacChest.getInstance().sql.conexao.prepareStatement("DELETE FROM superfacchest WHERE Faccao = ?");
			stm.setString(1, faccao);
			stm.executeUpdate();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean hasFaccao(String faccao) {
		try {
			PreparedStatement stm = SuperFacChest.getInstance().sql.conexao.prepareStatement("SELECT * FROM superfacchest WHERE Faccao = ?");
			stm.setString(1, faccao);
			ResultSet rs = stm.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void createChest(String faccao) {
		try {
			PreparedStatement stm = SuperFacChest.getInstance().sql.conexao.prepareStatement("INSERT INTO superfacchest (Faccao, Base64) VALUES (?,?)");
			stm.setString(1, faccao);
			stm.setString(2, "null");
			stm.executeUpdate();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setContents(String faccao, String contents) {
		try {
			PreparedStatement stm = SuperFacChest.getInstance().sql.conexao.prepareStatement("UPDATE superfacchest SET Base64 = ? WHERE Faccao = ?");
			stm.setString(1, contents);
			stm.setString(2, faccao);
			stm.executeUpdate();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getContents(String faccao) {
		String contents = null;
		try {
			PreparedStatement stm = SuperFacChest.getInstance().sql.conexao.prepareStatement("SELECT * FROM superfacchest WHERE Faccao = ?");
			stm.setString(1, faccao);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				contents = rs.getString("Base64");
				return contents;
			}
			stm.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contents;
	}

}
