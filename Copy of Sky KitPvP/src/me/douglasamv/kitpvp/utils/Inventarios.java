package me.douglasamv.kitpvp.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.cmd.Score;
import me.douglasamv.kitpvp.utils.scoreboard.Scroller;

public class Inventarios {

	public static void resetar(Player p) {
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.getInventory().setItem(0, ItemAPI.Criar(Material.CHEST, 1, 0, "§6Kits", false));
		if (Main.cfg_warps.getBoolean("warps.true")) {
			p.getInventory().setItem(1, ItemAPI.Criar(Material.BOOK, 1, 0, "§cWarps", false));
		}
		p.getInventory().setItem(7, ItemAPI.Criar(Material.PRISMARINE_SHARD, 1, 0, "§bRanks", false));
		p.getInventory().setItem(8, ItemAPI.Criar(Material.EMERALD, 1, 0, "§aStore", false));
		p.setGameMode(GameMode.SURVIVAL);
		p.setLevel(0);
		p.setFireTicks(0);
		p.setExp(0);
		p.setHealth(20);
		p.setHealthScale(20);
		HabilidadeApi.remover(p);
		p.getInventory().setHeldItemSlot(0);
		p.setWalkSpeed(0.2F);
		p.setAllowFlight(false);
		p.setFlying(false);
		if (p.getVehicle() != null) {
			p.getVehicle().eject();
		}
		new BukkitRunnable() {
			@Override
			public void run() {
				upDateScore(p);
			}
		}.runTaskLater(Main.pl, 5);
		for (PotionEffect effect : p.getActivePotionEffects())
			p.removePotionEffect(effect.getType());
		if (click.contains(p))
			click.remove(p);
		if (ProtecaoSpawn.Fogo.contains(p))
			ProtecaoSpawn.Fogo.remove(p);
	}

	public static ArrayList<Player> click = new ArrayList<>();
	public static ArrayList<Player> contar = new ArrayList<>();
	public static HashMap<Player, Integer> clicks = new HashMap<>();

	public static String cor1 = Main.pl.getConfig().getString("CorScore1");
	public static String cor2 = Main.pl.getConfig().getString("CorScore2");
	public static String cor3 = Main.pl.getConfig().getString("CorScore3");

	@SuppressWarnings("deprecation")
	public static void upDateScore(Player p) {
		if (Score.player.contains(p.getName()))
			return;
		if (p.getScoreboard() == null || p.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null) {
			Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
			Objective obj = board.registerNewObjective("bj", "bunda");
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
			Scroller sc = new Scroller(Main.pl.getConfig().getString("ScoreBoard").replace("&", "§"), 20, 5, '&');
			new BukkitRunnable() {
				@Override
				public void run() {
					obj.setDisplayName(sc.next());
				}
			}.runTaskTimer(Main.pl, 0, 3);
			obj.getScore("                           ").setScore(10);
			obj.getScore("§" + cor1 + "Player §" + cor2 + "» §" + cor3).setScore(9);
			obj.getScore("§" + cor1 + "Kit §" + cor2 + "» §" + cor3).setScore(8);
			obj.getScore(" ").setScore(7);
			obj.getScore("§" + cor1 + "Rank §" + cor2 + "» §" + cor3).setScore(6);
			obj.getScore("§" + cor1 + "Money §" + cor2 + "» §" + cor3).setScore(5);
			obj.getScore("§" + cor1 + "Kill §" + cor2 + "» §" + cor3).setScore(4);
			obj.getScore("§" + cor1 + "Death §" + cor2 + "» §" + cor3).setScore(3);
			obj.getScore("§" + cor1 + "KDR §" + cor2 + "» §" + cor3).setScore(2);
			obj.getScore("§" + cor1 + "Online §" + cor2 + "» §" + cor3).setScore(1);
			obj.getScore("   ").setScore(0);
			board.registerNewTeam("p").addPlayer(new FastOfflinePlayer("§" + cor1 + "Player §" + cor2 + "» §" + cor3));
			board.registerNewTeam("k").addPlayer(new FastOfflinePlayer("§" + cor1 + "Kit §" + cor2 + "» §" + cor3));
			board.registerNewTeam("r").addPlayer(new FastOfflinePlayer("§" + cor1 + "Rank §" + cor2 + "» §" + cor3));
			board.registerNewTeam("m").addPlayer(new FastOfflinePlayer("§" + cor1 + "Money §" + cor2 + "» §" + cor3));
			board.registerNewTeam("k2").addPlayer(new FastOfflinePlayer("§" + cor1 + "Kill §" + cor2 + "» §" + cor3));
			board.registerNewTeam("d").addPlayer(new FastOfflinePlayer("§" + cor1 + "Death §" + cor2 + "» §" + cor3));
			board.registerNewTeam("kd").addPlayer(new FastOfflinePlayer("§" + cor1 + "KDR §" + cor2 + "» §" + cor3));
			board.registerNewTeam("o").addPlayer(new FastOfflinePlayer("§" + cor1 + "Online §" + cor2 + "» §" + cor3));
			p.setScoreboard(board);
		}
		try {
			ResultSet rs;
			rs = Main.getMysql().conectar().createStatement()
					.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + p.getName() + "';");
			if (rs.next()) {
				double kill = (double) rs.getInt("kill");
				double death = (double) rs.getInt("death");
				if (kill == 0) {
					kill = 1;
				}
				if (death == 0) {
					death = 1;
				}
				double kdr = (kill / death);
				DecimalFormat df = new DecimalFormat("#.##");
				p.getScoreboard().getTeam("p").setSuffix(String.valueOf(p.getName()));
				p.getScoreboard().getTeam("k").setSuffix(
						HabilidadeApi.verHB(p).replace("nenhum", Main.cfg_kits.getString("kits.none.ability")));
				p.getScoreboard().getTeam("r").setSuffix(String.valueOf(rank(rs.getInt("kill"))));
				p.getScoreboard().getTeam("m").setSuffix(String.valueOf(rs.getInt("money")));
				p.getScoreboard().getTeam("k2").setSuffix(String.valueOf(rs.getInt("kill")));
				p.getScoreboard().getTeam("d").setSuffix(String.valueOf(rs.getInt("death")));
				p.getScoreboard().getTeam("kd").setSuffix(df.format(kdr));
				p.getScoreboard().getTeam("o").setSuffix(
						Main.pl.getServer().getOnlinePlayers().size() + "/" + Main.pl.getServer().getMaxPlayers());
				rs.getStatement().getConnection().close();
			}
			rs.getStatement().getConnection().close();

		} catch (SQLException e) {
		}

	}

	public static String rankNick(int i) {
		if (i <= 400) {
			return "§6✪";
		} else if (i <= 500) {
			return "§8✪";
		} else if (i <= 1500) {
			return "§e✪";
		} else if (i >= 4000) {
			return "§b✪";
		} else {
			return "§b✪";
		}
	}

	public static String rank(int i) {
		if (i <= 100) {
			return Main.cfg_rank.getString("ranks.rank1") + " IV";
		} else if (i <= 200) {
			return Main.cfg_rank.getString("ranks.rank1") + " III";
		} else if (i <= 300) {
			return Main.cfg_rank.getString("ranks.rank1") + " II";
		} else if (i <= 400) {
			return Main.cfg_rank.getString("ranks.rank1") + " I";
		} else if (i <= 500) {
			return Main.cfg_rank.getString("ranks.rank2") + " VI";
		} else if (i <= 600) {
			return Main.cfg_rank.getString("ranks.rank2") + " III";
		} else if (i <= 800) {
			return Main.cfg_rank.getString("ranks.rank2") + " II";
		} else if (i <= 1000) {
			return Main.cfg_rank.getString("ranks.rank2") + " I";
		} else if (i <= 1500) {
			return Main.cfg_rank.getString("ranks.rank3") + " VI";
		} else if (i <= 2000) {
			return Main.cfg_rank.getString("ranks.rank3") + " III";
		} else if (i <= 2500) {
			return Main.cfg_rank.getString("ranks.rank3") + " II";
		} else if (i <= 3000) {
			return Main.cfg_rank.getString("ranks.rank3") + " I";
		} else if (i <= 4000) {
			return Main.cfg_rank.getString("ranks.rank4") + " VI";
		} else if (i <= 5000) {
			return Main.cfg_rank.getString("ranks.rank4") + " III";
		} else if (i <= 6000) {
			return Main.cfg_rank.getString("ranks.rank4") + " II";
		} else {
			return Main.cfg_rank.getString("ranks.rank4") + " I";
		}
	}
}
