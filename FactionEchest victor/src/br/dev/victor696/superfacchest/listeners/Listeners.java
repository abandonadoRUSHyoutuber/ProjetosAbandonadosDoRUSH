package br.dev.victor696.superfacchest.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.event.EventFactionsDisband;

import br.dev.victor696.superfacchest.SuperFacChest;
import br.dev.victor696.superfacchest.managers.SqlManager;
import br.dev.victor696.superfacchest.object.Faccao;
import br.dev.victor696.superfacchest.utils.Serializer;

public class Listeners implements Listener {
	
	@EventHandler
	public void InventoryClose(InventoryCloseEvent e) {
		if (e.getInventory().getName().contains(" - Baú")) {
			Faccao f = SuperFacChest.getInstance().faccao.get(MPlayer.get(e.getPlayer()).getFaction().getName());
			f.setContents(Serializer.toBase64(e.getInventory()));
		}
	}
	
	@EventHandler
	public void FactionsDisband(EventFactionsDisband e) {
		String faccao = e.getFaction().getName();
		if (SqlManager.hasFaccao(faccao)) {
			SqlManager.deleteFaccao(faccao);
			SuperFacChest.getInstance().faccao.remove(faccao);
		}
	}
	
	@EventHandler
	public void InventoryClick(InventoryClickEvent e) {
		if (e.getCurrentItem() == null)
			return;

		Player p = (Player) e.getWhoClicked();
		MPlayer mp = MPlayer.get(p);
		
		if (e.getInventory().getTitle().equals("§8Deseja comprar?")) {
			e.setCancelled(true);
			if (e.getSlot() == 11) {
				if (!mp.hasFaction()) {
					p.sendMessage("§cVocê não possui facção!");
					p.closeInventory();
					return;
				}

				if (SuperFacChest.getInstance().economy.getEconomy().getBalance(p) < SuperFacChest.getInstance().getConfig().getInt("Valor")) {
					p.sendMessage("§cVocê precisa de $" + SuperFacChest.getInstance().economy.getEconomy().format(SuperFacChest.getInstance().getConfig().getInt("Valor")) + " coins para comprar este baú.");
					p.closeInventory();
					return;
				}
				
				Faction f = mp.getFaction();
				String faccao = f.getName();
				
				if (SqlManager.hasFaccao(faccao)) {
					p.sendMessage("§cSó é permitido um baú por facção.");
					p.closeInventory();
					return;
				}
				
				SqlManager.createChest(faccao);
				SuperFacChest.getInstance().economy.getEconomy().withdrawPlayer(p, SuperFacChest.getInstance().getConfig().getInt("Valor"));
				p.sendMessage("§aBaú da facção comprado com sucesso!");
				Faccao ff = new Faccao(f.getName(), "null");
				SuperFacChest.getInstance().faccao.put(f.getName(), ff);
				p.closeInventory();
			}
			if (e.getSlot() == 15) {
				p.closeInventory();
			}
		}
	}

}
