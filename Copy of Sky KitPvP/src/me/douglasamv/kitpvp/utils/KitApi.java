package me.douglasamv.kitpvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class KitApi {
	
	public static void MenuKit(Player p, String kit,ItemStack sobre,ItemStack item, ItemStack jogar) {
		Inventory inv = Bukkit.getServer().createInventory(p, 54, "§7Kit: " + kit);
		ItemStack vidro = ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 0, " ", false);
		for (int i=0;i<54;i++) {
			inv.setItem(i, vidro);
		}
		inv.setItem(19, sobre);
		inv.setItem(25, item);
		inv.setItem(40, jogar);
		p.openInventory(inv);
	}

}
