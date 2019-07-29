/*     */ package me.devleo.czpvpessentials;
/*     */ 
/*     */ import java.util.Random;
/*     */ import me.devleo.czpvpessentials.MySQL.LojaManager;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.entity.FoodLevelChangeEvent;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.event.server.ServerListPingEvent;
/*     */ import org.bukkit.event.weather.WeatherChangeEvent;
/*     */ 
/*     */ public class Eventos implements org.bukkit.event.Listener
/*     */ {
/*     */   @EventHandler
/*     */   private void entrar(PlayerJoinEvent e)
/*     */   {
/*  23 */     Player p = e.getPlayer();
/*  24 */     p.teleport(Bukkit.getWorld("Spawn").getSpawnLocation());
/*  25 */     int i = 0;
/*  26 */     while (i < 55) {
/*  27 */       p.sendMessage(" ");
/*  28 */       i++;
/*     */     }
/*  30 */     p.sendMessage("§a* Fique atento as novidades através do comando /changelog!");
/*  31 */     if ((!p.hasPermission("loja.set")) && (LojaManager.containsLoja(p.getUniqueId().toString()))) {
/*  32 */       LojaManager.removerLoja(p.getUniqueId().toString());
/*  33 */       p.sendMessage("§cSua loja foi deletada!");
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void motd(ServerListPingEvent e) {
/*  39 */     String desconto = ManagerCentered.getCenteredMessage("§c§lVIP EM POMOÇAO! §fAcesse: §nczpvp.com.br");
/*  40 */     e.setMotd("§2§lC§4§lz§6§lPvP §7(1.8.*)     §fServidor RESETOU! \n" + desconto);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void chuva(WeatherChangeEvent e) {
/*  45 */     if (e.toWeatherState()) {
/*  46 */       e.setCancelled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void quebrar(BlockBreakEvent e) {
/*  52 */     Player p = e.getPlayer();
/*  53 */     if ((p.getLocation().getWorld().getName().equalsIgnoreCase("VoidConstrucoes")) && (
/*  54 */       (e.getBlock().getType() == Material.STONE) || (e.getBlock().getType() == Material.GOLD_ORE) || 
/*  55 */       (e.getBlock().getType() == Material.IRON_ORE) || (e.getBlock().getType() == Material.LAPIS_ORE) || 
/*  56 */       (e.getBlock().getType() == Material.DIAMOND_ORE) || (e.getBlock().getType() == Material.EMERALD_ORE) || 
/*  57 */       (e.getBlock().getType() == Material.QUARTZ_ORE))) {
/*  58 */       Random r = new Random();
/*  59 */       int o = r.nextInt(700);
/*  60 */       if (p.hasPermission("Bonusmineracao.vip")) {
/*  61 */         if (o < 2) {
/*  62 */           if (o < 1) {
/*  63 */             p.sendMessage("§aVocê encontrou um booster!");
/*  64 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "givebooster " + p.getName());
/*  65 */             p.playSound(p.getLocation(), Sound.WOOD_CLICK, 1.0F, 1.0F);
/*  66 */             return; }
/*  67 */           if (o >= 1) {
/*  68 */             p.sendMessage("§aVocê encontrou um combustível!");
/*  69 */             p.playSound(p.getLocation(), Sound.WOOD_CLICK, 1.0F, 1.0F);
/*  70 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), 
/*  71 */               "maquinas darcombustivel " + p.getName() + " Blaze 1");
/*     */           }
/*     */         }
/*     */       }
/*     */       else {
/*  76 */         if (o == 243) {
/*  77 */           int n = r.nextInt(100);
/*  78 */           if (o + n < 248) {
/*  79 */             p.sendMessage("§aVocê encontrou um combustível!");
/*  80 */             p.playSound(p.getLocation(), Sound.WOOD_CLICK, 1.0F, 1.0F);
/*  81 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), 
/*  82 */               "maquinas darcombustivel " + p.getName() + " Blaze 1");
/*  83 */             return;
/*     */           }
/*     */         }
/*  86 */         if (o < 2) {
/*  87 */           p.sendMessage("§aVocê encontrou um combustível!");
/*  88 */           p.playSound(p.getLocation(), Sound.WOOD_CLICK, 1.0F, 1.0F);
/*  89 */           Bukkit.dispatchCommand(Bukkit.getConsoleSender(), 
/*  90 */             "maquinas darcombustivel " + p.getName() + " Carvao 1");
/*  91 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void fome(FoodLevelChangeEvent e)
/*     */   {
/* 100 */     if (!e.getEntity().getLocation().getWorld().getName().equalsIgnoreCase("Terrenos")) {
/* 101 */       e.setCancelled(true);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\Eventos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */