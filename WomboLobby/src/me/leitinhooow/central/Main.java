package me.leitinhooow.central;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.leitinhooow.api.ChatF;
import me.leitinhooow.api.Evento;
import me.leitinhooow.cmd.Build;
import me.leitinhooow.cmd.ChatOff;
import me.leitinhooow.cmd.ChatStaff;
import me.leitinhooow.cmd.Clear;
import me.leitinhooow.cmd.Fly;
import me.leitinhooow.cmd.Gamemode;
import me.leitinhooow.cmd.Tag;
import me.leitinhooow.cmd.Tell;
import me.leitinhooow.cmd.TpGeral;
import me.leitinhooow.gui.MENUZIN;
import me.leitinhooow.score.Scoreboarding;

public class Main extends JavaPlugin{
	
	
	public static String loja;
	public static String prefix;
	public static String Score_Name;
	  private FileConfiguration config;
	
	
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("§b> §8Voce esta ultilizando a versao 1.0 do §bWomboLobby");
		Bukkit.getConsoleSender().sendMessage("§b> §8Plugin feito por §b@LeitinhooowEz");
		Bukkit.getConsoleSender().sendMessage("§b> §8Este plugin necessita de §bBungeeCord§8.");
	    saveDefaultConfig();
	    this.config = getConfig();
		
		getCommand("fly").setExecutor(new Fly());
		getCommand("tag").setExecutor(new Tag());
		getCommand("pchat").setExecutor(new ChatOff());
		getCommand("staffchat").setExecutor(new ChatStaff());
		getCommand("gm").setExecutor(new Gamemode());
		getCommand("tpall").setExecutor(new TpGeral());
		getCommand("tell").setExecutor(new Tell());
		getCommand("cc").setExecutor(new Clear());
		getCommand("build").setExecutor(new Build());
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Evento(), this);
		pm.registerEvents(new ChatF(), this);
		pm.registerEvents(new MENUZIN(), this);
		pm.registerEvents(new Tag(), this);
		pm.registerEvents(new Scoreboarding(), this);
	    pm.registerEvents(new Tell(), this);
	    pm.registerEvents(new Build(), this);
	    
		loja = this.config.getString("Loja").replace("&", "§");
		Score_Name = this.config.getString("Nome_Scoreboard").replace("&", "§");
		prefix = this.config.getString("Prefix").replace("&", "§");
	
	}
}
