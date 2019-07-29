package me.douglasamv.kitpvp.kits;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Archer implements Listener {

	@EventHandler
	void sexuals2(PlayerInteractEvent e) {
		if (ProtecaoSpawn.protegido(e.getPlayer())) {
			if (e.getPlayer().getItemInHand().getType() == Material.BOW) {
				if (HabilidadeApi.verHB(e.getPlayer()).equalsIgnoreCase("archer")) {
					e.setCancelled(true);
				}
			}
		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.archer.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.archer.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.archer.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.archer.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.archer.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.archer.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.archer.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.archer.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.archer.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.archer.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.archer.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.archer.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.archer.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.archer.armor_name")), true));
		}

		p.getInventory().setItem(1, ItemAPI.Criar(Material.BOW, 1, 0,
				Mensagens.cor(Main.cfg_kits.getString("kits.archer.bow")), true, Enchantment.ARROW_INFINITE, 1));
		p.getInventory().setItem(9, ItemAPI.Criar(Material.ARROW, 1, 0,
				Mensagens.cor(Main.cfg_kits.getString("kits.archer.arrow")), false));
		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
