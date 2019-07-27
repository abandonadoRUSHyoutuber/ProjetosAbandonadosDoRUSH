package rush.limitadordeblocos;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import rush.limitadordeblocos.database.Database;
import rush.limitadordeblocos.listeners.BlockBreak;
import rush.limitadordeblocos.listeners.BlockPlace;
import rush.limitadordeblocos.listeners.PlayerJoin;
import rush.limitadordeblocos.manager.BlockManager;

public class Main extends JavaPlugin {
	
	public Database database;
	public BlockManager blockmanager;
	private static Main plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		registerEvents();
		createBlockManager();
		createDatabase();
	}

	@Override
	public void onDisable() {
		this.database.savePlayers();
	}
	
	private void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new BlockBreak(this), this);
		pm.registerEvents(new BlockPlace(this), this);
		pm.registerEvents(new PlayerJoin(this), this);
	}
	
	private void createBlockManager() {
		this.blockmanager = new BlockManager(this);
	}
	
	private void createDatabase() {
		if (getConfig().getBoolean("MySQL.Ativar")) {
			String user, password, host, database;
			user = getConfig().getString("MySQL.User");
			password = getConfig().getString("MySQL.Password");
			host = getConfig().getString("MySQL.Host");
			database = getConfig().getString("MySQL.Database");
			this.database = new Database(this, user, password, host, database);
		} else {
			this.database = new Database(this, "Boosters", getDataFolder());
		}
		this.database.startConnection();
		this.database.createDefaultTable();
		this.database.loadDatabaseCache();
		this.database.startTaskManager();
	}
	
	public static Main get() {
		return plugin;
	}

}