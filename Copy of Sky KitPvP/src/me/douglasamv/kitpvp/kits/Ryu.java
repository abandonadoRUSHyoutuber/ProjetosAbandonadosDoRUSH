package me.douglasamv.kitpvp.kits;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import me.douglasamv.kitpvp.Main;
import me.douglasamv.kitpvp.Mensagens;
import me.douglasamv.kitpvp.ProtecaoSpawn;
import me.douglasamv.kitpvp.utils.CoolDownAPI;
import me.douglasamv.kitpvp.utils.HabilidadeApi;
import me.douglasamv.kitpvp.utils.Inventarios;
import me.douglasamv.kitpvp.utils.ItemAPI;
import me.douglasamv.kitpvp.utils.titleapi.TitleAPI;

public class Ryu implements Listener {

	@EventHandler
	void hadouken(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (!HabilidadeApi.verHB(p).equalsIgnoreCase(Main.cfg_kits.getString("kits.ryu.ability")))
			return;
		if (e.getAction().equals(Action.LEFT_CLICK_AIR))
			return;
		if (e.getAction().equals(Action.LEFT_CLICK_BLOCK))
			return;

		if (p.getItemInHand().getType() == Material.BEACON) {
			if (ProtecaoSpawn.protegido(p)) {
				p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.ryu.msg_spawn")));
				return;
			}

			if (CoolDownAPI.isOnCooldown(p)) {
				p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.ryu.msg_cooldown")));
				return;
			}
			CoolDownAPI.putCooldown(p, 15);
			Location location = p.getEyeLocation();
			BlockIterator blocksToAdd = new BlockIterator(location, 0.0D, 40);
			while (blocksToAdd.hasNext()) {
				Location blockToAdd = blocksToAdd.next().getLocation();
				p.getWorld().playEffect(blockToAdd, Effect.STEP_SOUND, Material.BEACON, 20);
				p.playSound(blockToAdd, Sound.IRONGOLEM_THROW, 3.0F, 3.0F);
			}
			Snowball h = (Snowball) p.launchProjectile(Snowball.class);
			Vector velo1 = p.getLocation().getDirection().normalize().multiply(10);
			h.setVelocity(velo1);
			h.setMetadata("hadouken", new FixedMetadataValue(Main.pl, Boolean.valueOf(true)));
			p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.ryu.msg_kit_use")));
		}
	}

	@EventHandler
	void dano(EntityDamageByEntityEvent e) {
		if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Snowball))) {
			Snowball s = (Snowball) e.getDamager();
			if (s.hasMetadata("hadouken")) {
				if (ProtecaoSpawn.protegido((Player) e.getEntity())) {
					e.setCancelled(true);
					return;
				}
				e.setDamage(e.getDamage() + 8.0D);
			}
		}
	}

	public static void darKit(Player p) {
		p.closeInventory();
		HabilidadeApi.setarHB(p, Main.cfg_kits.getString("kits.ryu.ability"));
		TitleAPI.sendTitle(p, 5, 20, 5, "", Mensagens.cor(Main.cfg_kits.getString("kits.ryu.use_kit_title")));
		p.sendMessage(Mensagens.cor(Main.cfg_kits.getString("kits.ryu.use_kit_text")));
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setGameMode(GameMode.SURVIVAL);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 30, 30);
		if (Main.cfg_kits.getBoolean("kits.ryu.soup")) {
			for (int i = 0; i < 36; i++) {
				p.getInventory().addItem(ItemAPI.Criar(Material.MUSHROOM_SOUP, 1, 0,
						Mensagens.cor(Main.cfg_kits.getString("kits.ryu.soup_name")), false));
			}
		}
		if (Main.cfg_kits.getBoolean("kits.ryu.sharp")) {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.ryu.sword")), true, Enchantment.DAMAGE_ALL, 1));
		} else {
			p.getInventory().setItem(0, ItemAPI.Criar(Material.STONE_SWORD, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.ryu.sword")), true));
		}
		if (Main.cfg_kits.getBoolean("kits.ryu.recraft")) {
			p.getInventory().setItem(13, ItemAPI.Criar(Material.RED_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.ryu.red_mushroom")), false));
			p.getInventory().setItem(14, ItemAPI.Criar(Material.BROWN_MUSHROOM, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.ryu.brown_mushroom")), false));
			p.getInventory().setItem(15, ItemAPI.Criar(Material.BOWL, 64, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.ryu.bowl")), false));
		}
		if (Main.cfg_kits.getBoolean("kits.ryu.armor")) {
			p.getInventory().setChestplate(ItemAPI.Criar(Material.LEATHER_CHESTPLATE, 1, 0,
					Mensagens.cor(Main.cfg_kits.getString("kits.ryu.armor_name")), true));
		}

		p.getInventory().setItem(1, ItemAPI.Criar(Material.BEACON, 1, 0,
				Mensagens.cor(Main.cfg_kits.getString("kits.ryu.kit_item")), false));
		p.updateInventory();
		Inventarios.upDateScore(p);
	}
}
