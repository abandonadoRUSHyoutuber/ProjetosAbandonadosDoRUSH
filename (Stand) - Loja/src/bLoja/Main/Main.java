package bLoja.Main;

import org.bukkit.plugin.java.*;
import net.milkbowl.vault.economy.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.command.*;
import java.net.*;
import org.bukkit.configuration.file.*;
import java.util.*;
import java.io.*;
import org.bukkit.plugin.*;

public class Main extends JavaPlugin
{
    public static File msgfile;
    public static FileConfiguration msg;
    public static File cfgfile;
    public static FileConfiguration cfg;
    public static File bdfile;
    public static FileConfiguration bd;
    public Economy econ;
    LojaChecagem chec;
    LojaCore core;
    LojaCriar criar;
    LojaGUI gui;
    LojaMecanica mec;
    LojaSeguran\u00e7a seg;
    
    public Main() {
        this.econ = null;
    }
    
    public void onEnable() {
        this.iniciarConfig();
        this.core = new LojaCore(this);
        this.carregarLojas();
        this.chec = new LojaChecagem(this);
        this.criar = new LojaCriar(this);
        this.gui = new LojaGUI(this);
        this.mec = new LojaMecanica(this);
        this.seg = new LojaSeguran\u00e7a(this);
        this.getLogger().info("Plugin habilitado!");
        this.getLogger().info("Autor: Soldado_08");
        Bukkit.getServer().getPluginManager().registerEvents((Listener)this.criar, (Plugin)this);
        Bukkit.getServer().getPluginManager().registerEvents((Listener)this.mec, (Plugin)this);
        Bukkit.getServer().getPluginManager().registerEvents((Listener)this.chec, (Plugin)this);
        Bukkit.getServer().getPluginManager().registerEvents((Listener)this.seg, (Plugin)this);
        Bukkit.getServer().getPluginManager().registerEvents((Listener)this.gui, (Plugin)this);
        this.getCommand("lojagui").setExecutor((CommandExecutor)new Comandos(this));
        NomeItens.carregarNomes();
        if (!this.iniciarEconomia()) {
            this.getLogger().severe(String.format("[%s] - Desabilitado por falta do plugin Vault!", this.getDescription().getName()));
            this.getServer().getPluginManager().disablePlugin((Plugin)this);
        }
    }
    
    public void onDisable() {
        this.getLogger().info("Plugin desabilitado!");
        this.getLogger().info("Autor: Soldado_08");
        this.salvarLojas();
    }
    
    public static String getHTML(final String urlToRead) throws Exception {
        final StringBuilder result = new StringBuilder();
        final URL url = new URL(urlToRead);
        final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }
    
    public void iniciarConfig() {
        if (Main.msgfile == null) {
            Main.msgfile = new File(this.getDataFolder(), "mensagens.yml");
        }
        if (!Main.msgfile.exists()) {
            this.saveResource("mensagens.yml", false);
        }
        Main.msg = (FileConfiguration)YamlConfiguration.loadConfiguration(Main.msgfile);
        if (Main.cfgfile == null) {
            Main.cfgfile = new File(this.getDataFolder(), "config.yml");
        }
        if (!Main.cfgfile.exists()) {
            this.saveResource("config.yml", false);
        }
        Main.cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(Main.cfgfile);
        if (Main.bdfile == null) {
            Main.bdfile = new File(this.getDataFolder(), "lojas.dat");
        }
        if (!Main.bdfile.exists()) {
            this.saveResource("lojas.dat", false);
        }
        Main.bd = (FileConfiguration)YamlConfiguration.loadConfiguration(Main.bdfile);
    }
    
    public void carregarLojas() {
        if (Main.bd.getStringList("lojas") != null) {
            final List<String> s = (List<String>)Main.bd.getStringList("lojas");
            for (final String str : s) {
                this.core.recarregarLoja(str);
            }
        }
    }
    
    public void salvarLojas() {
        final ArrayList<Loja> lojas = new ArrayList<Loja>(this.core.lojas.values());
        if (Main.bd.getStringList("lojas") != null) {
            final List<String> s = (List<String>)Main.bd.getStringList("lojas");
            s.clear();
            for (final Loja l : lojas) {
                if (l != null) {
                    final String str = l.getString();
                    s.add(str);
                    this.getLogger().info("LOJA DE " + l.getDono().getName() + " FOI SALVA COM SUCESSO!!!");
                }
                else {
                    this.getLogger().info("LOJA NULA!!!!!!!!!!!!!!");
                }
            }
            Main.bd.set("lojas", (Object)s);
            try {
                Main.bd.save(Main.bdfile);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
	private boolean iniciarEconomia() {
        if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        @SuppressWarnings("rawtypes")
		final RegisteredServiceProvider<Economy> rsp = (RegisteredServiceProvider<Economy>)this.getServer().getServicesManager().getRegistration((Class)Economy.class);
        if (rsp == null) {
            return false;
        }
        this.econ = (Economy)rsp.getProvider();
        return this.econ != null;
    }
}
