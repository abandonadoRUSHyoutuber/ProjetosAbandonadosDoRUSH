package me.douglasamv.kitpvp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import me.douglasamv.kitpvp.kits.Ajnin;
import me.douglasamv.kitpvp.kits.Anchor;
import me.douglasamv.kitpvp.kits.Archer;
import me.douglasamv.kitpvp.kits.Backpacker;
import me.douglasamv.kitpvp.kits.Berserker;
import me.douglasamv.kitpvp.kits.Camel;
import me.douglasamv.kitpvp.kits.DeshFire;
import me.douglasamv.kitpvp.kits.EnderMage;
import me.douglasamv.kitpvp.kits.Fireman;
import me.douglasamv.kitpvp.kits.FisherMan;
import me.douglasamv.kitpvp.kits.ForceField;
import me.douglasamv.kitpvp.kits.Frosty;
import me.douglasamv.kitpvp.kits.Gladiator;
import me.douglasamv.kitpvp.kits.Grandpa;
import me.douglasamv.kitpvp.kits.Hulk;
import me.douglasamv.kitpvp.kits.Kangaroo;
import me.douglasamv.kitpvp.kits.Monk;
import me.douglasamv.kitpvp.kits.Neo;
import me.douglasamv.kitpvp.kits.Neurotic;
import me.douglasamv.kitpvp.kits.Ninja;
import me.douglasamv.kitpvp.kits.Phantom;
import me.douglasamv.kitpvp.kits.Poseidon;
import me.douglasamv.kitpvp.kits.PvP;
import me.douglasamv.kitpvp.kits.Reaper;
import me.douglasamv.kitpvp.kits.Ryu;
import me.douglasamv.kitpvp.kits.Snail;
import me.douglasamv.kitpvp.kits.Sonic;
import me.douglasamv.kitpvp.kits.Souper;
import me.douglasamv.kitpvp.kits.Stomper;
import me.douglasamv.kitpvp.kits.Switcher;
import me.douglasamv.kitpvp.kits.Urgal;
import me.douglasamv.kitpvp.kits.Vacuum;
import me.douglasamv.kitpvp.kits.Viper;
import me.douglasamv.kitpvp.kits.warps.Fps;
import me.douglasamv.kitpvp.kits.warps.Knock;
import me.douglasamv.kitpvp.kits.warps.Lava;
import me.douglasamv.kitpvp.kits.warps.MainA;
import me.douglasamv.kitpvp.utils.ComprarKit;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Eventos implements Listener {

	@EventHandler
	void ping(ServerListPingEvent e) {
		e.setMotd(Main.pl.getConfig().getString("Motd").replace("&", "§").replace("<linha>", "\n").replace("<online>",
				String.valueOf(Bukkit.getOnlinePlayers().size())));
		e.setMaxPlayers(Main.pl.getConfig().getInt("Slots"));
	}

	@EventHandler
	void entrar(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
		TitleAPI.sendTabTitle(e.getPlayer(),
				Main.pl.getConfig().getString("TabListUP").replace("&", "§").replace("$line$", "\n"),
				Main.pl.getConfig().getString("TabListDown").replace("&", "§").replace("$line$", "\n"));
		if (Main.pl.getConfig().getBoolean("JoinInv")) {
			Inventarios.resetar(e.getPlayer());
		}
	}

	@EventHandler
	void clicarfrenetico(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (Inventarios.contar.contains(p)) {
			if ((e.getAction() == Action.LEFT_CLICK_AIR) || (e.getAction() == Action.LEFT_CLICK_BLOCK)) {
				p.setLevel(p.getLevel() + 1);
				Inventarios.clicks.put(p, Inventarios.clicks.get(p) + 1);
			}
		}
	}

	@EventHandler
	public void morrer(PlayerDeathEvent e) {
		e.setDeathMessage(null);
		e.getDrops().clear();
		Player matou = e.getEntity().getKiller();
		Player morreu = e.getEntity().getPlayer();
		if (!(e.getEntity() instanceof Player))
			return;
		if (!(e.getEntity().getKiller() instanceof Player))
			return;
		if (HabilidadeApi.verHB(matou).equalsIgnoreCase(Main.cfg_x1.getString("x1.ability")))
			return;
		if (HabilidadeApi.verHB(matou).equalsIgnoreCase("stomper"))
			return;
		new BukkitRunnable() {

			@Override
			public void run() {
				try {
					ResultSet rs = Main.getMysql().conectar().createStatement()
							.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + matou.getName() + "';");
					if (rs.next()) {
						Main.getMysql().conectar().createStatement()
								.executeUpdate("UPDATE `pvp` SET `kill`='" + String.valueOf((rs.getInt("kill") + 1))
										+ "' WHERE `nick`='" + matou.getName() + "';");
						Main.getMysql().conectar().createStatement()
								.executeUpdate("UPDATE `pvp` SET `money`='" + String.valueOf((rs.getInt("money") + 100))
										+ "' WHERE `nick`='" + matou.getName() + "';");
					}
					rs.getStatement().getConnection().close();

					ResultSet rs1 = Main.getMysql().conectar().createStatement()
							.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + morreu.getName() + "';");
					if (rs1.next()) {
						Main.getMysql().conectar().createStatement()
								.executeUpdate("UPDATE `pvp` SET `death`='" + String.valueOf((rs1.getInt("death") + 1))
										+ "' WHERE `nick`='" + morreu.getName() + "';");
						Main.getMysql().conectar().createStatement()
								.executeUpdate("UPDATE `pvp` SET `money`='" + String.valueOf((rs1.getInt("money") - 25))
										+ "' WHERE `nick`='" + morreu.getName() + "';");
					}
					rs1.getStatement().getConnection().close();

				} catch (SQLException e1) {
				}

			}
		}.runTaskAsynchronously(Main.pl);
		Inventarios.upDateScore(matou);
		Inventarios.upDateScore(morreu);
		matou.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("killed").replace("$player$", morreu.getName())));
		morreu.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("killer").replace("$player$", matou.getName())));
	}

	@EventHandler
	void craftar(PrepareItemCraftEvent e) {
		if (e.getInventory().getResult().isSimilar(new ItemStack(Material.MUSHROOM_SOUP))) {
			e.getInventory().setResult(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0, "§7Soup", false));
		}
	}

	@EventHandler
	void spawne(ItemSpawnEvent e) {
		e.getEntity().setVelocity(new Vector(0, 1, 0));
		new BukkitRunnable() {

			@Override
			public void run() {
				e.getEntity().remove();
				e.getEntity().getLocation().getWorld().playEffect(e.getEntity().getLocation(), Effect.SMOKE, 10);
			}
		}.runTaskLater(Main.pl, 8);
	}

	@EventHandler
	void drops(PlayerDropItemEvent e) {
		Material type = e.getItemDrop().getItemStack().getType();
		if ((type != Material.MUSHROOM_SOUP) && (type != Material.BOWL) && (type != Material.RED_MUSHROOM)
				&& (type != Material.GLASS_BOTTLE) && (type != Material.BROWN_MUSHROOM)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	void sair(PlayerQuitEvent e) {
		e.setQuitMessage(null);
	}

	@EventHandler
	void spawn(CreatureSpawnEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	void sopa(PlayerInteractEvent e) {
		ItemStack tigela = new ItemStack(Material.BOWL);
		Player p = e.getPlayer();
		Damageable hp = p;
		if (hp.getHealth() != hp.getMaxHealth()) {
			int vida = 7;
			if (e.getAction().name().contains("RIGHT")) {
				if (p.getItemInHand().getType() == Material.MUSHROOM_SOUP) {
					p.setHealth(hp.getHealth() + vida >= hp.getMaxHealth() ? hp.getMaxHealth() : hp.getHealth() + vida);
					p.getItemInHand().setType(tigela.getType());
					p.getItemInHand().setItemMeta(tigela.getItemMeta());
				}
			}
		}
	}

	@EventHandler
	void kabum(BlockExplodeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	void morrerRespawn(PlayerDeathEvent e) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (e.getEntity().getPlayer() instanceof Player) {
					e.getEntity().getPlayer().spigot().respawn();
				}
			}
		}.runTask(Main.pl);
	}

	public void conectarBungee(Player p, String s) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(s);
		Player player = Bukkit.getPlayerExact(p.getName());
		player.sendPluginMessage(Main.pl, "BungeeCord", out.toByteArray());
	}

	@EventHandler
	void cmdBlock(PlayerCommandPreprocessEvent e) {
		/*
		 * if(e.getMessage().equalsIgnoreCase("/pl")) {
		 * e.getPlayer().sendMessage("§2Plugin feito por Douglas_AMV");
		 * e.getPlayer().sendMessage("§2Skype: duex_pessoal");
		 * e.setCancelled(true); }
		 * if(e.getMessage().equalsIgnoreCase("/plugins")) {
		 * e.getPlayer().sendMessage("§2Plugin feito por Douglas_AMV");
		 * e.getPlayer().sendMessage("§2Skype: duex_pessoal");
		 * e.setCancelled(true); } if(e.getMessage().equalsIgnoreCase("/?")) {
		 * e.getPlayer().sendMessage("§2Plugin feito por Douglas_AMV");
		 * e.getPlayer().sendMessage("§2Skype: duex_pessoal");
		 * e.setCancelled(true); }
		 * if(e.getMessage().equalsIgnoreCase("/bukkit:pl")) {
		 * e.getPlayer().sendMessage("§2Plugin feito por Douglas_AMV");
		 * e.getPlayer().sendMessage("§2Skype: duex_pessoal");
		 * e.setCancelled(true); }
		 * if(e.getMessage().equalsIgnoreCase("/bukkit:plugins")) {
		 * e.getPlayer().sendMessage("§2Plugin feito por Douglas_AMV");
		 * e.getPlayer().sendMessage("§2Skype: duex_pessoal");
		 * e.setCancelled(true); }
		 * if(e.getMessage().equalsIgnoreCase("/bukkit:?")) {
		 * e.getPlayer().sendMessage("§2Plugin feito por Douglas_AMV");
		 * e.getPlayer().sendMessage("§2Skype: duex_pessoal");
		 * e.setCancelled(true); }
		 */
	}

	@EventHandler
	void mover(PlayerMoveEvent e) {
		if (Inventarios.click.contains(e.getPlayer())) {
			Location loc = e.getPlayer().getLocation();
			e.getPlayer().teleport(loc);
		}
	}

	@EventHandler
	void clicar(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction().equals(Action.LEFT_CLICK_AIR))
			return;
		if (e.getAction().equals(Action.LEFT_CLICK_BLOCK))
			return;
		if (!HabilidadeApi.semHB(p))
			return;
		if (p.getItemInHand().isSimilar(ItemAPI.Criar(Material.PRISMARINE_SHARD, 1, 0, "§bRanks", false))) {
			Gui.rank(p);
			return;
		}
		if (p.getItemInHand().isSimilar(ItemAPI.Criar(Material.CHEST, 1, 0, "§6Kits", false))) {
			Gui.kits(p);
			return;
		}
		if (p.getItemInHand().isSimilar(ItemAPI.Criar(Material.EMERALD, 1, 0, "§aStore", false))) {
			Gui.loja(p);
			return;
		}
		if (p.getItemInHand().isSimilar(ItemAPI.Criar(Material.BOOK, 1, 0, "§cWarps", false))) {
			Gui.warps(p);
			return;
		}
		/*
		 * if (p.getItemInHand().isSimilar(ItemAPI.Criar(Material.STICK, 1, 0,
		 * "§dClick Teste", false))) { Inventarios.clicktest(p); return; }
		 */
	}

	@EventHandler
	void respawn(PlayerRespawnEvent e) {
		if (HabilidadeApi.verHB(e.getPlayer()).equalsIgnoreCase(Main.cfg_x1.getString("x1.ability")))
			return;
		Inventarios.resetar(e.getPlayer());
	}

	@EventHandler
	void Chat(AsyncPlayerChatEvent e) {
		if (me.douglasamv.kitpvp.cmd.Chat.chat == false) {
			if (!e.getPlayer().hasPermission("kitpvp.baypass.chat")) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(Mensagens.cor(Main.pl.getConfig().getString("ChatOffTry")));
				return;
			}
		}
		if (Main.cfg_chat.getBoolean("custom")) {
			try {
				ResultSet rs;
				rs = Main.getMysql().conectar().createStatement()
						.executeQuery("SELECT * FROM `pvp` WHERE `nick`='" + e.getPlayer().getName() + "';");
				rs.next();
				e.setFormat(String.valueOf(Main.cfg_chat.getString("chat_prefix")).replace("&", "§")
						.replace("$player$", e.getPlayer().getDisplayName()).replace("$rank$",
								Inventarios.rankNick(rs.getInt("kill")))
						+ " §r" + e.getMessage().toLowerCase());
				rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
			}
		}

	}

	@EventHandler
	void fome(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	void placa(SignChangeEvent e) {
		for (int i = 0; i < 4; i++) {
			e.setLine(i, e.getLine(i).replace("&", "§"));
		}
		if (e.getLine(0).toLowerCase().contains("sopa")) {
			e.setLine(0, "§9-=-=-=-=-=-=-=");
			e.setLine(1, "§b§lSopas");
			e.setLine(2, "§b§lFREE");
			e.setLine(3, "§9-=-=-=-=-=-=-=");
		} else if (e.getLine(0).toLowerCase().contains("recraft")) {
			e.setLine(0, "§9-=-=-=-=-=-=-=");
			e.setLine(1, "§b§lRecraft");
			e.setLine(2, "§b§lFREE");
			e.setLine(3, "§9-=-=-=-=-=-=-=");
		} else if (e.getLine(0).toLowerCase().contains("soup")) {
			e.setLine(0, "§9-=-=-=-=-=-=-=");
			e.setLine(1, "§b§lSoup");
			e.setLine(2, "§b§lFREE");
			e.setLine(3, "§9-=-=-=-=-=-=-=");
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction().name().contains("BLOCK")) {
			if (e.getClickedBlock().getType() == Material.WALL_SIGN
					|| e.getClickedBlock().getType() == Material.SIGN_POST) {
				Sign placa = (Sign) e.getClickedBlock().getState();
				if (e.getAction().equals(Action.LEFT_CLICK_BLOCK))
					return;
				if (placa.getLine(1).equalsIgnoreCase("§b§lSoup")) {
					e.setCancelled(true);
					Inventory inv = Bukkit.createInventory(null, 54, "§9Soup");
					for (int i = 0; i < inv.getSize(); i++) {
						inv.addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0, "§7Soup", false));
					}
					e.getPlayer().openInventory(inv);
				} else if (placa.getLine(1).equalsIgnoreCase("§b§lRecraft")) {
					e.setCancelled(true);
					ItemStack cogu = ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0, "§cRed mushroom", false);
					ItemStack cogu2 = ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0, "§6Brown mushroom", false);
					ItemStack pote = ItemAPI.Criar(Material.BOWL, 64, 0, "§7Bowl", false);
					Inventory inv = Bukkit.createInventory(null, 27, "§9Recraft");
					for (int i = 0; i < 9; i++) {
						inv.setItem(i, cogu);
					}
					for (int i = 9; i < 18; i++) {
						inv.setItem(i, cogu2);
					}
					for (int i = 18; i < 27; i++) {
						inv.setItem(i, pote);
					}
					e.getPlayer().openInventory(inv);
					/*
					 * if (!(e.getPlayer().getInventory().firstEmpty() < 0)) {
					 * e.getPlayer().sendMessage("§7Você pegou recraft");
					 * ItemStack cogu = ItemAPI.Criar(Material.RED_MUSHROOM, 64,
					 * 0, "§cRed mushroom", false); ItemStack cogu2 =
					 * ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					 * "§6Brown mushroom", false); ItemStack pote =
					 * ItemAPI.Criar(Material.BOWL, 64, 0, "§7Bowl", false);
					 * e.getPlayer().getInventory().addItem(new ItemStack[] {
					 * cogu, cogu2, pote }); e.getPlayer().updateInventory(); }
					 * else { e.getPlayer().sendMessage(
					 * "§7Seu inventário está lotado"); }
					 */
				} else if (placa.getLine(1).equalsIgnoreCase("§b§lSopas")) {
					e.setCancelled(true);
					Inventory inv = Bukkit.createInventory(null, 54, "§9Sopas");
					for (int i = 0; i < inv.getSize(); i++) {
						inv.addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0, "§7Soup", false));
					}
					e.getPlayer().openInventory(inv);
				}
			}
		}
	}

	public static ArrayList<String> jump = new ArrayList<>();

	@EventHandler
	void onJump(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if ((e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SPONGE) && (e.getTo().getBlock()
				.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType() == Material.DIAMOND_BLOCK)) {
			jump.remove(p.getName());
			p.setVelocity(p.getVelocity().setY(4.0D));
			p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, Material.SPONGE, 20);
			jump.add(p.getName());
		} else if ((e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SPONGE)
				&& (e.getTo().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN)
						.getType() == Material.EMERALD_BLOCK)) {
			jump.remove(p.getName());
			p.setVelocity(new Vector(p.getVelocity().getX(), 1.5D, p.getVelocity().getZ()));
			p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, Material.SPONGE, 20);
			jump.add(p.getName());
		} else if ((e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SPONGE) && (e.getTo()
				.getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType() == Material.IRON_BLOCK)) {
			jump.remove(p.getName());
			p.setVelocity(p.getEyeLocation().getDirection().multiply(3).add(new Vector(0, 0.2, 0)));
			p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, Material.SPONGE, 20);
			jump.add(p.getName());
		}
	}

	@EventHandler
	void onFall(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		Player p = (Player) e.getEntity();
		if ((e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) && (jump.contains(p.getName()))) {
			e.setCancelled(true);
			jump.remove(p.getName());
		}
	}

	@EventHandler
	void flecha(ProjectileHitEvent e) {
		Entity entity = e.getEntity();
		if (entity.getType() == EntityType.ARROW) {
			entity.remove();
		}
	}

	@EventHandler
	void onPlayerCLickInventry(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		try {
			if (e.getCurrentItem().getType() == Material.LEATHER_BOOTS && (e.getCurrentItem() != null)) {
				e.setCancelled(true);
			}
			if (e.getCurrentItem().getType() == Material.LEATHER_CHESTPLATE && (e.getCurrentItem() != null)) {
				e.setCancelled(true);
			}
			if (e.getCurrentItem().getType() == Material.LEATHER_HELMET && (e.getCurrentItem() != null)) {
				e.setCancelled(true);
			}
			if (e.getCurrentItem().getType() == Material.LEATHER_LEGGINGS && (e.getCurrentItem() != null)) {
				e.setCancelled(true);
			}
			if (e.getCurrentItem().getType() == Material.IRON_BOOTS && (e.getCurrentItem() != null)) {
				e.setCancelled(true);
			}
			if (e.getCurrentItem().getType() == Material.IRON_CHESTPLATE && (e.getCurrentItem() != null)) {
				e.setCancelled(true);
			}
			if (e.getCurrentItem().getType() == Material.IRON_HELMET && (e.getCurrentItem() != null)) {
				e.setCancelled(true);
			}
			if (e.getCurrentItem().getType() == Material.IRON_LEGGINGS && (e.getCurrentItem() != null)) {
				e.setCancelled(true);
			}
		} catch (Exception e2) {
		}

		if ((e.getInventory().getTitle().equalsIgnoreCase("§bRanks")) && (e.getCurrentItem() != null)) {
			e.setCancelled(true);
		}

		if ((e.getInventory().getTitle()
				.equalsIgnoreCase(Mensagens.cor(Main.cfg_store.getString("store.gui.kit_store"))))
				&& (e.getCurrentItem() != null)) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.pvp.cmd"),
						Main.cfg_store.getInt("store.kits.pvp.cost"),
						Main.cfg_store.getString("store.kits.pvp.msg_money"),
						Main.cfg_store.getString("store.kits.pvp.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.FIREWORK) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.kangaroo.cmd"),
						Main.cfg_store.getInt("store.kits.kangaroo.cost"),
						Main.cfg_store.getString("store.kits.kangaroo.msg_money"),
						Main.cfg_store.getString("store.kits.kangaroo.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.FISHING_ROD) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.fisherman.cmd"),
						Main.cfg_store.getInt("store.kits.fisherman.cost"),
						Main.cfg_store.getString("store.kits.fisherman.msg_money"),
						Main.cfg_store.getString("store.kits.fisherman.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.BOW) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.archer.cmd"),
						Main.cfg_store.getInt("store.kits.archer.cost"),
						Main.cfg_store.getString("store.kits.archer.msg_money"),
						Main.cfg_store.getString("store.kits.archer.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.LAPIS_BLOCK) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.sonic.cmd"),
						Main.cfg_store.getInt("store.kits.sonic.cost"),
						Main.cfg_store.getString("store.kits.sonic.msg_money"),
						Main.cfg_store.getString("store.kits.sonic.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.deshfire.cmd"),
						Main.cfg_store.getInt("store.kits.deshfire.cost"),
						Main.cfg_store.getString("store.kits.deshfire.msg_money"),
						Main.cfg_store.getString("store.kits.deshfire.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.ANVIL) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.anchor.cmd"),
						Main.cfg_store.getInt("store.kits.anchor.cost"),
						Main.cfg_store.getString("store.kits.anchor.msg_money"),
						Main.cfg_store.getString("store.kits.anchor.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.WOOD_HOE) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.reaper.cmd"),
						Main.cfg_store.getInt("store.kits.reaper.cost"),
						Main.cfg_store.getString("store.kits.reaper.msg_money"),
						Main.cfg_store.getString("store.kits.reaper.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.FEATHER) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.phantom.cmd"),
						Main.cfg_store.getInt("store.kits.phantom.cost"),
						Main.cfg_store.getString("store.kits.phantom.msg_money"),
						Main.cfg_store.getString("store.kits.phantom.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.POTION) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.urgal.cmd"),
						Main.cfg_store.getInt("store.kits.urgal.cost"),
						Main.cfg_store.getString("store.kits.urgal.msg_money"),
						Main.cfg_store.getString("store.kits.urgal.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.STICK) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.grandpa.cmd"),
						Main.cfg_store.getInt("store.kits.grandpa.cost"),
						Main.cfg_store.getString("store.kits.grandpa.msg_money"),
						Main.cfg_store.getString("store.kits.grandpa.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.STRING) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.snail.cmd"),
						Main.cfg_store.getInt("store.kits.snail.cost"),
						Main.cfg_store.getString("store.kits.snail.msg_money"),
						Main.cfg_store.getString("store.kits.snail.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.LEATHER) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.backpacker.cmd"),
						Main.cfg_store.getInt("store.kits.backpacker.cost"),
						Main.cfg_store.getString("store.kits.backpacker.msg_money"),
						Main.cfg_store.getString("store.kits.backpacker.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.BONE) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.hulk.cmd"),
						Main.cfg_store.getInt("store.kits.hulk.cost"),
						Main.cfg_store.getString("store.kits.hulk.msg_money"),
						Main.cfg_store.getString("store.kits.hulk.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.BLAZE_ROD) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.monk.cmd"),
						Main.cfg_store.getInt("store.kits.monk.cost"),
						Main.cfg_store.getString("store.kits.monk.msg_money"),
						Main.cfg_store.getString("store.kits.monk.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.IRON_BOOTS) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.stomper.cmd"),
						Main.cfg_store.getInt("store.kits.stomper.cost"),
						Main.cfg_store.getString("store.kits.stomper.msg_money"),
						Main.cfg_store.getString("store.kits.stomper.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.SPIDER_EYE) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.viper.cmd"),
						Main.cfg_store.getInt("store.kits.viper.cost"),
						Main.cfg_store.getString("store.kits.viper.msg_money"),
						Main.cfg_store.getString("store.kits.viper.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.WATER_BUCKET) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.poseidon.cmd"),
						Main.cfg_store.getInt("store.kits.poseidon.cost"),
						Main.cfg_store.getString("store.kits.poseidon.msg_money"),
						Main.cfg_store.getString("store.kits.poseidon.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.FIREBALL) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.fireman.cmd"),
						Main.cfg_store.getInt("store.kits.fireman.cost"),
						Main.cfg_store.getString("store.kits.fireman.msg_money"),
						Main.cfg_store.getString("store.kits.fireman.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.SNOW_BALL) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.swticher.cmd"),
						Main.cfg_store.getInt("store.kits.swticher.cost"),
						Main.cfg_store.getString("store.kits.swticher.msg_money"),
						Main.cfg_store.getString("store.kits.swticher.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.NETHER_STAR) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.ninja.cmd"),
						Main.cfg_store.getInt("store.kits.ninja.cost"),
						Main.cfg_store.getString("store.kits.ninja.msg_money"),
						Main.cfg_store.getString("store.kits.ninja.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.BEACON) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.ryu.cmd"),
						Main.cfg_store.getInt("store.kits.ryu.cost"),
						Main.cfg_store.getString("store.kits.ryu.msg_money"),
						Main.cfg_store.getString("store.kits.ryu.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.FERMENTED_SPIDER_EYE) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.berserker.cmd"),
						Main.cfg_store.getInt("store.kits.berserker.cost"),
						Main.cfg_store.getString("store.kits.berserker.msg_money"),
						Main.cfg_store.getString("store.kits.berserker.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.IRON_INGOT) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.gladiator.cmd"),
						Main.cfg_store.getInt("store.kits.gladiator.cost"),
						Main.cfg_store.getString("store.kits.gladiator.msg_money"),
						Main.cfg_store.getString("store.kits.gladiator.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.MUSHROOM_SOUP) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.souper.cmd"),
						Main.cfg_store.getInt("store.kits.souper.cost"),
						Main.cfg_store.getString("store.kits.souper.msg_money"),
						Main.cfg_store.getString("store.kits.souper.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.SAND) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.camel.cmd"),
						Main.cfg_store.getInt("store.kits.camel.cost"),
						Main.cfg_store.getString("store.kits.camel.msg_money"),
						Main.cfg_store.getString("store.kits.camel.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.SLIME_BALL) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.neurotic.cmd"),
						Main.cfg_store.getInt("store.kits.neurotic.cost"),
						Main.cfg_store.getString("store.kits.neurotic.msg_money"),
						Main.cfg_store.getString("store.kits.neurotic.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.ENDER_PEARL) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.vaccum.cmd"),
						Main.cfg_store.getInt("store.kits.vaccum.cost"),
						Main.cfg_store.getString("store.kits.vaccum.msg_money"),
						Main.cfg_store.getString("store.kits.vaccum.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.INK_SACK) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.ajnin.cmd"),
						Main.cfg_store.getInt("store.kits.ajnin.cost"),
						Main.cfg_store.getString("store.kits.ajnin.msg_money"),
						Main.cfg_store.getString("store.kits.ajnin.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.IRON_PLATE) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.forcefield.cmd"),
						Main.cfg_store.getInt("store.kits.forcefield.cost"),
						Main.cfg_store.getString("store.kits.forcefield.msg_money"),
						Main.cfg_store.getString("store.kits.forcefield.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.ARROW) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.neo.cmd"),
						Main.cfg_store.getInt("store.kits.neo.cost"),
						Main.cfg_store.getString("store.kits.neo.msg_money"),
						Main.cfg_store.getString("store.kits.neo.msg_buy"));
				return;
			}
			if (e.getCurrentItem().getType() == Material.PACKED_ICE) {
				e.setCancelled(true);
				ComprarKit.comprar(p, Main.cfg_store.getString("store.kits.frosty.cmd"),
						Main.cfg_store.getInt("store.kits.frosty.cost"),
						Main.cfg_store.getString("store.kits.frosty.msg_money"),
						Main.cfg_store.getString("store.kits.frosty.msg_buy"));
				return;
			}
		}
		if ((e.getInventory().getTitle().equalsIgnoreCase("§cWarps")) && (e.getCurrentItem() != null)) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.IRON_CHESTPLATE) {
				e.setCancelled(true);
				MainA.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.GLASS) {
				e.setCancelled(true);
				Fps.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.LAVA_BUCKET) {
				e.setCancelled(true);
				Lava.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.BLAZE_ROD) {
				e.setCancelled(true);
				X1.entrar1v1(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.STICK) {
				e.setCancelled(true);
				Knock.darKit(p);
				return;
			}
		}
		if ((e.getInventory().getTitle().equalsIgnoreCase("§aStore")) && (e.getCurrentItem() != null)) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.GOLD_NUGGET) {
				e.setCancelled(true);
				if (p.hasPermission("kitpvp.kit.*")) {
					Gui.lojaKits2(p);
				} else {
					Gui.lojaKits(p);
				}

				return;
			}
			if (e.getCurrentItem().getType() == Material.DIAMOND) {
				e.setCancelled(true);
				p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("Site")));
				p.closeInventory();
				return;
			}
		}
		if ((e.getInventory().getTitle()
				.equalsIgnoreCase(Mensagens.cor(Main.cfg_kits.getString("kits.gui.other_kits"))))
				&& (e.getCurrentItem() != null)) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.CARPET) {
				e.setCancelled(true);
				Gui.kits(p);
				return;
			}

		}
		if ((e.getInventory().getTitle().equalsIgnoreCase(Mensagens.cor(Main.cfg_kits.getString("kits.gui.your_kits"))))
				&& (e.getCurrentItem() != null)) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.CARPET) {
				e.setCancelled(true);
				if (p.hasPermission("kitpvp.kit.*")) {
					Gui.oKits2(p);
					return;
				} else {
					Gui.oKits(p);
					return;
				}
			}
			if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
				e.setCancelled(true);
				PvP.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.FIREWORK) {
				e.setCancelled(true);
				Kangaroo.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.FISHING_ROD) {
				e.setCancelled(true);
				FisherMan.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.BOW) {
				e.setCancelled(true);
				Archer.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.LAPIS_BLOCK) {
				e.setCancelled(true);
				Sonic.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
				e.setCancelled(true);
				DeshFire.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.ANVIL) {
				e.setCancelled(true);
				Anchor.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.WOOD_HOE) {
				e.setCancelled(true);
				Reaper.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.FEATHER) {
				e.setCancelled(true);
				Phantom.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.POTION) {
				e.setCancelled(true);
				Urgal.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.STICK) {
				e.setCancelled(true);
				Grandpa.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.STRING) {
				e.setCancelled(true);
				Snail.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.LEATHER) {
				e.setCancelled(true);
				Backpacker.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.BONE) {
				e.setCancelled(true);
				Hulk.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.BLAZE_ROD) {
				e.setCancelled(true);
				Monk.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.IRON_BOOTS) {
				e.setCancelled(true);
				Stomper.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.SPIDER_EYE) {
				e.setCancelled(true);
				Viper.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.WATER_BUCKET) {
				e.setCancelled(true);
				Poseidon.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.FIREBALL) {
				e.setCancelled(true);
				Fireman.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.SNOW_BALL) {
				e.setCancelled(true);
				Switcher.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.NETHER_STAR) {
				e.setCancelled(true);
				Ninja.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.BEACON) {
				e.setCancelled(true);
				Ryu.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.FERMENTED_SPIDER_EYE) {
				e.setCancelled(true);
				Berserker.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.IRON_INGOT) {
				e.setCancelled(true);
				Gladiator.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.MUSHROOM_SOUP) {
				e.setCancelled(true);
				Souper.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.SAND) {
				e.setCancelled(true);
				Camel.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.SLIME_BALL) {
				e.setCancelled(true);
				Neurotic.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.ENDER_PEARL) {
				e.setCancelled(true);
				Vacuum.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.INK_SACK) {
				e.setCancelled(true);
				Ajnin.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.IRON_PLATE) {
				e.setCancelled(true);
				ForceField.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.ARROW) {
				e.setCancelled(true);
				Neo.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.PACKED_ICE) {
				e.setCancelled(true);
				Frosty.darKit(p);
				return;
			}
			if (e.getCurrentItem().getType() == Material.NETHER_BRICK_ITEM) {
				e.setCancelled(true);
				EnderMage.darKit(p);
				return;
			}
		}
	}
}
