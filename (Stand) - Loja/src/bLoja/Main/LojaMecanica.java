package bLoja.Main;

import org.bukkit.configuration.file.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;
import org.bukkit.*;
import org.bukkit.block.*;
import java.util.*;
import java.text.*;

public class LojaMecanica implements Listener
{
    public Main plugin;
    FileConfiguration msg;
    String naovende;
    String naocompra;
    String sembau;
    String autocomprar;
    String comprar;
    String semespaco;
    String comprou;
    String naocontemnobau;
    String semdinheiro;
    String autovender;
    String vender;
    String semitem;
    String vendeu;
    String semespaconobau;
    String donosemdinheiro;
    String semitemdefinido;
    String lojaatualizada;
    
    public LojaMecanica(final Main plugin) {
        this.msg = Main.msg;
        this.naovende = this.msg.getString("LojaSoCompra").replace("&", "§");
        this.naocompra = this.msg.getString("LojaSoVende").replace("&", "§");
        this.sembau = this.msg.getString("LojaSemBau").replace("&", "§");
        this.autocomprar = this.msg.getString("NaoPodeComprarDeSiMesmo").replace("&", "§");
        this.comprar = this.msg.getString("Comprar").replace("&", "§");
        this.semespaco = this.msg.getString("SemEspacoNoInventario").replace("&", "§");
        this.comprou = this.msg.getString("ComprouNaSuaLoja").replace("&", "§");
        this.naocontemnobau = this.msg.getString("SemItemNoBau").replace("&", "§");
        this.semdinheiro = this.msg.getString("SemDinheiro").replace("&", "§");
        this.autovender = this.msg.getString("NaoPodeVenderParaSiMesmo").replace("&", "§");
        this.vender = this.msg.getString("Vender").replace("&", "§");
        this.semitem = this.msg.getString("JogadorSemItemParaVender").replace("&", "§");
        this.vendeu = this.msg.getString("VendeuNaSuaLoja").replace("&", "§");
        this.semespaconobau = this.msg.getString("SemEspacoNoBau").replace("&", "§");
        this.donosemdinheiro = this.msg.getString("DonoDaLojaSemDinheiro").replace("&", "§");
        this.semitemdefinido = this.msg.getString("SemItemDefinido").replace("&", "§");
        this.lojaatualizada = this.msg.getString("LojaAtualizada").replace("&", "§");
        this.plugin = plugin;
    }
    
    @SuppressWarnings("deprecation")
	@EventHandler
    public void definirItem(final PlayerInteractEvent event) {
        final Action ac = event.getAction();
        if (!ac.equals((Object)Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        final Player p = event.getPlayer();
        if (!p.isSneaking()) {
            return;
        }
        if (p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR) {
            return;
        }
        final Block b = event.getClickedBlock();
        if (this.checkPlaca(b) && this.plugin.core.validarLoja(b.getLocation())) {
            final Loja l = this.plugin.core.getLoja(b);
            if (l.getDono().equals(p) || (l.isAdm() && (p.hasPermission("loja.adm") || p.isOp()))) {
                final ItemStack item = p.getItemInHand().clone();
                item.setAmount(1);
                l.setItem(item);
                final Sign s = (Sign)b.getState();
                s.setLine(3, (String)NomeItens.nomes.get(item.getTypeId()));
                s.update();
                p.sendMessage(this.lojaatualizada);
                event.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void comprar(final PlayerInteractEvent event) {
        final Action ac = event.getAction();
        if (!ac.equals((Object)Action.LEFT_CLICK_BLOCK) && !ac.equals((Object)Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        final Block b = event.getClickedBlock();
        final Player p = event.getPlayer();
        if (this.checkPlaca(b) && this.plugin.core.validarLoja(b.getLocation())) {
            final Loja l = this.plugin.core.getLoja(b);
            if (ac.equals((Object)Action.LEFT_CLICK_BLOCK)) {
                if (l.soCompra()) {
                    p.sendMessage(this.naovende);
                }
                else {
                    this.vender(p, l);
                }
            }
            else if (ac.equals((Object)Action.RIGHT_CLICK_BLOCK)) {
                if (l.soVende()) {
                    if (!p.isSneaking()) {
                        p.sendMessage(this.naocompra);
                    }
                }
                else {
                    this.comprar(p, l);
                }
            }
        }
    }
    
    public boolean checkPlaca(final Block b) {
        if (b != null && b.getType() != Material.AIR) {
            final Material tipo = b.getType();
            return tipo == Material.SIGN_POST || tipo == Material.WALL_SIGN;
        }
        return false;
    }
    
    @SuppressWarnings("deprecation")
	public void comprar(final Player p, final Loja l) {
        final ItemStack item = l.getItem();
        final int quantidade = l.getQuantiade();
        final int valor = l.getCompra();
        final OfflinePlayer dono = l.getDono();
        final Chest c = (Chest) l.getBau();
        if (p.isSneaking()) {
            return;
        }
        if (item.getType().getId() == 0) {
            p.sendMessage(this.semitemdefinido);
            return;
        }
        if (c == null && !l.isAdm()) {
            p.sendMessage(this.sembau);
            return;
        }
        if (!l.isAdm() && p == dono) {
            p.sendMessage(this.autocomprar);
            return;
        }
        final String vfi = this.getValorString(valor);
        if (this.possuiDinheiro((OfflinePlayer)p, valor)) {
            if (l.isAdm()) {
                if (this.temEspa\u00e7o((Inventory)p.getInventory(), item, quantidade)) {
                    this.adicionarItem((Inventory)p.getInventory(), item, quantidade);
                    this.retirarDinheiro((OfflinePlayer)p, valor);
                    p.sendMessage(this.comprar.replace("%valor%", vfi).replace("%item%", NomeItens.nomes.get(item.getTypeId())).replace("%quantidade%", new StringBuilder(String.valueOf(quantidade)).toString()));
                }
                else {
                    p.sendMessage(this.semespaco);
                }
            }
            else if (this.contemNoInventario(c.getInventory(), item.getType(), quantidade)) {
                if (this.temEspa\u00e7o((Inventory)p.getInventory(), item, quantidade)) {
                    this.retirarDinheiro((OfflinePlayer)p, valor);
                    this.adicionarDinheiro(dono, valor);
                    final ArrayList<ItemStack> itens = this.removerItens(c.getInventory(), item.getType(), quantidade);
                    for (final ItemStack it : itens) {
                        this.adicionarItem((Inventory)p.getInventory(), it, it.getAmount());
                    }
                    p.sendMessage(this.comprar.replace("%valor%", vfi).replace("%item%", NomeItens.nomes.get(item.getTypeId())).replace("%quantidade%", new StringBuilder(String.valueOf(quantidade)).toString()));
                    try {
                        final Player donom = Bukkit.getServer().getPlayer(dono.getName());
                        donom.sendMessage(this.comprou.replace("%jogador%", p.getName()).replace("%valor%", vfi).replace("%item%", NomeItens.nomes.get(item.getTypeId())).replace("%quantidade%", new StringBuilder(String.valueOf(quantidade)).toString()));
                    }
                    catch (Exception ex) {}
                }
                else {
                    p.sendMessage(this.semespaco);
                }
            }
            else {
                p.sendMessage(this.naocontemnobau.replace("%item%", NomeItens.nomes.get(item.getTypeId())));
            }
        }
        else {
            p.sendMessage(this.semdinheiro);
        }
    }
    
    @SuppressWarnings("deprecation")
	public void vender(final Player p, final Loja l) {
        final ItemStack item = l.getItem();
        final int quantidade = l.getQuantiade();
        final int valor = l.getVenda();
        final OfflinePlayer dono = l.getDono();
        final Chest c = (Chest) l.getBau();
        final boolean tudo = p.isSneaking();
        if (item.getType().getId() == 0) {
            p.sendMessage(this.semitemdefinido);
            return;
        }
        if (c == null && !l.isAdm()) {
            p.sendMessage(this.sembau);
            return;
        }
        if (!l.isAdm() && p == dono) {
            p.sendMessage(this.autovender);
            return;
        }
        final double valord = valor;
        final double quantidaded = quantidade;
        final double vpi = valord / quantidaded;
        final int quant = this.quantidadeNoInv((Inventory)p.getInventory(), item.getType());
        int qf;
        double vf;
        if (tudo) {
            qf = quant;
            vf = vpi * quant;
        }
        else if (quant < quantidade) {
            qf = quant;
            vf = vpi * quant;
        }
        else {
            qf = quantidade;
            vf = valor;
        }
        final Double vfaux = vf;
        final String vfi = this.getValorString(vfaux);
        if (l.isAdm()) {
            if (this.contemNoInventario((Inventory)p.getInventory(), item.getType(), qf)) {
                this.removerItens((Inventory)p.getInventory(), item.getType(), qf);
                this.adicionarDinheiro((OfflinePlayer)p, vf);
                p.sendMessage(this.vender.replace("%valor%", new StringBuilder(String.valueOf(vfi)).toString()).replace("%item%", NomeItens.nomes.get(item.getTypeId())).replace("%quantidade%", new StringBuilder(String.valueOf(qf)).toString()));
            }
            else {
                p.sendMessage(this.semitem);
            }
        }
        else if (this.possuiDinheiro(dono, vf)) {
            if (this.temEspa\u00e7o(c.getInventory(), item, qf)) {
                if (this.contemNoInventario((Inventory)p.getInventory(), item.getType(), qf)) {
                    this.retirarDinheiro(dono, vf);
                    this.adicionarDinheiro((OfflinePlayer)p, vf);
                    final ArrayList<ItemStack> itens = this.removerItens((Inventory)p.getInventory(), item.getType(), qf);
                    for (final ItemStack it : itens) {
                        this.adicionarItem(c.getInventory(), it, it.getAmount());
                    }
                    p.sendMessage(this.vender.replace("%valor%", new StringBuilder(String.valueOf(vfi)).toString()).replace("%item%", NomeItens.nomes.get(item.getTypeId())).replace("%quantidade%", new StringBuilder(String.valueOf(qf)).toString()));
                    try {
                        final Player donom = Bukkit.getServer().getPlayer(dono.getName());
                        donom.sendMessage(this.vendeu.replace("%jogador%", p.getName()).replace("%valor%", new StringBuilder(String.valueOf(vfi)).toString()).replace("%item%", NomeItens.nomes.get(item.getTypeId())).replace("%quantidade%", new StringBuilder(String.valueOf(qf)).toString()));
                    }
                    catch (Exception ex) {}
                }
                else {
                    p.sendMessage(this.semitem);
                }
            }
            else {
                p.sendMessage(this.semespaconobau);
            }
        }
        else {
            p.sendMessage(this.donosemdinheiro);
        }
    }
    
    public void adicionarItem(final Inventory inv, final ItemStack item, final int quantidade) {
        int aux = quantidade;
        if (item.getMaxStackSize() < aux) {
            for (int max = item.getMaxStackSize(); aux > 0; aux -= max) {
                item.setAmount(max);
                inv.addItem(new ItemStack[] { item });
            }
        }
        else {
            item.setAmount(aux);
            inv.addItem(new ItemStack[] { item });
            aux = 0;
        }
    }
    
    public boolean contemNoInventario(final Inventory inv, final Material tipo, final int quantidade) {
        int quant = -1;
        for (int i = 0; i < inv.getSize(); ++i) {
            final ItemStack item = inv.getItem(i);
            if (item != null && item.getType() != Material.AIR && item.getType().equals((Object)tipo)) {
                if (quant == -1) {
                    quant = 0;
                }
                quant += item.getAmount();
            }
        }
        return quant >= quantidade;
    }
    
    public boolean temEspa\u00e7o(final Inventory inv, final ItemStack item, final int quantidade) {
        boolean aux = false;
        if (inv.firstEmpty() == -1) {
            int n = 0;
            for (int i = 0; i < inv.getSize(); ++i) {
                if (inv.getItem(i).getType().equals((Object)item.getType())) {
                    final int qnt = inv.getItem(i).getAmount();
                    final int resto = item.getMaxStackSize() - qnt;
                    n += resto;
                }
            }
            if (n >= quantidade) {
                aux = true;
            }
        }
        else {
            aux = true;
        }
        return aux;
    }
    
    public ArrayList<ItemStack> removerItens(final Inventory inventory, final Material type, int amount) {
        final int size = inventory.getSize();
        final ArrayList<ItemStack> itens = new ArrayList<ItemStack>();
        boolean baux = true;
        for (int slot = 0; slot < size; ++slot) {
            final ItemStack is = inventory.getItem(slot);
            if (is != null) {
                if (type == is.getType()) {
                    if (baux) {
                        final ItemStack aux = inventory.getItem(slot).clone();
                        aux.setAmount(amount);
                        itens.add(aux);
                        baux = false;
                    }
                    final int newAmount = is.getAmount() - amount;
                    if (newAmount > 0) {
                        is.setAmount(newAmount);
                        break;
                    }
                    inventory.clear(slot);
                    amount = -newAmount;
                    if (amount == 0) {
                        break;
                    }
                }
            }
        }
        return itens;
    }
    
    public int quantidadeNoInv(final Inventory inv, final Material type) {
        int quant = 0;
        for (int i = 0; i < inv.getSize(); ++i) {
            final ItemStack item = inv.getItem(i);
            if (item != null && item.getType() != Material.AIR && item.getType().equals((Object)type)) {
                quant += item.getAmount();
            }
        }
        return quant;
    }
    
    public String getValorString(final double d) {
        final double nd = Math.round(d * 100.0) / 100.0;
        final DecimalFormat df = new DecimalFormat("###,###,###,###.##");
        final String s = df.format(nd);
        return s;
    }
    
    public boolean possuiDinheiro(final OfflinePlayer dono, final double d) {
        final double dinheiro = this.plugin.econ.getBalance(dono);
        return dinheiro >= d;
    }
    
    public void adicionarDinheiro(final OfflinePlayer dono, final double d) {
        this.plugin.econ.depositPlayer(dono, d);
    }
    
    public void retirarDinheiro(final OfflinePlayer p, final double d) {
        this.plugin.econ.withdrawPlayer(p, d);
    }
    
    public double dinheiro(final OfflinePlayer p) {
        return this.plugin.econ.getBalance(p);
    }
}
