package me.douglasamv.kitpvp.kits.warps;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Knock {

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_warps.getString("warps.knock.ability"));
		if (!ProtecaoSpawn.protegidos.contains(p)) {
			ProtecaoSpawn.protegidos.add(p);
		}
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_warps.getString("warps.knock.msg_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_warps.getString("warps.knock.msg_chat")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		p.getInventory().setItem(0, ItemAPI.Criar(Material.STICK, 1, 0, "§cKnock", true, Enchantment.KNOCKBACK, 2));
		p.updateInventory();
		Inventarios.upDateScore(p);
		p.teleport(new Location(p.getWorld(), Main.cfg_warps.getDouble("warps.knock.x"),
				Main.cfg_warps.getDouble("warps.knock.y"), Main.cfg_warps.getDouble("warps.knock.z")));
	}
}
