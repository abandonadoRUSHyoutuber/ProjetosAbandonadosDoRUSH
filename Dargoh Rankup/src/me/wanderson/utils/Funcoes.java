package me.wanderson.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import me.wanderson.DRankUp;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

public class Funcoes {

	@SuppressWarnings({ "all" })
	private static DRankUp pl = (DRankUp) Bukkit.getPluginManager().getPlugin("DRankUp");
	
	public static String getClan(Player p){
		if(pl.Clans) {
			SimpleClans sc = (SimpleClans)Bukkit.getPluginManager().getPlugin("SimpleClans");
			if (sc != null){
				ClanPlayer cp = sc.getClanManager().getClanPlayer(p);
				if (cp != null){
					return cp.getClan().getColorTag();
				}
			}
		}
		return "Null";
	}
	
	public static float getKdr(Player p){
		if(pl.Clans) {
			SimpleClans sc = (SimpleClans)Bukkit.getPluginManager().getPlugin("SimpleClans");
			if (sc != null){
				ClanPlayer cp = sc.getClanManager().getClanPlayer(p);
				if (cp != null){
					return cp.getKDR();
				}
			}
		}
		return 0;
	}
	
	public static float getKdrClan(Player p){
		if(pl.Clans) {
			SimpleClans sc = (SimpleClans)Bukkit.getPluginManager().getPlugin("SimpleClans");
			if (sc != null){
				ClanPlayer cp = sc.getClanManager().getClanPlayer(p);
				if (cp != null){
					return cp.getClan().getTotalKDR();
				}
			}
		}
		return 0;
	}

	public static String getProgress(double rank, double coins) {
		double fim = (100 * coins) / rank;
		if (fim >= 0 && fim < 10) {
			return "|";
		} else if (fim >= 10 && fim < 20) {
			return "||";
		} else if (fim >= 20 && fim < 30) {
			return "|||";
		} else if (fim >= 30 && fim < 40) {
			return "||||";
		} else if (fim >= 40 && fim < 50) {
			return "|||||";
		} else if (fim >= 50 && fim < 60) {
			return "||||||";
		} else if (fim >= 60 && fim < 70) {
			return "|||||||";
		} else if (fim >= 70 && fim < 80) {
			return "||||||||";
		} else if (fim >= 80 && fim < 90) {
			return "|||||||||";
		} else if (fim >= 90 && fim < 100) {
			return "||||||||||";
		} else if (fim >= 100) {
			return pl.getConfig().getString("progress_completo").replace("&", "§");
		}
		return null;
	}

	public static String removeCor(String s) {
		if (s.contains("&")) {
			String[] n = s.split("&");
			s = "";
			for (String str : n) {
				if (!str.equals(null) && !str.equals("")) {
					s += str.substring(1);
				}
			}
		}
		return s;
	}
	
	private static void setSpace(Objective obj, int i) {
		if(i < 10) {
			obj.getScore("§" + i).setScore(i);
		}else if(i == 10){
			obj.getScore("§a").setScore(i);
		}else if(i == 11){
			obj.getScore("§b").setScore(i);
		}else if(i == 12){
			obj.getScore("§c").setScore(i);
		}else if(i == 13){
			obj.getScore("§d").setScore(i);
		}else if(i == 14){
			obj.getScore("§e").setScore(i);
		}else if(i == 15){
			obj.getScore("§f").setScore(i);
		}else if(i == 16){
			obj.getScore("").setScore(i);
		}else if(i == 17){
			obj.getScore(" ").setScore(i);
		}else if(i == 18){
			obj.getScore("   ").setScore(i);
		}else if(i == 19){
			obj.getScore("    ").setScore(i);
		}else if(i == 20){
			obj.getScore("     ").setScore(i);
		}else if(i == 21){
			obj.getScore("      ").setScore(i);
		}else if(i == 22){
			obj.getScore("§f ").setScore(i);
		}else if(i == 23){
			obj.getScore("§f  ").setScore(i);
		}else if(i == 24){
			obj.getScore("§f   ").setScore(i);
		}else if(i == 25){
			obj.getScore("§f    ").setScore(i);
		}
	}

	public static void setScoreboard(Player p) {
		Scoreboard Score = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = Score.registerNewObjective("RankUp", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(pl.getConfig().getString("titulo_score").replace("&", "§"));
		int i = pl.getConfig().getStringList("lines_score").size();
		for (String str : pl.getConfig().getStringList("lines_score")) {
			if (str.equals("")) {
				setSpace(obj, i);
			} else {
				if(pl.getRanks().getString("ranks." + pl.getPlayers().getString("players." + p.getName()) + ".proximo") != null) {
					double rank2 = pl.getRanks().getDouble("ranks."+ pl.getRanks().getString("ranks." + pl.getPlayers().getString("players." + p.getName()) + ".proximo")+ ".custo");
					double saldo = pl.economy.getBalance(p);
					obj.getScore(str
							.replace("&", "§")
							.replace("%money%", Funcoes.formatarPreco(pl.economy.getBalance(p)))
							.replace("%rank%", removeCor(pl.getRanks().getString("ranks." + pl.getPlayers().getString("players." + p.getName()) + ".name")))
							.replace("%proximoRank%", removeCor(pl.getRanks().getString("ranks."+ pl.getRanks().getString("ranks." + pl.getPlayers().getString("players." + p.getName()) + ".proximo")+ ".name")))
							.replace("%clan%", Funcoes.getClan(p))
							.replace("%clanKdr%", ""+Funcoes.getKdrClan(p))
							.replace("%playerKdr%", ""+Funcoes.getKdr(p))
							.replace("%progresso%", String.valueOf(getProgress(rank2, saldo))))
							.setScore(i);
				}else {
					obj.getScore(str
							.replace("&", "§")
							.replace("%money%", Funcoes.formatarPreco(pl.economy.getBalance(p)))
							.replace("%rank%", Funcoes.removeCor(pl.getRanks().getString("ranks." + pl.getPlayers().getString("players." + p.getName()) + ".name")))
							.replace("%proximoRank%", pl.getConfig().getString("score_ultimo").replace("&", "§"))
							.replace("%clan%", Funcoes.getClan(p))
							.replace("%clanKdr%", ""+Funcoes.getKdrClan(p))
							.replace("%playerKdr%", ""+Funcoes.getKdr(p))
							.replace("%progresso%", pl.getConfig().getString("score_ultimo").replace("&", "§")))
							.setScore(i);
				}
			}
			i--;
		}
		p.setScoreboard(Score);
	}

	public static void setJoinRank(Player p) {
		if (pl.getPlayers().getString("players." + p.getName()) == null) {
			for (String key : pl.getRanks().getConfigurationSection("ranks").getKeys(false)) {
				if (pl.getRanks().getBoolean("ranks." + key + ".padrao")) {
					pl.getPlayers().set("players." + p.getName(), key);
					pl.savePlayers();
					pl.reloadPlayers();
				}
			}
		}
	}

	public static String formatarPreco(double preco) {
		if (preco < 1000) {
			return "R$ " + preco;
		} else if (preco >= 1000.0 && preco < 1000000.0) {
			return "R$ " + (int) (preco / 1000.0) + "K";
		} else if (preco >= 1000000.0 && preco < 1000000000.0) {
			return "R$ " + (int) (preco / 1000000.0) + "M";
		} else if (preco >= 1000000000.0 && preco < 1000000000000.0) {
			return "R$ " + (int) (preco / 1000000000.0) + "B";
		} else {
			return "R$ " + (int) (preco / 1000000000000.0) + "T";
		}
	}

	public static ItemStack criarItem(Material mat, String nome, int data, List<String> lore, double custo,
			String next) {
		ItemStack it = new ItemStack(mat, 1, (short) data);
		ItemMeta mt = it.getItemMeta();
		mt.setDisplayName(nome.replace("%custo%", Funcoes.formatarPreco(custo)).replace("%proximoRank%", next));
		ArrayList<String> l = new ArrayList<String>();
		for (String str : lore) {
			l.add(str.replace("&", "§").replace("%custo%", Funcoes.formatarPreco(custo)).replace("%proximoRank%",
					next));
		}
		mt.setLore(l);
		it.setItemMeta(mt);
		return it;
	}

	public static ItemStack criarItemSimples(Material mat, String nome, int data) {
		ItemStack it = new ItemStack(mat, 1, (short) data);
		ItemMeta mt = it.getItemMeta();
		mt.setDisplayName(nome);
		it.setItemMeta(mt);
		return it;
	}
	
	public static boolean verifyInv(Inventory inv, ItemStack item, Player p, List<ItemStack> allItens) {
		for(ItemStack itens : inv.getContents()) {
			if(itens == null || (itens.getAmount() < itens.getType().getMaxStackSize() && itens.isSimilar(item))) {
				allItens.add(item);
				return false;
			}
		}
		return true;
	}
	
	public static Inventory getInventory(Player p) {
		int lines = 1;
		if(p.hasPermission("drankup.inv.6")) {
			lines = 6;
		}else if(p.hasPermission("drankup.inv.5")) {
			lines = 5;
		}else if(p.hasPermission("drankup.inv.4")) {
			lines = 4;
		}else if(p.hasPermission("drankup.inv.3")) {
			lines = 3;
		}else if(p.hasPermission("drankup.inv.2")) {
			lines = 2;
		}else if(p.hasPermission("drankup.inv.1")) {
			lines = 1;
		}
		
		return Bukkit.createInventory(null, lines*9, p.getName());
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
	public static void cancellDrop(BlockBreakEvent e, HashMap<Player, Boolean> msgFull, HashMap<Player, Boolean> chestFull, HashMap<Player, Integer> virtualItens, int id_ore, Material mat) {
		if(e.getPlayer() instanceof Player) {
			Player p = e.getPlayer();
			if(e.getBlock().getTypeId() == id_ore) {
				chestFull.put(p, false);
				p.giveExp(e.getExpToDrop());
				for(ItemStack item : e.getBlock().getDrops()) {
					if(virtualItens.get(p) != null) {
						int itens = virtualItens.get(p);
						itens += item.getAmount();
						virtualItens.put(p, itens);
					}
				}
				int itens = 0;
				if(virtualItens.get(p) != null) {
					itens = virtualItens.get(p);
				}
				int blocos = Integer.valueOf(itens/9);
				itens = itens-(blocos*9);
				virtualItens.put(p, itens);
				if(blocos > 0) {
					List<ItemStack> itensHash = new ArrayList<>();
					if(pl.chestMinerios.get(p.getUniqueId()) != null) {
						itensHash = pl.chestMinerios.get(p.getUniqueId());
					}
					int qntItens = 0;
					for(ItemStack item:itensHash) {
						qntItens += item.getAmount();
					}
					Inventory inv = getInventory(p);
					if(itensHash != null) {
						for(ItemStack item:itensHash) {
							inv.addItem(item);
						}
					}
					if(!Funcoes.verifyInv(inv, new ItemStack(mat,blocos), p, itensHash)) {
						pl.chestMinerios.put(p.getUniqueId(), itensHash);
					}else {
						chestFull.put(p, true);
					}
				}
				if(chestFull.get(p) != null && chestFull.get(p) && blocos > 0) {
					if(Funcoes.verifyInv(p.getInventory(), new ItemStack(mat,blocos), p, new ArrayList<>())) {
						msgFull.put(p, true);
						if(msgFull.get(p))p.sendTitle(Msg.FULL_TITLE, Msg.FULL_SUBTITLE);
					}else {
						p.getInventory().addItem(new ItemStack(mat,blocos));
						msgFull.put(p, false);
					}
				}
				e.getBlock().setType(Material.AIR);
				e.setCancelled(true);
			}
		}
	}
	
	public static void setThisDeposito(HashMap<UUID, List<ItemStack>> chestMinerios, UUID uuid, Material mat, List<ItemStack> itensC) {
		int qnt = 0;
		for(ItemStack it : chestMinerios.get(uuid)) {
			if(it.getType().equals(mat)) {
				qnt += it.getAmount();
			}
		}
		if(qnt > 0) {
			int items = Integer.valueOf(qnt/64);
			qnt = qnt - (items*64); 
			if(items > 0) {
				for(int i = 0;i < items;i++) {
					itensC.add(new ItemStack(mat,64));
				}
			}
			if(qnt > 0) {
				itensC.add(new ItemStack(mat,qnt));
			}
			
		}
	}
	
	public static int a(Material material, Random random){
	    return material == Material.LAPIS_ORE ? 4 + random.nextInt(5) : 1;
	}
	
	public int getDropCount(Material mat, int fortuneLevel, Random random){
	    if ((fortuneLevel > 0) && (/*verifica se é silck touch*/1==1)){
	        int drops = random.nextInt(fortuneLevel + 2) - 1;
	        if (drops < 0)
	        {
	            drops = 0;
	        }
	        return a(mat, random) * (drops + 1);
	    }
	    return a(mat,random);
	}
}
