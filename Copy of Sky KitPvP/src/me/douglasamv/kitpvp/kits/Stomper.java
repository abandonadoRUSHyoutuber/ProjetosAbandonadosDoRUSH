package me.douglasamv.kitpvp.kits;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Stomper implements Listener {

	@EventHandler
	void StomperFall(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		Player p = (Player) e.getEntity();
		if ((e.getCause() == EntityDamageEvent.DamageCause.FALL)
				&& (HabilidadeApi.verHB(p).equalsIgnoreCase(Main.cfg_kits.getString("kits.stomper.ability")))) {
			List<Entity> entity = p.getNearbyEntities(8.0D, 5.0D, 8.0D);
			for (Entity en : entity) {
				if (en instanceof Player) {
					Player stompados = (Player) en;
					if (ProtecaoSpawn.protegido(stompados)) {
						e.setDamage(4.0D);
						return;
					}
					if (stompados.isSneaking()) {
						stompados.damage(4.0D);
						stompados.playSound(p.getLocation(), Sound.ANVIL_BREAK, 4.0F, 4.0F);
						p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 4.0F, 4.0F);
					} else {
						stompados.damage(p.getFallDistance() - 8);
						stompados.playSound(p.getLocation(), Sound.ANVIL_BREAK, 4.0F, 4.0F);
						p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 4.0F, 4.0F);
						if (stompados.isDead()) {
							try {
								ResultSet rs = Main.getMysql().conectar().createStatement()
										.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + p.getName() + "';");
								if (rs.next()) {
									Main.getMysql().conectar().createStatement()
											.executeUpdate("UPDATE `pvp` SET `kill`='"
													+ String.valueOf((rs.getInt("kill") + 1)) + "' WHERE `nick`='"
													+ p.getName() + "';");
									Main.getMysql().conectar().createStatement()
											.executeUpdate("UPDATE `pvp` SET `money`='"
													+ String.valueOf((rs.getInt("money") + 100)) + "' WHERE `nick`='"
													+ p.getName() + "';");
								}
								rs.getStatement().getConnection().close();

								ResultSet rs1 = Main.getMysql().conectar().createStatement().executeQuery(
										"SELECT * FROM `pvp` WHERE `nick`='" + stompados.getName() + "';");
								if (rs1.next()) {
									Main.getMysql().conectar().createStatement()
											.executeUpdate("UPDATE `pvp` SET `death`='"
													+ String.valueOf((rs1.getInt("death") + 1)) + "' WHERE `nick`='"
													+ stompados.getName() + "';");
									Main.getMysql().conectar().createStatement()
											.executeUpdate("UPDATE `pvp` SET `money`='"
													+ String.valueOf((rs1.getInt("money") - 25)) + "' WHERE `nick`='"
													+ stompados.getName() + "';");
								}
								rs1.getStatement().getConnection().close();

							} catch (SQLException e1) {
							}
							Inventarios.upDateScore(p);
							Inventarios.upDateScore(stompados);
							p.sendMessage(Mensagens.cor(
									Main.pl.getConfig().getString("killed").replace("$player$", stompados.getName())));
							stompados.sendMessage(Mensagens
									.cor(Main.pl.getConfig().getString("killer").replace("$player$", p.getName())));
						}
					}
				}
			}
			if (e.getDamage() > 4.0D) {
				e.setDamage(4.0D);
			}
		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.stomper.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.stomper.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.stomper.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.stomper.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.stomper.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.stomper.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.stomper.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.stomper.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.stomper.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.stomper.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.stomper.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.stomper.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.stomper.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.stomper.armor_name")), true));
		}

		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
