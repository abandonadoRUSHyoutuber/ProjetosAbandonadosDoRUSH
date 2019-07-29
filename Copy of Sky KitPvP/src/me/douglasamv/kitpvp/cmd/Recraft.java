package me.douglasamv.kitpvp.cmd;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.utils.CoolDownRecraft;
import me.douglasamv.kitpvp.utils.FormatoTempo;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.ItemAPI;

public class Recraft implements CommandExecutor, Listener {

	public static ArrayList<Player> players = new ArrayList<>();

	@EventHandler
	void morrer(PlayerDeathEvent e) {
		if (players.contains(e.getEntity().getPlayer())) {
			players.remove(e.getEntity().getPlayer());
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cComando de jogador!");
			return false;
		}
		Player p = (Player) sender;
		if (HabilidadeApi.semHB(p)) {
			p.sendMessage("§cPegue algum kit para usar este comando!");
			return true;
		}
		if (CoolDownRecraft.isOnCooldown(p)) {
			p.sendMessage("§cAguarde " + FormatoTempo.tipo2(CoolDownRecraft.getCooldown(p)).replace("-", "")
					+ " para usar este comando novamente!");
			return true;
		}
		p.setHealth(0.5);
		p.setHealthScale(0.5);
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 255));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 5 * 20, 255));
		p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 5 * 20, 255));
		p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 5 * 20, 255));
		p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 5 * 20, -50));
		p.sendMessage("§cVoce esta vulneravel por 5 segundos!");
		players.add(p);
		CoolDownRecraft.putCooldown(p, 60);
		BukkitScheduler task = Bukkit.getServer().getScheduler();
		task.scheduleAsyncDelayedTask(Main.pl, new BukkitRunnable() {
			@Override
			public void run() {
				if (!players.contains(p))
					return;
				if (!p.isOnline())
					return;
				p.setHealth(20);
				p.setHealthScale(20);
				p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0, "§cCogu vermelho", false));
				p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0, "§6Cogu marrom", false));
				p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0, "§7Pote", false));
				p.sendMessage("§bVoce recebeu recraft!");
				players.remove(p);

			}
		}, 20 * 5);
		return false;
	}

}
