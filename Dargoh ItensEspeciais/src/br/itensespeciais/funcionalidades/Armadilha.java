package br.itensespeciais.funcionalidades;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import br.itensespeciais.main.Main;

public class Armadilha implements Listener{

	@EventHandler
	public void onThrowSnow(ProjectileLaunchEvent e) {
		if(e.getEntityType() == EntityType.SNOWBALL) {
			if(e.getEntity().getShooter() instanceof Player) {
				Player p = (Player)e.getEntity().getShooter();
				if(p.getItemInHand().isSimilar(Main.armadilha)){
					e.getEntity().setMetadata("armadilha", (MetadataValue)new FixedMetadataValue(Main.get(), true));
				}
			}
		}
	}
	
	@EventHandler
	public void playerBallHit(EntityDamageByEntityEvent e) {
		if(e.getDamager().getType() == EntityType.SNOWBALL) {
			if(e.getDamager().hasMetadata("armadilha")) {
				List<Block> blocos = getBlocks(e.getEntity().getLocation().getBlock(), 1);
				
				Block remover = e.getEntity().getLocation().getBlock();
				for(Block b : blocos) {
					if(b.getY() == e.getEntity().getLocation().getBlockY()) {	
						if(b.getType() == Material.AIR) {
							b.setType(Material.WEB);
						}
					}
				}
				
				new BukkitRunnable() {
					int count = 0;
					@Override
					public void run() {
						if(count == 20) {
							removerBlocos(remover);
							this.cancel();
						} count++;
					}
				}.runTaskTimer(Main.get(), 0L, 20L);
			}
		}
	}
	
	@EventHandler
	public void damageSnow(ProjectileHitEvent e) {
		if(e.getEntityType() == EntityType.SNOWBALL) {
			if(e.getEntity().hasMetadata("armadilha")) {
				List<Block> blocos = getBlocks(e.getEntity().getLocation().getBlock(), 1);
				
				Block remover = e.getEntity().getLocation().getBlock();
				remover.getLocation().setY(remover.getY() + 1);
				for(Block b : blocos) {
					if(b.getY() == e.getEntity().getLocation().getBlockY()) {	
						if(b.getType() == Material.AIR) {
							b.setType(Material.WEB);
						}
					}
				}
				
				new BukkitRunnable() {
					int count = 0;
					@Override
					public void run() {
						if(count == 20) {
							removerBlocos(remover);
							this.cancel();
						} count++;
					}
				}.runTaskTimer(Main.get(), 0L, 20L);
			}
		}
	}
	
	public void removerBlocos(Block b) {
		List<Block> blocos = getBlocks(b, 1);
		for(Block bl : blocos) {
			if(bl.getY() == b.getY()) {
				if(bl.getType() == Material.WEB) {
					bl.setType(Material.AIR);
				}
			}
		}
	}
	
	public ArrayList<Block> getBlocks(Block start, int radius){
		ArrayList<Block> blocks = new ArrayList<Block>();
		for(double x = start.getLocation().getX() - radius; x <= start.getLocation().getX() + radius; x++){
			for(double y = start.getLocation().getY() - radius; y <= start.getLocation().getY() + radius; y++){
				for(double z = start.getLocation().getZ() - radius; z <= start.getLocation().getZ() + radius; z++){
					Location loc = new Location(start.getWorld(), x, y, z);
					blocks.add(loc.getBlock());
				}
			}
		}
		return blocks;
	}
	 	
}
