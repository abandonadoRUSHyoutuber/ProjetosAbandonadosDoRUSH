package me.leitinhooow.score;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import me.leitinhooow.central.Main;


@SuppressWarnings("deprecation")
public class Scoreboarding implements Listener{
	
	public static void ScoreboardManager(Player p) {
		
		int online = Bukkit.getOnlinePlayers().length;
		Scoreboard Board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective Obj = Board.registerNewObjective("Score", "Board");
		Obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		Obj.setDisplayName(Main.Score_Name);
		Score a17 = Obj.getScore(Bukkit.getOfflinePlayer("§7      "));
		Score a16 = Obj.getScore(Bukkit.getOfflinePlayer("§fRank: " + Rank(p)));
		Score a15 = Obj.getScore(Bukkit.getOfflinePlayer("§fOnline:§b " + online));
		Score a14 = Obj.getScore(Bukkit.getOfflinePlayer("§c   "));
		Score a13 = Obj.getScore(Bukkit.getOfflinePlayer("§a" + Main.loja));

		a17.setScore(17);
		a16.setScore(16);
		a15.setScore(15);
		a14.setScore(14);
		a13.setScore(13);
		
		p.setScoreboard(Board);
	}
	
	public static String Rank(Player p) {
		if (p.hasPermission("rank.dono")) {
			return "§4§lDONO";
		}
		if (p.hasPermission("rank.admin")) {
			return "§c§lADMIN";
		}
		if (p.hasPermission("rank.mod")) {
			return "§5§lMOD";
		}
		if (p.hasPermission("rank.trial")) {
			return "§d§lTRIAL";
		}
		if (p.hasPermission("rank.pro")) {
			return "§6§lPRO";
		}
		if (p.hasPermission("rank.membro")) {
			return "§7§lMEMBRO";
		}
		if (p.hasPermission("rank.dev")) {
			return "§3§lDEV";
		}
		if (p.hasPermission("rank.elite")) {
			return "§9§lELITE";
		}
		return null;
	}
}