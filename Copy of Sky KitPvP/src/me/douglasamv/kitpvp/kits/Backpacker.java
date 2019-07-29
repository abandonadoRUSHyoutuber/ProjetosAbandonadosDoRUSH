package me.douglasamv.kitpvp.kits;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.CoolDownAPI;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Backpacker implements Listener {

	@EventHandler
	void clicar(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().isSimilar(ItemAPI.Criar(Material.LEATHER, 1, 0, Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.kit_item")), false))) {
			if (HabilidadeApi.verHB(p).equalsIgnoreCase(Main.cfg_kits.getString("kits.backpacker.ability"))) {
				if (ProtecaoSpawn.protegido(p)) {
					p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.msg_spawn")));
					return;
				}

				if (CoolDownAPI.isOnCooldown(p)) {
					p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.msg_cooldown")));
					return;
				}
				CoolDownAPI.putCooldown(p, 60);
				Inventory inv = Bukkit.getServer().createInventory(p, 27, Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.kit_gui")));
				for (int i = 0; i < 27; i++) {
					inv.addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0, Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.kit_soup")), false));
				}
				p.openInventory(inv);
			}
			return;
		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.backpacker.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.backpacker.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.backpacker.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.backpacker.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.backpacker.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.armor_name")), true));
		}

		p.getInventory().setItem(1, ItemAPI.Criar(Material.LEATHER, 1, 0,
				Mensagens.cor(Main.cfg_kits.getString("kits.backpacker.kit_item")), false));
		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
