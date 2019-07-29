package br.com.kickpost.ftopnpc.manager;

import java.util.HashMap;

import org.bukkit.scheduler.BukkitRunnable;

import com.massivecraft.factions.entity.Faction;

import br.com.kickpost.ftopnpc.FTopNpc;
import br.com.kickpost.ftopnpc.factory.FactionInteger;
import br.com.kickpost.ftopnpc.manager.npc.NPCLoader;
import es.eltrueno.npc.skin.SkinManager;

public class SkinSearcher extends BukkitRunnable {

	public static final HashMap<String, Boolean> CONTAINS_SKIN = new HashMap<>();

	public SkinSearcher() {
		runTaskAsynchronously(FTopNpc.getPlugin());
	}

	@Override
	public void run() {
		for (FactionInteger clan : FortuneManager.FORTUNE_BY_FACTION.values()) {
			Faction c = clan.getFaction();
			if(c.getLeader() == null) continue;
			String owner = c.getLeader().getName();

			CONTAINS_SKIN.put(owner, SkinManager.getUUID(owner) != null);
		}
		new NPCLoader();
	}

}
