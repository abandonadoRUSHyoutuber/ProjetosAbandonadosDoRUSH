package me.douglasamv.kitpvp.kits;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Kangaroo implements Listener {

	ArrayList<Player> kangaroo = new ArrayList<Player>();
	ArrayList<Player> cos = new ArrayList<Player>();
	HashMap<Player, Integer> jumped = new HashMap<Player, Integer>();

	@EventHandler
	void interact(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if ((HabilidadeApi.verHB(p).equalsIgnoreCase(Main.cfg_kits.getString("kits.kangaroo.ability")))
				&& (p.getItemInHand().getType() == Material.FIREWORK)) {
			e.setCancelled(true);
			if (ProtecaoSpawn.protegido(p)) {
				p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.kangaroo.msg_spawn")));
				return;
			}
			if ((!cos.contains(p)) && (!jumped.containsKey(p))) {
				if (!p.isSneaking()) {
					if (!((CraftPlayer) p).getHandle().onGround) {
						jumped.put(p, Integer.valueOf(1));
						p.setVelocity(new Vector(p.getVelocity().getX(), 1.0D, p.getVelocity().getZ()));
					} else {
						p.setVelocity(new Vector(p.getVelocity().getX(), 1.0D, p.getVelocity().getZ()));
					}
				} else if (!((CraftPlayer) p).getHandle().onGround) {
					p.setVelocity(p.getLocation().getDirection().multiply(1.2D));
					p.setVelocity(new Vector(p.getVelocity().getX(), 0.5D, p.getVelocity().getZ()));
					jumped.put(p, Integer.valueOf(1));
				} else {
					p.setVelocity(p.getLocation().getDirection().multiply(1.2D));
					p.setVelocity(new Vector(p.getVelocity().getX(), 0.5D, p.getVelocity().getZ()));
				}
			}
		}
	}

	@EventHandler
	void landed(PlayerMoveEvent e) {
		if ((e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR)
				&& (jumped.containsKey(e.getPlayer()))) {
			jumped.remove(e.getPlayer());
		}
	}

	@EventHandler
	public void onDamag123e(EntityDamageEvent e) {
		Entity et = e.getEntity();
		if (et instanceof Player) {
			Player player = (Player) et;
			if (((e.getEntity() instanceof Player)) && (e.getCause() == EntityDamageEvent.DamageCause.FALL)
					&& (HabilidadeApi.verHB(player).equalsIgnoreCase(Main.cfg_kits.getString("kits.kangaroo.ability")))
					&& (e.getDamage() >= 7.0D)) {
				e.setDamage(7.0D);
			}
		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.kangaroo.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.kangaroo.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.kangaroo.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.kangaroo.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.kangaroo.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.kangaroo.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.kangaroo.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.kangaroo.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.kangaroo.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.kangaroo.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.kangaroo.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.kangaroo.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.kangaroo.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.kangaroo.armor_name")), true));
		}
		p.getInventory().setItem(1, ItemAPI.Criar(Material.FIREWORK, 1, 0,
				Mensagens.cor(Main.cfg_kits.getString("kits.kangaroo.kit_item")), false));
		p.updateInventory();
		Inventarios.upDateScore(p);
	}

}
