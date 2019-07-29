package br.itensespeciais.funcionalidades;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import br.itensespeciais.main.Main;

public class FragmentoPoder implements Listener{

	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_AIR) {
			Player p = e.getPlayer();
			if(p.getItemInHand().isSimilar(Main.fragmentodepoder)) {
				
				if(p.getInventory().containsAtLeast(Main.fragmentodepoder, 4)) {
					ItemStack a = Main.fragmentodepoder;
					a.setAmount(4);
					p.getInventory().removeItem(a);
					p.getInventory().addItem(Main.podermaximo);
					p.sendMessage("§aVocê transformou 4 fragmentos em 1 poder máximo.");
					return;
				} p.sendMessage("§cVocê não tem fragmentos suficientes para fazer um poder máximo.");
			}
		}
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			if(p.getItemInHand().isSimilar(Main.fragmentodepoder)) {
				
				if(p.getInventory().containsAtLeast(Main.fragmentodepoder, 4)) {
					ItemStack a = Main.fragmentodepoder;
					a.setAmount(4);
					p.getInventory().removeItem(a);
					p.getInventory().addItem(Main.podermaximo);
					p.sendMessage("§aVocê transformou 4 fragmentos em 1 poder máximo.");
					return;
				} p.sendMessage("§cVocê não tem fragmentos suficientes para fazer um poder máximo.");
			}
		}
	}
}
