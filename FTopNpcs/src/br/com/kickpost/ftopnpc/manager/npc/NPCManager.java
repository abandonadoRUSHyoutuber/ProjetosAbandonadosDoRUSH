package br.com.kickpost.ftopnpc.manager.npc;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashSet;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.massivecraft.factions.entity.Faction;

import br.com.kickpost.ftopnpc.FTopNpc;
import br.com.kickpost.ftopnpc.manager.SkinSearcher;
import es.eltrueno.npc.TruenoNPC;
import es.eltrueno.npc.TruenoNPCApi;
import es.eltrueno.npc.skin.TruenoNPCSkin;
import es.eltrueno.npc.skin.TruenoNPCSkinBuilder;

public class NPCManager {

	public static final HashSet<TruenoNPC> NPCs = new HashSet<>();
	public static final HashSet<Hologram> HOLOGRAMS = new HashSet<>();

	private String name;
	private int posicao;
	private double money;

	private final static DecimalFormatSymbols DFS = new DecimalFormatSymbols(new Locale("pt", "BR"));
	public static final DecimalFormat FORMATTER = new DecimalFormat("###,###,###", DFS);

	public NPCManager(String name, int posicao, double money) {
		this.name = name;
		this.posicao = posicao;
		this.money = money;
	}

	public void createNPC(Location location, Faction faction) {
		
		TruenoNPCSkin skin = null;
		TruenoNPC npc = null;
		
		if (SkinSearcher.CONTAINS_SKIN.get(name)) {
			 skin = TruenoNPCSkinBuilder.fromUsername(FTopNpc.getPlugin(), name);
			 npc = TruenoNPCApi.createNPC(FTopNpc.getPlugin(), location, skin);
		} else {
			 skin = TruenoNPCSkinBuilder.fromUsername(FTopNpc.getPlugin(), "Steve");
			TruenoNPCApi.createNPC(FTopNpc.getPlugin(), location, skin);
		}

		new BukkitRunnable() {
			@Override
			public void run() {
				try {
					Hologram hologram = HologramsAPI.createHologram(FTopNpc.getPlugin(),
							location.clone().add(0, 2.7, 0));
					hologram.insertTextLine(0, ChatColor.AQUA + Integer.toString(posicao) + ChatColor.BOLD + "º"
							+ ChatColor.RESET + ChatColor.AQUA + " Lugar");
					hologram.insertTextLine(1, ChatColor.YELLOW + faction.getName());
					hologram.insertTextLine(2, ChatColor.GRAY + FORMATTER.format(money));

					HOLOGRAMS.add(hologram);

				} catch (Exception e) {
					Bukkit.getLogger().info("[FTOPNpcs] Nao foi possivel estabelecer o Clan do jogador: " + name);
				}
			}
		}.runTask(FTopNpc.getPlugin());
		NPCs.add(npc);
	}
}
