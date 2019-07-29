package me.douglasamv.kitpvp.kits;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
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

public class ForceField implements Listener {

	public static ArrayList<Player> ff = new ArrayList<>();

	@EventHandler
	void mexer(PlayerMoveEvent e) {
		if (ff.contains(e.getPlayer())) {
			for (Entity pertos : e.getPlayer().getNearbyEntities(8.0D, 8.0D, 8.0D)) {
				if ((pertos instanceof Player)) {
					Player perto = (Player) pertos;
					if (ProtecaoSpawn.protegido(perto))
						return;
					((Player) pertos).damage(3.0D);
					pertos.setVelocity(new Vector(0.1D, 0.0D, 0.1D));
				}
			}
		}
	}

	@EventHandler
	void clicar(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack i = p.getItemInHand();
		if (!HabilidadeApi.verHB(p).equalsIgnoreCase(Main.cfg_kits.getString("kits.forcefield.ability")))
			return;
		if (i.getType() == Material.IRON_FENCE) {
			if (e.getAction().equals(Action.LEFT_CLICK_AIR))
				return;
			if (e.getAction().equals(Action.LEFT_CLICK_BLOCK))
				return;

			if (ProtecaoSpawn.protegido(p)) {
				p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.forcefield.msg_spawn")));
				return;
			}

			if (CoolDownAPI.isOnCooldown(p)) {
				p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.forcefield.msg_cooldown")));
				return;
			}
			CoolDownAPI.putCooldown(p, 30);
			ff.add(p);
			p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.forcefield.msg_kit_use")));
			new BukkitRunnable() {
				
				@Override
				public void run() {
					ff.remove(p);
				}
			}.runTaskLater(Main.pl, 5*20);
		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.forcefield.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.forcefield.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.forcefield.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.forcefield.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.forcefield.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.forcefield.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.forcefield.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.forcefield.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.forcefield.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.forcefield.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.forcefield.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.forcefield.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.forcefield.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.forcefield.armor_name")), true));
		}
		p.getInventory().setItem(1, ItemAPI.Criar(Material.IRON_FENCE, 1, 0,
				Mensagens.cor(Main.cfg_kits.getString("kits.forcefield.kit_item")), false));
		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
