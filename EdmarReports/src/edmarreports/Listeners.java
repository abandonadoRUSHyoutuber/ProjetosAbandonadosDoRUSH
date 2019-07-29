package edmarreports;

import org.bukkit.event.EventHandler;
import org.bukkit.entity.Entity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class Listeners implements Listener
{
    private static HashMap<String, Report> managing;
    
    static {
        Listeners.managing = new HashMap<String, Report>();
    }
    
    @EventHandler
    public void onClick(final InventoryClickEvent e) {
        if (e.getClickedInventory() != null && e.getClickedInventory().getTitle().equals(EdmarReports.GUI.getTitle())) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() != Material.SKULL_ITEM) {
                return;
            }
            if (!e.getCurrentItem().hasItemMeta()) {
                return;
            }
            final Player p = (Player)e.getWhoClicked();
            final Report r = EdmarReports.getReport(e.getSlot());
            this.openReportOptions(p, r);
        }
        else if (e.getClickedInventory() != null && e.getClickedInventory().getTitle().equals(EdmarReports.OPTIONS_GUI.getTitle())) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            final Player p = (Player)e.getWhoClicked();
            final Report r = Listeners.managing.get(p.getName());
            if (e.getCurrentItem().getType() == Material.COMPASS) {
                p.closeInventory();
                final Player t = Bukkit.getPlayerExact(r.getReported());
                if (t == null) {
                    p.sendMessage(this.getMsg("jogador-invalido"));
                    return;
                }
                p.teleport((Entity)t);
            }
            else if (e.getCurrentItem().getType() == Material.EYE_OF_ENDER) {
                p.closeInventory();
                EdmarReports.deleteReport(r);
                p.sendMessage(this.getMsg("inocentado").replace("@player", r.getReported()));
            }
        }
    }
    
    private void openReportOptions(final Player p, final Report r) {
        Listeners.managing.put(p.getName(), r);
        p.openInventory(EdmarReports.OPTIONS_GUI);
    }
    
    private String getMsg(final String key) {
        return Bukkit.getPluginManager().getPlugin("EdmarReports").getConfig().getString(key).replace("§", "§");
    }
}
