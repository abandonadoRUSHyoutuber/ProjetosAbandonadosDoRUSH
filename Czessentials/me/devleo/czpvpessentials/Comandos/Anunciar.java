/*    */ package me.devleo.czpvpessentials.Comandos;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import me.devleo.czpvpessentials.Main;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.scheduler.BukkitScheduler;
/*    */ 
/*    */ public class Anunciar
/*    */   implements CommandExecutor
/*    */ {
/* 15 */   public static ArrayList<Player> delay = new ArrayList();
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
/* 18 */     if (!(sender instanceof Player)) {
/* 19 */       return true;
/*    */     }
/* 21 */     final Player p = (Player)sender;
/* 22 */     if (cmd.getName().equalsIgnoreCase("anunciar")) {
/* 23 */       if (!p.hasPermission("anunciar.usar")) {
/* 24 */         p.sendMessage("§cComando disponível para jogadores VIPS!");
/* 25 */         p.sendMessage("§cAdquira seu VIP em nossa loja:");
/* 26 */         p.sendMessage("§7www.czpvp.com.br");
/* 27 */         return true;
/*    */       }
/* 29 */       if (args.length == 0) {
/* 30 */         p.sendMessage("§b[Anunciar] §cUse /anunciar <msg>!");
/* 31 */         return true;
/*    */       }
/* 33 */       if (delay.contains(p)) {
/* 34 */         p.sendMessage("§b[Anunciar] §cAguarde para usar este comando novamente!");
/* 35 */         return true;
/*    */       }
/* 37 */       String mensagem = "";
/* 38 */       for (int i = 0; i <= args.length - 1; i++) {
/* 39 */         mensagem = mensagem + args[i];
/* 40 */         if (i != args.length - 1) {
/* 41 */           mensagem = mensagem + " ";
/*    */         }
/*    */       }
/* 44 */       Bukkit.broadcastMessage(" ");
/* 45 */       Bukkit.broadcastMessage("§b[Anunciar - " + p.getName() + "] §e" + mensagem.replace("&", "§"));
/* 46 */       Bukkit.broadcastMessage(" ");
/* 47 */       delay.add(p);
/* 48 */       Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
/*    */         public void run() {
/* 50 */           if (Anunciar.delay.contains(p)) {
/* 51 */             Anunciar.delay.remove(p);
/*    */           }
/*    */         }
/* 54 */       }, 4000L);
/*    */     }
/* 56 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\Comandos\Anunciar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */