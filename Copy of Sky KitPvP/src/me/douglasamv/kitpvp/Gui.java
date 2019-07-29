package me.douglasamv.kitpvp;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;

public class Gui {

	public static void kits(Player p) {
		Inventory inv = Bukkit.getServer().createInventory(p, 54,
				Mensagens.cor(Main.cfg_kits.getString("kits.gui.your_kits")));
		ItemStack vidro = ItemAPI.Criar(Material.IRON_FENCE, 1, 0, " ", false);
		for (int i = 0; i < 8; i++) {
			inv.setItem(i, vidro);
		}
		inv.setItem(8, ItemAPI.Criar(Material.CARPET, 1, 14, "§c»", false));
		if (p.hasPermission("kitpvp.kit.pvp")) {
			if (Main.cfg_kits.getBoolean("kits.pvp.desc")) {
				inv.addItem(ItemAPI.removeAttributes(ItemAPI.Criar(Material.DIAMOND_SWORD, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.pvp.gui_name")), false,
						Main.cfg_kits.getStringList("kits.pvp.gui_desc"))));
			} else {
				inv.addItem(ItemAPI.removeAttributes(ItemAPI.Criar(Material.DIAMOND_SWORD, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.pvp.gui_name")), false)));
			}
		}
		if (p.hasPermission("kitpvp.kit.kangaroo")) {
			if (Main.cfg_kits.getBoolean("kits.kangaroo.desc")) {
				inv.addItem(ItemAPI.Criar(Material.FIREWORK, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.kangaroo.gui_name")), false,
						Main.cfg_kits.getStringList("kits.kangaroo.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.FIREWORK, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.kangaroo.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.fisherman")) {
			if (Main.cfg_kits.getBoolean("kits.fisherman.desc")) {
				inv.addItem(ItemAPI.Criar(Material.FISHING_ROD, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.fisherman.gui_name")), false,
						Main.cfg_kits.getStringList("kits.fisherman.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.FISHING_ROD, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.fisherman.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.archer")) {
			if (Main.cfg_kits.getBoolean("kits.archer.desc")) {
				inv.addItem(ItemAPI.Criar(Material.BOW, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.archer.gui_name")), false,
						Main.cfg_kits.getStringList("kits.archer.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.BOW, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.archer.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.sonic")) {
			if (Main.cfg_kits.getBoolean("kits.sonic.desc")) {
				inv.addItem(ItemAPI.Criar(Material.LAPIS_BLOCK, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.sonic.gui_name")), false,
						Main.cfg_kits.getStringList("kits.sonic.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.LAPIS_BLOCK, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.sonic.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.deshfire")) {
			if (Main.cfg_kits.getBoolean("kits.deshfire.desc")) {
				inv.addItem(ItemAPI.Criar(Material.REDSTONE_BLOCK, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.gui_name")), false,
						Main.cfg_kits.getStringList("kits.deshfire.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.REDSTONE_BLOCK, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.anchor")) {
			if (Main.cfg_kits.getBoolean("kits.anchor.desc")) {
				inv.addItem(ItemAPI.Criar(Material.ANVIL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.anchor.gui_name")), false,
						Main.cfg_kits.getStringList("kits.anchor.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.ANVIL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.anchor.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.reaper")) {
			if (Main.cfg_kits.getBoolean("kits.reaper.desc")) {
				inv.addItem(ItemAPI.Criar(Material.WOOD_HOE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.reaper.gui_name")), false,
						Main.cfg_kits.getStringList("kits.reaper.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.WOOD_HOE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.reaper.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.phantom")) {
			if (Main.cfg_kits.getBoolean("kits.phantom.desc")) {
				inv.addItem(ItemAPI.Criar(Material.FEATHER, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.phantom.gui_name")), false,
						Main.cfg_kits.getStringList("kits.phantom.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.FEATHER, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.phantom.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.urgal")) {
			if (Main.cfg_kits.getBoolean("kits.urgal.desc")) {
				inv.addItem(ItemAPI.Criar(Material.POTION, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.urgal.gui_name")), false,
						Main.cfg_kits.getStringList("kits.urgal.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.POTION, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.urgal.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.grandpa")) {
			if (Main.cfg_kits.getBoolean("kits.grandpa.desc")) {
				inv.addItem(ItemAPI.Criar(Material.STICK, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.grandpa.gui_name")), false,
						Main.cfg_kits.getStringList("kits.grandpa.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.STICK, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.grandpa.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.snail")) {
			if (Main.cfg_kits.getBoolean("kits.snail.desc")) {
				inv.addItem(ItemAPI.Criar(Material.STRING, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.snail.gui_name")), false,
						Main.cfg_kits.getStringList("kits.snail.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.STRING, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.snail.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.backpacker")) {
			if (Main.cfg_kits.getBoolean("kits.backpacker.desc")) {
				inv.addItem(ItemAPI.Criar(Material.LEATHER, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.gui_name")), false,
						Main.cfg_kits.getStringList("kits.backpacker.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.LEATHER, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.hulk")) {
			if (Main.cfg_kits.getBoolean("kits.hulk.desc")) {
				inv.addItem(
						ItemAPI.Criar(Material.BONE, 1, 0, Mensagens.cor(Main.cfg_kits.getString("kits.hulk.gui_name")),
								false, Main.cfg_kits.getStringList("kits.hulk.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.BONE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.hulk.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.monk")) {
			if (Main.cfg_kits.getBoolean("kits.monk.desc")) {
				inv.addItem(ItemAPI.Criar(Material.BLAZE_ROD, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.monk.gui_name")), false,
						Main.cfg_kits.getStringList("kits.monk.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.BLAZE_ROD, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.monk.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.stomper")) {
			if (Main.cfg_kits.getBoolean("kits.stomper.desc")) {
				inv.addItem(ItemAPI.Criar(Material.IRON_BOOTS, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.stomper.gui_name")), false,
						Main.cfg_kits.getStringList("kits.stomper.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.IRON_BOOTS, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.stomper.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.viper")) {
			if (Main.cfg_kits.getBoolean("kits.viper.desc")) {
				inv.addItem(ItemAPI.Criar(Material.SPIDER_EYE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.viper.gui_name")), false,
						Main.cfg_kits.getStringList("kits.viper.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.SPIDER_EYE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.viper.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.poseidon")) {
			if (Main.cfg_kits.getBoolean("kits.poseidon.desc")) {
				inv.addItem(ItemAPI.Criar(Material.WATER_BUCKET, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.poseidon.gui_name")), false,
						Main.cfg_kits.getStringList("kits.poseidon.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.WATER_BUCKET, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.poseidon.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.fireman")) {
			if (Main.cfg_kits.getBoolean("kits.fireman.desc")) {
				inv.addItem(ItemAPI.Criar(Material.FIREBALL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.fireman.gui_name")), false,
						Main.cfg_kits.getStringList("kits.fireman.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.FIREBALL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.fireman.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.switcher")) {
			if (Main.cfg_kits.getBoolean("kits.switcher.desc")) {
				inv.addItem(ItemAPI.Criar(Material.SNOW_BALL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.switcher.gui_name")), false,
						Main.cfg_kits.getStringList("kits.switcher.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.SNOW_BALL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.switcher.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.ninja")) {
			if (Main.cfg_kits.getBoolean("kits.ninja.desc")) {
				inv.addItem(ItemAPI.Criar(Material.NETHER_STAR, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.ninja.gui_name")), false,
						Main.cfg_kits.getStringList("kits.ninja.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.NETHER_STAR, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.ninja.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.ryu")) {
			if (Main.cfg_kits.getBoolean("kits.ryu.desc")) {
				inv.addItem(ItemAPI.Criar(Material.BEACON, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.ryu.gui_name")), false,
						Main.cfg_kits.getStringList("kits.ryu.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.BEACON, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.ryu.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.berserker")) {
			if (Main.cfg_kits.getBoolean("kits.berserker.desc")) {
				inv.addItem(ItemAPI.Criar(Material.FERMENTED_SPIDER_EYE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.berserker.gui_name")), false,
						Main.cfg_kits.getStringList("kits.berserker.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.FERMENTED_SPIDER_EYE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.berserker.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.gladiator")) {
			if (Main.cfg_kits.getBoolean("kits.gladiator.desc")) {
				inv.addItem(ItemAPI.Criar(Material.IRON_INGOT, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.gui_name")), false,
						Main.cfg_kits.getStringList("kits.gladiator.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.IRON_INGOT, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.souper")) {
			if (Main.cfg_kits.getBoolean("kits.souper.desc")) {
				inv.addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.souper.gui_name")), false,
						Main.cfg_kits.getStringList("kits.souper.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.souper.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.camel")) {
			if (Main.cfg_kits.getBoolean("kits.camel.desc")) {
				inv.addItem(ItemAPI.Criar(Material.SAND, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.camel.gui_name")), false,
						Main.cfg_kits.getStringList("kits.camel.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.SAND, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.camel.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.neurotic")) {
			if (Main.cfg_kits.getBoolean("kits.neurotic.desc")) {
				inv.addItem(ItemAPI.Criar(Material.SLIME_BALL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.neurotic.gui_name")), false,
						Main.cfg_kits.getStringList("kits.neurotic.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.SLIME_BALL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.neurotic.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.vaccum")) {
			if (Main.cfg_kits.getBoolean("kits.vaccum.desc")) {
				inv.addItem(ItemAPI.Criar(Material.ENDER_PEARL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.vaccum.gui_name")), false,
						Main.cfg_kits.getStringList("kits.vaccum.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.ENDER_PEARL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.vaccum.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.ajnin")) {
			if (Main.cfg_kits.getBoolean("kits.ajnin.desc")) {
				inv.addItem(ItemAPI.Criar(Material.INK_SACK, 1, 7,
						Mensagens.cor(Main.cfg_kits.getString("kits.ajnin.gui_name")), false,
						Main.cfg_kits.getStringList("kits.ajnin.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.INK_SACK, 1, 7,
						Mensagens.cor(Main.cfg_kits.getString("kits.ajnin.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.forcefield")) {
			if (Main.cfg_kits.getBoolean("kits.forcefield.desc")) {
				inv.addItem(ItemAPI.Criar(Material.IRON_PLATE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.forcefield.gui_name")), false,
						Main.cfg_kits.getStringList("kits.forcefield.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.IRON_PLATE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.forcefield.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.neo")) {
			if (Main.cfg_kits.getBoolean("kits.neo.desc")) {
				inv.addItem(
						ItemAPI.Criar(Material.ARROW, 1, 0, Mensagens.cor(Main.cfg_kits.getString("kits.neo.gui_name")),
								false, Main.cfg_kits.getStringList("kits.neo.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.ARROW, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.neo.gui_name")), false));
			}
		}
		if (p.hasPermission("kitpvp.kit.frosty")) {
			if (Main.cfg_kits.getBoolean("kits.frosty.desc")) {
				inv.addItem(ItemAPI.Criar(Material.PACKED_ICE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.frosty.gui_name")), false,
						Main.cfg_kits.getStringList("kits.frosty.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.PACKED_ICE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.frosty.gui_name")), false));
			}
		}
		/*
		 * if (p.hasPermission("kitpvp.kit.endermage")) {
		 * inv.addItem(ItemAPI.Criar(Material.NETHER_BRICK_ITEM, 1, 0,
		 * "§7Kit §8» §7EnderMage", false)); }
		 */
		ItemStack[] arrayOfItemStack;
		int i = (arrayOfItemStack = inv.getContents()).length;
		for (int i2 = 0; i2 < i; i2++) {
			ItemStack item = arrayOfItemStack[i2];
			if (item == null) {
				inv.setItem(inv.firstEmpty(), ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 7, " ", false));
			}
		}
		p.openInventory(inv);
	}

	public static void oKits(Player p) {
		Inventory inv = Bukkit.getServer().createInventory(p, 54,
				Mensagens.cor(Main.cfg_kits.getString("kits.gui.other_kits")));
		ItemStack vidro = ItemAPI.Criar(Material.IRON_FENCE, 1, 0, " ", false);
		for (int i = 0; i < 9; i++) {
			inv.setItem(i, vidro);
		}
		inv.setItem(0, ItemAPI.Criar(Material.CARPET, 1, 5, "§2«", false));
		if (!p.hasPermission("kitpvp.kit.pvp")) {
			if (Main.cfg_kits.getBoolean("kits.pvp.desc")) {
				inv.addItem(ItemAPI.removeAttributes(ItemAPI.Criar(Material.DIAMOND_SWORD, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.pvp.gui_name")), false,
						Main.cfg_kits.getStringList("kits.pvp.gui_desc"))));
			} else {
				inv.addItem(ItemAPI.removeAttributes(ItemAPI.Criar(Material.DIAMOND_SWORD, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.pvp.gui_name")), false)));
			}
		}
		if (!p.hasPermission("kitpvp.kit.kangaroo")) {
			if (Main.cfg_kits.getBoolean("kits.kangaroo.desc")) {
				inv.addItem(ItemAPI.Criar(Material.FIREWORK, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.kangaroo.gui_name")), false,
						Main.cfg_kits.getStringList("kits.kangaroo.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.FIREWORK, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.kangaroo.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.fisherman")) {
			if (Main.cfg_kits.getBoolean("kits.fisherman.desc")) {
				inv.addItem(ItemAPI.Criar(Material.FISHING_ROD, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.fisherman.gui_name")), false,
						Main.cfg_kits.getStringList("kits.fisherman.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.FISHING_ROD, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.fisherman.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.archer")) {
			if (Main.cfg_kits.getBoolean("kits.archer.desc")) {
				inv.addItem(ItemAPI.Criar(Material.BOW, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.archer.gui_name")), false,
						Main.cfg_kits.getStringList("kits.archer.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.BOW, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.archer.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.sonic")) {
			if (Main.cfg_kits.getBoolean("kits.sonic.desc")) {
				inv.addItem(ItemAPI.Criar(Material.LAPIS_BLOCK, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.sonic.gui_name")), false,
						Main.cfg_kits.getStringList("kits.sonic.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.LAPIS_BLOCK, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.sonic.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.deshfire")) {
			if (Main.cfg_kits.getBoolean("kits.deshfire.desc")) {
				inv.addItem(ItemAPI.Criar(Material.REDSTONE_BLOCK, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.gui_name")), false,
						Main.cfg_kits.getStringList("kits.deshfire.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.REDSTONE_BLOCK, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.anchor")) {
			if (Main.cfg_kits.getBoolean("kits.anchor.desc")) {
				inv.addItem(ItemAPI.Criar(Material.ANVIL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.anchor.gui_name")), false,
						Main.cfg_kits.getStringList("kits.anchor.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.ANVIL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.anchor.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.reaper")) {
			if (Main.cfg_kits.getBoolean("kits.reaper.desc")) {
				inv.addItem(ItemAPI.Criar(Material.WOOD_HOE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.reaper.gui_name")), false,
						Main.cfg_kits.getStringList("kits.reaper.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.WOOD_HOE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.reaper.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.phantom")) {
			if (Main.cfg_kits.getBoolean("kits.phantom.desc")) {
				inv.addItem(ItemAPI.Criar(Material.FEATHER, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.phantom.gui_name")), false,
						Main.cfg_kits.getStringList("kits.phantom.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.FEATHER, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.phantom.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.urgal")) {
			if (Main.cfg_kits.getBoolean("kits.urgal.desc")) {
				inv.addItem(ItemAPI.Criar(Material.POTION, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.urgal.gui_name")), false,
						Main.cfg_kits.getStringList("kits.urgal.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.POTION, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.urgal.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.grandpa")) {
			if (Main.cfg_kits.getBoolean("kits.grandpa.desc")) {
				inv.addItem(ItemAPI.Criar(Material.STICK, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.grandpa.gui_name")), false,
						Main.cfg_kits.getStringList("kits.grandpa.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.STICK, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.grandpa.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.snail")) {
			if (Main.cfg_kits.getBoolean("kits.snail.desc")) {
				inv.addItem(ItemAPI.Criar(Material.STRING, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.snail.gui_name")), false,
						Main.cfg_kits.getStringList("kits.snail.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.STRING, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.snail.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.backpacker")) {
			if (Main.cfg_kits.getBoolean("kits.backpacker.desc")) {
				inv.addItem(ItemAPI.Criar(Material.LEATHER, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.gui_name")), false,
						Main.cfg_kits.getStringList("kits.backpacker.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.LEATHER, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.hulk")) {
			if (Main.cfg_kits.getBoolean("kits.hulk.desc")) {
				inv.addItem(
						ItemAPI.Criar(Material.BONE, 1, 0, Mensagens.cor(Main.cfg_kits.getString("kits.hulk.gui_name")),
								false, Main.cfg_kits.getStringList("kits.hulk.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.BONE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.hulk.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.monk")) {
			if (Main.cfg_kits.getBoolean("kits.monk.desc")) {
				inv.addItem(ItemAPI.Criar(Material.BLAZE_ROD, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.monk.gui_name")), false,
						Main.cfg_kits.getStringList("kits.monk.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.BLAZE_ROD, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.monk.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.stomper")) {
			if (Main.cfg_kits.getBoolean("kits.stomper.desc")) {
				inv.addItem(ItemAPI.Criar(Material.IRON_BOOTS, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.stomper.gui_name")), false,
						Main.cfg_kits.getStringList("kits.stomper.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.IRON_BOOTS, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.stomper.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.viper")) {
			if (Main.cfg_kits.getBoolean("kits.viper.desc")) {
				inv.addItem(ItemAPI.Criar(Material.SPIDER_EYE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.viper.gui_name")), false,
						Main.cfg_kits.getStringList("kits.viper.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.SPIDER_EYE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.viper.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.poseidon")) {
			if (Main.cfg_kits.getBoolean("kits.poseidon.desc")) {
				inv.addItem(ItemAPI.Criar(Material.WATER_BUCKET, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.poseidon.gui_name")), false,
						Main.cfg_kits.getStringList("kits.poseidon.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.WATER_BUCKET, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.poseidon.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.fireman")) {
			if (Main.cfg_kits.getBoolean("kits.fireman.desc")) {
				inv.addItem(ItemAPI.Criar(Material.FIREBALL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.fireman.gui_name")), false,
						Main.cfg_kits.getStringList("kits.fireman.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.FIREBALL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.fireman.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.switcher")) {
			if (Main.cfg_kits.getBoolean("kits.switcher.desc")) {
				inv.addItem(ItemAPI.Criar(Material.SNOW_BALL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.switcher.gui_name")), false,
						Main.cfg_kits.getStringList("kits.switcher.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.SNOW_BALL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.switcher.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.ninja")) {
			if (Main.cfg_kits.getBoolean("kits.ninja.desc")) {
				inv.addItem(ItemAPI.Criar(Material.NETHER_STAR, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.ninja.gui_name")), false,
						Main.cfg_kits.getStringList("kits.ninja.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.NETHER_STAR, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.ninja.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.ryu")) {
			if (Main.cfg_kits.getBoolean("kits.ryu.desc")) {
				inv.addItem(ItemAPI.Criar(Material.BEACON, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.ryu.gui_name")), false,
						Main.cfg_kits.getStringList("kits.ryu.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.BEACON, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.ryu.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.berserker")) {
			if (Main.cfg_kits.getBoolean("kits.berserker.desc")) {
				inv.addItem(ItemAPI.Criar(Material.FERMENTED_SPIDER_EYE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.berserker.gui_name")), false,
						Main.cfg_kits.getStringList("kits.berserker.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.FERMENTED_SPIDER_EYE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.berserker.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.gladiator")) {
			if (Main.cfg_kits.getBoolean("kits.gladiator.desc")) {
				inv.addItem(ItemAPI.Criar(Material.IRON_INGOT, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.gui_name")), false,
						Main.cfg_kits.getStringList("kits.gladiator.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.IRON_INGOT, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.souper")) {
			if (Main.cfg_kits.getBoolean("kits.souper.desc")) {
				inv.addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.souper.gui_name")), false,
						Main.cfg_kits.getStringList("kits.souper.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.souper.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.camel")) {
			if (Main.cfg_kits.getBoolean("kits.camel.desc")) {
				inv.addItem(ItemAPI.Criar(Material.SAND, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.camel.gui_name")), false,
						Main.cfg_kits.getStringList("kits.camel.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.SAND, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.camel.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.neurotic")) {
			if (Main.cfg_kits.getBoolean("kits.neurotic.desc")) {
				inv.addItem(ItemAPI.Criar(Material.SLIME_BALL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.neurotic.gui_name")), false,
						Main.cfg_kits.getStringList("kits.neurotic.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.SLIME_BALL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.neurotic.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.vaccum")) {
			if (Main.cfg_kits.getBoolean("kits.vaccum.desc")) {
				inv.addItem(ItemAPI.Criar(Material.ENDER_PEARL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.vaccum.gui_name")), false,
						Main.cfg_kits.getStringList("kits.vaccum.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.ENDER_PEARL, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.vaccum.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.ajnin")) {
			if (Main.cfg_kits.getBoolean("kits.ajnin.desc")) {
				inv.addItem(ItemAPI.Criar(Material.INK_SACK, 1, 7,
						Mensagens.cor(Main.cfg_kits.getString("kits.ajnin.gui_name")), false,
						Main.cfg_kits.getStringList("kits.ajnin.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.INK_SACK, 1, 7,
						Mensagens.cor(Main.cfg_kits.getString("kits.ajnin.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.forcefield")) {
			if (Main.cfg_kits.getBoolean("kits.forcefield.desc")) {
				inv.addItem(ItemAPI.Criar(Material.IRON_PLATE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.forcefield.gui_name")), false,
						Main.cfg_kits.getStringList("kits.forcefield.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.IRON_PLATE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.forcefield.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.neo")) {
			if (Main.cfg_kits.getBoolean("kits.neo.desc")) {
				inv.addItem(
						ItemAPI.Criar(Material.ARROW, 1, 0, Mensagens.cor(Main.cfg_kits.getString("kits.neo.gui_name")),
								false, Main.cfg_kits.getStringList("kits.neo.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.ARROW, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.neo.gui_name")), false));
			}
		}
		if (!p.hasPermission("kitpvp.kit.frosty")) {
			if (Main.cfg_kits.getBoolean("kits.frosty.desc")) {
				inv.addItem(ItemAPI.Criar(Material.PACKED_ICE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.frosty.gui_name")), false,
						Main.cfg_kits.getStringList("kits.frosty.gui_desc")));
			} else {
				inv.addItem(ItemAPI.Criar(Material.PACKED_ICE, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.frosty.gui_name")), false));
			}
		}
		ItemStack[] arrayOfItemStack;
		int i = (arrayOfItemStack = inv.getContents()).length;
		for (int i2 = 0; i2 < i; i2++) {
			ItemStack item = arrayOfItemStack[i2];
			if (item == null) {
				inv.setItem(inv.firstEmpty(), ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 7, " ", false));
			}
		}
		p.openInventory(inv);
	}

	public static void oKits2(Player p) {
		Inventory inv = Bukkit.getServer().createInventory(p, 54,
				Mensagens.cor(Main.cfg_kits.getString("kits.gui.other_kits")));
		ItemStack vidro = ItemAPI.Criar(Material.IRON_FENCE, 1, 0, " ", false);
		for (int i = 0; i < 9; i++) {
			inv.setItem(i, vidro);
		}
		inv.setItem(0, ItemAPI.Criar(Material.CARPET, 1, 5, "§2«", false));
		inv.setItem(31, ItemAPI.Criar(Material.SKULL_ITEM, 1, 0,
				Mensagens.cor(Main.cfg_kits.getString("kits.gui.all_kits")), false));
		ItemStack[] arrayOfItemStack;
		int i = (arrayOfItemStack = inv.getContents()).length;
		for (int i2 = 0; i2 < i; i2++) {
			ItemStack item = arrayOfItemStack[i2];
			if (item == null) {
				inv.setItem(inv.firstEmpty(), ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 7, " ", false));
			}
		}
		p.openInventory(inv);
	}

	public static void rank(Player p) {
		Inventory inv = Bukkit.getServer().createInventory(p, 27, "§bRanks");
		ItemStack vidro = ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 0, " ", false);
		for (int i = 0; i < 27; i++) {
			inv.setItem(i, vidro);
		}
		inv.setItem(11,
				ItemAPI.Criar(Material.PRISMARINE_CRYSTALS, 1, 0, "§a§lRanks:", false, Main.cfg_rank.getStringList("ranks.gui.ranks")));
		inv.setItem(15, ItemAPI.Criar(Material.GLOWSTONE_DUST, 1, 0, "§a§lSee rank:", false, Main.cfg_rank.getStringList("ranks.gui.seerank")));
		try {
			ResultSet rs;
			rs = Main.getMysql().conectar().createStatement()
					.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + p.getName() + "';");
			if (rs.next()) {
				inv.setItem(13, ItemAPI.Criar(Material.PRISMARINE_SHARD, 1, 0, "§a§lYour rank:", false,
						"§7" + Inventarios.rank(rs.getInt("kill"))));
				rs.getStatement().getConnection().close();
			} else {
				inv.setItem(13, ItemAPI.Criar(Material.PRISMARINE_SHARD, 1, 0, "§a§lYour rank:", false, "§7Relogue"));
				rs.getStatement().getConnection().close();
			}

		} catch (SQLException e) {
		}
		p.openInventory(inv);
	}

	public static void loja(Player p) {
		Inventory inv = Bukkit.getServer().createInventory(p, 27, "§aStore");
		ItemStack vidro = ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 0, " ", false);
		for (int i = 0; i < 27; i++) {
			inv.setItem(i, vidro);
		}
		inv.setItem(12, ItemAPI.Criar(Material.GOLD_NUGGET, 1, 0, "§6Kits", false));
		inv.setItem(14, ItemAPI.Criar(Material.DIAMOND, 1, 0, "§bSite", false));
		p.openInventory(inv);
	}

	public static void warps(Player p) {
		Inventory inv = Bukkit.getServer().createInventory(p, 27, "§cWarps");
		ItemStack vidro = ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 0, " ", false);
		for (int i = 0; i < 27; i++) {
			inv.setItem(i, vidro);
		}
		inv.setItem(11, ItemAPI.Criar(Material.GLASS, 1, 0, "§7Fps", false));
		inv.setItem(12, ItemAPI.Criar(Material.IRON_CHESTPLATE, 1, 0, "§7Main", false));
		inv.setItem(13, ItemAPI.Criar(Material.LAVA_BUCKET, 1, 0, "§7Lava", false));
		inv.setItem(14, ItemAPI.Criar(Material.STICK, 1, 0, "§7Knock", false));
		inv.setItem(15, ItemAPI.Criar(Material.BLAZE_ROD, 1, 0, "§71v1", false));
		p.openInventory(inv);
	}

	public static void lojaKits(Player p) {
		Inventory inv = Bukkit.getServer().createInventory(p, 54,
				Mensagens.cor(Main.cfg_store.getString("store.gui.kit_store")));
		ItemStack vidro = ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 0, " ", false);
		for (int i = 0; i < 9; i++) {
			inv.setItem(i, vidro);
		}
		if (!p.hasPermission("kitpvp.kit.pvp")) {
			inv.addItem(ItemAPI.removeAttributes(ItemAPI.Criar(Material.DIAMOND_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.pvp.name")), false,
					Main.cfg_store.getStringList("store.kits.pvp.desc"))));
		}
		if (!p.hasPermission("kitpvp.kit.kangaroo")) {
			inv.addItem(ItemAPI.Criar(Material.FIREWORK, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.kangaroo.name")), false,
					Main.cfg_store.getStringList("store.kits.kangaroo.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.fisherman")) {
			inv.addItem(ItemAPI.Criar(Material.FISHING_ROD, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.fisherman.name")), false,
					Main.cfg_store.getStringList("store.kits.fisherman.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.archer")) {
			inv.addItem(
					ItemAPI.Criar(Material.BOW, 1, 0, Mensagens.cor(Main.cfg_store.getString("store.kits.archer.name")),
							false, Main.cfg_store.getStringList("store.kits.archer.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.sonic")) {
			inv.addItem(ItemAPI.Criar(Material.LAPIS_BLOCK, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.sonic.name")), false,
					Main.cfg_store.getStringList("store.kits.sonic.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.deshfire")) {
			inv.addItem(ItemAPI.Criar(Material.REDSTONE_BLOCK, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.deshfire.name")), false,
					Main.cfg_store.getStringList("store.kits.deshfire.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.anchor")) {
			inv.addItem(ItemAPI.Criar(Material.ANVIL, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.anchor.name")), false,
					Main.cfg_store.getStringList("store.kits.anchor.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.reaper")) {
			inv.addItem(ItemAPI.Criar(Material.WOOD_HOE, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.reaper.name")), false,
					Main.cfg_store.getStringList("store.kits.reaper.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.phantom")) {
			inv.addItem(ItemAPI.Criar(Material.FEATHER, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.phantom.name")), false,
					Main.cfg_store.getStringList("store.kits.phantom.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.urgal")) {
			inv.addItem(ItemAPI.Criar(Material.POTION, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.urgal.name")), false,
					Main.cfg_store.getStringList("store.kits.urgal.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.grandpa")) {
			inv.addItem(ItemAPI.Criar(Material.STICK, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.grandpa.name")), false,
					Main.cfg_store.getStringList("store.kits.grandpa.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.snail")) {
			inv.addItem(ItemAPI.Criar(Material.STRING, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.snail.name")), false,
					Main.cfg_store.getStringList("store.kits.snail.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.backpacker")) {
			inv.addItem(ItemAPI.Criar(Material.LEATHER, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.backpacker.name")), false,
					Main.cfg_store.getStringList("store.kits.backpacker.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.hulk")) {
			inv.addItem(
					ItemAPI.Criar(Material.BONE, 1, 0, Mensagens.cor(Main.cfg_store.getString("store.kits.hulk.name")),
							false, Main.cfg_store.getStringList("store.kits.hulk.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.monk")) {
			inv.addItem(ItemAPI.Criar(Material.BLAZE_ROD, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.monk.name")), false,
					Main.cfg_store.getStringList("store.kits.monk.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.stomper")) {
			inv.addItem(ItemAPI.Criar(Material.IRON_BOOTS, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.stomper.name")), false,
					Main.cfg_store.getStringList("store.kits.stomper.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.viper")) {
			inv.addItem(ItemAPI.Criar(Material.SPIDER_EYE, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.viper.name")), false,
					Main.cfg_store.getStringList("store.kits.viper.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.poseidon")) {
			inv.addItem(ItemAPI.Criar(Material.WATER_BUCKET, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.poseidon.name")), false,
					Main.cfg_store.getStringList("store.kits.poseidon.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.fireman")) {
			inv.addItem(ItemAPI.Criar(Material.FIREBALL, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.fireman.name")), false,
					Main.cfg_store.getStringList("store.kits.fireman.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.swticher")) {
			inv.addItem(ItemAPI.Criar(Material.SNOW_BALL, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.swticher.name")), false,
					Main.cfg_store.getStringList("store.kits.swticher.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.ninja")) {
			inv.addItem(ItemAPI.Criar(Material.NETHER_STAR, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.ninja.name")), false,
					Main.cfg_store.getStringList("store.kits.ninja.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.ryu")) {
			inv.addItem(
					ItemAPI.Criar(Material.BEACON, 1, 0, Mensagens.cor(Main.cfg_store.getString("store.kits.ryu.name")),
							false, Main.cfg_store.getStringList("store.kits.ryu.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.berserker")) {
			inv.addItem(ItemAPI.Criar(Material.FERMENTED_SPIDER_EYE, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.berserker.name")), false,
					Main.cfg_store.getStringList("store.kits.berserker.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.gladiator")) {
			inv.addItem(ItemAPI.Criar(Material.IRON_INGOT, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.gladiator.name")), false,
					Main.cfg_store.getStringList("store.kits.gladiator.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.souper")) {
			inv.addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.souper.name")), false,
					Main.cfg_store.getStringList("store.kits.souper.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.camel")) {
			inv.addItem(
					ItemAPI.Criar(Material.SAND, 1, 0, Mensagens.cor(Main.cfg_store.getString("store.kits.camel.name")),
							false, Main.cfg_store.getStringList("store.kits.camel.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.neurotic")) {
			inv.addItem(ItemAPI.Criar(Material.SLIME_BALL, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.neurotic.name")), false,
					Main.cfg_store.getStringList("store.kits.neurotic.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.vaccum")) {
			inv.addItem(ItemAPI.Criar(Material.ENDER_PEARL, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.vaccum.name")), false,
					Main.cfg_store.getStringList("store.kits.vaccum.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.ajnin")) {
			inv.addItem(ItemAPI.Criar(Material.INK_SACK, 1, 7,
					Mensagens.cor(Main.cfg_store.getString("store.kits.ajnin.name")), false,
					Main.cfg_store.getStringList("store.kits.ajnin.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.forcefield")) {
			inv.addItem(ItemAPI.Criar(Material.IRON_PLATE, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.forcefield.name")), false,
					Main.cfg_store.getStringList("store.kits.forcefield.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.neo")) {
			inv.addItem(
					ItemAPI.Criar(Material.ARROW, 1, 0, Mensagens.cor(Main.cfg_store.getString("store.kits.neo.name")),
							false, Main.cfg_store.getStringList("store.kits.neo.desc")));
		}
		if (!p.hasPermission("kitpvp.kit.frosty")) {
			inv.addItem(ItemAPI.Criar(Material.PACKED_ICE, 1, 0,
					Mensagens.cor(Main.cfg_store.getString("store.kits.frosty.name")), false,
					Main.cfg_store.getStringList("store.kits.frosty.desc")));
		}
		ItemStack[] arrayOfItemStack;
		int i = (arrayOfItemStack = inv.getContents()).length;
		for (int i2 = 0; i2 < i; i2++) {
			ItemStack item = arrayOfItemStack[i2];
			if (item == null) {
				inv.setItem(inv.firstEmpty(), ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 7, " ", false));
			}
		}
		p.openInventory(inv);
	}

	public static void lojaKits2(Player p) {
		Inventory inv = Bukkit.getServer().createInventory(p, 54,
				Mensagens.cor(Main.cfg_store.getString("store.gui.kit_store")));
		ItemStack vidro = ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 0, " ", false);
		for (int i = 0; i < 9; i++) {
			inv.setItem(i, vidro);
		}
		inv.setItem(31, ItemAPI.Criar(Material.SKULL_ITEM, 1, 0,
				Mensagens.cor(Main.cfg_kits.getString("kits.gui.all_kits")), false));
		ItemStack[] arrayOfItemStack;
		int i = (arrayOfItemStack = inv.getContents()).length;
		for (int i2 = 0; i2 < i; i2++) {
			ItemStack item = arrayOfItemStack[i2];
			if (item == null) {
				inv.setItem(inv.firstEmpty(), ItemAPI.Criar(Material.STAINED_GLASS_PANE, 1, 7, " ", false));
			}
		}
		p.openInventory(inv);
	}

}
