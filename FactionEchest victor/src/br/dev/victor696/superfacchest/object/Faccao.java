package br.dev.victor696.superfacchest.object;

import java.sql.PreparedStatement;

import br.dev.victor696.superfacchest.SuperFacChest;

public class Faccao {
	
	private String faccao;
	private String contents;
	
	public Faccao(String faccao, String contents) {
		this.faccao = faccao;
		this.contents = contents;
	}
	
	public String getFaccao() {
		return this.faccao;
	}
	
	public String getContents() {
		return this.contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public void saveChest() {
		try {
			PreparedStatement stm = SuperFacChest.getInstance().sql.conexao.prepareStatement("UPDATE superfacchest SET Base64 = ? WHERE Faccao = ?");
			stm.setString(1, this.contents);
			stm.setString(2, this.faccao);
			stm.executeUpdate();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
