package me.douglasamv.kitpvp.kits;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Frosty implements Listener {

	@EventHandler
	void onPlayerCamel(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (((e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.ICE)
				|| (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SNOW_BLOCK)
				|| (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.PACKED_ICE))
				&& (HabilidadeApi.verHB(p).equalsIgnoreCase(Main.cfg_kits.getString("kits.frosty.ability")))) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 80, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 0));
		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.frosty.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.frosty.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.frosty.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.frosty.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.frosty.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.frosty.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.frosty.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.frosty.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.frosty.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.frosty.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.frosty.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.frosty.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.frosty.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.frosty.armor_name")), true));
		}

		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
