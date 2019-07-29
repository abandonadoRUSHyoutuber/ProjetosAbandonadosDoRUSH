package me.douglasamv.kitpvp.kits;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Berserker implements Listener {

	@EventHandler
	void irado(PlayerDeathEvent e) {
		if ((e.getEntity().getKiller() instanceof Player)) {
			Player p = e.getEntity().getKiller();
			if (HabilidadeApi.verHB(p).equalsIgnoreCase(Main.cfg_kits.getString("kits.berserker.ability"))) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 0));
				p.playSound(p.getLocation(), Sound.AMBIENCE_THUNDER, 1.0F, 1.0F);
				p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.berserker.msg_angry")));
			}
		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.berserker.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.berserker.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.berserker.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.berserker.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.berserker.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.berserker.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.berserker.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.berserker.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.berserker.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.berserker.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.berserker.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.berserker.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.berserker.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.berserker.armor_name")), true));
		}
		
		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
