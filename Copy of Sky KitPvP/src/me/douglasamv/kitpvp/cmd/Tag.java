package me.douglasamv.kitpvp.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;

public class Tag implements Listener, CommandExecutor {
	
	public static String dono = Mensagens.cor(Main.pl.getConfig().getString("tags.owner"));
	public static String admin = Mensagens.cor(Main.pl.getConfig().getString("tags.admin"));
	public static String mod = Mensagens.cor(Main.pl.getConfig().getString("tags.mod"));
	public static String trial = Mensagens.cor(Main.pl.getConfig().getString("tags.trial"));
	public static String youtuber = Mensagens.cor(Main.pl.getConfig().getString("tags.youtuber"));
	public static String pro = Mensagens.cor(Main.pl.getConfig().getString("tags.pro"));
	public static String vip = Mensagens.cor(Main.pl.getConfig().getString("tags.vip"));
	public static String normal = Mensagens.cor(Main.pl.getConfig().getString("tags.normal"));
	
	
	
	@EventHandler
	void join(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(p.getName().equalsIgnoreCase(Main.pl.getConfig().getString("Owner"))) {
			Main.setarTag(p, dono);
			return;
		}
		if(p.hasPermission("tag.admin")) {
			Main.setarTag(p, admin);
			return;
		}
		if(p.hasPermission("tag.mod")) {
			Main.setarTag(p, mod);
			return;
		}
		if(p.hasPermission("tag.trial")) {
			Main.setarTag(p, trial);
			return;
		}
		if(p.hasPermission("tag.youtuber")) {
			Main.setarTag(p, youtuber);
			return;
		}
		if(p.hasPermission("tag.pro")) {
			Main.setarTag(p, pro);
			return;
		}
		if(p.hasPermission("tag.vip")) {
			Main.setarTag(p, vip);
			return;
		}
		if(p.hasPermission("tag.normal")) {
			Main.setarTag(p, normal);
			return;
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cComando de jogador!");
			return false;
		}
		Player p = (Player) sender;
		if(args.length == 0) {
			if(p.getName().equalsIgnoreCase(Main.pl.getConfig().getString("Owner"))) {
				p.sendMessage("§cUse /tag <owner/admin/mod/trial/youtuber/pro/vip/normal>");
				return true;
			}
			if(p.hasPermission("tag.admin")) {
				p.sendMessage("§cUse /tag <admin/mod/trial/youtuber/pro/vip/normal>");
				return true;
			}
			if(p.hasPermission("tag.mod")) {
				p.sendMessage("§cUse /tag <mod/trial/youtuber/pro/vip/normal>");
				return true;
			}
			if(p.hasPermission("tag.trial")) {
				p.sendMessage("§cUse /tag <trial/youtuber/pro/vip/normal>");
				return true;
			}
			if(p.hasPermission("tag.youtuber")) {
				p.sendMessage("§cUse /tag <youtuber/pro/vip/normal>");
				return true;
			}
			if(p.hasPermission("tag.pro")) {
				p.sendMessage("§cUse /tag <pro/vip/normal>");
				return true;
			}
			if(p.hasPermission("tag.vip")) {
				p.sendMessage("§cUse /tag <vip/normal>");
				return true;
			}
			if(p.hasPermission("tag.normal")) {
				p.sendMessage("§cUse /tag <normal>");
				return true;
			}
			
		}
		else {
			if(p.getName().equalsIgnoreCase(Main.pl.getConfig().getString("Owner"))) {
				if(args[0].equalsIgnoreCase("owner")) {
					Main.setarTag(p, dono);
					p.sendMessage("§cTag changed to owner!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("admin")) {
					Main.setarTag(p, admin);
					p.sendMessage("§cTag changed to admin!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("mod")) {
					Main.setarTag(p, mod);
					p.sendMessage("§cTag changed to mod!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("trial")) {
					Main.setarTag(p, trial);
					p.sendMessage("§cTag changed to trial!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("youtuber")) {
					Main.setarTag(p, youtuber);
					p.sendMessage("§cTag changed to youtuber!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("pro")) {
					Main.setarTag(p, pro);
					p.sendMessage("§cTag changed to pro!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("vip")) {
					Main.setarTag(p, vip);
					p.sendMessage("§cTag changed to vip!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§cTag changed to normal!");
					return true;
				}
				else {
					p.sendMessage("§cUse /tag <owner/admin/mod/trial/youtuber/pro/vip/normal>");
					return true;
				}
			}
			if(p.hasPermission("tag.admin")) {
				if(args[0].equalsIgnoreCase("admin")) {
					Main.setarTag(p, admin);
					p.sendMessage("§cTag changed to admin!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("mod")) {
					Main.setarTag(p, mod);
					p.sendMessage("§cTag changed to mod!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("trial")) {
					Main.setarTag(p, trial);
					p.sendMessage("§cTag changed to trial!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("youtuber")) {
					Main.setarTag(p, youtuber);
					p.sendMessage("§cTag changed to youtuber!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("pro")) {
					Main.setarTag(p, pro);
					p.sendMessage("§cTag changed to pro!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("vip")) {
					Main.setarTag(p, vip);
					p.sendMessage("§cTag changed to vip!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§cTag changed to normal!");
					return true;
				}
				else {
					p.sendMessage("§cUse /tag <admin/mod/trial/youtuber/pro/vip/normal>");
					return true;
				}
				
			}
			if(p.hasPermission("tag.mod")) {
				if(args[0].equalsIgnoreCase("mod")) {
					Main.setarTag(p, mod);
					p.sendMessage("§cTag changed to mod!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("trial")) {
					Main.setarTag(p, trial);
					p.sendMessage("§cTag changed to trial!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("youtuber")) {
					Main.setarTag(p, youtuber);
					p.sendMessage("§cTag changed to youtuber!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("pro")) {
					Main.setarTag(p, pro);
					p.sendMessage("§cTag changed to pro!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("vip")) {
					Main.setarTag(p, vip);
					p.sendMessage("§cTag changed to vip!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§cTag changed to normal!");
					return true;
				}
				else {
					p.sendMessage("§cUse /tag <mod/trial/youtuber/pro/vip/normal>");
					return true;
				}
				
			}
			if(p.hasPermission("tag.trial")) {
				if(args[0].equalsIgnoreCase("trial")) {
					Main.setarTag(p, trial);
					p.sendMessage("§cTag changed to trial!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("youtuber")) {
					Main.setarTag(p, youtuber);
					p.sendMessage("§cTag changed to youtuber!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("pro")) {
					Main.setarTag(p, pro);
					p.sendMessage("§cTag changed to pro!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("vip")) {
					Main.setarTag(p, vip);
					p.sendMessage("§cTag changed to vip!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§cTag changed to normal!");
					return true;
				}
				else {
					p.sendMessage("§cUse /tag <trial/youtuber/pro/vip/normal>");
					return true;
				}
				
			}
			if(p.hasPermission("tag.youtuber")) {
				if(args[0].equalsIgnoreCase("youtuber")) {
					Main.setarTag(p, youtuber);
					p.sendMessage("§cTag changed to youtuber!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("pro")) {
					Main.setarTag(p, pro);
					p.sendMessage("§cTag changed to pro!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("vip")) {
					Main.setarTag(p, vip);
					p.sendMessage("§cTag changed to vip!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§cTag changed to normal!");
					return true;
				}
				else {
					p.sendMessage("§cUse /tag <youtuber/pro/vip/normal>");
					return true;
				}
				
			}
			if(p.hasPermission("tag.pro")) {
				if(args[0].equalsIgnoreCase("pro")) {
					Main.setarTag(p, pro);
					p.sendMessage("§cTag changed to pro!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("vip")) {
					Main.setarTag(p, vip);
					p.sendMessage("§cTag changed to vip!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§cTag changed to normal!");
					return true;
				}
				else {
					p.sendMessage("§cUse /tag <pro/vip/normal>");
					return true;
				}
				
			}
			if(p.hasPermission("tag.vip")) {
				if(args[0].equalsIgnoreCase("vip")) {
					Main.setarTag(p, vip);
					p.sendMessage("§cTag changed to vip!");
					return true;
				}
				else if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§cTag changed to normal!");
					return true;
				}
				else {
					p.sendMessage("§cUse /tag <vip/normal>");
					return true;
				}
				
			}
			if(p.hasPermission("tag.normal")) {
				if(args[0].equalsIgnoreCase("normal")) {
					Main.setarTag(p, normal);
					p.sendMessage("§cTag changed to normal!");
					return true;
				}
				else {
					p.sendMessage("§cUse /tag <normal>");
					return true;
				}
			}
			else {
				p.sendMessage("§cUse /tag <normal>");
				return true;
			}
		}
		return false;
	}
	
}
