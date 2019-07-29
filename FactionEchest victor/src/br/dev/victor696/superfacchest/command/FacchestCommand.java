package br.dev.victor696.superfacchest.command;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.massivecraft.factions.entity.MPlayer;

import br.dev.victor696.superfacchest.SuperFacChest;
import br.dev.victor696.superfacchest.managers.InventoryManager;
import br.dev.victor696.superfacchest.managers.SqlManager;
import br.dev.victor696.superfacchest.object.Faccao;
import br.dev.victor696.superfacchest.utils.Serializer;

public class FacchestCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		if (sender instanceof Player) {
			
			Player p = (Player) sender;
			MPlayer mp = MPlayer.get(p);
			
			if (!mp.hasFaction()) {
				p.sendMessage("§cVocê precisa de uma facção para acessar esta função.");
				return false;
			}
			
			if (!SqlManager.hasFaccao(mp.getFaction().getName())) {
				p.openInventory(InventoryManager.getBuyInv());
				return false;
			}
			
			if (args.length == 0) {
				Faccao f = SuperFacChest.getInstance().faccao.get(mp.getFaction().getName());
				Inventory inv = Bukkit.createInventory(null, 6*9, "§8" + f.getFaccao() + " - Baú");
				if (f.getContents().equalsIgnoreCase("null")) {
					p.openInventory(inv);
					return true;
				}
				try {
					p.openInventory(Serializer.fromBase64(f.getContents(), "§8" + f.getFaccao() + " - Baú"));
				} catch (IOException e) {
					p.sendMessage("§cOcorreu um erro ao abrir o baú da sua facção!");
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}

}
