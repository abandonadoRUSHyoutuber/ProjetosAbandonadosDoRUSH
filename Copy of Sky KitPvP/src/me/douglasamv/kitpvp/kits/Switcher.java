package me.douglasamv.kitpvp.kits;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Switcher implements Listener {

	@EventHandler
	void clicar(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().getData().getItemType() == Material.SNOW_BALL) {
			if (HabilidadeApi.verHB(p).equalsIgnoreCase(Main.cfg_kits.getString("kits.switcher.ability"))) {
				if (ProtecaoSpawn.protegido(p)) {
					e.setCancelled(true);
					p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.switcher.msg_spawn")));
					return;
				}
			}
			return;
		}
	}

	@EventHandler
	void switcher(EntityDamageByEntityEvent e) {
		Entity ent = e.getEntity();
		Entity damager = e.getDamager();
		if (ent instanceof Player) {
			Player hit = (Player) ent;
			if (damager instanceof Snowball) {
				Snowball snowball = (Snowball) damager;
				if (snowball.getShooter() instanceof Player) {
					Player shooter = (Player) snowball.getShooter();
					if (HabilidadeApi.verHB(shooter)
							.equalsIgnoreCase(Main.cfg_kits.getString("kits.switcher.ability"))) {
						if (ProtecaoSpawn.protegido(hit)) {
							e.setCancelled(true);
							return;
						}
						Location ploc = shooter.getLocation();
						Location hitloc = hit.getLocation();
						shooter.teleport(hitloc);
						hit.teleport(ploc);
					}
				}
			}
		}

	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.switcher.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.switcher.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.switcher.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.switcher.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.switcher.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.switcher.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.switcher.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.switcher.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.switcher.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.switcher.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.switcher.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.switcher.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.switcher.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.switcher.armor_name")), true));
		}

		p.getInventory().setItem(1, ItemAPI.Criar(Material.SNOW_BALL, Main.cfg_kits.getInt("kits.switcher.kit_amount"), 0,
				Mensagens.cor(Main.cfg_kits.getString("kits.switcher.kit_item")), false));
		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
