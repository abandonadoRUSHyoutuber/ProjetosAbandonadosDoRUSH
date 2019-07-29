package bLoja.Main;

import java.util.*;
import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;

public class LojaSeguran\u00e7a implements Listener
{
    public Main plugin;
    public HashMap<Location, Loja> lojas;
    
    public LojaSeguran\u00e7a(final Main plugin) {
        this.lojas = new HashMap<Location, Loja>();
        this.plugin = plugin;
    }
    
    @SuppressWarnings("unlikely-arg-type")
	@EventHandler
    public void abrirBau(final PlayerInteractEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        final Block b = event.getClickedBlock();
        final Player p = event.getPlayer();
        if (b.getType().equals((Object)Material.CHEST) || b.getType().equals((Object)Material.TRAPPED_CHEST)) {
            this.lojas = this.plugin.core.lojas;
            final Location loc = b.getLocation();
            if (this.lojas.containsKey(loc)) {
                final Loja l = this.lojas.get(loc);
                final Chest c = (Chest)b.getState();
                boolean aux = false;
                if (l.getBau() != null && l.getBau().equals(c)) {
                    aux = true;
                }
                if (l.getDono() == p) {
                    aux = false;
                }
                if (aux) {
                    event.setCancelled(true);
                }
            }
        }
    }
    
    @SuppressWarnings("unlikely-arg-type")
	@EventHandler
    public void quebrarBau(final BlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }
        final Block b = event.getBlock();
        final Player p = event.getPlayer();
        if (b.getType().equals((Object)Material.CHEST) || b.getType().equals((Object)Material.TRAPPED_CHEST)) {
            final Chest c = (Chest)b.getState();
            boolean aux = false;
            this.lojas = this.plugin.core.lojas;
            final Location loc = b.getLocation();
            if (this.lojas.containsKey(loc)) {
                final Loja l = this.lojas.get(loc);
                if (l.getBau() != null && l.getBau().equals(c)) {
                    aux = true;
                }
                if (l.getDono() == p) {
                    aux = false;
                }
                if (l.isAdm()) {
                    aux = false;
                }
                if (aux) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
