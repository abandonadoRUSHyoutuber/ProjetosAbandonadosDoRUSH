package br.com.kickpost.ftopnpc.manager;

import org.bukkit.scheduler.BukkitRunnable;

import br.com.kickpost.ftop.factions.FactionsManager;

public class NPCStarter extends BukkitRunnable {

	private int TIMES = 0;

	@Override
	public void run() {
		TIMES++;
		if (FactionsManager.COINS_BY_FACTION.size() != 0 || TIMES >= 20) {
			new FortuneManager();
			cancel();
		}
	}
}
