package br.com.kickpost.ftopnpc.manager;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import com.massivecraft.factions.entity.Faction;

import br.com.kickpost.ftop.factions.FactionsManager;
import br.com.kickpost.ftop.factions.FactionsUtils;
import br.com.kickpost.ftopnpc.FTopNpc;
import br.com.kickpost.ftopnpc.factory.FactionInteger;

public class FortuneManager {
	public static HashMap<Integer, FactionInteger> FORTUNE_BY_FACTION = new HashMap<>();

	public FortuneManager() {
		load();
	}

	private void load() {
		
		Bukkit.getLogger().info("[FTOPNpcs] Carregando os Clans e suas fortunas...");
		new BukkitRunnable() {
			
			@Override
			public void run() {
				int i = 1;
				for (Entry<Faction, Double> fac : FactionsManager.COINS_BY_FACTION.entrySet()) {
					double grana = fac.getValue();
					double granaSpawners = FactionsUtils.getMobSpawnersValor(FactionsManager.SPAWNERS_BY_FACTION.get(fac.getKey()));

					FORTUNE_BY_FACTION.put(i, new FactionInteger(fac.getKey(), grana + granaSpawners));
					i++;
				}
				FORTUNE_BY_FACTION = organizeHashMap(FORTUNE_BY_FACTION);
				new SkinSearcher();
			}
		}.runTaskAsynchronously(FTopNpc.getPlugin());
	
	}

	private static HashMap<Integer, FactionInteger> organizeHashMap(HashMap<Integer, FactionInteger> unsortMap) {
		List<Entry<Integer, FactionInteger>> list = new LinkedList<Entry<Integer, FactionInteger>>(
				unsortMap.entrySet());

		// Sorting the list based on values
		Collections.sort(list, new Comparator<Entry<Integer, FactionInteger>>() {
			@Override
			public int compare(Entry<Integer, FactionInteger> o1, Entry<Integer, FactionInteger> o2) {
				return o2.getValue().getFortuna().compareTo(o1.getValue().getFortuna());
			}
		});

		// Maintaining insertion order with the help of LinkedList
		HashMap<Integer, FactionInteger> sortedMap = new HashMap<>();
		int v = 1;
		for (Entry<Integer, FactionInteger> entry : list) {
			sortedMap.put(v, entry.getValue());
			v++;
		}
		return sortedMap;
	}
}