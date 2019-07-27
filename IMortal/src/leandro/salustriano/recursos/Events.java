package leandro.salustriano.recursos;

import java.util.List;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import leandro.salustriano.bydefault.Imortal;

public class Events implements Listener {
	
	public Imortal m;
	private Player deadPlayer;
	private Player damagerPlayer;
	private Arrow arrow;
	private List<String> rulesCommands;
	private List<String> rulesCommandsEspera;
	private List<String> rulesCommandsCamarote;

	public Events(Imortal main) {
		this.m = main;
	}

	@EventHandler
	public void PlayerExit(PlayerQuitEvent e) {
		if (ResourcesThreadEvent.fila.contains(e.getPlayer())) {
			ResourcesThreadEvent.fila.remove(e.getPlayer());
			this.m.srcEvents.src.teleportPlayer(e.getPlayer(), "saida");
		} else if (ResourcesThreadEvent.camarote.contains(e.getPlayer())) {
			ResourcesThreadEvent.camarote.remove(e.getPlayer());
			this.m.srcEvents.src.teleportPlayer(e.getPlayer(), "saida");
		}
		if ((e.getPlayer() == ResourcesThreadEvent.player1) || (e.getPlayer() == ResourcesThreadEvent.player2)) {
			if ((e.getPlayer().getLastDamageCause() != null) && ((e.getPlayer().getLastDamageCause().getEntity() instanceof Player))) {
				if (this.m.srcEvents.src.checkHitSameClan(e.getPlayer(), (Player) e.getPlayer().getLastDamageCause().getEntity())) {
					this.m.srcEvents.src.teleportPlayer(e.getPlayer(), "saida");
				} else {
					e.getPlayer().setHealth(0);
				}
			}
			if (!this.m.srcEvents.playerListsDeadFK.isEmpty()) {
				this.m.srcEvents.playerListsDeadFK.clear();
			}
			if (ResourcesThreadEvent.player1 == e.getPlayer()) {
				if (ResourcesThreadEvent.player2 != null) {
					if (e.getPlayer().getLastDamageCause() != null) {
						this.m.srcEvents.src.savePlayerKillConsecutivesInConfig(ResourcesThreadEvent.player2);
					} else {
						ResourcesThreadEvent.player2.sendMessage(
								"§5[Imortal]§6Devido a desistencia for�ada do jogador, o ponto nao foi contabilizado.");
					}
				}
				ResourcesThreadEvent.player1 = null;
			} else if (ResourcesThreadEvent.player2 == e.getPlayer()) {
				if (ResourcesThreadEvent.player1 != null) {
					if (e.getPlayer().getLastDamageCause() != null) {
						this.m.srcEvents.src.savePlayerKillConsecutivesInConfig(ResourcesThreadEvent.player1);
					} else {
						ResourcesThreadEvent.player1.sendMessage("§5[Imortal]§6Devido a desistencia for�ada do jogador, o ponto nao foi contabilizado.");
					}
				}
				ResourcesThreadEvent.player2 = null;
			}
		}
	}

	@EventHandler
	public void noMove(PlayerMoveEvent e) {
		if (this.m.srcEvents.playerListsDeadFK.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void respawEventExit(PlayerRespawnEvent e) {
		if (e.getPlayer() == this.deadPlayer) {
			e.setRespawnLocation(this.m.srcEvents.src.getLocateInConfig("saida"));
			this.deadPlayer = null;
		}
	}

	@EventHandler
	private void onChat(ChatMessageEvent e) {
		if (this.m.getConfig().getBoolean("player_in_event_interact_chat")) {
			if (ResourcesThreadEvent.player1 == e.getSender()) {
				e.setCancelled(true);
				e.getSender().sendMessage("§5[Imortal]§cVoce nao pode falar enquanto esta batalhando.");
			} else if (ResourcesThreadEvent.player2 == e.getSender()) {
				e.getSender().sendMessage("§5[Imortal]§cVoce nao pode falar enquanto esta batalhando.");
				e.setCancelled(true);
			}
		}
		if ((this.m.getConfig().getString("player").equalsIgnoreCase(e.getSender().getName())) && (e.getTags().contains("imortal"))) {
			e.setTagValue("imortal", this.m.getConfig().getString("winner_tag_chat").replaceAll("&", "§"));
		}
	}

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();

		this.rulesCommands = this.m.getConfig().getStringList("commands_block");
		this.rulesCommandsEspera = this.m.getConfig().getStringList("commands_block_espera");
		this.rulesCommandsCamarote = this.m.getConfig().getStringList("commands_block_camarote");
		if ((ResourcesThreadEvent.player1 == p) || (ResourcesThreadEvent.player2 == p)) {
			for (int i = 0; i < this.rulesCommands.size(); i++) {
				if (((String) this.rulesCommands.get(i)).contains(e.getMessage())) {
					p.sendMessage("§5[Imortal]§6Você não pode usar este comando agora");
					e.setCancelled(true);
				}
			}
		} else if (ResourcesThreadEvent.fila.contains(p)) {
			for (int i = 0; i < this.rulesCommandsEspera.size(); i++) {
				if (((String) this.rulesCommandsEspera.get(i)).contains(e.getMessage())) {
					p.sendMessage("§5[Imortal]§6Você não pode usar este comando agora");
					e.setCancelled(true);
				}
			}
		} else if (ResourcesThreadEvent.camarote.contains(p)) {
			for (int i = 0; i < this.rulesCommandsCamarote.size(); i++) {
				if (((String) this.rulesCommandsCamarote.get(i)).contains(e.getMessage())) {
					p.sendMessage("§5[Imortal]§6Você não pode usar este comando agora");
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onHitSameClan(EntityDamageByEntityEvent e) {
		if ((e.getDamager() instanceof Arrow)) {
			this.arrow = ((Arrow) e.getDamager());
			this.damagerPlayer = ((Player) this.arrow.getShooter());
		} else if ((e.getDamager() instanceof Player)) {
			this.damagerPlayer = ((Player) e.getDamager());
		} else {
			e.getCause().equals(Snowball.class);
		}
		if ((this.damagerPlayer != null) && ((e.getEntity() instanceof Player))) {
			if (this.m.srcEvents.src.checkHitSameClan((Player) e.getEntity(), this.damagerPlayer)) {
				e.setCancelled(true);
			}
			if (((this.damagerPlayer == ResourcesThreadEvent.player1)
					&& ((Player) e.getEntity() == ResourcesThreadEvent.player2))
					|| ((this.damagerPlayer == ResourcesThreadEvent.player2)
							&& ((Player) e.getEntity() == ResourcesThreadEvent.player1))) {
				if ((this.m.srcEvents.playerListsDeadFK.contains(this.damagerPlayer))
						|| (this.m.srcEvents.playerListsDeadFK.contains(e.getEntity()))) {
					e.setCancelled(true);
				} else {
					e.setCancelled(false);
				}
				if (((Player) e.getEntity()).getHealth() - e.getDamage() <= 0) {
					((Player) e.getEntity()).setHealth(20);
					if (ResourcesThreadEvent.player1 == (Player) e.getEntity()) {
						this.m.srcEvents.src.checkFreeKill(ResourcesThreadEvent.player1);
						this.m.srcEvents.src.teleportPlayer(ResourcesThreadEvent.player1, "saida");
						ResourcesThreadEvent.player1 = null;
						if (ResourcesThreadEvent.player2 != null) {
							this.m.srcEvents.src.savePlayerKillConsecutivesInConfig(ResourcesThreadEvent.player2);
						}
					} else if (ResourcesThreadEvent.player2 == (Player) e.getEntity()) {
						this.m.srcEvents.src.checkFreeKill(ResourcesThreadEvent.player2);
						this.m.srcEvents.src.teleportPlayer(ResourcesThreadEvent.player2, "saida");
						ResourcesThreadEvent.player2 = null;
						if (ResourcesThreadEvent.player1 != null) {
							this.m.srcEvents.src.savePlayerKillConsecutivesInConfig(ResourcesThreadEvent.player1);
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void PlayerTeleport(PlayerTeleportEvent e) {
		if (((e.getPlayer() == ResourcesThreadEvent.player1) || (e.getPlayer() == ResourcesThreadEvent.player2))
				&& (!e.getTo().equals(this.m.srcEvents.src.getLocateInConfig("pos1")))
				&& (!e.getTo().equals(this.m.srcEvents.src.getLocateInConfig("pos2")))
				&& (!e.getTo().equals(this.m.srcEvents.src.getLocateInConfig("saida")))) {
			e.getPlayer().sendMessage("§5[Imortal]§cSaia primeiro do evento digitando §6/imortal sair");
			e.setCancelled(true);
		}
		if ((this.m.srcEvents.src.world != null) && (this.m.srcEvents.src.getLocateInConfig("camarote") != null)
				&& (e.getFrom() == this.m.srcEvents.src.getLocateInConfig("camarote"))
				&& (ResourcesThreadEvent.camarote.contains(e.getPlayer()))) {
			ResourcesThreadEvent.camarote.remove(e.getPlayer());
		}
		if ((ResourcesThreadEvent.fila.contains(e.getPlayer())) && (e.getTo() != null)) {
			if (e.getTo().equals(this.m.srcEvents.src.getLocateInConfig("pos1"))) {
				e.setCancelled(false);
			} else if (e.getTo().equals(this.m.srcEvents.src.getLocateInConfig("pos2"))) {
				e.setCancelled(false);
			} else if (e.getTo().equals(this.m.srcEvents.src.getLocateInConfig("saida"))) {
				e.setCancelled(false);
			} else if (e.getTo().equals(this.m.srcEvents.src.getLocateInConfig("espera"))) {
				e.setCancelled(false);
			} else {
				e.getPlayer().sendMessage("§5[Imortal]§cSaia primeiro do evento digitando §6/imortal sair");
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void inventoryClick(InventoryClickEvent e) {
		if (e.getInventory().getTitle().contains("Imortal top:")) {
			e.setCancelled(true);
			this.m.srcEvents.src.getSelectGui(e.getCurrentItem().getItemMeta().getDisplayName(),
					(Player) e.getWhoClicked(), e.getCurrentItem().getType());
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if (e.getEntity().getPlayer().isOnline()) {
			if (ResourcesThreadEvent.player1 == e.getEntity().getPlayer()) {
				this.m.srcEvents.src.checkFreeKill(e.getEntity().getPlayer());
				this.deadPlayer = e.getEntity().getPlayer();
				ResourcesThreadEvent.player1 = null;
				if (ResourcesThreadEvent.player2 != null) {
					this.m.srcEvents.src.savePlayerKillConsecutivesInConfig(ResourcesThreadEvent.player2);
				}
			} else if (ResourcesThreadEvent.player2 == e.getEntity().getPlayer()) {
				this.m.srcEvents.src.checkFreeKill(e.getEntity().getPlayer());
				this.deadPlayer = e.getEntity().getPlayer();
				ResourcesThreadEvent.player2 = null;
				if (ResourcesThreadEvent.player1 != null) {
					this.m.srcEvents.src.savePlayerKillConsecutivesInConfig(ResourcesThreadEvent.player1);
				}
			}
		} else if (ResourcesThreadEvent.player1 == e.getEntity().getPlayer()) {
			this.m.srcEvents.src.checkFreeKill(e.getEntity().getPlayer());
			ResourcesThreadEvent.player1 = null;
			if (ResourcesThreadEvent.player2 != null) {
				this.m.srcEvents.src.savePlayerKillConsecutivesInConfig(ResourcesThreadEvent.player2);
			}
		} else if (ResourcesThreadEvent.player2 == e.getEntity().getPlayer()) {
			this.m.srcEvents.src.checkFreeKill(e.getEntity().getPlayer());
			ResourcesThreadEvent.player2 = null;
			if (ResourcesThreadEvent.player1 != null) {
				this.m.srcEvents.src.savePlayerKillConsecutivesInConfig(ResourcesThreadEvent.player1);
			}
		}
	}
}