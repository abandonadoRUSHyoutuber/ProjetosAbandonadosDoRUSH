package rush.mamba.app.server.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.massivecraft.factions.event.EventFactionsDisband;
import com.massivecraft.factions.event.EventFactionsEnteredInAttack;
import com.massivecraft.factions.event.EventFactionsFinishAttack;

import rush.mamba.app.server.MambaApp;

public class FactionsEvents implements Listener {

	@EventHandler(ignoreCancelled = true)
	public void onEnterInAttack(EventFactionsEnteredInAttack e) {
		MambaApp.getApp().sendNotification(e, 0);
	}

	@EventHandler(ignoreCancelled = true)
	public void onLeaveOfAttack(EventFactionsFinishAttack e) {
		MambaApp.getApp().sendNotification(e, 1);
	}

	@EventHandler(ignoreCancelled = true)
	public void onDisbandFaction(EventFactionsDisband e) {
		MambaApp.getApp().sendNotification(e, 2);
	}

}