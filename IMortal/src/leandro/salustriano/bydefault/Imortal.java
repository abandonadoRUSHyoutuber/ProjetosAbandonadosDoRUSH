package leandro.salustriano.bydefault;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.devpaulo.legendchat.api.Legendchat;
import leandro.salustriano.commands.Commands;
import leandro.salustriano.commands.PlayerCommands;
import leandro.salustriano.recursos.Events;
import leandro.salustriano.recursos.ResourcesThreadEvent;

public class Imortal extends JavaPlugin {
	
	public Manager messages = new Manager(this);
	public Manager assets = new Manager(this);
	public Manager log = new Manager(this);
	public Manager banidos = new Manager(this);
	public ResourcesThreadEvent srcEvents;

	public void onEnable() {
		this.srcEvents = new ResourcesThreadEvent(this);
		Legendchat.getChannelManager();
		Bukkit.getPluginManager().registerEvents(new Events(this), this);

		this.messages.mkdir("messages.yml", getDataFolder());
		this.assets.mkdir("assets.yml", getDataFolder());
		this.log.mkdir("log.yml", getDataFolder());
		this.banidos.mkdir("banidos.yml", getDataFolder());
		if (!new File(getDataFolder(), "config.yml").exists()) {
			saveDefaultConfig();
		}
		this.messages.loadMessageConfig();
		this.assets.loadMessageConfig();
		this.log.loadMessageConfig();
		this.banidos.loadMessageConfig();
		this.srcEvents.threadEventImortal(true);
		this.srcEvents.threadCheckImortal(true);
		getCommand("imortal-op").setExecutor(new Commands(this));
		getCommand("imortal").setExecutor(new PlayerCommands(this));
	}

	public void onDisable() {
		if ((ResourcesThreadEvent.player1 != null) || (ResourcesThreadEvent.player2 != null)) {
			if (ResourcesThreadEvent.player1 != null) {
				this.srcEvents.src.teleportPlayer(ResourcesThreadEvent.player1, "saida");
				ResourcesThreadEvent.player1 = null;
			} else {
				this.srcEvents.src.teleportPlayer(ResourcesThreadEvent.player2, "saida");
				ResourcesThreadEvent.player2 = null;
			}
		} else if (!ResourcesThreadEvent.fila.isEmpty()) {
			for (int i = 0; i < ResourcesThreadEvent.fila.size(); i++) {
				this.srcEvents.src.teleportPlayer((Player) ResourcesThreadEvent.fila.get(i), "saida");
				ResourcesThreadEvent.fila.remove(ResourcesThreadEvent.fila.get(i));
			}
		}
		this.srcEvents.threadEventImortal(false);
		this.srcEvents.threadCheckImortal(false);
	}
}
