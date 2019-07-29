package me.wanderson.comandos;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.wanderson.DRankUp;
import me.wanderson.utils.Funcoes;
import me.wanderson.utils.Msg;

public class Comandos implements CommandExecutor {
	
	private static DRankUp pl = (DRankUp)Bukkit.getPluginManager().getPlugin("DRankUp");
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage("§4Comandos apenas para players!");
			return true;
		}
		Player p = (Player)sender;
		if(cmd.getName().equalsIgnoreCase("ranks")) {
			if(args.length == 0) {
				FileConfiguration ranks = pl.getRanks();
				Inventory inv = Bukkit.createInventory(null, 6*9, Msg.TITLE_RANKS);
				for(String key : ranks.getConfigurationSection("ranks").getKeys(false)) {
					ItemStack it = new ItemStack(ranks.getInt("ranks."+key+".item_id"),1,(short)ranks.getInt("ranks."+key+".item_data"));
					ItemMeta mt = it.getItemMeta();
					mt.setDisplayName(ranks.getString("ranks."+key+".name").replace("&", "§"));
					ArrayList<String> lore = new ArrayList<String>();
					for(String str : ranks.getStringList("ranks."+key+".item_lore")) {
						lore.add(str.replace("&", "§"));
					}
					lore.add("");
					lore.add("§aPreco: §f" + Funcoes.formatarPreco(ranks.getDouble("ranks."+key+".custo")));
					mt.setLore(lore);
					it.setItemMeta(mt);
					inv.setItem(ranks.getInt("ranks."+key+".item_slot"), it);
				}
				p.openInventory(inv);
			}else {
				p.sendMessage("§c/ranks §7- para ver os ranks");
			}
		}else if(cmd.getName().equalsIgnoreCase("rankup")) {
			if(args.length == 0) {
				String rankAtual = pl.getPlayers().getString("players."+p.getName());
				if(pl.getRanks().getBoolean("ranks."+rankAtual+".ultimo")) {
					p.sendMessage(Msg.ULTIMO.replace("%rank%", pl.getRanks().getString("ranks."+rankAtual+".name").replace("&", "§")));
				}else {
					for(String key : pl.getRanks().getConfigurationSection("ranks").getKeys(false)) {
						if(key.equals(rankAtual)) {
							String next = pl.getRanks().getString("ranks."+key+".proximo");
							Inventory inv = Bukkit.createInventory(null, 4*9, Msg.TITLE_RANKUP);
							inv.setItem(13, Funcoes.criarItem(Material.DIAMOND_PICKAXE, Msg.RANKUP_NAME, 0, Msg.RANKUP_LORE, pl.getRanks().getDouble("ranks."+next+".custo"), pl.getRanks().getString("ranks."+next+".name").replace("&", "§")));
							inv.setItem(20, Funcoes.criarItemSimples(Material.WOOL, Msg.CANCELAR, 14));
							inv.setItem(24, Funcoes.criarItemSimples(Material.WOOL, Msg.CONFIRMAR, 5));
							p.openInventory(inv);
							break;
						}
					}
				}
			}else {
				p.sendMessage("§c/rankup §7- para upar de rank");
			}
		}else if(cmd.getName().equalsIgnoreCase("deposito")) {
			if(p.hasPermission("drankup.mine")) {
				if(args.length == 0) {
					Inventory inv = Funcoes.getInventory(p);
					List<ItemStack> itensHashs = pl.chestMinerios.get(p.getUniqueId());
					if(itensHashs != null) {
						for(ItemStack item:itensHashs) {
							inv.addItem(item);
						}
					}
					p.openInventory(inv);
				}else {
					p.sendMessage("  &lUse: &7/deposito");
				}
			}else {
				p.sendMessage(Msg.PERM_NULL);
			}
		}
		return false;
	}

}
