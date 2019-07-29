package bLoja.Main;

import org.bukkit.configuration.file.*;
import org.bukkit.event.block.*;
import org.bukkit.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.block.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.material.*;
import org.bukkit.material.Sign;

import java.util.*;

@SuppressWarnings("unused")
public class LojaCriar implements Listener
{
    public Main plugin;
    public FileConfiguration msg;
    public FileConfiguration cfg;
    String semperm;
    String erro;
    String sembau;
    String prefixo;
    String corloja;
    
    public LojaCriar(final Main plugin) {
        this.msg = Main.msg;
        this.cfg = Main.cfg;
        this.semperm = this.msg.getString("SemPermissao").replace("&", "§");
        this.erro = this.msg.getString("ErroAoCriarLoja").replace("&", "§");
        this.sembau = this.msg.getString("LojaCriadaForaDoBau").replace("&", "§");
        this.prefixo = this.cfg.getString("PrefixoLoja").replace("&", "§");
        this.corloja = this.cfg.getString("CorLojaVip").replace("&", "§");
        this.plugin = plugin;
    }
    
    @SuppressWarnings({ "deprecation" })
	@EventHandler
    public void criarLoja(final SignChangeEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (event.getLine(0).equals("[Loja]")) {
            final Player p = event.getPlayer();
            final Block b = event.getBlock();
            if (!b.getType().equals((Object)Material.SIGN_POST) && !b.getType().equals((Object)Material.WALL_SIGN)) {
                return;
            }
            if (!p.hasPermission("loja.criar")) {
                p.sendMessage(this.semperm);
                b.breakNaturally();
                return;
            }
            if (!event.getLine(3).equals(null)) {
                if (!this.validarID(event.getLine(3))) {
                    event.setLine(3, "???");
                }
            }
            else {
                event.setLine(3, "???");
            }
            if (this.checkLoja(event.getLines())) {
                ItemStack aux = new ItemStack(Material.AIR);
                if (!event.getLine(3).equals("???") && this.validarID(event.getLine(3))) {
                    final String ids = event.getLine(3);
                    if (this.isNumericString(ids)) {
                        final int id = Integer.parseInt(ids);
                        aux = new ItemStack(Material.getMaterial(id));
                    }
                    else if (ids.contains(":")) {
                        final String[] n = ids.split(":");
                        if (this.isNumericString(n[0]) && this.isNumericString(n[1])) {
                            final int id2 = Integer.parseInt(n[0]);
                            final int data = Integer.parseInt(n[1]);
                            aux = new ItemStack(Material.getMaterial(id2), 1, (short)(byte)data);
                        }
                    }
                }
                boolean adminshop = false;
                if (p.hasPermission("loja.adm")) {
                    adminshop = true;
                }
                int qnt = 1;
                if (this.isNumericString(event.getLine(1))) {
                    final int auxi = qnt = Integer.parseInt(event.getLine(1));
                }
                final int valorvenda = this.getValorVenda(event.getLine(2));
                final int valorcompra = this.getValorCompra(event.getLine(2));
                final Player dono = event.getPlayer();
                final Location loc = b.getLocation();
                final ItemStack item = aux;
                final int quantidade = qnt;
                final int venda = valorvenda;
                final int compra = valorcompra;
                final boolean adm = adminshop;
                if (!p.hasPermission("loja.adm")) {
                    event.setLine(0, String.valueOf(this.corloja) + p.getName());
                }
                else {
                    event.setLine(0, this.prefixo);
                }
                String s = "§aC§r " + compra + " : " + "§cV§r " + venda;
                if (venda == 0) {
                    final String[] s2 = s.split(":");
                    s = s2[0];
                }
                if (compra == 0) {
                    final String[] s2 = s.split(":");
                    s = s2[1];
                }
                event.setLine(2, s);
                if (this.validarID(event.getLine(3))) {
                    final String linha = event.getLine(3);
                    int id3 = 0;
                    int data2 = 0;
                    if (linha.contains(":")) {
                        final String[] lados = linha.split(":");
                        final String idstring = lados[0];
                        final String datastring = lados[1];
                        id3 = Integer.parseInt(idstring);
                        data2 = Integer.parseInt(datastring);
                    }
                    else {
                        id3 = Integer.parseInt(event.getLine(3));
                    }
                    String mn = NomeItens.nomes.get(id3);
                    if (data2 != 0) {
                        mn = String.valueOf(mn) + ":" + data2;
                    }
                    event.setLine(3, mn);
                }
                if (!this.checkBau(b) && !adm) {
                    p.sendMessage(this.sembau);
                }
                this.plugin.core.criarLoja((OfflinePlayer)p, loc, item, valorcompra, valorvenda, quantidade, adminshop);
            }
            else {
                p.sendMessage(this.erro);
                b.breakNaturally();
            }
        }
    }
    
    public boolean checkLoja(final String[] linhas) {
        boolean aux = true;
        if (linhas.length == 4) {
            if (!linhas[0].equals("[Loja]")) {
                aux = false;
            }
            else if (!this.isNumericString(linhas[1])) {
                aux = false;
            }
            else if (!this.checkLinha3(linhas[2])) {
                aux = false;
            }
        }
        return aux;
    }
    
    public boolean checkLinha3(final String s) {
        boolean l1aux = false;
        boolean l2aux = false;
        if (s.contains(":")) {
            final String[] lados = s.split(":");
            final String l1 = lados[1];
            final String l2 = lados[0];
            if (l1.contains("V")) {
                final String lad1 = l1.replace("V", "").replace(" ", "");
                if (this.isNumericString(lad1)) {
                    l1aux = true;
                }
            }
            if (l2.contains("C")) {
                final String lad2 = l2.replace("C", "").replace(" ", "");
                if (this.isNumericString(lad2)) {
                    l2aux = true;
                }
            }
        }
        return l1aux && l2aux;
    }
    
    public boolean isNumeric(final char c) {
        String s = "";
        s = String.valueOf(s) + c;
        try {
            Double.parseDouble(s);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public boolean isNumericString(final String s) {
        try {
            Double.parseDouble(s);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    @SuppressWarnings({ "deprecation" })
	public boolean validarID(final String s) {
        boolean aux = false;
        if (this.isNumericString(s)) {
            final int id = Integer.parseInt(s);
            try {
                final Material m = Material.getMaterial(id);
                aux = true;
            }
            catch (Exception ex) {}
        }
        else if (s.contains(":")) {
            final String[] n = s.split(":");
            if (this.isNumericString(n[0]) && this.isNumericString(n[1])) {
                final int id2 = Integer.parseInt(n[0]);
                final int data = Integer.parseInt(n[1]);
                try {
                    final ItemStack item = new ItemStack(Material.getMaterial(id2), 1, (short)(byte)data);
                    aux = true;
                }
                catch (Exception e) {
                    aux = false;
                }
            }
        }
        return aux;
    }
    
    public boolean checkBau(final Block b) {
        boolean aux = false;
        if (b.getType() == Material.SIGN_POST || b.getType() == Material.WALL_SIGN) {
            final Sign s = (Sign)b.getState().getData();
            final Block bau = b.getRelative(s.getAttachedFace());
            if (bau.getType().equals((Object)Material.CHEST) || bau.getType().equals((Object)Material.TRAPPED_CHEST)) {
                aux = true;
            }
        }
        return aux;
    }
    
    public int getValorCompra(final String s) {
        final String[] lados = s.split(":");
        final String compra = lados[0];
        String valor = "";
        for (int i = 0; i < compra.length(); ++i) {
            final char c = compra.charAt(i);
            if (this.isNumeric(c)) {
                valor = String.valueOf(valor) + c;
            }
        }
        final int valorint = Integer.parseInt(valor);
        return valorint;
    }
    
    public int getValorVenda(final String s) {
        final String[] lados = s.split(":");
        final String venda = lados[1];
        String valor = "";
        for (int i = 0; i < venda.length(); ++i) {
            final char c = venda.charAt(i);
            if (this.isNumeric(c)) {
                valor = String.valueOf(valor) + c;
            }
        }
        final int valorint = Integer.parseInt(valor);
        return valorint;
    }
    
    public String getLore(final Player p, final String s) {
        if (!p.getItemInHand().hasItemMeta()) {
            return "Item n\u00e3o possui item meta";
        }
        if (!p.getItemInHand().getItemMeta().hasLore()) {
            return "Item n\u00e3o possui lore.";
        }
        final List<String> lore = new ArrayList<String>();
        lore.addAll(p.getItemInHand().getItemMeta().getLore());
        return lore.get(1);
    }
}
