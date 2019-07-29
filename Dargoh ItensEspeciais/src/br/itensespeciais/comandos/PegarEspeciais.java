package br.itensespeciais.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import br.itensespeciais.main.Main;

public class PegarEspeciais implements CommandExecutor, Listener{

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		Player p = (Player)s;
		if (c.getName().equals("pegarespeciais")) {
			if(s.hasPermission("despeciais.pegar")) {
				if(!s.isOp()) {
					s.sendMessage("§cVocê não tem permissão para isto.");
					return true;
				}
			}
			Inventory itens = Bukkit.createInventory(null, 4*9, "§8Itens especiais");
			
			itens.setItem(10, Main.capsula);
			itens.setItem(11, Main.lancador);
			itens.setItem(12, Main.raiomestre);
			itens.setItem(13, Main.armadilha);
			itens.setItem(14, Main.purificador);
			itens.setItem(15, Main.silktouch);
			itens.setItem(16, Main.fragmentodepoder);
			itens.setItem(20, Main.podermaximo);
			itens.setItem(19, Main.creepereletrico);
			itens.setItem(21, Main.poderinstantaneo);
			
			p.openInventory(itens);
			return true;
		} return false;
	}
	
	@EventHandler
	public void clicarInv(InventoryClickEvent e) {
		if(e.getInventory().getName() == "§8Itens especiais") {
			Player p = (Player)e.getWhoClicked();
			e.setCancelled(true);
			/// capsula
			if(e.getSlot() == 10) {
				p.getInventory().addItem(Main.capsula);
				return;
			}
			/// lancador
			if(e.getSlot() == 11) {
				p.getInventory().addItem(Main.lancador);
				return;
			}
			/// raio mestre
			if(e.getSlot() == 12) {
				p.getInventory().addItem(Main.raiomestre);
				return;
			}
			/// armadilha
			if(e.getSlot() == 13) {
				p.getInventory().addItem(Main.armadilha);
				return;
			}
			/// purificador
			if(e.getSlot() == 14) {
				p.getInventory().addItem(Main.purificador);
				return;
			}
			/// silktouch
			if(e.getSlot() == 15) {
				p.getInventory().addItem(Main.silktouch);
				return;
			}
			/// fragmento
			if(e.getSlot() == 16) {
				p.getInventory().addItem(Main.fragmentodepoder);
				return;
			}
			/// poder
			if(e.getSlot() == 20) {
				p.getInventory().addItem(Main.podermaximo);
				return;
			}
			/// creeper eletrizado
			if(e.getSlot() == 19) {
				p.getInventory().addItem(Main.creepereletrico);
				return;
			}
			/// poder instantaneo
			if(e.getSlot() == 21) {
				p.getInventory().addItem(Main.poderinstantaneo);
				return;
			}
		}
	}
}
