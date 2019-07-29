/*    */ package me.devleo.czpvpessentials.Comandos;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ public class Convidado implements CommandExecutor, Listener
/*    */ {
/*    */   @EventHandler
/*    */   private void clicarMenu(InventoryClickEvent e)
/*    */   {
/* 24 */     if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)) {
/* 25 */       Inventory inv = e.getInventory();
/* 26 */       Player p = (Player)e.getWhoClicked();
/* 27 */       if (inv.getTitle().equals("Convidado")) {
/* 28 */         p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 5.0F, 5.0F);
/* 29 */         e.setCancelled(true);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
/* 35 */     if (!(sender instanceof Player)) {
/* 36 */       return true;
/*    */     }
/* 38 */     Player p = (Player)sender;
/* 39 */     if ((cmd.getName().equalsIgnoreCase("convidado")) && 
/* 40 */       (args.length == 0)) {
/* 41 */       Inventory inv = Bukkit.createInventory(p, 27, "Convidado");
/* 42 */       List<String> lore = new ArrayList();
/*    */       
/* 44 */       ItemStack info = new ItemStack(Material.PRISMARINE_SHARD);
/* 45 */       ItemMeta info2 = info.getItemMeta();
/* 46 */       info2.setDisplayName("§aInformações");
/* 47 */       lore.add("§7Você já trouxe §a0 §7amigos para o servidor!");
/* 48 */       lore.add(" ");
/* 49 */       lore.add("§7Quando convidar um amigo peça para ele usar");
/* 50 */       lore.add("§7o comando §b/convidado " + p.getName() + " §7ao");
/* 51 */       lore.add("§7entrar no servidor!");
/* 52 */       info2.setLore(lore);
/* 53 */       info.setItemMeta(info2);
/* 54 */       inv.setItem(12, info);
/*    */       
/* 56 */       ItemStack premios = new ItemStack(Material.getMaterial(342));
/* 57 */       ItemMeta premios2 = premios.getItemMeta();
/* 58 */       premios2.setDisplayName("§aRecompensas");
/* 59 */       lore.clear();
/* 60 */       lore.add("§7Trouxe 10 amigos: §e1 Caixa Básica");
/* 61 */       lore.add("§7Trouxe 15 amigos: §e1 Caixa §5Avançada");
/* 62 */       lore.add("§7Trouxe 20 amigos: §625 Cash");
/* 63 */       lore.add(" ");
/* 64 */       lore.add("§7Os premios são entregues automaticamente!");
/* 65 */       premios2.setLore(lore);
/* 66 */       premios.setItemMeta(premios2);
/* 67 */       inv.setItem(14, premios);
/*    */       
/* 69 */       p.openInventory(inv);
/* 70 */       return true;
/*    */     }
/*    */     
/*    */ 
/* 74 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\Comandos\Convidado.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */