package me.douglasamv.kitpvp.kits;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.CoolDownAPI;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class DeshFire implements Listener {

	public ArrayList<String> semdano = new ArrayList<>();
	public static ArrayList<Player> players = new ArrayList<>();

	@EventHandler
	void morrer(PlayerDeathEvent e) {
		if (players.contains(e.getEntity().getPlayer())) {
			players.remove(e.getEntity().getPlayer());
		}
	}

	@EventHandler
	public void clicou(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack i = p.getItemInHand();
		if (!HabilidadeApi.verHB(p).equalsIgnoreCase(Main.cfg_kits.getString("kits.deshfire.ability")))
			return;
		if (i.getType() == Material.REDSTONE_BLOCK) {
			if (e.getAction().equals(Action.LEFT_CLICK_AIR))
				return;
			if (e.getAction().equals(Action.LEFT_CLICK_BLOCK))
				return;

			if (ProtecaoSpawn.protegido(p)) {
				p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.msg_spawn")));
				return;
			}

			if (CoolDownAPI.isOnCooldown(p)) {
				p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.msg_cooldown")));
				return;
			}
			p.setVelocity(p.getEyeLocation().getDirection().multiply(5).add(new Vector(0, 0.2D, 0)));

			ItemStack item2 = new ItemStack(Material.LEATHER_HELMET);
			ItemMeta item2M = item2.getItemMeta();
			((LeatherArmorMeta) item2M).setColor(Color.RED);
			item2M.spigot().setUnbreakable(true);
			item2.setItemMeta(item2M);
			p.getInventory().setHelmet(item2);

			ItemStack item3 = new ItemStack(Material.LEATHER_CHESTPLATE);
			ItemMeta item3M = item3.getItemMeta();
			((LeatherArmorMeta) item3M).setColor(Color.RED);
			item3M.spigot().setUnbreakable(true);
			item3.setItemMeta(item3M);
			p.getInventory().setChestplate(item3);

			ItemStack item4 = new ItemStack(Material.LEATHER_LEGGINGS);
			ItemMeta item4M = item4.getItemMeta();
			((LeatherArmorMeta) item4M).setColor(Color.RED);
			item4M.spigot().setUnbreakable(true);
			item4.setItemMeta(item4M);
			p.getInventory().setLeggings(item4);

			ItemStack item5 = new ItemStack(Material.LEATHER_BOOTS);
			ItemMeta item5M = item5.getItemMeta();
			((LeatherArmorMeta) item5M).setColor(Color.RED);
			item5M.spigot().setUnbreakable(true);
			item5.setItemMeta(item5M);
			p.getInventory().setBoots(item5);
			semdano.add(p.getName());
			players.add(p);
			CoolDownAPI.putCooldown(p, 45);
			p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.msg_kit_use")));
			new BukkitRunnable() {

				@Override
				public void run() {
					if (!p.isOnline())
						return;
					if (!players.contains(p))
						return;
					players.remove(p);
					p.getInventory().setArmorContents(null);
					if (Main.cfg_kits.getBoolean("kits.deshfire.armor")) {
						p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
								Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.armor_name")), true));
					}
					semdano.remove(p.getName());
				}
			}.runTaskLater(Main.pl, 80);
		}
	}

	@EventHandler
	public void move(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (semdano.contains(p.getName())) {
			p.getPlayer().getWorld().playEffect(p.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 10, 0);
			for (Entity pertos : p.getNearbyEntities(5.0D, 5.0D, 5.0D)) {
				if ((pertos instanceof Player)) {
					Player perto = (Player) pertos;
					if (!ProtecaoSpawn.protegido(perto)) {
						perto.setFireTicks(20 * 3);
					}

				}
			}
		}
	}

	@EventHandler
	public void dano(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		Player p = (Player) e.getEntity();
		if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
			if (semdano.contains(p.getName())) {
				semdano.remove(p.getName());
				e.setDamage(6.5D);
			}
		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.deshfire.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.deshfire.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.deshfire.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.deshfire.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.deshfire.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.armor_name")), true));
		}

		p.getInventory().setItem(1, ItemAPI.Criar(Material.REDSTONE_BLOCK, 1, 0,
				Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.kit_item")), false));
		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
