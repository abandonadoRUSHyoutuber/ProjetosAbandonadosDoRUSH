/*    */ package me.devleo.czpvpessentials.Comandos;
/*    */ 
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class Reparar implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args)
/*    */   {
/* 13 */     if (!(sender instanceof Player)) {
/* 14 */       return true;
/*    */     }
/* 16 */     Player p = (Player)sender;
/* 17 */     if (cmd.getName().equalsIgnoreCase("reparar")) {
/* 18 */       if (p.hasPermission("reparar.usar")) {
/* 19 */         if ((p.getItemInHand().getType() == null) || (p.getItemInHand().getType() == org.bukkit.Material.AIR)) {
/* 20 */           p.sendMessage("§b[Reparar] §cVocê precisa segurar o item que quer reparar.");
/* 21 */           return true;
/*    */         }
/* 23 */         if (p.getLevel() < 30) {
/* 24 */           p.sendMessage("§b[Reparar] §cVocê precisa de 30 Leveis de EXP.");
/* 25 */           return true;
/*    */         }
/* 27 */         ItemStack item = p.getItemInHand();
/* 28 */         if (item.getDurability() != 0) {
/* 29 */           item.setDurability((short)0);
/* 30 */           p.sendMessage("§b[Reparar] §eItem reparado com sucesso!");
/* 31 */           p.setLevel(p.getLevel() - 30);
/* 32 */           p.updateInventory();
/* 33 */           return true;
/*    */         }
/* 35 */         p.sendMessage("§b[Reparar] §cEste item não precisa ser reparado.");
/*    */       }
/*    */       else {
/* 38 */         p.sendMessage("§cComando disponível para jogadores VIPS!");
/* 39 */         p.sendMessage("§cAdquira seu VIP em nossa loja:");
/* 40 */         p.sendMessage("§7www.czpvp.com.br");
/* 41 */         return true;
/*    */       }
/*    */     }
/* 44 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\Comandos\Reparar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */