package me.douglasamv.kitpvp.kits;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.CoolDownAPI;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Phantom implements Listener {

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
		if (!HabilidadeApi.verHB(p).equalsIgnoreCase(Main.cfg_kits.getString("kits.phantom.ability")))
			return;
		if (i.getType() == Material.FEATHER) {
			if (e.getAction().equals(Action.LEFT_CLICK_AIR))
				return;
			if (e.getAction().equals(Action.LEFT_CLICK_BLOCK))
				return;

			if (ProtecaoSpawn.protegido(p)) {
				p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.phantom.msg_spawn")));
				return;
			}

			if (CoolDownAPI.isOnCooldown(p)) {
				p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.phantom.msg_cooldown")));
				return;
			}
			p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.phantom.msg_kit_use")));
			p.setAllowFlight(true);
			p.setFlying(true);

			ItemStack item2 = new ItemStack(Material.LEATHER_HELMET);
			ItemMeta item2M = item2.getItemMeta();
			((LeatherArmorMeta) item2M).setColor(Color.WHITE);
			item2M.spigot().setUnbreakable(true);
			item2.setItemMeta(item2M);
			p.getInventory().setHelmet(item2);

			ItemStack item3 = new ItemStack(Material.LEATHER_CHESTPLATE);
			ItemMeta item3M = item3.getItemMeta();
			((LeatherArmorMeta) item3M).setColor(Color.WHITE);
			item3M.spigot().setUnbreakable(true);
			item3.setItemMeta(item3M);
			p.getInventory().setChestplate(item3);

			ItemStack item4 = new ItemStack(Material.LEATHER_LEGGINGS);
			ItemMeta item4M = item4.getItemMeta();
			((LeatherArmorMeta) item4M).setColor(Color.WHITE);
			item4M.spigot().setUnbreakable(true);
			item4.setItemMeta(item4M);
			p.getInventory().setLeggings(item4);

			ItemStack item5 = new ItemStack(Material.LEATHER_BOOTS);
			ItemMeta item5M = item5.getItemMeta();
			((LeatherArmorMeta) item5M).setColor(Color.WHITE);
			item5M.spigot().setUnbreakable(true);
			item5.setItemMeta(item5M);
			p.getInventory().setBoots(item5);
			players.add(p);
			CoolDownAPI.putCooldown(p, 45);
			new BukkitRunnable() {

				@Override
				public void run() {
					if (!p.isOnline())
						return;
					if (!players.contains(p))
						return;
					players.remove(p);
					p.getInventory().setArmorContents(null);
					p.setAllowFlight(false);
					p.setFlying(false);
					if (Main.cfg_kits.getBoolean("kits.phantom.armor")) {
						p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
								Mensagens.cor(Main.cfg_kits.getString("kits.deshfire.armor_name")), true));
					}
				}
			}.runTaskLater(Main.pl, 20 * 6);
		}
	}

	@EventHandler
	public void dano(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		Player p = (Player) e.getEntity();
		if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
			if (players.contains(p)) {
				e.setCancelled(true);
			}
		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.phantom.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.phantom.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.phantom.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.phantom.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.phantom.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.phantom.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.phantom.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.phantom.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.phantom.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.phantom.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.phantom.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.phantom.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.phantom.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.phantom.armor_name")), true));
		}

		p.getInventory().setItem(1, ItemAPI.Criar(Material.FEATHER, 1, 0,
				Mensagens.cor(Main.cfg_kits.getString("kits.phantom.kit_item")), false));
		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
