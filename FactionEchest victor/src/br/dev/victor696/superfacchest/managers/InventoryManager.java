package br.dev.victor696.superfacchest.managers;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import br.dev.victor696.superfacchest.utils.Methods;

public class InventoryManager {
	
	public static Inventory getBuyInv() {
		Inventory inv = Bukkit.getServer().createInventory(null, 3 * 9, "�8Deseja comprar?");
		inv.setItem(11, Methods.getItem(35, 1, 5, "�aSim", Arrays.asList("�7Ao comprar, voc� estar�", "�7ciente de que n�o receber�", "�7reembolso pela compra.")));
		inv.setItem(15, Methods.getItem(35, 1, 14, "�cN�o", Arrays.asList("�7Clique para voltar")));
		return inv;
	}

}
