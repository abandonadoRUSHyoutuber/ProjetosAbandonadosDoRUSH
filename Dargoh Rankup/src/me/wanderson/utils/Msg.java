package me.wanderson.utils;

import java.util.List;

import org.bukkit.Bukkit;

import me.wanderson.DRankUp;

public class Msg {
	private static DRankUp pl = (DRankUp)Bukkit.getPluginManager().getPlugin("DRankUp");
	public static String TITLE_RANKS = pl.getConfig().getString("ranks_title").replace("&", "§");
	public static String TITLE_RANKUP = pl.getConfig().getString("rankup_title").replace("&", "§");
	public static String RANKUP_NAME = pl.getConfig().getString("rankup_name").replace("&", "§");
	public static List<String> RANKUP_LORE = pl.getConfig().getStringList("rankup_lore");
	public static String CONFIRMAR = pl.getConfig().getString("confirmar").replace("&", "§");
	public static String CANCELAR = pl.getConfig().getString("cancelar").replace("&", "§");
	public static String SEM_SALDO = pl.getConfig().getString("saldo_insulficiente").replace("&", "§");
	public static String ULTIMO = pl.getConfig().getString("ultimo_rank").replace("&", "§");
	public static List<String> PLAYER_RANKUP = pl.getConfig().getStringList("player_rankup");
	public static List<String> PLAYERS_RANKUP = pl.getConfig().getStringList("players_rankup");
	public static String FULL_TITLE = pl.getConfig().getString("full_title").replace("&", "§");
	public static String FULL_SUBTITLE = pl.getConfig().getString("full_subtitle").replace("&", "§");
	public static String PERM_NULL = pl.getConfig().getString("perm_null").replace("&", "§");
}
