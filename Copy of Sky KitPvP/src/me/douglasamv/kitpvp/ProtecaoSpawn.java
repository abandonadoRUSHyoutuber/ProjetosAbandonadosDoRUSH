package me.douglasamv.kitpvp;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.douglasamv.kitpvp.kits.PvP;
import me.douglasamv.kitpvp.utils.HabilidadeApi;

public class ProtecaoSpawn implements Listener {

	public static ArrayList<Player> protegidos = new ArrayList<>();
	public static ArrayList<Player> Fogo = new ArrayList<>();
	int raio = Main.cfg_spawn.getInt("SpawnProtectionRatio");

	public static boolean protegido(Player p) {
		if (protegidos.contains(p)) {
			return false;
		} else {
			return true;
		}
	}

	@EventHandler
	void morrer(PlayerDeathEvent e) {
		if(!Main.cfg_spawn.getBoolean("SpawnTrue")) return;
		if (!(e.getEntity().getPlayer() instanceof Player))
			return;
		if (HabilidadeApi.verHB(e.getEntity().getPlayer()).equalsIgnoreCase(Main.cfg_x1.getString("x1.ability")))
			return;
		if (protegidos.contains(e.getEntity().getPlayer())) {
			protegidos.remove(e.getEntity().getPlayer());
			e.getEntity().getPlayer().sendMessage(Main.cfg_spawn.getString("SpawnWon").replace("&", "§"));
		}
		if (Fogo.contains(e.getEntity().getPlayer())) {
			Fogo.remove(e.getEntity().getPlayer());
		}
	}

	@EventHandler
	void sair(PlayerQuitEvent e) {
		if(!Main.cfg_spawn.getBoolean("SpawnTrue")) return;
		if (protegidos.contains(e.getPlayer())) {
			protegidos.remove(e.getPlayer());
		}
		if (Fogo.contains(e.getPlayer())) {
			Fogo.remove(e.getPlayer());
		}
	}

	@EventHandler
	void kick(PlayerKickEvent e) {
		if(!Main.cfg_spawn.getBoolean("SpawnTrue")) return;
		if (protegidos.contains(e.getPlayer())) {
			protegidos.remove(e.getPlayer());
		}
		if (Fogo.contains(e.getPlayer())) {
			Fogo.remove(e.getPlayer());
		}
	}

	@EventHandler
	public void dano(EntityDamageByEntityEvent e) {
		if(!Main.cfg_spawn.getBoolean("SpawnTrue")) return;
		if (!(e.getEntity() instanceof Player))
			return;
		if (e.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) e.getDamager();
			if (arrow.getShooter() instanceof Player) {
				if (arrow.getShooter().equals(e.getEntity())) {
					e.setCancelled(true);
				}
				if (!protegidos.contains(e.getEntity())) {
					e.setCancelled(true);
				}
			}
		}
		Material material = Material.FISHING_ROD;
		if (material == Material.FISHING_ROD) {
			if (!protegidos.contains(e.getEntity())) {
				e.setCancelled(true);
			}

		}
		if (e.getDamager() instanceof FishHook) {
			FishHook fish = (FishHook) e.getDamager();
			if (fish.getShooter().equals(e.getEntity())) {
				e.setCancelled(true);
			}
		}
		if (!(e.getEntity() instanceof Player))
			return;
		if (!(e.getDamager() instanceof Player))
			return;
		if (Fogo.contains(e.getEntity())) {
			e.setCancelled(true);
			return;
		}
		if (Fogo.contains(e.getDamager())) {
			e.setCancelled(true);
			return;
		}
		if (!protegidos.contains(e.getEntity())) {
			e.setCancelled(true);
		}
		if (!protegidos.contains(e.getDamager())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void mexer(PlayerMoveEvent e) {
		if(!Main.cfg_spawn.getBoolean("SpawnTrue")) return;
		if (protegidos.contains(e.getPlayer()))
			return;
		Player p = e.getPlayer();
		if (p.getLocation().getBlockX() > Bukkit.getWorld("world").getSpawnLocation().getBlockX() + raio) {
			p.sendMessage(Main.cfg_spawn.getString("SpawnLost").replace("&", "§"));
			protegidos.add(p);
			if (HabilidadeApi.semHB(p)) {
				PvP.darKit(p);
			}
		}
		if (p.getLocation().getBlockX() < -(raio - Bukkit.getWorld("world").getSpawnLocation().getBlockX())) {
			p.sendMessage(Main.cfg_spawn.getString("SpawnLost").replace("&", "§"));
			protegidos.add(p);
			if (HabilidadeApi.semHB(p)) {
				PvP.darKit(p);
			}
		}
		if (p.getLocation().getBlockZ() > Bukkit.getWorld("world").getSpawnLocation().getBlockZ() + raio) {
			p.sendMessage(Main.cfg_spawn.getString("SpawnLost").replace("&", "§"));
			protegidos.add(p);
			if (HabilidadeApi.semHB(p)) {
				PvP.darKit(p);
			}
		}
		if (p.getLocation().getBlockZ() < -(raio - Bukkit.getWorld("world").getSpawnLocation().getBlockZ())) {
			p.sendMessage(Main.cfg_spawn.getString("SpawnLost").replace("&", "§"));
			protegidos.add(p);
			if (HabilidadeApi.semHB(p)) {
				PvP.darKit(p);
			}
		}
	}

}
