package me.douglasamv.kitpvp.kits;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Neo implements Listener {

	@EventHandler
	public void dano(EntityDamageByEntityEvent e) {
		if (e.getCause() != EntityDamageEvent.DamageCause.PROJECTILE)
			return;
		Projectile proj = (Projectile) e.getDamager();
		if (!(proj.getShooter() instanceof Player))
			return;
		if (!(e.getEntity() instanceof Player))
			return;
		Entity shot = e.getEntity();
		if (!HabilidadeApi.verHB((Player) shot).equalsIgnoreCase(Main.cfg_kits.getString("kits.neo.ability")))
			return;
		if (ProtecaoSpawn.protegido((Player) shot))
			return;
		e.setCancelled(true);
		((Player) shot).launchProjectile(proj.getClass());
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.neo.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.neo.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.neo.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.neo.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.neo.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.neo.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.neo.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.neo.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.neo.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.neo.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.neo.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.neo.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.neo.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.neo.armor_name")), true));
		}

		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
