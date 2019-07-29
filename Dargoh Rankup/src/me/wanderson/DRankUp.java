package me.wanderson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.wanderson.comandos.Comandos;
import me.wanderson.enventos.Eventos;
import me.wanderson.utils.Funcoes;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class DRankUp extends JavaPlugin {

	private File players = null;
	private FileConfiguration playersConfig = null;
	private File ranks = null;
	private FileConfiguration ranksConfig = null;
	private File dep = null;
	private FileConfiguration depConfig = null;
	public Economy economy = null;
	public boolean Clans = false;
	public HashMap<UUID, List<ItemStack>> chestMinerios = new HashMap<>();
	private static Chat chat = null;

	public void onEnable() {
		saveDefaultConfig();
		int chave = 2;
		if (chave == 0) {
			Message("c", "A chave informada nao existe em nosso banco de dados.");
			getServer().getPluginManager().disablePlugin(this);
		} else if (chave == 1) {
			Message("c", "A chave informada esta em uso no momento.");
			getServer().getPluginManager().disablePlugin(this);
		} else if (chave == 2) {
			Message("a", "-=-=-=-=-=-=-=-=-=-=-=-=-");
			Message("a", "|    DRankUp Iniciado   |");
			Message("a", "|    Criador: Derson    |");
			Message("c", "|  Editado por: RUSHyt  |");
			Message("a", "-=-=-=-=-=-=-=-=-=-=-=-=-");
			Bukkit.getPluginManager().registerEvents(new Eventos(), this);
			getCommand("ranks").setExecutor(new Comandos());
			getCommand("rankup").setExecutor(new Comandos());
			getCommand("deposito").setExecutor(new Comandos());
			setupEconomy();
			Plugin plug = getServer().getPluginManager().getPlugin("SimpleClans");
			if (plug != null) {
				Clans = true;
			}
			File p = new File(getDataFolder(), "players.yml");
			if (!p.exists()) {
				saveResource("players.yml", false);
			}
			File r = new File(getDataFolder(), "ranks.yml");
			if (!r.exists()) {
				saveResource("ranks.yml", false);
			}
			File d = new File(getDataFolder(), "depositos.yml");
			if (!d.exists()) {
				saveResource("depositos.yml", false);
			}
			verificaVips();
			loadDeposito();
			setupChat();
			runTab();
		}
	}

	public void onDisable() {
		Message("c", "-=-=-=-=-=-=-=-=-=-=-=-=-");
		Message("c", "|   DRankUp Desligado   |");
		Message("c", "|   Criador: Derson     |");
		Message("c", "|  Editado por: RUSHyt  |");
		Message("c", "-=-=-=-=-=-=-=-=-=-=-=-=-");
		saveDeposito();
	}
	
	private void runTab() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for (Player player : Bukkit.getOnlinePlayers()) {
					String s = chat.getPlayerPrefix(player);
					if (s == null) {
						player.sendMessage(ChatColor.RED + "No Prefix Found");
					}
					if (player.hasPermission("drankup.tab.use")) {
						player.setPlayerListName(ChatColor.translateAlternateColorCodes('&',
										getConfig().getString("TabFormat")
										.replace("%prefix%", seVip(chat.getPlayerPrefix(player)))
										.replace("%name%", player.getName())
										.replace("%suffix%", chat.getPlayerSuffix(player))
										.replace("%displayname%", player.getDisplayName())));
					} else {
						player.setPlayerListName(ChatColor.translateAlternateColorCodes('&',
										getConfig().getString("NoPermFormat")
										.replace("%prefix%", seVip(chat.getPlayerPrefix(player)))
										.replace("%name%", player.getName())
										.replace("%suffix%", chat.getPlayerSuffix(player))
										.replace("%displayname%", player.getDisplayName())));
					}
				}
			}
		}, 20L, 30L);
	}
	
	private String seVip(String prefix) {
		if(prefix.contains(" ")) {
			String[] newPrefix = prefix.split(" ");
			if(newPrefix[0] != null)return newPrefix[0];
		}
		return prefix;
	}
	
	private boolean setupChat() {
		RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
		chat = (Chat) rsp.getProvider();
		return chat != null;
	}
	
	@SuppressWarnings({ "unchecked" })
	public void loadDeposito() {
		for(String uuid : getDepositos().getKeys(false)) {
			List<ItemStack> itensC = (List<ItemStack>) getDepositos().get(uuid + ".itens");
			if(itensC != null) {
				chestMinerios.put(UUID.fromString(uuid), itensC);
			}
		}
	}
	
	public void saveDeposito() {
		for(UUID uuid : chestMinerios.keySet()) {
			List<ItemStack> itensC = new ArrayList<>();
			Funcoes.setThisDeposito(chestMinerios, uuid, Material.LAPIS_BLOCK, itensC);
			Funcoes.setThisDeposito(chestMinerios, uuid, Material.COAL_BLOCK, itensC);
			Funcoes.setThisDeposito(chestMinerios, uuid, Material.REDSTONE_BLOCK, itensC);
			Funcoes.setThisDeposito(chestMinerios, uuid, Material.DIAMOND_BLOCK, itensC);
			Funcoes.setThisDeposito(chestMinerios, uuid, Material.EMERALD_BLOCK, itensC);
			getDepositos().set(uuid + ".itens", itensC);
			saveDepositos();
			reloadDepositos();
		}
	}

	private int verificaVips() {
		return Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					Funcoes.setScoreboard(p);
					Funcoes.setJoinRank(p);
					String rankAtual = getPlayers().getString("players." + p.getName());
					PermissionUser user = PermissionsEx.getUser(p);
					int i = 0;
					for (String vip : getConfig().getConfigurationSection("vips").getKeys(false)) {
						if (user.getParentIdentifiers().contains(vip)) {
							for (World w : Bukkit.getWorlds()) {
								String prefix = getConfig().getString("vips." + vip).replace("&", "§")
										+ " " +getRanks().getString("ranks." + rankAtual + ".prefix").replace("&", "§");
								if (!user.getPrefix(w.getName()).equals(prefix)) {
									user.setPrefix(prefix, w.getName());
								}
							}
							break;
						} else {
							i++;
						}

						if (getConfig().getConfigurationSection("vips").getKeys(false).size() == i) {
							for (World w : Bukkit.getWorlds()) {
								String prefix = getRanks().getString("ranks." + rankAtual + ".prefix").replace("&",
										"§");
								if (!user.getPrefix(w.getName()).equals(prefix)) {
									user.setPrefix(prefix, w.getName());
								}
							}
							break;
						}
					}
				}
			}
		}, 0L, 5 * 20L);
	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager()
				.getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}

		return (economy != null);
	}

	public List<String> reverse(List<String> list) {
		ArrayList<String> array = new ArrayList<String>();
		for (int i = list.size() - 1; i >= 0; i--) {
			array.add(list.get(i));
		}
		return array;
	}

	private void Message(String color, String msg) {
		Bukkit.getConsoleSender().sendMessage("§" + color + msg);
	}

	public FileConfiguration getPlayers() {
		if (this.playersConfig == null) {
			this.players = new File(getDataFolder(), "players.yml");
			this.playersConfig = YamlConfiguration.loadConfiguration(this.players);
		}
		return this.playersConfig;
	}

	public void savePlayers() {
		if (this.players == null) {
			this.players = new File(getDataFolder(), "players.yml");
		}
		try {
			getPlayers().save(this.players);
		} catch (IOException e) {
		}
	}

	public void reloadPlayers() {
		if (this.players == null) {
			this.players = new File(getDataFolder(), "players.yml");
		}

		this.playersConfig = YamlConfiguration.loadConfiguration(this.players);

		if (this.playersConfig != null) {
			YamlConfiguration defaults = YamlConfiguration.loadConfiguration(this.players);
			this.playersConfig.setDefaults(defaults);
		}
	}

	public FileConfiguration getRanks() {
		if (this.ranksConfig == null) {
			this.ranks = new File(getDataFolder(), "ranks.yml");
			this.ranksConfig = YamlConfiguration.loadConfiguration(this.ranks);
		}
		return this.ranksConfig;
	}
	
	public FileConfiguration getDepositos() {
		if (this.depConfig == null) {
			this.dep = new File(getDataFolder(), "depositos.yml");
			this.depConfig = YamlConfiguration.loadConfiguration(this.dep);
		}
		return this.depConfig;
	}

	public void saveDepositos() {
		if (this.dep == null) {
			this.dep = new File(getDataFolder(), "depositos.yml");
		}
		try {
			getDepositos().save(this.dep);
		} catch (IOException e) {
		}
	}

	public void reloadDepositos() {
		if (this.dep == null) {
			this.dep = new File(getDataFolder(), "depositos.yml");
		}

		this.depConfig = YamlConfiguration.loadConfiguration(this.dep);

		if (this.depConfig != null) {
			YamlConfiguration defaults = YamlConfiguration.loadConfiguration(this.dep);
			this.depConfig.setDefaults(defaults);
		}
	}
}
