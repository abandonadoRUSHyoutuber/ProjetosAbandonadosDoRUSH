package me.leitinho.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class arena implements CommandExecutor{


	@SuppressWarnings({ "unused" })
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cApenas jogadores podem executar esse comando");
			return true;
		}
		final Player p = (Player) sender;
	
		if (args.length == 0 && cmd.getName().equalsIgnoreCase("arena")) {
			p.sendMessage("§4§lERRO: §7Use: /arena <Player>");
			return true;
		}
		if (args.length >= 1 && cmd.getName().equalsIgnoreCase("arena")) {
			if (p.hasPermission("cmd.arena")) {
			Player k = Bukkit.getPlayer(args[0]);
			k.sendMessage("§a Um administrador te puxou para a arena, caso você saia do servidor, você será banido.");
			if (k != null) {
				k.getLocation().add(0.0D, 13.0D, 0.0D).getBlock().setType(Material.BEDROCK);
				k.getLocation().add(0.0D, 11.0D, 1.0D).getBlock().setType(Material.BEDROCK);
				k.getLocation().add(1.0D, 11.0D, 0.0D).getBlock().setType(Material.BEDROCK);
				k.getLocation().add(0.0D, 11.0D, -1.0D).getBlock().setType(Material.BEDROCK);
				k.getLocation().add(-1.0D, 11.0D, 0.0D).getBlock().setType(Material.BEDROCK);
				k.getLocation().add(0.0D, 10.0D, 0.0D).getBlock().setType(Material.BEDROCK);
		        k.teleport(k.getLocation().add(0.0D, 11.0D, -0.05D));
		        p.sendMessage("§7Player §c" + k.getName() + "§7 foi puxado para a arena.");
		        
		        if (!p.hasPermission("cmd.arena")) {
		        	p.sendMessage("Você não tem permissão");
		        }
			} else {
				p.sendMessage("§4§lERRO: §7Esse jogador não existe.");
			}
		

	}

		}
		return false;
	}
	
}
