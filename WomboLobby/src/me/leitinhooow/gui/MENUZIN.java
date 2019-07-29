package me.leitinhooow.gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class MENUZIN implements Listener{
	
	public void SetarInv(Inventory inv, Player p, Material item, int quantidade, int data, int slot, String descricao, String nome) {
		ItemStack icone = new ItemStack(item, quantidade, (short)data);
		ItemMeta iconem = icone.getItemMeta();
		iconem.setDisplayName(nome);
		ArrayList<String> lore = new ArrayList<>();
		lore.add(descricao);
		iconem.setLore(lore);
		icone.setItemMeta(iconem);
		inv.setItem(slot, icone);
	}
	
	public void gadgetsgui(Player p) {
		
		Inventory inv = Bukkit.createInventory(p, 27, "§8Modos de Jogo");
		
		ItemStack icone = new ItemStack(Material.STAINED_GLASS_PANE);
		ItemMeta iconem = icone.getItemMeta();
		iconem.setDisplayName(" ");
		icone.setItemMeta(iconem);
		icone.setAmount(1);
		
		SetarInv(inv, p, Material.MUSHROOM_SOUP, 1, 0, 10, "", "§aHardcoreGames");
		SetarInv(inv, p, Material.DIAMOND_CHESTPLATE, 1, 0, 12, "", "§aTeam HardcoreGames");
		SetarInv(inv, p, Material.BEACON, 1, 0, 14, "", "§aHardcoreClans");
		SetarInv(inv, p, Material.ARROW, 1, 0, 16, "", "§aUltra HardcoreGames");
	    
		for (int i = 0; i < 1; i++) {
		}
		
		p.openInventory(inv);
	}
	
	@EventHandler
	private void clickBau(PlayerInteractEvent Twork) {
		Player jogador = Twork.getPlayer();
		
		if (Twork.getAction() == Action.RIGHT_CLICK_AIR && jogador.getItemInHand().getType() == Material.COMPASS) {
			gadgetsgui(jogador);
			jogador.playSound(jogador.getLocation(), Sound.NOTE_PIANO, 1.0F, 1.0F);
			Twork.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void  ClicarInv(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase("§8Modos de Jogo")) {
			p.closeInventory();
			if (e.getCurrentItem() == null);
			
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aHardcoreGames")) {
				e.setCancelled(true);
				p.closeInventory();
				p.chat("/server hg");
				return;
			}
			
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aTeam HardcoreGames")) {
				e.setCancelled(true);
				p.closeInventory();
				p.chat("/server teamhg");
				return;
			}
			
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aHardcoreClans")) {
				e.setCancelled(true);
				p.closeInventory();
				p.chat("/server hardcoreclans");
				return;
			}
			
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aUltra HardcoreGames")) {
				e.setCancelled(true);
				p.closeInventory();
				p.chat("/server ultrahg");
				return;
			}
			
		}
		
		if (e.getCurrentItem().getType() == Material.AIR) {
			e.setCancelled(true);
			return;
		}
		
		
	}

}
