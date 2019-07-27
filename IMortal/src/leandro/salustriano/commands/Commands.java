package leandro.salustriano.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import leandro.salustriano.bydefault.Imortal;
import leandro.salustriano.recursos.Resources;

public class Commands implements CommandExecutor {
	private Resources src;

	public Commands(Imortal imortal) {
		this.src = new Resources(imortal);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("imortal-op")) {
				if (!p.hasPermission("imortalop.use")) {
					p.sendMessage("§5[Imortal]§cVoce nao tem permissao para administrar este evento");
				} else if (arg.length <= 0) {
					p.sendMessage("§c/imortal-op restart;resetscore<nicknameplayer>;kick <nicknameplayer>;ban <nicknameplayer>; pardon <nicknameplayer>;tp <camarote,espera,saida,pos1,pos2>; set <espera,saida,camarote,pos1,pos2>");
				} else if (arg[0].equalsIgnoreCase("set")) {
					if (arg.length <= 1) {
						p.sendMessage("§c/imortal-op set <camarote,saida,espera,pos1,pos2>");
					} else if (arg[1].equalsIgnoreCase("pos1")) {
						this.src.setLocate(p, "pos1");
						p.sendMessage(ChatColor.GOLD + "A posição foi definida com exito.");
					} else if (arg[1].equalsIgnoreCase("pos2")) {
						this.src.setLocate(p, "pos2");
						p.sendMessage(ChatColor.GOLD + "A posição foi definida com exito.");
					} else if (arg[1].equalsIgnoreCase("camarote")) {
						this.src.setLocate(p, "camarote");
						p.sendMessage(ChatColor.GOLD + "A posição foi definida com exito.");
					} else if (arg[1].equalsIgnoreCase("saida")) {
						this.src.setLocate(p, "saida");
						p.sendMessage(ChatColor.GOLD + "A posição foi definida com exito.");
					} else if (arg[1].equalsIgnoreCase("espera")) {
						this.src.setLocate(p, "espera");
						p.sendMessage(ChatColor.GOLD + "A posição foi definida com exito.");
					} else {
						p.sendMessage(ChatColor.WHITE + "/imortal set <camarote,saida,espera,pos1,pos2>");
					}
				} else if (arg[0].equalsIgnoreCase("tp")) {
					if (arg.length <= 1) {
						p.sendMessage("§c/imortal tp <camarote,espera,saida,pos1,pos2>");
					} else if (arg[1].equalsIgnoreCase("pos1")) {
						this.src.teleportPlayer(p, "pos1");
					} else if (arg[1].equalsIgnoreCase("pos2")) {
						this.src.teleportPlayer(p, "pos2");
					} else if (arg[1].equalsIgnoreCase("camarote")) {
						this.src.teleportPlayer(p, "camarote");
					} else if (arg[1].equalsIgnoreCase("saida")) {
						this.src.teleportPlayer(p, "saida");
					} else if (arg[1].equalsIgnoreCase("espera")) {
						this.src.teleportPlayer(p, "espera");
					} else {
						p.sendMessage("§c/imortal-op tp <camarote,saida,espera,pos1,pos2>");
					}
				} else if (arg[0].equalsIgnoreCase("ban")) {
					if (arg.length == 2) {
						this.src.playerBanned(arg[1], p);
					} else {
						p.sendMessage("§c /imortal-op ban <nickplayername>");
					}
				} else if (arg[0].equalsIgnoreCase("pardon")) {
					if (arg.length == 2) {
						this.src.pardonPlayer(arg[1], p);
					} else {
						p.sendMessage("§c /imortal-op pardon <nickplayername>");
					}
				} else if (arg[0].equalsIgnoreCase("kick")) {
					if (arg.length == 2) {
						this.src.kickPlayer(arg[1], p);
					} else {
						p.sendMessage("§c /imortal-op kick <nickplayername>");
					}
				} else if (arg[0].equalsIgnoreCase("resetscore")) {
					if (arg.length == 2) {
						this.src.resetScorePlayer(arg[1], p);
					} else {
						p.sendMessage("§c /imortal-op resetscore <nickplayername>");
					}
				} else if (arg[0].equalsIgnoreCase("desativar")) {
					this.src.DisableEvent(p);
				} else if (arg[0].equalsIgnoreCase("ativar")) {
					this.src.EnableEvent(p);
				} else if (arg[0].equalsIgnoreCase("restart")) {
					this.src.restartEvent();
				} else {
					p.sendMessage("§c/imortal-op resetscore<nicknameplayer>;kick <nicknameplayer>;ban <nicknameplayer>; pardon <nicknameplayer>;tp <camarote,espera,saida,pos1,pos2>; set <espera,saida,camarote,pos1,pos2>");
				}
			}
		} else {
			sender.sendMessage("§4Apenas jogadores podem usar este comando !");
		}
		return true;
	}
}
