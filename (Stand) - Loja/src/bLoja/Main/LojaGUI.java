package bLoja.Main;

import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import java.util.*;
import org.bukkit.*;

public class LojaGUI implements Listener
{
    public Main plugin;
    public HashMap<Location, Loja> lojas;
    
    public LojaGUI(final Main plugin) {
        this.lojas = new HashMap<Location, Loja>();
        this.plugin = plugin;
    }
    
    @EventHandler
    public void clicar(final InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        if (!event.getInventory().getTitle().contains("Menu de Lojas")) {
            return;
        }
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
            return;
        }
        final Player p = (Player)event.getWhoClicked();
        final ItemStack item = event.getCurrentItem();
        if (this.checkLoja(item)) {
            final Location loc = this.getLoc(item);
            p.teleport(loc);
            p.closeInventory();
        }
        else if (item.getType().equals((Object)Material.EMPTY_MAP)) {
            final String invnome = event.getInventory().getTitle();
            final String spagatual = ChatColor.stripColor(invnome).replace("Menu de Lojas ", "");
            final int pagatual = Integer.parseInt(spagatual);
            if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                final String nome = item.getItemMeta().getDisplayName();
                if (nome.contains("anterior")) {
                    p.closeInventory();
                    this.abrirGUI(p, pagatual - 1);
                }
                else if (nome.contains("pr\u00f3xima")) {
                    p.closeInventory();
                    this.abrirGUI(p, pagatual + 1);
                }
            }
        }
        event.setCancelled(true);
    }
    
    public void abrirGUI(final Player p, final int pag) {
        this.lojas = this.plugin.core.lojas;
        final Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)null, 54, "§aMenu de Lojas " + pag);
        for (int i = 0; i < 45; ++i) {
            final int index = i + (pag - 1) * 45;
            if (index < this.lojas.size()) {
                inv.setItem(i, this.getLoja(index));
            }
        }
        if (pag > 1) {
            final ItemStack ant = new ItemStack(Material.EMPTY_MAP);
            final ItemMeta antm = ant.getItemMeta();
            antm.setDisplayName(ChatColor.GOLD + "Clique para ir para a p\u00e1gina anterior");
            ant.setItemMeta(antm);
            inv.setItem(48, ant);
        }
        final ItemStack prox = new ItemStack(Material.EMPTY_MAP);
        final ItemMeta proxm = prox.getItemMeta();
        proxm.setDisplayName(ChatColor.GOLD + "Clique para ir para a pr\u00f3xima p\u00e1gina");
        prox.setItemMeta(proxm);
        inv.setItem(50, prox);
        p.openInventory(inv);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ItemStack getLoja(final int i) {
        this.lojas = this.plugin.core.lojas;
        final ArrayList<Loja> lojs = new ArrayList<Loja>(this.lojas.values());
        final Loja l = lojs.get(i);
        final Location loc = l.getLoc();
        final String dono = l.getDono().getName();
        final String xyz = "x" + loc.getBlockX() + " y" + loc.getBlockY() + " z" + loc.getBlockZ();
        final String mundo = l.getLoc().getWorld().getName();
        final String compra = new StringBuilder(String.valueOf(l.getCompra())).toString();
        final String venda = new StringBuilder(String.valueOf(l.getVenda())).toString();
        final String nomeitem = l.getItem().getType().toString();
        final String quantidade = new StringBuilder(String.valueOf(l.getQuantiade())).toString();
        final boolean adm = l.isAdm();
        String admnome;
        if (adm) {
            admnome = "Sim";
        }
        else {
            admnome = "N\u00e3o";
        }
        final ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        final SkullMeta sm = (SkullMeta)skull.getItemMeta();
        sm.setOwner(dono);
        sm.setDisplayName(ChatColor.DARK_AQUA + "Loja de " + dono);
        final List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + "Localiza\u00e7\u00e3o: " + ChatColor.WHITE + xyz);
        lore.add(ChatColor.GREEN + "Mundo: " + ChatColor.WHITE + mundo);
        lore.add(ChatColor.GREEN + "Valor de compra: " + ChatColor.WHITE + compra);
        lore.add(ChatColor.GREEN + "Valor de venda: " + ChatColor.WHITE + venda);
        lore.add(ChatColor.GREEN + "Item: " + ChatColor.WHITE + nomeitem);
        lore.add(ChatColor.GREEN + "Quantidade: " + ChatColor.WHITE + quantidade);
        lore.add(ChatColor.GREEN + "Loja do servidor: " + ChatColor.WHITE + admnome);
        lore.add(ChatColor.GOLD + "Clique aqui para se teletransportar para a loja.");
        sm.setLore((List)lore);
        skull.setItemMeta((ItemMeta)sm);
        return skull;
    }
    
    public boolean checkLoja(final ItemStack item) {
        boolean aux = false;
        if (item == null || item.getType() == Material.AIR) {
            return false;
        }
        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
            final String s = item.getItemMeta().getDisplayName();
            if (s.contains("Loja de ")) {
                aux = true;
            }
        }
        return aux;
    }
    
    public Location getLoc(final ItemStack item) {
        final String loreloc = item.getItemMeta().getLore().get(0);
        final String loreaux = ChatColor.stripColor(loreloc).replace("Localiza\u00e7\u00e3o: ", "");
        final String mundo = item.getItemMeta().getLore().get(1);
        final String mundoaux = ChatColor.stripColor(mundo).replace("Mundo: ", "");
        final String[] locs = loreaux.split(" ");
        final String xs = locs[0].replace("x", "");
        final String ys = locs[1].replace("y", "");
        final String zs = locs[2].replace("z", "");
        final World w = Bukkit.getServer().getWorld(mundoaux);
        final int x = Integer.parseInt(xs);
        final int y = Integer.parseInt(ys);
        final int z = Integer.parseInt(zs);
        return new Location(w, (double)x, (double)y, (double)z);
    }
}
