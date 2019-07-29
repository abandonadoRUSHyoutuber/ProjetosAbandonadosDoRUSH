package bLoja.Main;

import java.util.*;
import org.bukkit.configuration.file.*;
import org.bukkit.*;
import org.bukkit.material.*;
import org.bukkit.material.Sign;
import org.bukkit.block.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.entity.*;

@SuppressWarnings("unused")
public class LojaChecagem implements Listener
{
    public Main plugin;
    public HashMap<Location, Loja> lojas;
    FileConfiguration msg;
    String lojadestruida;
    
    public LojaChecagem(final Main plugin) {
        this.lojas = new HashMap<Location, Loja>();
        this.msg = Main.msg;
        this.lojadestruida = this.msg.getString("LojaDestruida").replace("&", "§");
        this.plugin = plugin;
    }
    
    @EventHandler
    public void placaCaindo(final BlockPhysicsEvent event) {
        if (event.isCancelled()) {
            return;
        }
        final Block b = event.getBlock();
        if (b.getType() == Material.WALL_SIGN || b.getType() == Material.SIGN_POST) {
            final Sign s = (Sign)b.getState().getData();
            final Block attachedBlock = b.getRelative(s.getAttachedFace());
            if (attachedBlock.getType() == Material.AIR && this.plugin.core.validarLoja(b.getLocation())) {
                final Loja l = this.plugin.core.getLoja(b);
                this.plugin.core.removerLoja(l);
            }
        }
    }
    
    @EventHandler
    public void quebrarPlaca(final BlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }
        final Block b = event.getBlock();
        if ((b.getType() == Material.WALL_SIGN || b.getType() == Material.SIGN_POST) && this.plugin.core.validarLoja(b.getLocation())) {
            final Loja l = this.plugin.core.getLoja(b);
            final Player p = event.getPlayer();
            if (l.getDono() == p || l.isAdm()) {
                this.plugin.core.removerLoja(l);
                p.sendMessage(this.lojadestruida);
            }
            else {
                event.setCancelled(true);
            }
        }
    }
}
