package me.leitinho;

import java.awt.geom.Area;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.leitinho.cmd.Aplicar;
import me.leitinho.cmd.Chat;
import me.leitinho.cmd.ChatStaff;
import me.leitinho.cmd.Chat_Lindu;
import me.leitinho.cmd.Fly;
import me.leitinho.cmd.Gamemode;
import me.leitinho.cmd.Tell;
import me.leitinho.cmd.TogglePvP;
import me.leitinho.cmd.aTAG;
import me.leitinho.cmd.arena;

public class Main
  extends JavaPlugin
{
  public static String prefix;
  public static String Form;
  public static Plugin plugin;
  public static Main instance;
  public static String cor;
public static String semperm;
  private FileConfiguration config;
  
  public void onEnable()
  {
	  
    plugin = this;
    instance = this;
    RegisterEvents();
    Comandos();
    saveDefaultConfig();
    this.config = getConfig();
    prefix = this.config.getString("Prefix").replace("{default}", "§eLobby").replace("&", "§").replace("{setinha}", "§");
    Form = this.config.getString("Formulario").replace("&", "§").replace("{setinha}", "§");
    Bukkit.getConsoleSender().sendMessage(Main.prefix + "§7 Plugin ativado com sucesso!");
    Bukkit.getConsoleSender().sendMessage(Main.prefix + "§7 Plugin desenvolvido por Leitinho (Todos direitos reservados)");
    Bukkit.getConsoleSender().sendMessage(Main.prefix + "§7 Feito para: TutssBrUtilidades)");
  }
  
  public void onDisable()
  {
    Bukkit.getConsoleSender().sendMessage(Main.prefix + "§7 Plugin desativado com sucesso!");
    Bukkit.getConsoleSender().sendMessage(Main.prefix + "§7 Plugin desenvolvido por Leitinho");
    Bukkit.getConsoleSender().sendMessage(Main.prefix + "§7 Este plugin nao foi decompilado! no cry :)");
  }
  
  public static Main getIntance()
  {
    return instance;
  }
  
  public static Plugin getPlugin()
  {
    return plugin;
  }
  
  public void RegisterEvents()
  {
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(new aTAG(), this);
    pm.registerEvents(new Chat(), this);
    pm.registerEvents(new Tell(), this);
  pm.registerEvents(new Chat_Lindu(), this);
  }
  
  public void Comandos() {
  getCommand("fly").setExecutor(new Fly());
  getCommand("tag").setExecutor(new aTAG());
  getCommand("gm").setExecutor(new Gamemode());
  getCommand("chat").setExecutor(new Chat());
  getCommand("pvp").setExecutor(new TogglePvP());
  getCommand("tell").setExecutor(new Tell());
  getCommand("arena").setExecutor(new arena());
  getCommand("aplicar").setExecutor(new Aplicar());
  getCommand("staff").setExecutor(new ChatStaff());
  {
  }
}
}
