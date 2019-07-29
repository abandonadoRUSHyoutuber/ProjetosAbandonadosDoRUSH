package br.itensespeciais.funcionalidades;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.massivecraft.factions.entity.MPlayer;

import br.itensespeciais.itemstack.ItemManager;
import br.itensespeciais.main.Main;

public class PoderInstantaneo implements Listener {

	@EventHandler
	public void click(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_AIR) {
			Player p = e.getPlayer();
			if(p.getItemInHand().isSimilar(Main.poderinstantaneo)) {
				e.setCancelled(true);
				MPlayer mp = MPlayer.get(p);
				
				int poderatual = mp.getPowerRounded();
				if(poderatual >= mp.getPowerMax()) {
					p.sendMessage("§cVocê está com seu poder atual no máximo. (" + mp.getPowerRounded() + "/" + mp.getPowerMaxRounded() + ")");
					return;
				}
				
				ItemManager.removerItemMao(p);
				
				mp.setPower(mp.getPowerRounded() + 1.0);
				p.sendMessage("§aPoder atual aumentado em 1! Total: (" + mp.getPowerRounded() + "/" + mp.getPowerMaxRounded() + ")");
			}
		}
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			if(p.getItemInHand().isSimilar(Main.poderinstantaneo)) {
				e.setCancelled(true);
				MPlayer mp = MPlayer.get(p);
				
				int poderatual = mp.getPowerRounded();
				if(poderatual >= mp.getPowerMax()) {
					p.sendMessage("§cVocê está com seu poder atual no máximo. (" + mp.getPowerRounded() + "/" + mp.getPowerMaxRounded() + ")");
					return;
				}
				
				ItemManager.removerItemMao(p);
				
				mp.setPower(mp.getPowerRounded() + 1.0);
				p.sendMessage("§aPoder atual aumentado em 1! Total: (" + mp.getPowerRounded() + "/" + mp.getPowerMaxRounded() + ")");
			}
		}
	}
}
