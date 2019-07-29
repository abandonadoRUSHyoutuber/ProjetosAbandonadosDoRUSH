package me.douglasamv.kitpvp.kits;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.CoolDownAPI;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Hulk implements Listener {

	@EventHandler
	void husk(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if (e.getRightClicked() instanceof Player) {
			Player d = (Player) e.getRightClicked();
			if ((HabilidadeApi.verHB(p).equalsIgnoreCase(Main.cfg_kits.getString("kits.hulk.ability")))
					&& (p.getItemInHand().getType().equals(Material.AIR))) {
				if (ProtecaoSpawn.protegido(p)) {
					p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.hulk.msg_spawn")));
					return;
				}

				if (CoolDownAPI.isOnCooldown(p)) {
					p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.hulk.msg_cooldown")));
					return;
				}
				if (ProtecaoSpawn.protegido(d))
					return;

				CoolDownAPI.putCooldown(p, 10);
				if (p.getPassenger() == null) {
					p.setPassenger(d);
				} else {
					return;
				}
			}
		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.hulk.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.hulk.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.hulk.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.hulk.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.hulk.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.hulk.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.hulk.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.hulk.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.hulk.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.hulk.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.hulk.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.hulk.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.hulk.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.hulk.armor_name")), true));
		}
		
		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
