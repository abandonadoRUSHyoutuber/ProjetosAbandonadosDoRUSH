package me.douglasamv.kitpvp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.nametagedit.plugin.NametagEdit;

import me.douglasamv.kitpvp.cmd.Admin;
import me.douglasamv.kitpvp.cmd.Build;
import me.douglasamv.kitpvp.cmd.Cc;
import me.douglasamv.kitpvp.cmd.Chat;
import me.douglasamv.kitpvp.cmd.Checar;
import me.douglasamv.kitpvp.cmd.Fly;
import me.douglasamv.kitpvp.cmd.Gm;
import me.douglasamv.kitpvp.cmd.InvSee;
import me.douglasamv.kitpvp.cmd.Lag;
import me.douglasamv.kitpvp.cmd.Money;
import me.douglasamv.kitpvp.cmd.Pvp;
import me.douglasamv.kitpvp.cmd.Rank;
import me.douglasamv.kitpvp.cmd.Recraft;
import me.douglasamv.kitpvp.cmd.Report;
import me.douglasamv.kitpvp.cmd.ResetKDR;
import me.douglasamv.kitpvp.cmd.Score;
import me.douglasamv.kitpvp.cmd.Setar;
import me.douglasamv.kitpvp.cmd.Spawn;
import me.douglasamv.kitpvp.cmd.StaffChat;
import me.douglasamv.kitpvp.cmd.Tag;
import me.douglasamv.kitpvp.cmd.Tp;
import me.douglasamv.kitpvp.cmd.TpAll;
import me.douglasamv.kitpvp.cmd.TpHere;
import me.douglasamv.kitpvp.kits.Ajnin;
import me.douglasamv.kitpvp.kits.Anchor;
import me.douglasamv.kitpvp.kits.Archer;
import me.douglasamv.kitpvp.kits.Backpacker;
import me.douglasamv.kitpvp.kits.Berserker;
import me.douglasamv.kitpvp.kits.Camel;
import me.douglasamv.kitpvp.kits.DeshFire;
import me.douglasamv.kitpvp.kits.EnderMage;
import me.douglasamv.kitpvp.kits.Fireman;
import me.douglasamv.kitpvp.kits.FisherMan;
import me.douglasamv.kitpvp.kits.ForceField;
import me.douglasamv.kitpvp.kits.Frosty;
import me.douglasamv.kitpvp.kits.Gladiator;
import me.douglasamv.kitpvp.kits.Hulk;
import me.douglasamv.kitpvp.kits.Kangaroo;
import me.douglasamv.kitpvp.kits.Monk;
import me.douglasamv.kitpvp.kits.Neo;
import me.douglasamv.kitpvp.kits.Neurotic;
import me.douglasamv.kitpvp.kits.Ninja;
import me.douglasamv.kitpvp.kits.Phantom;
import me.douglasamv.kitpvp.kits.Poseidon;
import me.douglasamv.kitpvp.kits.Reaper;
import me.douglasamv.kitpvp.kits.Ryu;
import me.douglasamv.kitpvp.kits.Snail;
import me.douglasamv.kitpvp.kits.Sonic;
import me.douglasamv.kitpvp.kits.Souper;
import me.douglasamv.kitpvp.kits.Stomper;
import me.douglasamv.kitpvp.kits.Switcher;
import me.douglasamv.kitpvp.kits.Vacuum;
import me.douglasamv.kitpvp.kits.Viper;
import me.douglasamv.kitpvp.utils.CombatLog;
import me.douglasamv.kitpvp.utils.CoolDownAPI;
import me.douglasamv.kitpvp.utils.CoolDownRecraft;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Mysql;
import me.douglasamv.kitpvp.utils.Nerf;

public class Main extends JavaPlugin implements Listener {

	public static Plugin pl;
	public static String version;

	public static File file_kits = new File("plugins/SkyKits", "kits.yml");
	public static FileConfiguration cfg_kits;

	public static File file_rank = new File("plugins/SkyKits", "rank.yml");
	public static FileConfiguration cfg_rank;
	
	public static File file_store = new File("plugins/SkyKits", "store.yml");
	public static FileConfiguration cfg_store;

	public static File file_mysql = new File("plugins/SkyKits", "mysql.yml");
	public static FileConfiguration cfg_mysql;

	public static File file_automessage = new File("plugins/SkyKits", "automessage.yml");
	public static FileConfiguration cfg_automessage;

	public static File file_x1 = new File("plugins/SkyKits", "1v1.yml");
	public static FileConfiguration cfg_x1;

	public static File file_warps = new File("plugins/SkyKits", "warps.yml");
	public static FileConfiguration cfg_warps;

	public static File file_chat = new File("plugins/SkyKits", "chat.yml");
	public static FileConfiguration cfg_chat;
	
	public static File file_spawn = new File("plugins/SkyKits", "spawn.yml");
	public static FileConfiguration cfg_spawn;
	
	public static File file_uuid = new File("plugins/SkyKits", "uuid.yml");
	public static FileConfiguration cfg_uuid;

	private static Mysql mysql;

	public static Mysql getMysql() {
		return mysql;
	}

	@Override
	public void onEnable() {
		pl = this;

		if (!getDescription().getName().equals("SkyKits")) {
			Bukkit.getServer().getPluginManager().disablePlugin(Main.pl);
			Bukkit.getConsoleSender().sendMessage("§cDo not rename the plugin!");
			Bukkit.getConsoleSender().sendMessage("§chttps://www.spigotmc.org/resources/skykits-kitpvp-34-kits.23231");
			Bukkit.getConsoleSender().sendMessage("§cBy:Douglas_AMV");
			return;
		}
		version = getDescription().getVersion();

		saveDefaultConfig();
		if (!file_store.exists()) {
			saveResource("store.yml", false);
		}
		if (!file_spawn.exists()) {
			saveResource("spawn.yml", false);
		}
		if (!file_warps.exists()) {
			saveResource("warps.yml", false);
		}
		if (!file_x1.exists()) {
			saveResource("1v1.yml", false);
		}
		if (!file_kits.exists()) {
			saveResource("kits.yml", false);
		}
		if (!file_mysql.exists()) {
			saveResource("mysql.yml", false);
		}
		if (!file_automessage.exists()) {
			saveResource("automessage.yml", false);
		}
		if (!file_chat.exists()) {
			saveResource("chat.yml", false);
		}
		if (!file_chat.exists()) {
			saveResource("chat.yml", false);
		}
		if (!file_uuid .exists()) {
			saveResource("uuid.yml", false);
		}
		if (!file_rank .exists()) {
			saveResource("rank.yml", false);
		}

		try {
			cfg_kits.load(file_kits);
			cfg_rank.load(file_rank);
			cfg_spawn.load(file_spawn);
			cfg_mysql.load(file_mysql);
			cfg_x1.load(file_x1);
			cfg_automessage.load(file_automessage);
			cfg_warps.load(file_warps);
			cfg_store.load(file_store);
			cfg_chat.load(file_chat);
			cfg_uuid.load(file_uuid);
		} catch (IOException | InvalidConfigurationException e1) {
			System.out.println(e1.getMessage());
		}

		mysql = new Mysql(cfg_mysql.getString("mysql.database"), cfg_mysql.getString("mysql.host"),
				cfg_mysql.getString("mysql.port"), cfg_mysql.getString("mysql.pass"),
				cfg_mysql.getString("mysql.user"));

		try {
			mysql.conectar().createStatement().executeUpdate(
					"CREATE TABLE IF NOT EXISTS `pvp` (`nick` varchar(64), `kill` int, `death` int, `money` int, `click` int)");
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cSet the mysql correctly!");
			Bukkit.getConsoleSender().sendMessage("§chttps://www.spigotmc.org/resources/skykits-kitpvp-34-kits.23231");
			Bukkit.getConsoleSender().sendMessage("§cBy:Douglas_AMV");
			Bukkit.getServer().getPluginManager().disablePlugin(Main.pl);
			return;
		}

		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new Nerf(), this);
		getServer().getPluginManager().registerEvents(new HabilidadeApi(), this);
		getServer().getPluginManager().registerEvents(new ProtecaoSpawn(), this);
		getServer().getPluginManager().registerEvents(new Eventos(), this);
		getServer().getPluginManager().registerEvents(new Build(), this);
		getServer().getPluginManager().registerEvents(new Score(), this);
		getServer().getPluginManager().registerEvents(new Kangaroo(), this);
		getServer().getPluginManager().registerEvents(new Tag(), this);
		getServer().getPluginManager().registerEvents(new Recraft(), this);
		getServer().getPluginManager().registerEvents(new CoolDownRecraft(), this);
		getServer().getPluginManager().registerEvents(new FisherMan(), this);
		getServer().getPluginManager().registerEvents(new Archer(), this);
		getServer().getPluginManager().registerEvents(new Sonic(), this);
		getServer().getPluginManager().registerEvents(new CoolDownAPI(), this);
		getServer().getPluginManager().registerEvents(new DeshFire(), this);
		getServer().getPluginManager().registerEvents(new Anchor(), this);
		getServer().getPluginManager().registerEvents(new CombatLog(), this);
		getServer().getPluginManager().registerEvents(new Reaper(), this);
		getServer().getPluginManager().registerEvents(new Phantom(), this);
		getServer().getPluginManager().registerEvents(new Snail(), this);
		getServer().getPluginManager().registerEvents(new Backpacker(), this);
		getServer().getPluginManager().registerEvents(new Hulk(), this);
		getServer().getPluginManager().registerEvents(new Monk(), this);
		getServer().getPluginManager().registerEvents(new Stomper(), this);
		getServer().getPluginManager().registerEvents(new StaffChat(), this);
		getServer().getPluginManager().registerEvents(new Admin(), this);
		getServer().getPluginManager().registerEvents(new Viper(), this);
		getServer().getPluginManager().registerEvents(new Poseidon(), this);
		getServer().getPluginManager().registerEvents(new Fireman(), this);
		getServer().getPluginManager().registerEvents(new Switcher(), this);
		getServer().getPluginManager().registerEvents(new Ninja(), this);
		getServer().getPluginManager().registerEvents(new Ryu(), this);
		getServer().getPluginManager().registerEvents(new Berserker(), this);
		getServer().getPluginManager().registerEvents(new Gladiator(), this);
		getServer().getPluginManager().registerEvents(new Souper(), this);
		getServer().getPluginManager().registerEvents(new Camel(), this);
		getServer().getPluginManager().registerEvents(new Neurotic(), this);
		getServer().getPluginManager().registerEvents(new Vacuum(), this);
		getServer().getPluginManager().registerEvents(new Ajnin(), this);
		getServer().getPluginManager().registerEvents(new ForceField(), this);
		getServer().getPluginManager().registerEvents(new Neo(), this);
		getServer().getPluginManager().registerEvents(new Frosty(), this);
		getServer().getPluginManager().registerEvents(new EnderMage(), this);
		getServer().getPluginManager().registerEvents(new Pvp(), this);
		getServer().getPluginManager().registerEvents(new X1(), this);

		getCommand("build").setExecutor(new Build());
		getCommand("score").setExecutor(new Score());
		getCommand("check").setExecutor(new Checar());
		getCommand("tag").setExecutor(new Tag());
		getCommand("rank").setExecutor(new Rank());
		getCommand("lag").setExecutor(new Lag());
		getCommand("set").setExecutor(new Setar());
		getCommand("cc").setExecutor(new Cc());
		getCommand("chat").setExecutor(new Chat());
		getCommand("spawn").setExecutor(new Spawn());
		getCommand("sc").setExecutor(new StaffChat());
		getCommand("staffchat").setExecutor(new StaffChat());
		getCommand("admin").setExecutor(new Admin());
		getCommand("gm").setExecutor(new Gm());
		getCommand("report").setExecutor(new Report());
		getCommand("invsee").setExecutor(new InvSee());
		getCommand("tpall").setExecutor(new TpAll());
		getCommand("fly").setExecutor(new Fly());
		getCommand("tp").setExecutor(new Tp());
		getCommand("s").setExecutor(new TpHere());
		getCommand("pvp").setExecutor(new Pvp());
		getCommand("1v1").setExecutor(new me.douglasamv.kitpvp.cmd.X1());
		getCommand("money").setExecutor(new Money());
		getCommand("resetkdr").setExecutor(new ResetKDR());
		
		if(!cfg_uuid.getString("uuid").equals("kljashdlkasjhdlasjlkjaskdjndlasdsa684d65as16das687asaskjhldas")) {
			Bukkit.shutdown();
		}
		
		checar();

		new BukkitRunnable() {
			@Override
			public void run() {
				getServer().getWorld("world").setStorm(false);
			}
		}.runTaskTimerAsynchronously(this, 120, 120);

		if (cfg_automessage.getBoolean("auto_msg.show")) {
			String asd = removeUltimoChar(cfg_automessage.getStringList("auto_msg.msg").toString().replace("&", "§"));
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				String[] items = asd.split(",");

				@Override
				public void run() {
					Bukkit.getServer()
							.broadcastMessage(removeFirstChar(items[new java.util.Random().nextInt(items.length)]));

				}
			}, 0L, cfg_automessage.getInt("auto_msg.delay") * 20L);
		}

	}

	public static void checar() {
		new Thread(new Runnable() {
			public void run() {
				try {
					URL url = new URL("http://pastebin.com/raw/agwQqtVK");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String inputLine = in.readLine();
					if (!(inputLine.equals(version))) {
						Bukkit.getConsoleSender().sendMessage("§cOutdated plugin!");
						Bukkit.getConsoleSender()
								.sendMessage("§chttps://www.spigotmc.org/resources/skykits-kitpvp-34-kits.23231");
						Bukkit.getConsoleSender().sendMessage("§cPlugin version:" + version);
						Bukkit.getConsoleSender().sendMessage("§cNew version:" + inputLine);
						Bukkit.getConsoleSender().sendMessage("§cBy:Douglas_AMV");
						// Bukkit.getServer().getPluginManager().disablePlugin(Main.pl);
						in.close();
						return;
					}
					Bukkit.getConsoleSender().sendMessage("§2Plugin Updated!");
					Bukkit.getConsoleSender()
							.sendMessage("§2https://www.spigotmc.org/resources/skykits-kitpvp-34-kits.23231");
					Bukkit.getConsoleSender().sendMessage("§2By:Douglas_AMV");
					in.close();

				} catch (Exception e) {
					Bukkit.getConsoleSender().sendMessage("§cError checking version!");
					Bukkit.getConsoleSender()
							.sendMessage("§chttps://www.spigotmc.org/resources/skykits-kitpvp-34-kits.23231");
					Bukkit.getConsoleSender().sendMessage("§cBy:Douglas_AMV");
					// Bukkit.getServer().getPluginManager().disablePlugin(Main.pl);
				}
			}
		}).start();
	}

	static {
		cfg_kits = YamlConfiguration.loadConfiguration(Main.file_kits);
		cfg_rank = YamlConfiguration.loadConfiguration(Main.file_rank);
		cfg_spawn = YamlConfiguration.loadConfiguration(Main.file_spawn);
		cfg_mysql = YamlConfiguration.loadConfiguration(Main.file_mysql);
		cfg_automessage = YamlConfiguration.loadConfiguration(Main.file_automessage);
		cfg_x1 = YamlConfiguration.loadConfiguration(Main.file_x1);
		cfg_warps = YamlConfiguration.loadConfiguration(Main.file_warps);
		cfg_store = YamlConfiguration.loadConfiguration(Main.file_store);
		cfg_chat = YamlConfiguration.loadConfiguration(Main.file_chat);
		cfg_uuid = YamlConfiguration.loadConfiguration(Main.file_uuid);
	}

	public static String removeFirstChar(String s) {
		return s.substring(1);
	}

	public static String removeUltimoChar(String s) {
		int tamanho = s.length();
		s = s.substring(0, tamanho - 1);
		return s;
	}

	public static void setarTag(Player p, String tag) {
		new BukkitRunnable() {

			@Override
			public void run() {
				p.setDisplayName(tag + p.getName() + "§r");
				NametagEdit.getApi().setPrefix(p, tag);
			}
		}.runTaskAsynchronously(pl);
	}

	@EventHandler
	public void loginMSQL(PlayerLoginEvent e) {
		new BukkitRunnable() {

			@Override
			public void run() {
				String nick = e.getPlayer().getName();
				ResultSet rs;
				try {
					rs = Main.getMysql().conectar().createStatement()
							.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + nick + "';");
					if (!rs.next()) {
						Main.getMysql().conectar().createStatement().executeUpdate(
								"INSERT INTO `pvp` (`nick` , `kill` , `death` , `money`, `click`) VALUES ('" + nick
										+ "', '0', '0', '0', '0');");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e1) {
				}
			}
		}.runTaskAsynchronously(Main.pl);
	}

}
