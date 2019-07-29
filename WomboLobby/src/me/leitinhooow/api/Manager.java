package me.leitinhooow.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Manager {
	
	public static HashMap<Player, String> players =  new HashMap<Player, String>();
	
	public static void SetarItemInv(Material material, int durabilidade, String nome, String descricao, Player jogador, int slot) {
		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		item.setDurability((short)durabilidade);
		itemMeta.setDisplayName(nome);
		ArrayList<String> Desc = new ArrayList<>();
		Desc.add(descricao);
		itemMeta.setLore(Desc);
		item.setItemMeta(itemMeta);
		
		jogador.getInventory().setItem(slot, item);
	}
	
	public static ItemStack criarItem(Material material, String nome){
		ItemStack stack = new ItemStack(material);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.setDisplayName(nome);
		stack.setItemMeta(stack2);
		return stack;
	}
	
	public static ItemStack criarItem(Material material, String nome, String lore){
		ItemStack stack = new ItemStack(material);
		ItemMeta stack2 = stack.getItemMeta();
		List<String> ls = new ArrayList<String>();
		ls.add(lore);
		stack2.setLore(ls);
		stack2.setDisplayName(nome);
		stack.setItemMeta(stack2);
		return stack;
	}
	
	public static ItemStack criarItem(Material material, String nome, Enchantment encantamento){
		ItemStack stack = new ItemStack(material);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.addEnchant(encantamento, 1, true);
		stack2.setDisplayName(nome);
		stack.setItemMeta(stack2);
		return stack;
	}
	

}
