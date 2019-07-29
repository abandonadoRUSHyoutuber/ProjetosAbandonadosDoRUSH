package me.douglasamv.kitpvp.kits;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.CoolDownAPI;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Vacuum implements Listener {

	@EventHandler
	void vacuum(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (HabilidadeApi.verHB(p).equalsIgnoreCase(Main.cfg_kits.getString("kits.vaccum.ability"))) {
			if (p.getItemInHand().getType().equals(Material.ENDER_PEARL)) {
				e.setCancelled(true);
				p.updateInventory();
				if (e.getAction().equals(Action.LEFT_CLICK_AIR))
					return;
				if (e.getAction().equals(Action.LEFT_CLICK_BLOCK))
					return;

				if (ProtecaoSpawn.protegido(p)) {
					p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.vaccum.msg_spawn")));
					return;
				}
				if (CoolDownAPI.isOnCooldown(p)) {
					p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.vaccum.msg_cooldown")));
					return;
				}
				CoolDownAPI.putCooldown(p, 10);
				for (Entity nearby : p.getNearbyEntities(15.0D, 15.0D, 15.0D)) {
					Entity targetplayer = nearby;
					if (!(targetplayer instanceof Player))
						return;
					if (!ProtecaoSpawn.protegido((Player) targetplayer))
						return;
					Location lc = targetplayer.getLocation();
					Location to = p.getLocation();
					lc.setY(lc.getY() + 0.5D);
					double g = -0.08D;
					double d = to.distance(lc);
					double t = d;
					double v_x = (1.0D + 0.17D * t) * (to.getX() - lc.getX()) / t;
					double v_y = (1.0D + 0.03D * t) * (to.getY() - lc.getY()) / t - 0.5D * g * t;
					double v_z = (1.0D + 0.17D * t) * (to.getZ() - lc.getZ()) / t;
					Vector v = p.getVelocity();
					v.setX(v_x);
					v.setY(v_y);
					v.setZ(v_z);
					targetplayer.setVelocity(v);
					p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.vaccum.msg_pull")));
				}
			}
		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.vaccum.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.vaccum.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.vaccum.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.vaccum.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.vaccum.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.vaccum.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.vaccum.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.vaccum.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.vaccum.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.vaccum.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.vaccum.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.vaccum.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.vaccum.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.vaccum.armor_name")), true));
		}
		p.getInventory().setItem(1, ItemAPI.Criar(Material.ENDER_PEARL, 1, 0,
				Mensagens.cor(Main.cfg_kits.getString("kits.vaccum.kit_item")), false));
		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
