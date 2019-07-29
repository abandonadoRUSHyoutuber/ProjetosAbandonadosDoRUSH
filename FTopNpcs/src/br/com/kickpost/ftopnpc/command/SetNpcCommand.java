package br.com.kickpost.ftopnpc.command;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.kickpost.ftopnpc.FTopNpc;

public class SetNpcCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("setnpc") && sender instanceof Player) {

			Player p = (Player) sender;

			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + "/setnpc <posicao>");
				return true;
			}
			if (canParse(args[0])) {
				int posicao = Integer.parseInt(args[0]);
				Location location = p.getLocation();
				String locationSerialized = location.getX() + "," + location.getY() + "," + location.getZ() + ","
						+ location.getYaw() + "," + location.getPitch() + "," + location.getWorld().getName();

				FTopNpc.getPlugin().getConfig().set("NPCs." + posicao + ".Localizacao" , locationSerialized);
				FTopNpc.getPlugin().saveConfig();
				p.sendMessage(ChatColor.GREEN  + "NPC '" + posicao + "' setado com sucesso!");
			} else {
				p.sendMessage(ChatColor.RED + "O número informado não é valido!");
			}
		}
		return false;
	}

	private boolean canParse(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
