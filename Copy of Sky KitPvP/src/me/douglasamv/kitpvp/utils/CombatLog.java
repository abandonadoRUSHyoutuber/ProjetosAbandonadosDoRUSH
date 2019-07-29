package me.douglasamv.kitpvp.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;

public class CombatLog implements Listener {

	public static ArrayList<Player> Sair = new ArrayList<>();

	@EventHandler
	void dano(EntityDamageByEntityEvent e) {
		if (((e.getDamager() instanceof Player)) && ((e.getEntity() instanceof Player))) {
			Player p = (Player) e.getEntity();
			Player hitter = (Player) e.getDamager();
			if ((!Sair.contains(p)) && (!Sair.contains(hitter))) {
				if (ProtecaoSpawn.protegido(p))
					return;
				if (ProtecaoSpawn.protegido(hitter))
					return;
				if (HabilidadeApi.verHB(p).equalsIgnoreCase(Main.cfg_x1.getString("x1.ability")))
					return;
				if (HabilidadeApi.verHB(hitter).equalsIgnoreCase(Main.cfg_x1.getString("x1.ability")))
					return;
				Sair.add(p);
				Sair.add(hitter);
				p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("CombatLogHitted")));
				hitter.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("CombatLogHitter")));
				new BukkitRunnable() {

					@Override
					public void run() {
						Sair.remove(p);
						Sair.remove(hitter);
						p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("CombatLogExit")));
						hitter.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("CombatLogExit")));
					}
				}.runTaskLater(Main.pl, 15 * 20);
			}
		}
	}

	@EventHandler
	void quitar(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (Sair.contains(p)) {
			p.teleport(Bukkit.getWorld("world").getSpawnLocation());
			p.setHealth(0.0D);
			Bukkit.broadcastMessage(
					Mensagens.cor(Main.pl.getConfig().getString("CombatLogQuit").replace("$player$", p.getName())));
		}
	}

	@EventHandler
	void morrer(PlayerDeathEvent e) {
		if (Sair.contains(e.getEntity().getPlayer())) {
			Sair.remove(e.getEntity().getPlayer());
		}
	}

	@EventHandler
	public void cmd(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (!Sair.contains(p))
			return;
		if (e.getMessage().startsWith("/")) {
			if (!e.getPlayer().hasPermission("kitpvp.combatlog.cmdbypass")) {
				e.setCancelled(true);
				p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("CombatLogCommands")));
			}

		}
	}

}
