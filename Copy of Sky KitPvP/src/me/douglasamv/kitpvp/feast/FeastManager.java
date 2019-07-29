package me.douglasamv.kitpvp.feast;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.douglasamv.kitpvp.Main;

public class FeastManager {
	
	public static void spawnarChestFeast(Location loc) {
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		Random r = new Random();
		int perc = 20;
		Bukkit.getWorld("world").getBlockAt(x, y, z).setType(Material.CHEST);
		for(Player p:Bukkit.getOnlinePlayers()) {
		      Chest e2 = (Chest)p.getLocation().getWorld().getBlockAt(x , y, z).getState();
		      if (r.nextInt(100) <= perc) {
		        e2.getBlockInventory().setItem(new Random().nextInt(e2.getBlockInventory().getSize()), new ItemStack(Material.GOLDEN_APPLE));
		      }
		      if (r.nextInt(100) <= perc) {
			        e2.getBlockInventory().setItem(new Random().nextInt(e2.getBlockInventory().getSize()), new ItemStack(Material.GOLDEN_APPLE));
			      }
		      if (r.nextInt(100) <= perc) {
			        e2.getBlockInventory().setItem(new Random().nextInt(e2.getBlockInventory().getSize()), new ItemStack(Material.EXP_BOTTLE));
			      }
		      if (r.nextInt(100) <= perc) {
		        e2.getBlockInventory().setItem(new Random().nextInt(e2.getBlockInventory().getSize()), new ItemStack(Material.IRON_AXE));
		      }
		      if (r.nextInt(100) <= perc) {
		        e2.getBlockInventory().setItem(new Random().nextInt(e2.getBlockInventory().getSize()), new ItemStack(Material.ARROW, 5));
		      }
		      if (r.nextInt(100) <= perc) {
		        e2.getBlockInventory().setItem(new Random().nextInt(e2.getBlockInventory().getSize()), new ItemStack(Material.BOW));
		      }
		      if (r.nextInt(100) <= perc) {
		        e2.getBlockInventory().setItem(new Random().nextInt(e2.getBlockInventory().getSize()), new ItemStack(Material.IRON_HELMET));
		      }
		      if (r.nextInt(100) <= perc) {
		        e2.getBlockInventory().setItem(new Random().nextInt(e2.getBlockInventory().getSize()), new ItemStack(Material.IRON_BOOTS));
		      }
		      e2.getBlockInventory().setItem(new Random().nextInt(e2.getBlockInventory().getSize()), new ItemStack(Material.MUSHROOM_SOUP, 3));
		      if (r.nextInt(100) <= perc) {
		        e2.getBlockInventory().setItem(new Random().nextInt(e2.getBlockInventory().getSize()), new ItemStack(Material.LEATHER_LEGGINGS));
		      }
		      if (r.nextInt(100) <= perc) {
			        e2.getBlockInventory().setItem(new Random().nextInt(e2.getBlockInventory().getSize()), new ItemStack(Material.EXP_BOTTLE));
			      }
		      if (r.nextInt(100) <= perc) {
			        e2.getBlockInventory().setItem(new Random().nextInt(e2.getBlockInventory().getSize()), new ItemStack(Material.EXP_BOTTLE));
			      }
		      e2.getBlockInventory().setItem(new Random().nextInt(e2.getBlockInventory().getSize()), new ItemStack(Material.POTION, 1, (short)16410));
		      if (r.nextInt(100) <= perc) {
		        e2.getBlockInventory().setItem(new Random().nextInt(e2.getBlockInventory().getSize()), new ItemStack(Material.POTION, 1, (short)16426));
		      }
		      if (r.nextInt(100) <= perc) {
		        e2.getBlockInventory().setItem(new Random().nextInt(e2.getBlockInventory().getSize()), new ItemStack(Material.POTION, 
		          1, (short)16428));
		      }
		      if (r.nextInt(100) <= perc) {
		        e2.getBlockInventory().setItem(new Random().nextInt(e2.getBlockInventory().getSize()), new ItemStack(Material.POTION, 1, (short)16418));
		      }
		      if (r.nextInt(100) <= perc) {
		        e2.getBlockInventory().setItem(new Random().nextInt(e2.getBlockInventory().getSize()), new ItemStack(Material.IRON_SWORD));
		      }
		      if (r.nextInt(100) <= perc) {
			        e2.getBlockInventory().setItem(new Random().nextInt(e2.getBlockInventory().getSize()), new ItemStack(Material.IRON_SWORD));
			      }
		      new BukkitRunnable() {
				
				@Override
				public void run() {
					e2.getBlockInventory().clear();
					Bukkit.getWorld("world").getBlockAt(x, y, z).setType(Material.STAINED_GLASS);
				}
			}.runTaskLater(Main.pl, 10*20);
		}
		
		
	}
}
