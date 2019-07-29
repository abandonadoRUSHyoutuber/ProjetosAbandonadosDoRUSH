package me.douglasamv.kitpvp.kits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Gladiator implements Listener {

	public HashMap<Player, Location> localizacao = new HashMap<>();
	public static HashMap<String, String> fighting = new HashMap<>();
	int nextID = 0;
	public HashMap<Integer, String[]> players = new HashMap<>();
	public HashMap<String, Location> oldl = new HashMap<>();
	public HashMap<Integer, ArrayList<Location>> blocks = new HashMap<>();
	public HashMap<Location, Block> bloco = new HashMap<>();
	public int id1;
	public int id2;

	@SuppressWarnings("deprecation")
	@EventHandler
	void OnGladiatorKit(PlayerInteractEntityEvent event) {
		if (!(event.getRightClicked() instanceof Player))
			return;
		Player p = event.getPlayer();
		Player r = (Player) event.getRightClicked();
		if (HabilidadeApi.verHB(p).equalsIgnoreCase(Main.cfg_kits.getString("kits.gladiator.ability"))) {
			if (p.getItemInHand().getType() == Material.IRON_FENCE) {
				event.setCancelled(true);
				if (ProtecaoSpawn.protegido(p)) {
					p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.msg_spawn")));
					return;
				}
				if (ProtecaoSpawn.protegido(r))
					return;
				Location loc = new Location(p.getWorld(), p.getLocation().getBlockX(), p.getLocation().getBlockY() + 70,
						p.getLocation().getBlockZ());
				localizacao.put(p, loc);
				localizacao.put(r, loc);
				Location loc2 = new Location(p.getWorld(), p.getLocation().getBlockX() + 8,
						p.getLocation().getBlockY() + 73, p.getLocation().getBlockZ() + 8);
				Location loc3 = new Location(p.getWorld(), p.getLocation().getBlockX() - 8,
						p.getLocation().getBlockY() + 73, p.getLocation().getBlockZ() - 8);
				if ((fighting.containsKey(p.getName())) || (fighting.containsKey(r.getName()))) {
					event.setCancelled(true);
					p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.msg_fighting")));
					return;
				}
				Integer currentID = Integer.valueOf(nextID);
				nextID += 1;
				ArrayList<String> list = new ArrayList<>();
				list.add(p.getName());
				players.put(currentID, (String[]) list.toArray(new String[1]));
				oldl.put(p.getName(), p.getLocation());
				oldl.put(r.getName(), r.getLocation());
				List<Location> cuboid = new ArrayList<>();
				cuboid.clear();
				int bY;
				for (int bX = -10; bX <= 10; bX++) {
					for (int bZ = -10; bZ <= 10; bZ++) {
						for (bY = -1; bY <= 10; bY++) {
							Block b = loc.clone().add(bX, bY, bZ).getBlock();
							if (!b.isEmpty()) {
								event.setCancelled(true);
								p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.msg_loc_error")));
								return;
							}
							if (bY == 10) {
								cuboid.add(loc.clone().add(bX, bY, bZ));
							} else if (bY == -1) {
								cuboid.add(loc.clone().add(bX, bY, bZ));
							} else if ((bX == -10) || (bZ == -10) || (bX == 10) || (bZ == 10)) {
								cuboid.add(loc.clone().add(bX, bY, bZ));
							}
						}
					}

				}
				for (Location loc1 : cuboid) {
					loc1.getBlock().setType(Material.GLASS);
					bloco.put(loc1, loc1.getBlock());
				}
				loc2.setYaw(135.0F);
				p.teleport(loc2);
				loc3.setYaw(-45.0F);
				r.teleport(loc3);
				p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 110, 5));
				r.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 110, 5));
				p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.msg_pulled").replace("$line$", "\n")));
				r.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.msg_pulled_who").replace("$line$", "\n")));
				
				fighting.put(p.getName(), r.getName());
				fighting.put(r.getName(), p.getName());
				id2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.pl, new BukkitRunnable() {
					@Override
					public void run() {
						p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 2000000, 5));
						r.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 2000000, 5));
					}
				}, 2400L);
				id1 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.pl, new BukkitRunnable() {
					@Override
					public void run() {
						fighting.remove(p.getName());
						fighting.remove(r.getName());
						p.teleport((Location) oldl.get(p.getName()));
						r.teleport((Location) oldl.get(r.getName()));
						oldl.remove(p.getName());
						oldl.remove(r.getName());
						p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.msg_no_winner")));
						r.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.msg_no_winner")));
						Location loc = (Location) localizacao.get(p);
						List<Location> cuboid = new ArrayList<>();
						int bY;
						for (int bX = -10; bX <= 10; bX++) {
							for (int bZ = -10; bZ <= 10; bZ++) {
								for (bY = -1; bY <= 10; bY++) {
									if (bY == 10) {
										cuboid.add(loc.clone().add(bX, bY, bZ));
									} else if (bY == -1) {
										cuboid.add(loc.clone().add(bX, bY, bZ));
									} else if ((bX == -10) || (bZ == -10) || (bX == 10) || (bZ == 10)) {
										cuboid.add(loc.clone().add(bX, bY, bZ));
									}
								}
							}
						}
						for (Location loc1 : cuboid) {
							loc1.getBlock().setType(Material.AIR);
							((Block) bloco.get(loc1)).setType(Material.AIR);
						}
					}
				}, 4800L);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR)
	void onPlyaerInteract(final PlayerInteractEvent e) {
		if ((e.getAction() == Action.LEFT_CLICK_BLOCK) && (e.getClickedBlock().getType() == Material.GLASS)
				&& (e.getPlayer().getGameMode() != GameMode.CREATIVE)
				&& (fighting.containsKey(e.getPlayer().getName()))) {
			e.setCancelled(true);
			e.getClickedBlock().setType(Material.BEDROCK);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.pl, new BukkitRunnable() {
				@Override
				public void run() {
					if (Gladiator.fighting.containsKey(e.getPlayer().getName())) {
						e.getClickedBlock().setType(Material.GLASS);
					}
				}
			}, 30L);
		}
	}

	@EventHandler
	void onPlayerLeft(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (fighting.containsKey(p.getName())) {
			Player k = Bukkit.getServer().getPlayer((String) fighting.get(p.getName()));
			Location old = (Location) oldl.get(p.getName());
			k.teleport(old);
			k.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.msg_won").replace("$player$", p.getName())));
			Bukkit.getScheduler().cancelTask(id1);
			Bukkit.getScheduler().cancelTask(id2);
			k.removePotionEffect(PotionEffectType.WITHER);
			k.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 10));
			fighting.remove(k.getName());
			fighting.remove(p.getName());
			Location loc = (Location) localizacao.get(p);
			List<Location> cuboid = new ArrayList<>();
			cuboid.clear();
			int bY;
			for (int bX = -10; bX <= 10; bX++) {
				for (int bZ = -10; bZ <= 10; bZ++) {
					for (bY = -1; bY <= 10; bY++) {
						if (bY == 10) {
							cuboid.add(loc.clone().add(bX, bY, bZ));
						} else if (bY == -1) {
							cuboid.add(loc.clone().add(bX, bY, bZ));
						} else if ((bX == -10) || (bZ == -10) || (bX == 10) || (bZ == 10)) {
							cuboid.add(loc.clone().add(bX, bY, bZ));
						}
					}
				}
			}
			for (Location loc1 : cuboid) {
				loc1.getBlock().setType(Material.AIR);
				((Block) bloco.get(loc1)).setType(Material.AIR);
			}
		}
	}
	@EventHandler
	void morrer(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if (fighting.containsKey(p.getName())) {
			Player k = Bukkit.getServer().getPlayer((String) fighting.get(p.getName()));
			Location old = (Location) oldl.get(p.getName());
			k.teleport(old);
			k.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.msg_won").replace("$player$", p.getName())));
			Bukkit.getScheduler().cancelTask(id1);
			Bukkit.getScheduler().cancelTask(id2);
			k.removePotionEffect(PotionEffectType.WITHER);
			k.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 10));
			fighting.remove(k.getName());
			fighting.remove(p.getName());
			Location loc = (Location) localizacao.get(p);
			List<Location> cuboid = new ArrayList<Location>();
			cuboid.clear();
			int bY;
			for (int bX = -10; bX <= 10; bX++) {
				for (int bZ = -10; bZ <= 10; bZ++) {
					for (bY = -1; bY <= 10; bY++) {
						if (bY == 10) {
							cuboid.add(loc.clone().add(bX, bY, bZ));
						} else if (bY == -1) {
							cuboid.add(loc.clone().add(bX, bY, bZ));
						} else if ((bX == -10) || (bZ == -10) || (bX == 10) || (bZ == 10)) {
							cuboid.add(loc.clone().add(bX, bY, bZ));
						}
					}
				}
			}
			for (Location loc1 : cuboid) {
				loc1.getBlock().setType(Material.AIR);
				if (this.bloco.containsKey(loc1)) {
					((Block) this.bloco.get(loc1)).setType(Material.AIR);
				}
			}
			return;
		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.gladiator.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.gladiator.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.gladiator.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.gladiator.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.gladiator.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.armor_name")), true));
		}

		p.getInventory().setItem(1, ItemAPI.Criar(Material.IRON_FENCE, 1, 0,
				Mensagens.cor(Main.cfg_kits.getString("kits.gladiator.kit_item")), false));
		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
