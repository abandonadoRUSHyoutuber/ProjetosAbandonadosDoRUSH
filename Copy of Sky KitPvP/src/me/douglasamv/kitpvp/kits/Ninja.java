package me.douglasamv.kitpvp.kits;

import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.CoolDownAPI;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Ninja implements Listener {

	public HashMap<Player, Player> players = new HashMap<Player, Player>();

	@EventHandler
	void ninja(EntityDamageByEntityEvent e) {
		if (((e.getDamager() instanceof Player)) && ((e.getEntity() instanceof Player))) {
			Player p = (Player) e.getDamager();
			Player p2 = (Player) e.getEntity();
			if ((HabilidadeApi.verHB(p).equalsIgnoreCase(Main.cfg_kits.getString("kits.ninja.ability")))) {
				if (players.containsKey(p))
					return;
				if (ProtecaoSpawn.protegido(p2))
					return;
				if (ProtecaoSpawn.protegido(p))
					return;
				players.put(p, p2);
				new BukkitRunnable() {
					@Override
					public void run() {
						players.remove(p);
					}
				}.runTaskLater(Main.pl, 25 * 20);
			}
		}
	}

	@EventHandler
	void morrer(PlayerDeathEvent e) {
		Player matou = e.getEntity().getKiller();
		Player morreu = e.getEntity().getPlayer();
		if (!(e.getEntity() instanceof Player))
			return;
		if (!(e.getEntity().getKiller() instanceof Player))
			return;
		if (players.containsKey(matou)) {
			players.remove(matou);
		}
		if (players.containsKey(morreu)) {
			players.remove(matou);
		}
		if (players.containsValue(matou)) {
			players.values().remove(matou);
		}
		if (players.containsValue(morreu)) {
			players.values().remove(morreu);
		}
	}

	@EventHandler
	void ninjaSneak(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		if ((e.isSneaking()) && ((HabilidadeApi.verHB(p).equalsIgnoreCase(Main.cfg_kits.getString("kits.ninja.ability"))) && (players.containsKey(p)))) {
			Player p2 = (Player) players.get(p);
			if ((p2 != null)) {
				if (ProtecaoSpawn.protegido(p))
					return;

				if (CoolDownAPI.isOnCooldown(p)) {
					p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.ninja.msg_cooldown")));
					return;
				}
				CoolDownAPI.putCooldown(p, 10);
				if (p.getLocation().distance(p2.getLocation()) < 30.0D) {
					p.teleport(p2.getLocation());
					p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.ninja.msg_teleported")));
				} else {
					p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.ninja.msg_hited_far")));
				}
			}
		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.ninja.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.ninja.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.ninja.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.ninja.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.ninja.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.ninja.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.ninja.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.ninja.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.ninja.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.ninja.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.ninja.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.ninja.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.ninja.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.ninja.armor_name")), true));
		}
		
		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
