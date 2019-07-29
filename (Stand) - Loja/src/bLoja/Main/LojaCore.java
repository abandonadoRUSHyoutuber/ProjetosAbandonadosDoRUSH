package bLoja.Main;

import java.util.*;
import org.bukkit.configuration.file.*;
import org.bukkit.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.block.*;
import org.bukkit.*;

public class LojaCore
{
    public Main plugin;
    public HashMap<Location, Loja> lojas;
    public FileConfiguration msg;
    String lojacriada;
    
    public LojaCore(final Main plugin) {
        this.msg = Main.msg;
        this.lojacriada = this.msg.getString("LojaCriada").replace("&", "§");
        this.plugin = plugin;
        this.lojas = new HashMap<Location, Loja>();
    }
    
    public void criarLoja(final OfflinePlayer p, final Location loc, final ItemStack item, final int compra, final int venda, final int quantidade, final boolean adm) {
        final Loja l = new Loja(p, loc, item, compra, venda, quantidade, adm);
        this.lojas.put(loc, l);
        try {
            final Player pl = (Player)p;
            pl.sendMessage(this.lojacriada);
        }
        catch (Exception ex) {}
    }
    
    public boolean validarLoja(final Location loc) {
        return this.lojas.containsKey(loc);
    }
    
    public Loja getLoja(final Block b) {
        Loja returnloja = null;
        final Location loc = b.getLocation();
        if (this.validarLoja(loc)) {
            returnloja = this.lojas.get(loc);
        }
        return returnloja;
    }
    
    public void removerLoja(final Loja l) {
        final Location loc = l.getLoc();
        this.lojas.remove(loc);
    }
    
    @SuppressWarnings({ "unused", "deprecation" })
	public void recarregarLoja(final String s) {
        boolean suc = true;
        final String[] param = s.split(";");
        if (param.length != 7) {
            suc = false;
        }
        try {
            final OfflinePlayer dono = Bukkit.getServer().getOfflinePlayer(param[0]);
            final String locs = param[1];
            final String[] xyz = locs.split(":");
            final int x = Integer.parseInt(xyz[0]);
            final int y = Integer.parseInt(xyz[1]);
            final int z = Integer.parseInt(xyz[2]);
            final WorldCreator wc = new WorldCreator(xyz[3]);
            final World mundo = Bukkit.createWorld(wc);
            final Location loc = new Location(mundo, (double)x, (double)y, (double)z);
            final String id = param[2];
            ItemStack item;
            if (id.contains(":")) {
                final String[] ids = id.split(":");
                final int idnumero = Integer.parseInt(ids[0]);
                final int datanumero = Integer.parseInt(ids[1]);
                item = new ItemStack(Material.getMaterial(idnumero), 1, (short)(byte)datanumero);
            }
            else {
                final int idnumero2 = Integer.parseInt(id);
                item = new ItemStack(Material.getMaterial(idnumero2));
            }
            final int compra = Integer.parseInt(param[3]);
            final int venda = Integer.parseInt(param[4]);
            final int quantidade = Integer.parseInt(param[5]);
            final boolean adm = param[6].equals("true");
            this.criarLoja(dono, loc, item, compra, venda, quantidade, adm);
        }
        catch (Exception e) {
            suc = false;
            e.printStackTrace();
        }
    }
}
