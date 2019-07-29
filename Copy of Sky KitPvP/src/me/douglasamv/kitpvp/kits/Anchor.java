package me.douglasamv.kitpvp.kits;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Anchor implements Listener {

	@EventHandler
	void bater(EntityDamageByEntityEvent e) {
		if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player))) {
			Player p = (Player) e.getEntity();
			Player d = (Player) e.getDamager();
			if (ProtecaoSpawn.protegido(p))
				return;
			if (ProtecaoSpawn.protegido(d))
				return;
			if (HabilidadeApi.verHB(p).equalsIgnoreCase(Main.cfg_kits.getString("kits.anchor.ability"))) {
				p.setVelocity(new Vector());
				d.setVelocity(new Vector());
				new BukkitRunnable() {

					@Override
					public void run() {
						p.setVelocity(new Vector());
						d.setVelocity(new Vector());
					}
				}.runTaskLater(Main.pl, 1);
			}
			if (HabilidadeApi.verHB(d).equalsIgnoreCase(Main.cfg_kits.getString("kits.anchor.ability"))) {
				p.setVelocity(new Vector());
				d.setVelocity(new Vector());
				new BukkitRunnable() {

					@Override
					public void run() {
						p.setVelocity(new Vector());
						d.setVelocity(new Vector());
					}
				}.runTaskLater(Main.pl, 1);
			}
		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.anchor.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.anchor.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.anchor.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.anchor.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.anchor.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.anchor.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.anchor.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.anchor.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.anchor.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.anchor.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.anchor.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.anchor.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.anchor.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.anchor.armor_name")), true));
		}
		
		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
