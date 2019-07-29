package br.itensespeciais.funcionalidades;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;

import br.itensespeciais.itemstack.ItemManager;

public class SilkTouch implements Listener{

	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
			Block block = e.getClickedBlock();
			if(block.getType() == Material.BEDROCK) {
				Player p = e.getPlayer();
				if(p.getItemInHand().isSimilar(ItemManager.getSilkTouch())) {
					Location loc = block.getLocation();
					if(loc.getY() == 0) {
						return;
					}
					
					p.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, Material.BEDROCK);
					PS chunk = PS.valueOf(loc);
					MPlayer mp = MPlayer.get(p);
					if(BoardColl.get().getFactionAt(chunk) == FactionColl.get().getNone()) {
						block.setType(Material.AIR);
						p.getInventory().addItem(new ItemStack(Material.BEDROCK, 1));
					}
			
					if(BoardColl.get().getFactionAt(chunk) == mp.getFaction()) {
						block.setType(Material.AIR);
						p.getInventory().addItem(new ItemStack(Material.BEDROCK, 1));
					}
				}
			}
		}
	}
}
