package me.douglasamv.kitpvp.cmd;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;

public class Admin implements CommandExecutor, Listener {

	static ArrayList<Player> player = new ArrayList<>();

	public static HashMap<String, ItemStack[]> saveinv = new HashMap<>();
	public static HashMap<String, ItemStack[]> armadura = new HashMap<>();

	public void esperar(final Player tpl, final Player testador) {

		new BukkitRunnable() {

			@Override
			public void run() {
				if (tpl.getHealth() > 3.0D) {
					testador.sendMessage(Mensagens.autoSoupResult1.replace("$tested$", tpl.getName()).replace("$line$", "\n"));
					tpl.getInventory().setContents((ItemStack[]) saveinv.get(tpl.getName()));
					tpl.getInventory().setArmorContents((ItemStack[]) armadura.get(tpl.getName()));
					tpl.updateInventory();
					tpl.setHealth(20.0D);
				} else if (tpl.getHealth() < 3.0D) {
					testador.sendMessage(Mensagens.autoSoupResult2.replace("$tested$", tpl.getName()).replace("$line$", "\n"));
					tpl.getInventory().setContents((ItemStack[]) saveinv.get(tpl.getName()));
					tpl.getInventory().setArmorContents((ItemStack[]) armadura.get(tpl.getName()));
					tpl.updateInventory();
					tpl.setHealth(20.0D);
				}

			}
		}.runTaskLaterAsynchronously(Main.pl, 20);

	}

	@EventHandler
	void click(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if (!HabilidadeApi.verHB(p).equalsIgnoreCase("admin"))
			return;
		if (!(e.getRightClicked() instanceof Player))
			return;
		if (p.getItemInHand().getType() == Material.AIR) {
			p.openInventory(((Player) e.getRightClicked()).getInventory());
		}
		if (p.getItemInHand().getType() == Material.MUSHROOM_SOUP) {
			p.openInventory(((Player) e.getRightClicked()).getInventory());
			saveinv.put(((Player) e.getRightClicked()).getName(),
					((Player) e.getRightClicked()).getInventory().getContents());
			armadura.put(((Player) e.getRightClicked()).getName(),
					((Player) e.getRightClicked()).getInventory().getArmorContents());
			((Player) e.getRightClicked()).getInventory().clear();
			((Player) e.getRightClicked()).getInventory().setArmorContents(null);

			ItemStack item222 = new ItemStack(Material.MUSHROOM_SOUP);
			ItemMeta item222M = item222.getItemMeta();
			item222M.setDisplayName("§eForge auto-soup = ban");
			item222.setItemMeta(item222M);
			((Player) e.getRightClicked()).getInventory().setItem(22, item222);
			((Player) e.getRightClicked())
					.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 999));
			((Player) e.getRightClicked()).setHealth(1);

			new BukkitRunnable() {

				@Override
				public void run() {

					esperar(((Player) e.getRightClicked()), p);
				}
			}.runTaskLater(Main.pl, 5);

		}
		if (p.getItemInHand().getType() == Material.ANVIL) {
			e.getRightClicked().setVelocity(new Vector(0, 5, 0));
			new BukkitRunnable() {

				@Override
				public void run() {
					e.getRightClicked().setVelocity(new Vector(0, -5, 0));

				}
			}.runTaskLaterAsynchronously(Main.pl, 20);
		}
		if (p.getItemInHand().getType() == Material.IRON_FENCE) {

			((Player) e.getRightClicked()).getLocation().add(0.0D, 13.0D, 0.0D).getBlock().setType(Material.BEDROCK);
			((Player) e.getRightClicked()).getLocation().add(0.0D, 11.0D, 1.0D).getBlock().setType(Material.BEDROCK);
			((Player) e.getRightClicked()).getLocation().add(1.0D, 11.0D, 0.0D).getBlock().setType(Material.BEDROCK);
			((Player) e.getRightClicked()).getLocation().add(0.0D, 11.0D, -1.0D).getBlock().setType(Material.BEDROCK);
			((Player) e.getRightClicked()).getLocation().add(-1.0D, 11.0D, 0.0D).getBlock().setType(Material.BEDROCK);
			((Player) e.getRightClicked()).getLocation().add(0.0D, 10.0D, 0.0D).getBlock().setType(Material.BEDROCK);
			((Player) e.getRightClicked())
					.teleport(((Player) e.getRightClicked()).getLocation().add(0.0D, 11.0D, -0.05D));
		}
	}

	@EventHandler
	void join(PlayerJoinEvent e) {
		if (!e.getPlayer().hasPermission("kitpvp.cmd.admin")) {
			for (Player p : player) {
				e.getPlayer().hidePlayer(p);
			}
		}
	}

	@EventHandler
	public void clicar(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack i = p.getItemInHand();
		if (!HabilidadeApi.verHB(p).equalsIgnoreCase("admin"))
			return;
		if (i.getType() == Material.LEVER) {
			p.setVelocity(p.getEyeLocation().getDirection().multiply(3).add(new Vector(0, 0, 0)));
		}
		if (i.getType() == Material.NETHER_BRICK_ITEM) {
			for (Player p2 : Bukkit.getOnlinePlayers()) {
				p2.showPlayer(p);
			}
			p.setGameMode(GameMode.SURVIVAL);
			p.setHealth(20);
			p.setAllowFlight(true);
			p.setFlying(true);
			new BukkitRunnable() {

				@Override
				public void run() {
					for (Player p2 : Bukkit.getOnlinePlayers()) {
						if (!p2.hasPermission("kitpvp.cmd.admin")) {
							p2.hidePlayer(p);
						}
					}
					p.setGameMode(GameMode.CREATIVE);
					p.sendMessage(Mensagens.trocaRapida);
				}
			}.runTaskLater(Main.pl, 20);
		}
	}

	@EventHandler
	void sair(PlayerQuitEvent e) {
		if (player.contains(e.getPlayer().getName())) {
			player.remove(e.getPlayer().getName());
		}
	}

	@EventHandler
	void kick(PlayerKickEvent e) {
		if (player.contains(e.getPlayer().getName())) {
			player.remove(e.getPlayer().getName());
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("kitpvp.cmd.admin")) {
			p.sendMessage(Mensagens.noPerm);
			return true;
		}

		if (player.contains(p)) {
			player.remove(p);
			p.sendMessage(Mensagens.sairModoAdmin);
			Inventarios.resetar(p);
			for (Player p2 : Bukkit.getOnlinePlayers()) {
				p2.showPlayer(p);
			}
			Inventarios.upDateScore(p);
		} else {
			player.add(p);
			HabilidadeApi.setarHB(p, "admin");
			Inventarios.upDateScore(p);
			p.sendMessage(Mensagens.entrarModoAdmin);
			p.setGameMode(GameMode.CREATIVE);
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);

			ItemStack item = new ItemStack(Material.MUSHROOM_SOUP);
			ItemMeta itemM = item.getItemMeta();
			itemM.setDisplayName(Main.pl.getConfig().getString("adminItem.autosoup").replace("&", "§"));
			item.setItemMeta(itemM);
			p.getInventory().setItem(0, item);

			ItemStack item2 = new ItemStack(Material.NETHER_BRICK_ITEM);
			ItemMeta item2M = item2.getItemMeta();
			item2M.setDisplayName(Main.pl.getConfig().getString("adminItem.quickchange").replace("&", "§"));
			item2.setItemMeta(item2M);
			p.getInventory().setItem(2, item2);

			ItemStack item222 = new ItemStack(Material.IRON_FENCE);
			ItemMeta item222M = item2.getItemMeta();
			item222M.setDisplayName(Main.pl.getConfig().getString("adminItem.cage").replace("&", "§"));
			item222.setItemMeta(item222M);
			p.getInventory().setItem(4, item222);

			ItemStack item22 = new ItemStack(Material.ANVIL);
			ItemMeta item22M = item22.getItemMeta();
			item22M.setDisplayName(Main.pl.getConfig().getString("adminItem.noFall").replace("&", "§"));
			item22.setItemMeta(item22M);
			p.getInventory().setItem(6, item22);

			ItemStack item1 = new ItemStack(Material.LEVER);
			ItemMeta item1M = item1.getItemMeta();
			item1M.setDisplayName(Main.pl.getConfig().getString("adminItem.speed").replace("&", "§"));
			item1.setItemMeta(item1M);
			p.getInventory().setItem(8, item1);

			for (Player p2 : Bukkit.getOnlinePlayers()) {
				if (!p2.hasPermission("kitpvp.cmd.admin")) {
					p2.hidePlayer(p);
				}
			}
		}

		return false;
	}
}
