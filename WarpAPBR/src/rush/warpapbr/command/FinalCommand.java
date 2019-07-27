package rush.warpapbr.command;

import java.util.Map;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import rush.warpapbr.config.Config;
import rush.warpapbr.utils.CommandUtils;

public class FinalCommand extends Config implements CommandExecutor {

	private Map<String, Location> locations;
	private String name;
	private String command;
	private final Random random = new Random();

	protected FinalCommand(String command, String name) {
		this.locations = CommandUtils.loadLocations(command);
		this.command = command;
		this.name = name;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		if (!(s instanceof Player)) {
			s.sendMessage(CONSOLE_ERRO);
			return true;
		}

		final Player p = (Player) s;

		if (args.length > 0 && s.hasPermission("warp.admin")) {
			if (args.length != 2) {
				p.sendMessage(INCORRETO.replace("%comando%", cmd.getName()));
				return true;
			}

			if (args[0].equalsIgnoreCase("set")) {
				CommandUtils.setLocation(command, p.getLocation(), args[1]);
				locations.put(args[1], p.getLocation());
				p.sendMessage(CRIADA.replace("%pos%", args[1]));
				return true;
			}

			else if (args[0].equalsIgnoreCase("del")) {
				CommandUtils.deleteLocation(command, args[1]);
				locations.remove(args[1]);
				p.sendMessage(DELETADA.replace("%pos%", args[1]));
				return true;
			}

			else {
				p.sendMessage(INCORRETO.replace("%comando%", cmd.getName()));
				return true;
			}

		} else {
			if (locations.isEmpty()) {
				p.sendMessage(ERRO.replace("%nome%", name));
			} else {
				p.teleport(random(), TeleportCause.COMMAND);
				p.sendMessage(TELEPORTADO.replace("%nome%", name));
			}
		}
		return true;
	}

	private Location random() {
		int size = locations.values().size();
		int item = random.nextInt(size);
		int i = 0;
		for (Location obj : locations.values()) {
			if (i == item) {
				return obj;
			}
			i++;
		}
		return null;
	}

}