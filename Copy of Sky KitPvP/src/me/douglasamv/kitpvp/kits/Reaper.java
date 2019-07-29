package me.douglasamv.kitpvp.kits;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Reaper implements Listener {

	@EventHandler
	void bater(EntityDamageByEntityEvent e) {
		if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player))) {
			Player damager = (Player) e.getDamager();
			Player victim = (Player) e.getEntity();
			if (HabilidadeApi.verHB(damager).equalsIgnoreCase(Main.cfg_kits.getString("kits.reaper.ability"))) {
				if (damager.getInventory().getItemInHand().getType() == Material.WOOD_HOE) {
					if (ProtecaoSpawn.protegido(victim))
						return;
					if (ProtecaoSpawn.protegido(damager))
						return;
					victim.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 3 * 20, 3));
					return;
				}
			}
		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.reaper.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.reaper.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.reaper.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.reaper.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.reaper.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.reaper.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.reaper.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.reaper.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.reaper.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.reaper.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.reaper.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.reaper.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.reaper.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.reaper.armor_name")), true));
		}

		p.getInventory().setItem(1, ItemAPI.Criar(Material.WOOD_HOE, 1, 0,
				Mensagens.cor(Main.cfg_kits.getString("kits.reaper.kit_item")), false));
		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
