package br.com.kickpost.ftopnpc.manager.npc;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.massivecraft.factions.entity.Faction;

import br.com.kickpost.ftopnpc.manager.ConfigurationManager;
import br.com.kickpost.ftopnpc.manager.FortuneManager;

public class NPCLoader {

	public NPCLoader() {
		load();
	}

	private void load() {
		Bukkit.getLogger().info("[FTOPNpcs] Setando os NPCs...");
		for (Entry<Integer, Location> posicao : ConfigurationManager.NPC_BY_LOCATION.entrySet()) {
			try {
				Faction faction = FortuneManager.FORTUNE_BY_FACTION.get(posicao.getKey()).getFaction();
				double fortuna = FortuneManager.FORTUNE_BY_FACTION.get(posicao.getKey()).getFortuna();
				new NPCManager(faction.getLeader().getName(), posicao.getKey(), fortuna).createNPC(posicao.getValue(),
						faction);

				Bukkit.getLogger().info("[FTopNPC] NPC '" + posicao.getKey() + "'' setado!");
				Bukkit.getLogger().info("[FTopNPC] NPC '" + posicao.getKey() + "' definido a Faccao: "
						+ faction.getName() + " com Skin do jogador: " + faction.getLeader().getName());
			} catch (Exception e) {
				Bukkit.getLogger().info("[FTopNPC] Nao ha nenhuma Faccao para ocupar a posicao: " + posicao.getKey());
			}
		}
	}
}
