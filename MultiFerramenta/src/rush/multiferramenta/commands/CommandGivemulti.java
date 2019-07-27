package rush.multiferramenta.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.multiferramenta.config.Config;

public class CommandGivemulti implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {				
		
		// Verificando se o player digitou o número de argumentos corretos
		if (args.length != 1) {
			s.sendMessage(Config.COMANDO_INCORRETO);
			return true;
		}

		// Pegando o player
		Player p = Bukkit.getPlayer(args[0]);
		if (p == null) {
			s.sendMessage(Config.PLAYER_OFFLINE.replace("{player}", args[0]));
			return true;
		}

		// Setando a quantia especificada e enviando o item
		p.getInventory().addItem(Config.MULTI_FERRAMENTA.clone());
		s.sendMessage(Config.GIVADO_SUCESSO.replace("{player}", p.getName()));
		return true;
	}
}
