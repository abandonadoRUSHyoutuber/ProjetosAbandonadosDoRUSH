package edmarreports;

import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.SkullType;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.HumanEntity;
import java.util.Date;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.ArrayList;
import org.bukkit.inventory.Inventory;
import java.sql.Connection;
import org.bukkit.plugin.java.JavaPlugin;

public class EdmarReports extends JavaPlugin
{
    private static Connection conn;
    protected static Inventory GUI;
    protected static Inventory OPTIONS_GUI;
    private static boolean needUpdate;
    private static boolean updating;
    private static ArrayList<String> reporting;
    private static HashMap<Integer, Report> cache;
    private static final SimpleDateFormat DATE_FORMAT;
    
    static {
        EdmarReports.needUpdate = true;
        EdmarReports.reporting = new ArrayList<String>();
        EdmarReports.cache = new HashMap<Integer, Report>();
        DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy H:m");
    }
    
    public void onEnable() {
        this.saveDefaultConfig();
        this.reloadConfig();
        final String host = this.getConfig().getString("MySQL.host").trim();
        final String user = this.getConfig().getString("MySQL.user").trim();
        final String pass = this.getConfig().getString("MySQL.pass").trim();
        final String dbname = this.getConfig().getString("MySQL.dbname").trim();
        final int port = this.getConfig().getInt("MySQL.port");
        try {
            EdmarReports.conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbname, user, pass);
            EdmarReports.conn.prepareStatement("CREATE TABLE IF NOT EXISTS reports (id INT PRIMARY KEY AUTO_INCREMENT, player VARCHAR(16), reportado VARCHAR(16), motivo VARCHAR(32), data TIMESTAMP);").executeUpdate();
        }
        catch (SQLException e) {
            this.getLogger().severe("*** Erro ao iniciar MySQL: " + e.getMessage());
            this.setEnabled(false);
            return;
        }
        final int size = this.getConfig().getInt("TamanhoGUI");
        final String title = this.getMsg("TituloGUI");
        EdmarReports.GUI = Bukkit.createInventory((InventoryHolder)null, size, title);
        (EdmarReports.OPTIONS_GUI = Bukkit.createInventory((InventoryHolder)null, 9, this.getMsg("GUIOpcoes.Titulo"))).setItem(2, this.getItem(Material.COMPASS, this.getMsg("GUIOpcoes.Teleportar")));
        EdmarReports.OPTIONS_GUI.setItem(6, this.getItem(Material.EYE_OF_ENDER, this.getMsg("GUIOpcoes.Inocentar")));
        Bukkit.getPluginManager().registerEvents((Listener)new Listeners(), (Plugin)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (command.getName().equalsIgnoreCase("report")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("[EdmarReports] Comando apenas para jogadores in-game.");
                return false;
            }
            final Player p = (Player)sender;
            if (args.length == 0) {
                p.sendMessage(this.getMsg("uso-correto"));
                return false;
            }
            final Player t = Bukkit.getPlayerExact(args[0]);
            if (t == null) {
                p.sendMessage(this.getMsg("jogador-invalido"));
                return false;
            }
            p.sendMessage(this.getMsg("report-cabecalho").replace("@player", t.getName()));
            for (final String reason : this.getConfig().getStringList("motivos")) {
                final TextComponent cmp = new TextComponent(this.getMsg("report-motivo").replace("@motivo", reason));
                final BaseComponent[] hover = new ComponentBuilder(this.getMsg("report-hover").replace("@motivo", reason).replace("@player", t.getName())).create();
                cmp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hover));
                cmp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dreport " + t.getName() + " " + reason));
                p.spigot().sendMessage((BaseComponent)cmp);
            }
            p.sendMessage(this.getMsg("report-rodape"));
            EdmarReports.reporting.add(p.getName());
        }
        else if (command.getName().equalsIgnoreCase("dreport")) {
            if (!(sender instanceof Player)) {
                return false;
            }
            if (args.length < 2) {
                return false;
            }
            final Player p = (Player)sender;
            if (!EdmarReports.reporting.contains(p.getName())) {
                p.sendMessage(this.getMsg("reportar-novamente"));
                return false;
            }
            final Player t = Bukkit.getPlayerExact(args[0]);
            if (t == null) {
                return false;
            }
            String reason = "";
            for (int i = 1; i < args.length; ++i) {
                reason = String.valueOf(reason) + args[i] + " ";
            }
            reason = reason.trim();
            if (!this.getConfig().getStringList("motivos").contains(reason)) {
                return false;
            }
            EdmarReports.reporting.remove(p.getName());
            final Report report = new Report(p.getName(), t.getName(), reason, new Date());
            final boolean success = this.registerReport(report);
            if (success) {
                EdmarReports.needUpdate = true;
                p.sendMessage(this.getMsg("sucesso-report").replace("@player", t.getName()));
            }
            else {
                p.sendMessage(this.getMsg("falha-report"));
            }
        }
        else if (command.getName().equalsIgnoreCase("reports")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("[EdmarReports] Comando apenas para jogadores in-game.");
                return false;
            }
            final Player p = (Player)sender;
            if (!p.hasPermission("edmarreports.staff")) {
                p.sendMessage(this.getMsg("sem-permissao"));
                return false;
            }
            if (EdmarReports.updating) {
                p.sendMessage(this.getMsg("atualizando"));
                return false;
            }
            if (EdmarReports.needUpdate) {
                EdmarReports.cache.clear();
                EdmarReports.GUI.clear();
                EdmarReports.updating = true;
                for (final HumanEntity he : EdmarReports.GUI.getViewers()) {
                    he.closeInventory();
                    he.sendMessage(this.getMsg("necessario-atualizar"));
                }
                new Thread() {
                    @Override
                    public void run() {
                        final ArrayList<Report> reports = EdmarReports.this.getReports();
                        if (reports.isEmpty()) {
                            EdmarReports.access$1(false);
                            EdmarReports.access$2(false);
                            p.sendMessage(EdmarReports.this.getMsg("nenhum-report"));
                            return;
                        }
                        for (int i = 0; i < reports.size(); ++i) {
                            final Report r = reports.get(i);
                            final ItemStack skull = EdmarReports.this.generateSkull(r);
                            EdmarReports.GUI.setItem(i, skull);
                            EdmarReports.cache.put(i, r);
                        }
                        EdmarReports.access$1(false);
                        EdmarReports.access$2(false);
                        p.openInventory(EdmarReports.GUI);
                    }
                }.start();
            }
            else {
                p.openInventory(EdmarReports.GUI);
            }
        }
        return true;
    }
    
    private boolean registerReport(final Report r) {
        try {
            final PreparedStatement st = EdmarReports.conn.prepareStatement("INSERT INTO reports (player, reportado, motivo, data) VALUES (?, ?, ?, ?);");
            st.setString(1, r.getSender());
            st.setString(2, r.getReported());
            st.setString(3, r.getReason());
            st.setTimestamp(4, new Timestamp(r.getDate().getTime()));
            st.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            this.getLogger().severe("*** Erro ao adicionar report: " + e.getMessage());
            return false;
        }
    }
    
    private ArrayList<Report> getReports() {
        final ArrayList<Report> reports = new ArrayList<Report>();
        try {
            final PreparedStatement st = EdmarReports.conn.prepareStatement("SELECT * FROM reports ORDER BY data LIMIT " + EdmarReports.GUI.getSize() + ";");
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final int id = rs.getInt("id");
                final String player = rs.getString("player");
                final String reported = rs.getString("reportado");
                final String reason = rs.getString("motivo");
                final Date date = new Date(rs.getTimestamp("data").getTime());
                final Report report = new Report(player, reported, reason, date);
                report.setId(id);
                reports.add(report);
            }
        }
        catch (SQLException e) {
            this.getLogger().severe("*** Erro ao obter reports: " + e.getMessage());
        }
        return reports;
    }
    
    protected static void deleteReport(final Report r) {
        try {
            final PreparedStatement st = EdmarReports.conn.prepareStatement("DELETE FROM reports WHERE id = ?;");
            st.setInt(1, r.getId());
            st.executeUpdate();
            EdmarReports.needUpdate = true;
        }
        catch (SQLException e) {
            System.out.println("*** Erro ao deletar report: " + e.getMessage());
        }
    }
    
    protected static Report getReport(final int slot) {
        return EdmarReports.cache.get(slot);
    }
    
    @SuppressWarnings("unchecked")
	private ItemStack generateSkull(final Report r) {
        final ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
        final SkullMeta sm = (SkullMeta)is.getItemMeta();
        sm.setDisplayName(this.getMsg("Cabecas.nome").replace("@player", r.getReported()));
        final ArrayList<String> lore = new ArrayList<String>();
        for (final String line : this.getConfig().getStringList("Cabecas.lore")) {
            lore.add(line.replace("&", "§").replace("@motivo", r.getReason()).replace("@reportador", r.getSender()).replace("@data", EdmarReports.DATE_FORMAT.format(r.getDate())).replace("@id", Integer.toString(r.getId())));
        }
        sm.setLore((List)lore);
        sm.setOwner(r.getReported());
        is.setItemMeta((ItemMeta)sm);
        return is;
    }
    
    private ItemStack getItem(final Material m, final String display) {
        final ItemStack is = new ItemStack(m);
        final ItemMeta im = is.getItemMeta();
        im.setDisplayName(display);
        is.setItemMeta(im);
        return is;
    }
    
    private String getMsg(final String key) {
        return this.getConfig().getString(key).replace("&", "§");
    }
    
    static /* synthetic */ void access$1(final boolean needUpdate) {
        EdmarReports.needUpdate = needUpdate;
    }
    
    static /* synthetic */ void access$2(final boolean updating) {
        EdmarReports.updating = updating;
    }
}
