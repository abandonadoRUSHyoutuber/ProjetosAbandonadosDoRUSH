package br.dev.victor696.superfacchest.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import br.dev.victor696.superfacchest.SuperFacChest;
import br.dev.victor696.superfacchest.object.Faccao;

public class Methods {
	
	@SuppressWarnings("deprecation")
	public static ItemStack getItem(int id, int quantia, int data, String nome, List<String> lore) {
		ItemStack item = new ItemStack(Material.getMaterial(id), quantia, (short) data);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome.replaceAll("&", "§"));
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public static void loadFaccoes() {
		try {
			PreparedStatement stm = SuperFacChest.getInstance().sql.conexao.prepareStatement("SELECT * FROM superfacchest");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				String faccao = rs.getString("Faccao");
				String contents = rs.getString("Base64");
				
				Faccao f = new Faccao(faccao, contents);
				SuperFacChest.getInstance().faccao.put(faccao, f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
