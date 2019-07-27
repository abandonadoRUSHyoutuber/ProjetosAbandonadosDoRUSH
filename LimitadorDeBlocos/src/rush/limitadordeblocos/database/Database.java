package rush.limitadordeblocos.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

import org.bukkit.Bukkit;

import rush.limitadordeblocos.Main;

public class Database {

	private String user, password, host, database;
	private Connection connection;
	private File db;
	private boolean mySql;
	private Main plugin;
	
	public Database(Main plugin, String user, String password, String host, String database) {
		this.plugin = plugin;
		this.user = user;
		this.password = password;
		this.host = host;
		this.database = database;
		this.mySql = true;
	}

	public Database(Main plugin, String string, File dataFolder) {
		File db = new File(dataFolder + File.separator + "player-blocks.db");
		if (!db.exists()) {
			try {
				db.createNewFile();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		this.db = db;
		this.mySql = false;
		this.database = "Boosters";
		this.plugin = plugin;
	}
	
	public void startConnection() {
		try {
			if (mySql) {
				Class.forName("com.mysql.jdbc.Driver");
				this.connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database + "", user, password);
			} else {
				Class.forName("org.sqlite.JDBC");
				this.connection = DriverManager.getConnection("jdbc:sqlite:" + db.getAbsolutePath());
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void createDefaultTable() {
		try {
			PreparedStatement statement = this.connection.prepareStatement(SQL.createTable);
			statement.execute();
			statement.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void loadDatabaseCache() {
		Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
			@Override
			public void run() {
				try {
					Map<String, Map<String, Integer>> users = plugin.blockmanager.getUsers();
					PreparedStatement statement = connection.prepareStatement(SQL.loadTable);
					ResultSet result = statement.executeQuery();
					while (result.next()) {
						String player = result.getString("player");
						String text = result.getString("blocks");
						Map<String, Integer> blocks = new HashMap<>();
						if (text.length() > 1) {
							for (String input : text.split("-")) {
								String[] info = input.split(":");
								String block = info[0] + ":" + info[1];
								Integer used = Integer.parseInt(info[2]);
								blocks.put(block, used);
							}
						}
						users.put(player, blocks);
					}
					result.close();
					statement.close();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void startTaskManager() {
		Bukkit.getScheduler().runTaskTimerAsynchronously(Main.get(), new Runnable() {
			@Override
			public void run() {
				savePlayers();
			}
		}, 0L, 6000L);
	}
	
	public void savePlayers() {
		try {
			Map<String, Map<String, Integer>> users = plugin.blockmanager.getUsers();
			PreparedStatement statement = this.connection.prepareStatement(SQL.updateInfo);
			for (Entry<String, Map<String, Integer>> entry : users.entrySet()) {
				StringJoiner text = new StringJoiner("-");
				for (Entry<String, Integer> block: entry.getValue().entrySet()) {
					text.add(block.getKey() + ":" + block.getValue());
				}
				statement.setString(1, text.toString());
				statement.setString(2, entry.getKey());
				statement.addBatch();
			}
			statement.executeBatch();
			statement.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void savePlayer(String player) {
		try {
			PreparedStatement statement = this.connection.prepareStatement(SQL.insertInfo);
			statement.setString(1, player);
			statement.setString(2, "");
			statement.executeUpdate();
			statement.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			this.connection.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	private static final class SQL {
		
		private static final String
		
		createTable = "CREATE TABLE IF NOT EXISTS PBlocks(" + 
				      "   player varchar(16) PRIMARY KEY,"  + 
				      "   blocks text NOT NUll"        + 
				      ");"                                  ,
		
				      
		loadTable   = "SELECT * FROM PBlocks"               ,
		
		
		insertInfo  = "INSERT INTO PBlocks VALUES (?, ?);"  ,
		
		
		updateInfo  = "UPDATE PBlocks "                     +
				      "SET blocks = ? "                     +
		              "WHERE player = ?"                    ;
	}

}
