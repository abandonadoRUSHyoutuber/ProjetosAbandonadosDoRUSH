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

public class Lava {

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_warps.getString("warps.lava.ability"));
		if (!ProtecaoSpawn.protegidos.contains(p)) {
			ProtecaoSpawn.protegidos.add(p);
		}
		if (!ProtecaoSpawn.Fogo.contains(p)) {
			ProtecaoSpawn.Fogo.add(p);
		}
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_warps.getString("warps.lava.msg_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_warps.getString("warps.lava.msg_chat")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		for (int i = 0; i < 36; i++) {
			p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0, "§7Soup", false));
		}
		p.getInventory().setItem(0,
				ItemAPI.Criar(Material.WOOD_SWORD, 1, 0, "§cSword", true, Enchantment.DAMAGE_ALL, 1));
		p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0, "§cRed mushroom", false));
		p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0, "§6Brown mushroom", false));
		p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0, "§7Bowl", false));
		p.updateInventory();
		Inventarios.upDateScore(p);
		p.teleport(new Location(p.getWorld(), Main.cfg_warps.getDouble("warps.lava.x"),
				Main.cfg_warps.getDouble("warps.lava.y"), Main.cfg_warps.getDouble("warps.lava.z")));
	}
}
