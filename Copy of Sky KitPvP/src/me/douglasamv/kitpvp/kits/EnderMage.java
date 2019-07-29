package me.douglasamv.kitpvp.kits;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class EnderMage implements Listener {

	public void TeleportP(Location portal, Player p1, Player p2) {
		p1.teleport(portal.clone().add(0.0D, 1.0D, 0.0D));
		p2.teleport(portal.clone().add(0.0D, 1.0D, 0.0D));
		p1.setNoDamageTicks(100);
		p2.setNoDamageTicks(100);
		p1.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("EnderMageTeleport").replace("$line$", "\n")));
		p2.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("EnderMageTeleport").replace("$line$", "\n")));
		p2.getWorld().playEffect(p2.getLocation(), Effect.ENDER_SIGNAL, 9);
		p1.getWorld().playEffect(portal, Effect.ENDER_SIGNAL, 9);
		p2.playSound(p2.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.2F);
		p1.playSound(portal, Sound.ENDERMAN_TELEPORT, 1.0F, 1.2F);
	}

	private boolean isEnderable(Location portal, Location player) {
		return (Math.abs(portal.getX() - player.getX()) < 3.0D) && (Math.abs(portal.getZ() - player.getZ()) < 3.0D)
				&& (Math.abs(portal.getY() - player.getY()) >= 3.5D);
	}

	@EventHandler
	void click(PlayerInteractEvent e) {
		Player mage = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& (mage.getItemInHand().getType() == Material.NETHER_BRICK_ITEM)
				&& (HabilidadeApi.verHB(mage).equalsIgnoreCase("endermage"))) {
			e.setCancelled(true);
			mage.updateInventory();
			mage.setItemInHand(new ItemStack(Material.AIR));
			mage.updateInventory();
			Block b = e.getClickedBlock();
			Location bLoc = b.getLocation();
			BlockState bs = b.getState();
			b.setType(Material.ENDER_PORTAL_FRAME);
			for (Player nearby : Bukkit.getOnlinePlayers()) {
				Player target = nearby.getPlayer();
				new BukkitRunnable() {
					int time = 5;

					@SuppressWarnings("deprecation")
					@Override
					public void run() {
						time -= 1;
						if ((!target.getInventory().contains(Material.NETHER_BRICK_ITEM))
								&& (isEnderable(bLoc, target.getLocation())) && (target != mage)
								&& (!target.isDead())) {
							if (ProtecaoSpawn.protegido(target))
								return;
							b.setType(bs.getType());
							b.setData(bs.getBlock().getData());
							cancel();
							TeleportP(bLoc, mage, target);
							if (!(e.getPlayer().getInventory().firstEmpty() < 0)) {
								mage.getInventory()
										.addItem(ItemAPI.Criar(Material.NETHER_BRICK_ITEM, 1, 0, "§9EnderMage", false));
								mage.updateInventory();
							} else {
								mage.getInventory().setItem(1,
										ItemAPI.Criar(Material.NETHER_BRICK_ITEM, 1, 0, "§9EnderMage", false));
							}
						} else if (this.time == 0) {
							cancel();
							b.setType(bs.getType());
							b.setData(bs.getBlock().getData());
							if ((!mage.getInventory().contains(new ItemStack(Material.NETHER_BRICK_ITEM)))
									&& (HabilidadeApi.verHB(mage).equalsIgnoreCase("endermage"))) {
								if (!(e.getPlayer().getInventory().firstEmpty() < 0)) {
									mage.getInventory().addItem(
											ItemAPI.Criar(Material.NETHER_BRICK_ITEM, 1, 0, "§9EnderMage", false));
									mage.updateInventory();
								} else {
									mage.getInventory().setItem(1,
											ItemAPI.Criar(Material.NETHER_BRICK_ITEM, 1, 0, "§9EnderMage", false));
								}

							}
						}
					}
				}.runTaskTimer(Main.pl, 0, 20);
			}

		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, "endermage");
		TitleAPI.sendTitle(p, 5, 20, 5, "",
				Main.pl.getConfig().getString("Kit").replace("<kit>", HabilidadeApi.verHB(p)).replace("&", "§"));
		p.sendMessage(Main.pl.getConfig().getString("Kit").replace("<kit>", HabilidadeApi.verHB(p)).replace("&", "§"));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		for (int i = 0; i < 36; i++) {
			p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0, "§7Soup", false));
		}
		p.getInventory().setItem(0,
				ItemAPI.Criar(Material.STONE_SWORD, 1, 0, "§cSword", true, Enchantment.DAMAGE_ALL, 1));
		p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0, "§cRed mushroom", false));
		p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0, "§6Brown mushroom", false));
		p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0, "§7Bowl", false));
		p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0, "§7Armor", true));
		p.getInventory().setItem(1, ItemAPI.Criar(Material.NETHER_BRICK_ITEM, 1, 0, "§9EnderMage", false));
		p.updateInventory();
		Inventarios.upDateScore(p);
		// NametagEdit.getApi().setSuffix(p.getName(), " §b" +
		// HabilidadeApi.verHB(p));
	}
}
