package rush.editfarm.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamage implements Listener {
	
	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageByBlockEvent e) {
		if (e.getCause() == DamageCause.CONTACT) {
			e.setCancelled(true);
			e.setDamage(0d);
		}
	}
}
