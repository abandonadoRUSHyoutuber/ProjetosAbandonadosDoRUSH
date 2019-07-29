package me.wanderson.enventos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.wanderson.DRankUp;
import me.wanderson.utils.Funcoes;
import me.wanderson.utils.Msg;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Eventos implements Listener {
	
	private static DRankUp pl = (DRankUp)Bukkit.getPluginManager().getPlugin("DRankUp");
	private HashMap<Player, Boolean> chestFull = new HashMap<>();
	private HashMap<Player, Boolean> msgFull = new HashMap<>();
	private HashMap<Player, Integer> virtualItens1 = new HashMap<>();
	private HashMap<Player, Integer> virtualItens2 = new HashMap<>();
	private HashMap<Player, Integer> virtualItens3 = new HashMap<>();
	private HashMap<Player, Integer> virtualItens4 = new HashMap<>();
	private HashMap<Player, Integer> virtualItens5 = new HashMap<>();
	
	@EventHandler
	public void aoEntrar(PlayerJoinEvent e) {
		if(e.getPlayer() instanceof Player) {
			Player p = e.getPlayer();
			Funcoes.setJoinRank(p);
			Funcoes.setScoreboard(p);
			pl.saveDeposito();
			pl.loadDeposito();
		}
	}
	
	@EventHandler
	public void aoFechar(InventoryCloseEvent e) {
		Player p = (Player)e.getPlayer();
		Inventory inv = e.getInventory();
		if(inv.getTitle().equals(p.getName())) {
			
			List<ItemStack> itens = new ArrayList<>();
			
			for(ItemStack item : inv.getContents()) {
				if(item != null) {
					itens.add(item);
				}
			}
			
			pl.chestMinerios.put(p.getUniqueId(), itens);
		}
	}
	
	@EventHandler
	public void aoQuebrar(BlockBreakEvent e) {
		if(e.getPlayer().hasPermission("drankup.mine")) {
			Funcoes.cancellDrop(e, msgFull, chestFull, virtualItens1, 21, Material.LAPIS_BLOCK);
			Funcoes.cancellDrop(e, msgFull, chestFull, virtualItens2, 16, Material.COAL_BLOCK);
			Funcoes.cancellDrop(e, msgFull, chestFull, virtualItens3, 74, Material.REDSTONE_BLOCK);
			Funcoes.cancellDrop(e, msgFull, chestFull, virtualItens4, 56, Material.DIAMOND_BLOCK);
			Funcoes.cancellDrop(e, msgFull, chestFull, virtualItens5, 129, Material.EMERALD_BLOCK);
		}
	}
	
	@EventHandler
	public void aoClicar(InventoryClickEvent e) {
		if(e.getWhoClicked() instanceof Player) {
			Player p = (Player)e.getWhoClicked();
			if(e.getInventory().getTitle().equals(Msg.TITLE_RANKS)) {
				e.setCancelled(true);
			}else if(e.getInventory().getTitle().equals(Msg.TITLE_RANKUP)) {
				e.setCancelled(true);
				if(e.getSlotType() == SlotType.OUTSIDE) return;
				if(e.getCurrentItem().hasItemMeta()) {
					if(e.getCurrentItem().getItemMeta().hasDisplayName()) {
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Msg.CANCELAR)) {
							p.closeInventory();
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Msg.CONFIRMAR)) {
							String rankAtual = pl.getPlayers().getString("players."+p.getName());
							for(String key : pl.getRanks().getConfigurationSection("ranks").getKeys(false)) {
								if(key.equals(rankAtual)) {
									String next = pl.getRanks().getString("ranks."+key+".proximo");
									double custo = pl.getRanks().getDouble("ranks."+next+".custo");
									double saldo = pl.economy.getBalance(p);
									if(custo <= saldo) {
										PermissionUser user = PermissionsEx.getUser(p);
										for(String vip : pl.getConfig().getConfigurationSection("vips").getKeys(false)) {
											if(user.getParentIdentifiers().contains(vip)) {
												for(World w : Bukkit.getWorlds()) {
													String prefix = pl.getConfig().getString("vips."+vip).replace("&", "§")+pl.getRanks().getString("ranks."+next+".prefix").replace("&", "§");
													user.setPrefix(prefix, w.getName());
												}
												break;
											}
										}
										p.closeInventory();
										pl.economy.withdrawPlayer(p, custo);
										user.removeGroup(rankAtual);
										user.addGroup(next);
										p.playSound(p.getLocation(), Sound.EXPLODE, 1F, 1F);
										pl.getPlayers().set("players."+p.getName(), next);
										pl.savePlayers();
										pl.reloadPlayers();
										if(pl.getRanks().getStringList("ranks."+pl.getPlayers().getString("players."+p.getName())+".commands") != null) {
											for(String cmd : pl.getRanks().getStringList("ranks."+rankAtual+".commands")) {
												if(cmd.contains("@allPlayers")) {
													for(Player ap : Bukkit.getOnlinePlayers()) {
														Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("%player%", ap.getName()).replace("@allPlayers", ""));
													}
												}else if(cmd.contains("@player")){
													Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("%player%", p.getName()).replace("@player", ""));
												}
											}
										}
										for(String str : Msg.PLAYER_RANKUP) {
											p.sendMessage(str.replace("&", "§").replace("%custo%", Funcoes.formatarPreco(custo)).replace("%rank%", pl.getRanks().getString("ranks."+next+".name").replace("&", "§")));
										}
										for(Player pb : Bukkit.getOnlinePlayers()) {
											for(String str : Msg.PLAYERS_RANKUP) {
												pb.sendMessage(str.replace("&", "§").replace("%custo%", Funcoes.formatarPreco(custo)).replace("%rank%", pl.getRanks().getString("ranks."+next+".name").replace("&", "§")).replace("%player%", p.getName()));
											}
										}
										Funcoes.setScoreboard(p);
									}else {
										p.closeInventory();
										p.sendMessage(Msg.SEM_SALDO.replace("%custo%", Funcoes.formatarPreco(custo)).replace("%proximoRank%", pl.getRanks().getString("ranks."+next+".name").replace("&", "§")));
									}
									break;
								}
							}
						}
					}
				}
			}
		}
	}

}
