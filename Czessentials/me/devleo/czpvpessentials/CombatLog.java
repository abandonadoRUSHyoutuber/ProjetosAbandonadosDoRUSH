/*    */ package me.devleo.czpvpessentials;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ import org.bukkit.scheduler.BukkitScheduler;
/*    */ 
/*    */ public class CombatLog implements Listener
/*    */ {
/* 17 */   public static List<String> Sair = new ArrayList();
/*    */   
/*    */   @EventHandler
/*    */   private void DanoCombate(EntityDamageByEntityEvent e) {
/* 21 */     if (((e.getDamager() instanceof Player)) && ((e.getEntity() instanceof Player))) {
/* 22 */       final Player p = (Player)e.getEntity();
/* 23 */       final Player hitter = (Player)e.getDamager();
/* 24 */       if (e.isCancelled()) {
/* 25 */         return;
/*    */       }
/* 27 */       if ((!Main.areaPvP(hitter)) || (!Main.areaPvP(p))) {
/* 28 */         return;
/*    */       }
/* 30 */       if ((!Sair.contains(p.getName())) && (!Sair.contains(hitter.getName()))) {
/* 31 */         if ((hitter.getGameMode() == GameMode.CREATIVE) || (hitter.getGameMode() == GameMode.SPECTATOR)) {
/* 32 */           return;
/*    */         }
/* 34 */         Sair.add(p.getName());
/* 35 */         Sair.add(hitter.getName());
/* 36 */         p.sendMessage("§c[§4!§c] Você está em combate, se deslogar será morto!");
/* 37 */         hitter.sendMessage("§c[§4!§c] Você está em combate, se deslogar será morto!");
/* 38 */         Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
/*    */           public void run() {
/* 40 */             CombatLog.Sair.remove(p.getName());
/* 41 */             CombatLog.Sair.remove(hitter.getName());
/* 42 */             p.sendMessage("§a[§2!§a] Você não está mais em combate, pode deslogar!");
/* 43 */             hitter.sendMessage("§a[§2!§a] Você não está mais em combate, pode deslogar!");
/*    */           }
/* 45 */         }, 300L);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   private void SairCombate(PlayerQuitEvent e) {
/* 52 */     Player p = e.getPlayer();
/* 53 */     if (Sair.contains(p.getName())) {
/* 54 */       p.setHealth(0.0D);
/* 55 */       p.teleport(new org.bukkit.Location((World)Bukkit.getWorlds().get(0), 0.0D, 0.0D, 0.0D));
/* 56 */       p.teleport(e.getPlayer().getWorld().getSpawnLocation());
/* 57 */       Sair.remove(p.getName());
/* 58 */       for (Player p2 : Bukkit.getOnlinePlayers()) {
/* 59 */         Manager.sendActionBar(p2, "§c" + p.getName() + " deslogou em combate e morreu!");
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\CombatLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */