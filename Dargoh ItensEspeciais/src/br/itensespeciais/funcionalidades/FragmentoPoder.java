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
					p.sendMessage("�aVoc� transformou 4 fragmentos em 1 poder m�ximo.");
					return;
				} p.sendMessage("�cVoc� n�o tem fragmentos suficientes para fazer um poder m�ximo.");
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
					p.sendMessage("�aVoc� transformou 4 fragmentos em 1 poder m�ximo.");
					return;
				} p.sendMessage("�cVoc� n�o tem fragmentos suficientes para fazer um poder m�ximo.");
			}
		}
	}
}
