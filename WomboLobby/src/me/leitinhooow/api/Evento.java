package me.leitinhooow.api;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import me.leitinhooow.central.Main;
import me.leitinhooow.score.Scoreboarding;



public class Evento implements Listener{
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoinEvent(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.setGameMode(GameMode.SURVIVAL);
		p.setHealth(20);
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		Manager.SetarItemInv(Material.COMPASS, 0, "§aServers", "", p, 0);
		p.updateInventory();
		Scoreboarding.ScoreboardManager(p);
		e.setJoinMessage(null);
		p.sendMessage("              ");
		p.sendMessage("           §7Bem-Vindo ao §eLobby§7!");
		p.sendMessage("          §7Para comprar §bVIP§7 e §bKits§7 acesse:");
		p.sendMessage("          §a" + Main.loja);
	}
	
	@EventHandler
	public void onQuitEvent(PlayerQuitEvent e) {
		e.setQuitMessage(null);
	}
	
    @EventHandler
    public void nome(ServerListPingEvent e) {
    	e.setMotd("           §c§m-§6§m-§e§m-§a§m-§b§m- §r §d§lWOMBBONETWORK §c§m-§6§m-§e§m-§a§m-§b§m- §r                    §6§lNOVO: §a§lNovos plugins, venha testar!");
    }
    
	
	
    @EventHandler
    public void nuncaFome(FoodLevelChangeEvent evento) {
        evento.setFoodLevel(20);
    }

    @EventHandler
    public void NaoQueimar(BlockIgniteEvent evento) {
        evento.setCancelled(true);
    }
    
    @EventHandler
    public void nuncaChover(WeatherChangeEvent evento) {
        evento.setCancelled(true);
    }

    @EventHandler
    public void cancelarExplosao(EntityExplodeEvent evento) {
        evento.setCancelled(true);
    }
    
	
	@EventHandler
	public void morte(PlayerDeathEvent Twork) {
		Player jogador = Twork.getEntity();
		
		Twork.getDrops().clear();
		Twork.setDeathMessage(null);
		Scoreboarding.ScoreboardManager(jogador);

		jogador.teleport(jogador.getWorld().getSpawnLocation());
		jogador.getInventory().clear();
		jogador.getInventory().setArmorContents(null);
		Manager.SetarItemInv(Material.COMPASS, 0, "§aServers", "", jogador, 0);
		jogador.updateInventory();
		
			jogador.sendMessage(Main.prefix + "§7Você morreu.");
		}
	
	@EventHandler
	public void respawnar(PlayerRespawnEvent Twork) {
		Player jogador = Twork.getPlayer();
		jogador.getInventory().clear();
		jogador.getInventory().setArmorContents(null);
		Manager.SetarItemInv(Material.COMPASS, 0, "§aServers", "", jogador, 0);
		jogador.updateInventory();
		jogador.setGameMode(GameMode.SURVIVAL);
	}
	
    @EventHandler
    public void fullJoin(PlayerLoginEvent event) {
        Player p = event.getPlayer();
        if (event.getResult() == PlayerLoginEvent.Result.KICK_FULL) {
            if (p.hasPermission("lobbby.util.entrar")) {
                event.setResult(PlayerLoginEvent.Result.ALLOWED);
            } else {
                event.setKickMessage("      §6§lServidor Lotado! §a§lTente novamente!");
            }
        } else if (event.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST) {
            event.setKickMessage("       §a§lServidor em manuntencao , Aguarde, nossa equipe ja vai liberar!");
        }
    }

}
