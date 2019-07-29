package me.douglasamv.kitpvp.kits;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class FisherMan implements Listener {

	@EventHandler
	public void onPlayerFish(PlayerFishEvent event) {
		Entity caught = event.getCaught();
		Block block = event.getHook().getLocation().getBlock();
		if ((caught != null) && (caught != block)
				&& (HabilidadeApi.verHB(event.getPlayer()).equalsIgnoreCase(Main.cfg_kits.getString("kits.fisherman.ability")))) {
			if (ProtecaoSpawn.protegido((Player) caught))
				return;
			if (ProtecaoSpawn.protegido(event.getPlayer()))
				return;
			caught.teleport(event.getPlayer().getLocation());
		}
	}

	@EventHandler
	void sexuals2(PlayerInteractEvent e) {
		if (ProtecaoSpawn.protegido(e.getPlayer())) {
			if (e.getPlayer().getItemInHand().getType() == Material.FISHING_ROD) {
				if (HabilidadeApi.verHB(e.getPlayer()).equalsIgnoreCase(Main.cfg_kits.getString("kits.fisherman.ability"))) {
					e.setCancelled(true);
					return;
				}
			}
		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.fisherman.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.fisherman.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.fisherman.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.fisherman.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.fisherman.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.fisherman.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.fisherman.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.fisherman.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.fisherman.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.fisherman.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.fisherman.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.fisherman.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.fisherman.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.fisherman.armor_name")), true));
		}
		p.getInventory().setItem(1, ItemAPI.Criar(Material.FISHING_ROD, 1, 0,
				Mensagens.cor(Main.cfg_kits.getString("kits.fisherman.kit_item")), true));
		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
