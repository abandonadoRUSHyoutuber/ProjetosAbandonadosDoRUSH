package br.com.kickpost.ftopnpc.manager;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import br.com.kickpost.ftopnpc.FTopNpc;

public class ConfigurationManager {

	public static final HashMap<Integer, Location> NPC_BY_LOCATION = new HashMap<>();

	public ConfigurationManager() {
		load();
	}

	private void load() {
		final ConfigurationSection SECTION = FTopNpc.getPlugin().getConfig().getConfigurationSection("NPCs");
		final Set<String> SECTION_SET = SECTION.getKeys(false);
		for (String s : SECTION_SET) {
			int posicao = Integer.parseInt(s);
			String[] locationSplitted = SECTION.getString(s + ".Localizacao").split(",");
			Location location = new Location(Bukkit.getWorld(locationSplitted[5]),
					Double.parseDouble(locationSplitted[0]), Double.parseDouble(locationSplitted[1]),
					Double.parseDouble(locationSplitted[2]), Float.parseFloat(locationSplitted[3]),
					Float.parseFloat(locationSplitted[4]));

			NPC_BY_LOCATION.put(posicao, location);
		}
	}
}