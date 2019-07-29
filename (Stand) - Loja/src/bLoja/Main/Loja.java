package bLoja.Main;

import org.bukkit.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.material.*;
import org.bukkit.material.Chest;
import org.bukkit.material.Sign;
import org.bukkit.*;
import org.bukkit.block.*;

@SuppressWarnings("unused")
public class Loja
{
    OfflinePlayer dono;
    Location loc;
    ItemStack item;
    int compra;
    int venda;
    int quantiade;
    boolean adm;
    String mundo;
    
    public Loja(final OfflinePlayer dono, final Location loc, final ItemStack item, final int compra, final int venda, final int quantidade, final boolean adm) {
        this.dono = dono;
        this.loc = loc;
        this.item = item;
        this.compra = compra;
        this.venda = venda;
        this.quantiade = quantidade;
        this.adm = adm;
        this.mundo = loc.getWorld().getName();
    }
    
    public OfflinePlayer getDono() {
        return this.dono;
    }
    
    public void setP(final Player dono) {
        this.dono = (OfflinePlayer)dono;
    }
    
    public Location getLoc() {
        return this.loc;
    }
    
    public String getMundo() {
        return this.loc.getWorld().getName();
    }
    
    public void setLoc(final Location loc) {
        this.loc = loc;
    }
    
    public ItemStack getItem() {
        return this.item;
    }
    
    public void setItem(final ItemStack item) {
        this.item = item;
    }
    
    public int getCompra() {
        return this.compra;
    }
    
    public void setCompra(final int compra) {
        this.compra = compra;
    }
    
    public int getVenda() {
        return this.venda;
    }
    
    public void setVenda(final int venda) {
        this.venda = venda;
    }
    
    public int getQuantiade() {
        return this.quantiade;
    }
    
    public void setQuantiade(final int quantiadde) {
        this.quantiade = quantiadde;
    }
    
    public boolean isAdm() {
        return this.adm;
    }
    
    public void setAdm(final boolean adm) {
        this.adm = adm;
    }
    
    public boolean soCompra() {
        return this.venda == 0;
    }
    
    public boolean soVende() {
        return this.compra == 0;
    }
    
    public Chest getBau() {
        final Block b = this.loc.getBlock();
        final Sign placa = (Sign)b.getState().getData();
        final Block blocobau = b.getRelative(placa.getAttachedFace());
        if (blocobau.getType().equals((Object)Material.CHEST) || blocobau.getType().equals((Object)Material.TRAPPED_CHEST)) {
            final Chest c = (Chest)blocobau.getState();
            return c;
        }
        return null;
    }
    
    @SuppressWarnings("deprecation")
	public String getString() {
        String data = "";
        if (this.item.getData().getData() > 0) {
            data = String.valueOf(data) + this.item.getData().getData();
        }
        final String n = this.dono.getName();
        final String l = String.valueOf(this.loc.getBlockX()) + ":" + this.loc.getBlockY() + ":" + this.loc.getBlockZ() + ":" + this.mundo;
        String i = new StringBuilder(String.valueOf(this.item.getType().getId())).toString();
        if (data != "") {
            i = String.valueOf(i) + ":" + data;
        }
        final String c = new StringBuilder(String.valueOf(this.compra)).toString();
        final String v = new StringBuilder(String.valueOf(this.venda)).toString();
        final String q = new StringBuilder(String.valueOf(this.quantiade)).toString();
        final String a = new StringBuilder(String.valueOf(this.adm)).toString();
        final String s = String.valueOf(n) + ";" + l + ";" + i + ";" + c + ";" + v + ";" + q + ";" + a;
        return s;
    }
}
