package leandro.salustriano.commands;

import leandro.salustriano.bydefault.Imortal;
import leandro.salustriano.recursos.Resources;
import leandro.salustriano.recursos.ResourcesThreadEvent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCommands implements CommandExecutor {

	public Imortal f1m;
	public Resources src;
	public ResourcesThreadEvent srcPlayerEvent;

	public PlayerCommands(Imortal main) {
		this.f1m = main;
		this.src = new Resources(main);
		this.srcPlayerEvent = new ResourcesThreadEvent(main);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("imortal")) {
				if (p.hasPermission("imortal.use")) {
					this.src.openGui(p);
				} else {
					p.sendMessage("§cVoce nao tem permissao para interagir com este evento.");
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Apenas jogadores podem usar este comando !");
		}
		return false;
	}
}
