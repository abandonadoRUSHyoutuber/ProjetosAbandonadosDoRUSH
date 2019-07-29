package br.itensespeciais.itemstack;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import br.itensespeciais.main.Main;

public class ItemManager {

	public static List<String> lore = new ArrayList<String>();
	
	public static ItemStack getPoderMaximo() {
		ItemStack podermaximo = new ItemStack(Material.NETHER_STAR);
		ItemMeta poderm = podermaximo.getItemMeta();
		poderm.setDisplayName(Main.get().getConfig().getString("podermaximo.nome").replace("&", "§"));
		for(String s : Main.get().getConfig().getStringList("podermaximo.lore")) {
			lore.add(s.replace("&", "§"));
		}
		poderm.setLore(lore);
		lore.clear();
		podermaximo.setItemMeta(poderm);
		return podermaximo;
	}
	
	public static ItemStack getFragmentoPoder() {
		ItemStack item = new ItemStack(Material.QUARTZ);
		ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(Main.get().getConfig().getString("fragmentodepoder.nome").replace("&", "§"));
		for(String s : Main.get().getConfig().getStringList("fragmentodepoder.lore")) {
			lore.add(s.replace("&", "§"));
		}
		itemm.setLore(lore);
		lore.clear();
		item.setItemMeta(itemm);
		if(Main.get().getConfig().getBoolean("fragmentodepoder.glow")) {
			ItemMeta glow = item.getItemMeta();
			glow.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(glow);
			item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		}
		return item;
	}
	
	public static ItemStack getCapsula() {
		ItemStack item = new ItemStack(Material.ENDER_PEARL);
		ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(Main.get().getConfig().getString("capsula.nome").replace("&", "§"));
		for(String s : Main.get().getConfig().getStringList("capsula.lore")) {
			lore.add(s.replace("&", "§"));
		}
		itemm.setLore(lore);
		lore.clear();
		item.setItemMeta(itemm);
		if(Main.get().getConfig().getBoolean("capsula.glow")) {
			ItemMeta glow = item.getItemMeta();
			glow.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(glow);
			item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		}
		return item;
	}
	
	public static ItemStack getRaioMestre() {
		ItemStack item = new ItemStack(Material.BLAZE_ROD);
		ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(Main.get().getConfig().getString("raiomestre.nome").replace("&", "§"));
		for(String s : Main.get().getConfig().getStringList("raiomestre.lore")) {
			lore.add(s.replace("&", "§"));
		}
		itemm.setLore(lore);
		lore.clear();
		item.setItemMeta(itemm);
		if(Main.get().getConfig().getBoolean("raiomestre.glow")) {
			ItemMeta glow = item.getItemMeta();
			glow.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(glow);
			item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		}
		return item;
	}
	
	public static ItemStack getLancador() {
		ItemStack item = new ItemStack(Material.FIREWORK);
		ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(Main.get().getConfig().getString("lancador.nome").replace("&", "§"));
		for(String s : Main.get().getConfig().getStringList("lancador.lore")) {
			lore.add(s.replace("&", "§"));
		}
		itemm.setLore(lore);
		lore.clear();
		item.setItemMeta(itemm);
		if(Main.get().getConfig().getBoolean("lancador.glow")) {
			ItemMeta glow = item.getItemMeta();
			glow.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(glow);
			item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		}
		return item;
	}
	
	public static ItemStack getArmadilha() {
		ItemStack item = new ItemStack(Material.SNOW_BALL);
		ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(Main.get().getConfig().getString("armadilha.nome").replace("&", "§"));
		for(String s : Main.get().getConfig().getStringList("armadilha.lore")) {
			lore.add(s.replace("&", "§"));
		}
		itemm.setLore(lore);
		lore.clear();
		item.setItemMeta(itemm);
		if(Main.get().getConfig().getBoolean("armadilha.glow")) {
			ItemMeta glow = item.getItemMeta();
			glow.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(glow);
			item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		}
		return item;
	}
	
	public static ItemStack getCreeperEletrico() {
		ItemStack item = new ItemStack(Material.MONSTER_EGG, 1, (short)50);
		ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(Main.get().getConfig().getString("creepereletrizado.nome").replace("&", "§"));
		for(String s : Main.get().getConfig().getStringList("creepereletrizado.lore")) {
			lore.add(s.replace("&", "§"));
		}
		itemm.setLore(lore);
		lore.clear();
		item.setItemMeta(itemm);
		if(Main.get().getConfig().getBoolean("creepereletrizado.glow")) {
			ItemMeta glow = item.getItemMeta();
			glow.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(glow);
			item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		}
		return item;
	}
	
	public static ItemStack getSilkTouch() {
		ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(Main.get().getConfig().getString("silktouch.nome").replace("&", "§"));
		for(String s : Main.get().getConfig().getStringList("silktouch.lore")) {
			lore.add(s.replace("&", "§"));
		}
		itemm.spigot().setUnbreakable(true);
		itemm.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemm.setLore(lore);
		lore.clear();
		item.setItemMeta(itemm);
		item.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 2);
		
		return item;
	}
	
	public static ItemStack getPurificador() {
		ItemStack item = new ItemStack(Material.POTION, (byte) 1);
		PotionMeta itemm = (PotionMeta)item.getItemMeta();
		itemm.setDisplayName(Main.get().getConfig().getString("purificador.nome").replace("&", "§"));
		for(String s : Main.get().getConfig().getStringList("purificador.lore")) {
			lore.add(s.replace("&", "§"));
		}
		itemm.setLore(lore);
		itemm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ATTRIBUTES);
		lore.clear();
		item.setItemMeta(itemm);
		
		Potion pot = new Potion(1);
        pot.setType(PotionType.WEAKNESS);
        pot.setSplash(true);
        pot.apply(item);
        
		return item;
	}
	
	public static ItemStack getPoderInstantaneo() {
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(Main.get().getConfig().getString("poderinstantaneo.nome").replace("&", "§"));
		for(String s : Main.get().getConfig().getStringList("poderinstantaneo.lore")) {
			lore.add(s.replace("&", "§"));
		}
		itemm.setLore(lore);
		lore.clear();
		item.setItemMeta(itemm);
		if(Main.get().getConfig().getBoolean("poderinstantaneo.glow")) {
			ItemMeta glow = item.getItemMeta();
			glow.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(glow);
			item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		}
		return item;
	}
	
	public static ItemStack getCreeperNormal() {
		ItemStack item = new ItemStack(Material.MONSTER_EGGS, (short) 50);
		return item;
	}
	
	public static void removerItemMao(Player p) {
		if(p.getItemInHand().getAmount() == 1) {
			p.setItemInHand(new ItemStack(Material.AIR));
			return;
		} 
		ItemStack ap = p.getItemInHand();
		ap.setAmount(ap.getAmount() - 1);
		p.setItemInHand(ap);
	}
}
