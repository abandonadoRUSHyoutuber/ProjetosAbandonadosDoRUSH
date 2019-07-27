package rush.limitadordeblocos.manager;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import rush.limitadordeblocos.Main;

public class BlockManager {

	private Map<String, Integer> limitBlocks = new HashMap<>();
	private Map<String, Integer> limitBlocksVip = new HashMap<>();
	private Map<String, Map<String, Integer>> users = new HashMap<>();
	private String error;
	private Main plugin;
	
	public BlockManager(Main plugin) {
		this.plugin = plugin;

		FileConfiguration config = plugin.getConfig();
		for (String input : config.getStringList("Blocos-Limitados")) {
			try {
				String info[] = input.split(":");
				String block = info[0] + ":" + info[1];
				Integer limit = Integer.parseInt(info[2]);
				Integer limitVip = Integer.parseInt(info[3]);
				limitBlocks.put(block, limit);
				limitBlocksVip.put(block, limitVip);
			} catch (Throwable e) {
				Bukkit.getConsoleSender().sendMessage("§c[LimitadorDeBlocos] Nao foi possivel carregar o bloco '" + input + "' !");
			}
		}
		this.error = config.getString("Limite-Atingido").replace('&', '§');
	}
	
	public Map<String, Map<String, Integer>> getUsers() {
		return users;
	}
	
	public void savePlayer(String player) {
		if (!users.containsKey(player)) {
			plugin.database.savePlayer(player);
			users.put(player, new HashMap<>());
		}
	}
	
	public boolean canPlaceBlock(Player p, String block) {
		String player = p.getName();
		Map<String, Integer> user = users.get(player);
		if (user.containsKey(block)) {
			int used = user.get(block);
			if (p.hasPermission("limitador.vip")) {
				if (used < limitBlocksVip.get(block)) {
					user.replace(block, used + 1);
					return true;
				} else {
					p.sendMessage(error.replace("{quantia}", plugin.blockmanager.getVipLimit(block).toString()));
					return false;
				}
			} else {
				if (used < limitBlocks.get(block)) {
					user.replace(block, used + 1);
					return true;
				} else {
					p.sendMessage(error.replace("{quantia}", plugin.blockmanager.getLimit(block).toString()));
					return false;
				}
			}
		} else {
			user.put(block, 1);
			return true;
		}
	}
	
	public void incrementUserBlock(String player, String block) {
		Map<String, Integer> user = users.get(player);
		if (user.containsKey(block)) {
			user.replace(block, user.get(block) + 1);
		} else {
			user.put(block, 1);
		}
	}
	
	public void decrementUserBlock(String player, String block) {
		Map<String, Integer> user = users.get(player);
		if (user.containsKey(block)) {
			int used = user.get(block);
			if (used > 0) {
				user.replace(block, used - 1);
			} else {
				user.remove(block);
			}
		}
	}
	
	public boolean isLimitedBlock(String block) {
		return limitBlocks.containsKey(block);
	}
	
	public Integer getLimit(String block) {
		return limitBlocks.get(block);
	}
	
	public Integer getVipLimit(String block) {
		return limitBlocksVip.get(block);
	}
}