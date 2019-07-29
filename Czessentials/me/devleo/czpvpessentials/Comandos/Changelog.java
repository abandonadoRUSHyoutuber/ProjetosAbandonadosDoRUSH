/*    */ package me.devleo.czpvpessentials.Comandos;
/*    */ 
/*    */ import me.devleo.czpvpessentials.ManagerCentered;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class Changelog implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args)
/*    */   {
/* 13 */     if (!(sender instanceof Player)) {
/* 14 */       return true;
/*    */     }
/* 16 */     Player p = (Player)sender;
/* 17 */     if (cmd.getName().equalsIgnoreCase("changelog")) {
/* 18 */       ManagerCentered.sendCenteredMessage(p, "§aNovidades (09/01)");
/* 19 */       p.sendMessage(" ");
/* 20 */       ManagerCentered.sendCenteredMessage(p, "§a- Jogadores VIPS podem setar lojas (/setloja)");
/* 21 */       ManagerCentered.sendCenteredMessage(p, "§a- Adicionado comando /lojas");
/* 22 */       ManagerCentered.sendCenteredMessage(p, "§a- Menus refeitos");
/* 23 */       p.sendMessage(" ");
/* 24 */       ManagerCentered.sendCenteredMessage(p, "§7Em breve mais novidades!");
/*    */     }
/* 26 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\Comandos\Changelog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */