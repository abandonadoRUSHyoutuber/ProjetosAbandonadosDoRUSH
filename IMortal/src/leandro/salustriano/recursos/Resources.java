package leandro.salustriano.recursos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import leandro.salustriano.bydefault.Imortal;
import leandro.salustriano.bydefault.Manager;
import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.managers.ClanManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Resources {
	public Integer maxRecordPlayer = Integer.valueOf(0);
	public Integer contador = Integer.valueOf(1);
	public Integer checkKillFK = Integer.valueOf(0);
	List getMaior = new ArrayList();
	List<String> rankListTest = new ArrayList();
	Location l = null;
	Map<Integer, String> treeMap = new TreeMap(new Comparator() {
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	});
	public Imortal m;
	ClanPlayer clanPlayer1;
	ClanPlayer clanPlayer2;
	public String notice_camarote_pvp;
	public String nameInRank;
	public String count;
	public String clanTag1;
	public String clanTag2;
	public String world;
	public Integer verifyExistInLog;
	boolean existInConfig;
	List<String> playerBanList;
	OfflinePlayer p;
	OfflinePlayer auxRs;
	Player aux;
	Player onPlayer;
	Player afterPlayerDeadCheckFK;

	public Resources(Imortal main) {
		this.m = main;
	}

	public void restartEvent() {
		if ((ResourcesThreadEvent.player1 != null) || (ResourcesThreadEvent.player2 != null)) {
			if (ResourcesThreadEvent.player1 != null) {
				this.m.srcEvents.src.teleportPlayer(ResourcesThreadEvent.player1, "saida");
				ResourcesThreadEvent.player1.sendMessage("�5[Imortal]�6O evento foi reinicializado.");
				ResourcesThreadEvent.player1 = null;
			} else if (ResourcesThreadEvent.player2 != null) {
				this.m.srcEvents.src.teleportPlayer(ResourcesThreadEvent.player2, "saida");
				ResourcesThreadEvent.player2.sendMessage("�5[Imortal]�6O evento foi reinicializado.");
				ResourcesThreadEvent.player2 = null;
			} else if ((ResourcesThreadEvent.player1 != null) && (ResourcesThreadEvent.player2 != null)) {
				this.m.srcEvents.src.teleportPlayer(ResourcesThreadEvent.player1, "saida");
				ResourcesThreadEvent.player1.sendMessage("�5[Imortal]�6O evento foi reinicializado.");
				ResourcesThreadEvent.player1 = null;

				this.m.srcEvents.src.teleportPlayer(ResourcesThreadEvent.player2, "saida");
				ResourcesThreadEvent.player2.sendMessage("�5[Imortal]�6O evento foi reinicializado.");
				ResourcesThreadEvent.player2 = null;
			}
		} else if (!ResourcesThreadEvent.fila.isEmpty()) {
			for (int i = 0; i < ResourcesThreadEvent.fila.size(); i++) {
				this.m.srcEvents.src.teleportPlayer((Player) ResourcesThreadEvent.fila.get(i), "saida");
				((Player) ResourcesThreadEvent.fila.get(i)).sendMessage("�5[Imortal]�6O evento foi reinicializado.");
				ResourcesThreadEvent.fila.remove(ResourcesThreadEvent.fila.get(i));
			}
		}
	}

	public void getSelectGui(String action, Player p, Material m) {
		p.closeInventory();
		if ((action.equalsIgnoreCase("Participe")) && (m == Material.DIAMOND_SWORD)) {
			if ((isActive(p)) && (playerIsBanned(p.getName())) && (this.m.srcEvents.hasLocateInConfig(p))) {
				this.m.srcEvents.addPlayerFila(p);
			}
		} else if ((action.equalsIgnoreCase("Sair")) && (m == Material.BED)) {
			if (this.m.srcEvents.hasLocateInConfig(p)) {
				this.m.srcEvents.PlayerExit(p);
			}
		} else if ((action.equalsIgnoreCase("Assistir")) && (m == Material.EYE_OF_ENDER)) {
			if (this.m.srcEvents.hasLocateInConfig(p)) {
				this.m.srcEvents.addPlayerCamarote(p);
			}
		} else if ((action.equalsIgnoreCase("Desistir")) && (m == Material.BED)) {
			Desistir(p);
		} else if ((action.equalsIgnoreCase("Ranking")) && (m == Material.DIAMOND_HELMET)) {
			topRank(p);
		} else if ((action.equalsIgnoreCase("Sair do camarote")) && (m == Material.BED)) {
			ResourcesThreadEvent.camarote.remove(p);
			teleportPlayer(p, "saida");
		}
	}

	public void openGui(Player p) {
		Inventory inv;
		if (getNameTop1() != null) {
			inv = Bukkit.createInventory(null, 9, "Imortal top: " + getNameTop1());
		} else {
			inv = Bukkit.createInventory(null, 9, "Imortal top:");
		}
		ItemStack entrar = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack fechar = new ItemStack(Material.PAPER);
		ItemStack sair = new ItemStack(Material.BED);
		ItemStack ranking = new ItemStack(Material.DIAMOND_HELMET);
		ItemStack desistir = new ItemStack(Material.BED);
		ItemStack sairCamarote = new ItemStack(Material.BED);
		ItemStack entrarCamarote = new ItemStack(Material.EYE_OF_ENDER);

		ItemMeta entrarCamaroteMeta = entrarCamarote.getItemMeta();
		ItemMeta sairCamaroteMeta = sairCamarote.getItemMeta();
		ItemMeta entrarMeta = entrar.getItemMeta();
		ItemMeta sairMeta = sair.getItemMeta();
		ItemMeta rankingMeta = ranking.getItemMeta();
		ItemMeta fecharMeta = fechar.getItemMeta();
		ItemMeta desistirMeta = desistir.getItemMeta();

		entrarCamaroteMeta.setDisplayName("Assistir");
		sairCamaroteMeta.setDisplayName("Sair do camarote");
		entrarMeta.setDisplayName("Participe");
		sairMeta.setDisplayName("Sair");
		rankingMeta.setDisplayName("Ranking");
		fecharMeta.setDisplayName("Fechar");
		desistirMeta.setDisplayName("Desistir");

		entrarCamarote.setItemMeta(entrarCamaroteMeta);
		sairCamarote.setItemMeta(sairCamaroteMeta);
		entrar.setItemMeta(entrarMeta);
		desistir.setItemMeta(desistirMeta);
		sair.setItemMeta(sairMeta);
		ranking.setItemMeta(rankingMeta);
		fechar.setItemMeta(fecharMeta);
		if (ResourcesThreadEvent.fila.contains(p)) {
			inv.setItem(0, sair);
			inv.setItem(1, fechar);
			inv.setItem(2, fechar);
			inv.setItem(3, fechar);
			inv.setItem(4, ranking);
			inv.setItem(5, fechar);
			inv.setItem(6, fechar);
			inv.setItem(7, fechar);
			inv.setItem(8, fechar);
		} else if ((ResourcesThreadEvent.player1 == p) || (ResourcesThreadEvent.player2 == p)) {
			inv.setItem(0, desistir);
			inv.setItem(1, fechar);
			inv.setItem(2, fechar);
			inv.setItem(3, fechar);
			inv.setItem(4, fechar);
			inv.setItem(5, fechar);
			inv.setItem(6, fechar);
			inv.setItem(7, fechar);
			inv.setItem(8, fechar);
		} else if (ResourcesThreadEvent.camarote.contains(p)) {
			inv.setItem(0, fechar);
			inv.setItem(1, entrar);
			inv.setItem(2, fechar);
			inv.setItem(3, fechar);
			inv.setItem(4, ranking);
			inv.setItem(5, fechar);
			inv.setItem(6, fechar);
			inv.setItem(7, sairCamarote);
			inv.setItem(8, fechar);
		} else {
			inv.setItem(0, fechar);
			inv.setItem(1, entrar);
			inv.setItem(2, fechar);
			inv.setItem(3, fechar);
			inv.setItem(4, ranking);
			inv.setItem(5, fechar);
			inv.setItem(6, fechar);
			inv.setItem(7, entrarCamarote);
			inv.setItem(8, fechar);
		}
		p.openInventory(inv);
	}

	public boolean checkHitSameClan(Player p1, Player p2) {
		this.clanPlayer1 = SimpleClans.getInstance().getClanManager().getClanPlayer(p1);
		this.clanPlayer2 = SimpleClans.getInstance().getClanManager().getClanPlayer(p2);
		this.clanTag1 = (this.clanPlayer1 != null ? this.clanPlayer1.getClan().getColorTag() : "�cNenhum");
		this.clanTag2 = (this.clanPlayer2 != null ? this.clanPlayer2.getClan().getColorTag() : "�cNenhum");
		if (this.clanTag1.equals(this.clanTag2)) {
			return true;
		}
		if (!this.clanTag1.equals(this.clanTag2)) {
			return false;
		}
		return false;
	}

	public void checkFreeKill(Player p) {
		if (this.afterPlayerDeadCheckFK == p) {
			this.afterPlayerDeadCheckFK = p;
			this.checkKillFK = Integer.valueOf(this.checkKillFK.intValue() + 1);
		} else {
			this.afterPlayerDeadCheckFK = p;
			this.checkKillFK = Integer.valueOf(1);
		}
		if ((this.checkKillFK.intValue() == this.m.getConfig().getInt("Auto_ban_qtd_kill_seguidos"))
				&& (!this.m.banidos.getConfig().getStringList("list").contains(p.getName()))) {
			this.playerBanList = this.m.banidos.getConfig().getStringList("list");
			this.playerBanList.add(p.getName());
			this.m.banidos.getConfig().set("list", this.playerBanList);
			this.m.banidos.saveConfig();
			this.m.banidos.loadMessageConfig();
			if (p.isOnline()) {
				p.getPlayer().sendMessage("�5[Imortal]�cVoce foi banido do evento por suspeitas de free kill.");
			}
		}
	}

	public void setLocate(Player p, String positionName) {
		this.m.assets.getConfig().set("position_" + positionName + ".world", p.getLocation().getWorld().getName());
		this.m.assets.getConfig().set("position_" + positionName + ".x", Double.valueOf(p.getLocation().getX()));
		this.m.assets.getConfig().set("position_" + positionName + ".y", Double.valueOf(p.getLocation().getY()));
		this.m.assets.getConfig().set("position_" + positionName + ".z", Double.valueOf(p.getLocation().getZ()));
		this.m.assets.getConfig().set("position_" + positionName + ".yaw", Float.valueOf(p.getLocation().getYaw()));
		this.m.assets.getConfig().set("position_" + positionName + ".pitch", Float.valueOf(p.getLocation().getPitch()));

		this.m.assets.saveConfig();
	}

	public void teleportPlayer(Player p, String positionName) {
		this.m.assets.loadMessageConfig();
		Double x = Double.valueOf(this.m.assets.getConfig().getDouble("position_" + positionName + ".x"));
		Double y = Double.valueOf(this.m.assets.getConfig().getDouble("position_" + positionName + ".y"));
		Double z = Double.valueOf(this.m.assets.getConfig().getDouble("position_" + positionName + ".z"));
		Float yaw = Float.valueOf((float) this.m.assets.getConfig().getDouble("position_" + positionName + ".yaw"));
		Float pitch = Float.valueOf((float) this.m.assets.getConfig().getDouble("position_" + positionName + ".pitch"));
		String world = this.m.assets.getConfig().getString("position_" + positionName + ".world");
		try {
			World fatWorld = Bukkit.getWorld(world);
			Location l = new Location(fatWorld, x.doubleValue(), y.doubleValue(), z.doubleValue(), yaw.floatValue(),
					pitch.floatValue());
			p.teleport(l);
		} catch (Throwable error) {
			p.sendMessage(ChatColor.RED + "A posi�ao ainda nao foi defida.");
		}
	}

	public void Desistir(Player p) {
		if ((p == ResourcesThreadEvent.player1) || (p == ResourcesThreadEvent.player2)) {
			if ((p == ResourcesThreadEvent.player1) && (ResourcesThreadEvent.player2 == null)) {
				p.sendMessage("�5[Imortal]�6Voc� desistiu do evento");
				ResourcesThreadEvent.player1 = null;
				p.teleport(getLocateInConfig("saida"));
			} else if ((p == ResourcesThreadEvent.player2) && (ResourcesThreadEvent.player1 == null)) {
				p.sendMessage("�5[Imortal]�6Voc� desistiu do evento");
				ResourcesThreadEvent.player2 = null;
				p.teleport(getLocateInConfig("saida"));
			} else {
				p.sendMessage("�5[Imortal]�cVoc� n�o pode desistir do evento no momento.");
			}
		} else {
			p.sendMessage("�5[Imortal]�cVoc� n�o est� participando do evento no momento.");
		}
	}

	public Location getLocateInConfig(String locate) {
		this.l = null;
		Double x = Double.valueOf(this.m.assets.getConfig().getDouble("position_" + locate + ".x"));
		Double y = Double.valueOf(this.m.assets.getConfig().getDouble("position_" + locate + ".y"));
		Double z = Double.valueOf(this.m.assets.getConfig().getDouble("position_" + locate + ".z"));
		Float yaw = Float.valueOf((float) this.m.assets.getConfig().getDouble("position_" + locate + ".yaw"));
		Float pitch = Float.valueOf((float) this.m.assets.getConfig().getDouble("position_" + locate + ".pitch"));
		String world = this.m.assets.getConfig().getString("position_" + locate + ".world");
		World fatWorld = Bukkit.getWorld(world);

		this.l = new Location(fatWorld, x.doubleValue(), y.doubleValue(), z.doubleValue(), yaw.floatValue(),
				pitch.floatValue());

		return this.l;
	}

	public void savePlayerKillConsecutivesInConfig(Player p) {
		this.m.log.loadMessageConfig();
		int verifyExistInLog = this.m.log.getConfig().getInt(p.getName());
		if (verifyExistInLog == 0) {
			this.m.log.getConfig().set(p.getName(), Integer.valueOf(1));
			this.m.log.saveConfig();
		} else {
			this.m.log.getConfig().set(p.getName(), Integer.valueOf(verifyExistInLog + 1));
			this.m.log.saveConfig();
		}
	}

	public Integer getTop1() {
		this.m.log.loadMessageConfig();
		this.getMaior.clear();
		for (int i = 0; i < this.m.log.getConfig().getValues(true).keySet().toArray().length; i++) {
			this.getMaior.add(this.m.log.getConfig().getValues(true).values().toArray()[i]);
		}
		Collections.sort(this.getMaior);
		Collections.reverse(this.getMaior);
		return (Integer) this.getMaior.get(0);
	}

	public void playerBanned(String playerName, Player sender) {
		this.p = Bukkit.getOfflinePlayer(playerName);
		if (!this.m.banidos.getConfig().getStringList("list").contains(playerName)) {
			this.playerBanList = this.m.banidos.getConfig().getStringList("list");
			this.playerBanList.add(playerName);
			this.m.banidos.getConfig().set("list", this.playerBanList);
			this.m.banidos.saveConfig();
			sender.sendMessage("�5[Imortal]�6O jogador �c" + playerName + "�6 foi banido com exito.");
			this.m.banidos.loadMessageConfig();
			if (this.p.isOnline()) {
				this.p.getPlayer().sendMessage("�5[Imortal]�cVoce foi banido do evento.");
			}
		} else {
			sender.sendMessage("�5[Imortal]�cO Jogador �c" + playerName + "�c ja encontra-se banido.");
		}
	}

	public boolean playerIsBanned(String playerName) {
		this.aux = Bukkit.getPlayer(playerName);
		if (!this.m.banidos.getConfig().getStringList("list").contains(playerName)) {
			return true;
		}
		this.aux.getPlayer().sendMessage("�5[Imortal]�cVoce foi banido do evento.");
		return false;
	}

	public void kickPlayer(String playerName, Player sender) {
		this.onPlayer = Bukkit.getPlayer(playerName);
		if (ResourcesThreadEvent.fila.contains(this.onPlayer)) {
			ResourcesThreadEvent.fila.remove(this.onPlayer);
			teleportPlayer(this.onPlayer, "saida");
			sender.sendMessage("�5[Imortal]�6O jogador �c" + playerName + "�6 foi expulso com exito.");
			this.onPlayer.sendMessage("�5[Imortal]�cVoc� foi expulso do evento.");
		} else {
			sender.sendMessage("�5[Imortal]�cO jogador n�o esta participando do evento.");
		}
	}

	public void resetScorePlayer(String playerName, Player sender) {
		this.auxRs = Bukkit.getOfflinePlayer(playerName);
		for (int i = 0; i < this.m.log.getConfig().getValues(true).keySet().size(); i++) {
			if (this.m.log.getConfig().getValues(true).keySet().toArray()[i].equals(playerName)) {
				if (this.m.getConfig().getString("player").equalsIgnoreCase(playerName)) {
					this.m.getConfig().set("player", "");
				}
				this.m.log.getConfig().set(playerName, Integer.valueOf(0));
				this.m.log.saveConfig();
				this.m.log.loadMessageConfig();
				this.existInConfig = true;
			}
		}
		if (this.existInConfig) {
			sender.sendMessage("�6O jogador �c" + playerName + "�6 foi resetado com exito.");
			this.existInConfig = false;
		} else {
			sender.sendMessage("�cJogador n�o encontrado");
		}
	}

	public boolean isActive(Player p) {
		if (this.m.getConfig().getBoolean("active")) {
			return true;
		}
		p.sendMessage("�5[Imortal]�cO evento nao esta ativo no momento.");
		return false;
	}

	public void DisableEvent(Player p) {
		if (!this.m.getConfig().getBoolean("active")) {
			p.sendMessage("�5[Imortal]�cO evento j� esta desativado.");
		} else {
			this.m.getConfig().set("active", Boolean.valueOf(false));
			this.m.saveConfig();
			this.m.reloadConfig();
			p.sendMessage("�5[Imortal]�6O evento foi �cdesativado �6com exito.");
		}
	}

	public void EnableEvent(Player p) {
		if (this.m.getConfig().getBoolean("active")) {
			p.sendMessage("�5[Imortal]�cO evento j� esta ativado.");
		} else {
			this.m.getConfig().set("active", Boolean.valueOf(true));
			this.m.saveConfig();
			this.m.reloadConfig();
			p.sendMessage("�5[Imortal]�6O evento foi �cativado �6com exito.");
		}
	}

	public void pardonPlayer(String playerName, Player sender) {
		this.auxRs = Bukkit.getOfflinePlayer(playerName);
		if (this.m.banidos.getConfig().getStringList("list").contains(playerName)) {
			this.playerBanList = this.m.banidos.getConfig().getStringList("list");
			this.playerBanList.remove(playerName);
			this.m.banidos.getConfig().set("list", this.playerBanList);
			this.m.banidos.saveConfig();
			sender.sendMessage("�5[Imortal]�6O jogador �c" + playerName + "�6 foi desbanido com exito.");
			this.m.banidos.loadMessageConfig();
			if (this.auxRs.isOnline()) {
				this.auxRs.getPlayer().sendMessage("�5[Imortal]�9Voce foi desbanido do evento.");
			}
		} else {
			sender.sendMessage("�5[Imortal]�cEste jogado n�o esta banido.");
		}
	}

	public void setTagInName(Player newTagPlayer) {
		this.m.getConfig().set("player", newTagPlayer.getName());
		if (newTagPlayer.isOnline()) {
			newTagPlayer.sendMessage("�5[Imortal]�6Voce e o novo imortal do servidor, parabens.");
		}
	}

	public String getNameTop1() {
		this.m.log.loadMessageConfig();
		if (this.m.log.getConfig().getValues(true).isEmpty()) {
			return null;
		}
		for (int i = 0; i < this.m.log.getConfig().getValues(true).entrySet().size(); i++) {
			this.treeMap.put((Integer) this.m.log.getConfig().getValues(true).values().toArray()[i],
					this.m.log.getConfig().getValues(true).keySet().toArray()[i].toString());
		}
		Collections.sort(this.rankListTest);
		Collections.reverse(this.rankListTest);
		this.nameInRank = this.treeMap.values().toArray()[0].toString();
		this.treeMap.clear();
		return this.nameInRank;
	}

	public void topRank(Player p) {
		this.m.log.loadMessageConfig();
		this.treeMap.clear();
		for (int i = 0; i < this.m.log.getConfig().getValues(true).entrySet().size(); i++) {
			this.treeMap.put((Integer) this.m.log.getConfig().getValues(true).values().toArray()[i],
					this.m.log.getConfig().getValues(true).keySet().toArray()[i].toString());
		}
		p.sendMessage("�2========== �6Imortal TOP �2=================");
		p.sendMessage("");
		p.sendMessage("");
		if (this.treeMap.size() <= 5) {
			for (int i = 0; i < this.treeMap.size(); i++) {
				this.count = String.valueOf(this.contador);
				p.sendMessage(this.m.messages.getConfig().getString("command_top_execute").replaceAll("&", "�")
						.replaceAll("\\{player\\}", this.treeMap.entrySet().toArray()[i].toString().split("=")[1])
						.replaceAll("\\{vitorias\\}", this.treeMap.entrySet().toArray()[i].toString().split("=")[0])
						.replaceAll("\\{position\\}", this.count));
				this.contador = Integer.valueOf(this.contador.intValue() + 1);
			}
			this.contador = Integer.valueOf(1);
			this.count = "";
		} else {
			for (int i = 0; i < this.treeMap.size(); i++) {
				this.count = String.valueOf(this.contador);
				p.sendMessage(this.m.messages.getConfig().getString("command_top_execute").replaceAll("&", "�")
						.replaceAll("\\{player\\}", this.treeMap.entrySet().toArray()[i].toString().split("=")[1])
						.replaceAll("\\{vitorias\\}", this.treeMap.entrySet().toArray()[i].toString().split("=")[0])
						.replaceAll("\\{position\\}", this.count));
				this.contador = Integer.valueOf(this.contador.intValue() + 1);
			}
			this.contador = Integer.valueOf(1);
			this.count = "";
		}
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("2======================================");
	}

	public void noticeCamarote(ArrayList<Player> camarote, Player one, Player two) {
		this.notice_camarote_pvp = this.m.messages.getConfig().getString("player_vs_player");
		if (camarote.size() > 0) {
			for (int i = 0; i < camarote.size(); i++) {
				((Player) camarote.get(i)).sendMessage(this.notice_camarote_pvp.replaceAll("&", "�")
						.replaceAll("\\{player2\\}", ResourcesThreadEvent.player2.getName())
						.replaceAll("\\{player1\\}", ResourcesThreadEvent.player1.getName()));
			}
		}
	}
}
