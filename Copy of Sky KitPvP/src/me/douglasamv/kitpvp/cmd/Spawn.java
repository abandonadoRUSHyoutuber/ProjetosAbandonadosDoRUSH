package me.douglasamv.kitpvp.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.kits.Gladiator;
import me.douglasamv.kitpvp.utils.CombatLog;
import me.douglasamv.kitpvp.utils.CoolDownAPI;
import me.douglasamv.kitpvp.utils.CoolDownRecraft;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;

public class Spawn implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Mensagens.consolePlayer);
			return false;
		}
		Player p = (Player) sender;
		if (HabilidadeApi.semHB(p)) {
			p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("SpawnKit")));
			return true;
		}
		if (CombatLog.Sair.contains(p)) {
			p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("SpawnCombatLog")));
			return true;
		}
		if (Gladiator.fighting.containsKey(p.getName())) {
			p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("SpawnGladiator")));
			return true;
		}
		CoolDownAPI.removeCooldown(p);
		CoolDownRecraft.removeCooldown(p);
		p.teleport(p.getWorld().getSpawnLocation());
		Inventarios.resetar(p);
		if (ProtecaoSpawn.protegidos.contains(p)) {
			ProtecaoSpawn.protegidos.remove(p);
		}
		if (ProtecaoSpawn.Fogo.contains(p)) {
			ProtecaoSpawn.Fogo.remove(p);
		}
		p.sendMessage(Mensagens.cor(Main.pl.getConfig().getString("SpawnTeleport")));
		return false;
	}

}
