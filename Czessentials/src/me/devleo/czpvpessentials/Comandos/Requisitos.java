/*    */ package me.devleo.czpvpessentials.Comandos;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.devleo.czpvpessentials.Manager;
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
/*    */ 
/*    */ public class Requisitos implements CommandExecutor, Listener
/*    */ {
/*    */   @EventHandler
/*    */   private void clicarMenu(InventoryClickEvent e)
/*    */   {
/* 24 */     if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)) {
/* 25 */       Inventory inv = e.getInventory();
/* 26 */       Player p = (Player)e.getWhoClicked();
/* 27 */       if (inv.getTitle().equals("Requisitos")) {
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
/* 39 */     if (cmd.getName().equalsIgnoreCase("requisitos")) {
/* 40 */       Inventory inv = Bukkit.createInventory(p, 27, "Requisitos");
/* 41 */       List<String> lore = new ArrayList();
/*    */       
/* 43 */       lore.add("§cNão tem os requisitos para YT?:");
/* 44 */       lore.add("§7Caso seu canal ainda seja pequeno, você");
/* 45 */       lore.add("§7terá a oportunidade de ganhar um Kit §b[VIP§6+§b]");
/* 46 */       lore.add(" ");
/* 47 */       lore.add("§6» §eGravar no servidor");
/* 48 */       lore.add("§6» §ePegar 150 Views e 20 Likes no video");
/* 49 */       lore.add(" ");
/* 50 */       lore.add("§aApós gravar, envie seu canal à um staff para avaliação!");
/* 51 */       inv.setItem(11, Manager.criarItem(Material.LEATHER_CHESTPLATE, "§cRecompensa Básica", lore));
/*    */       
/* 53 */       lore.clear();
/* 54 */       lore.add("§7Requisitos:");
/* 55 */       lore.add("§6» §eGravar no servidor");
/* 56 */       lore.add("§6» §ePossuir 2000+ inscritos");
/* 57 */       lore.add("§6» §ePegar 800 Views no video");
/* 58 */       lore.add(" ");
/* 59 */       lore.add("§aApós gravar, envie seu canal à um staff para avaliação!");
/* 60 */       inv.setItem(13, Manager.criarItem(Material.GOLD_CHESTPLATE, "§aRecompensa VIP", lore));
/*    */       
/* 62 */       lore.clear();
/* 63 */       lore.add("§7Requisitos:");
/* 64 */       lore.add("§6» §eGravar no servidor");
/* 65 */       lore.add("§6» §ePossuir 4000+ inscritos");
/* 66 */       lore.add("§6» §ePegar 1000 Views no video");
/* 67 */       lore.add(" ");
/* 68 */       lore.add("§aApós gravar, envie seu canal à um staff para avaliação!");
/* 69 */       inv.setItem(15, Manager.criarItem(Material.DIAMOND_CHESTPLATE, "§cRequisitos YT", lore));
/*    */       
/* 71 */       p.openInventory(inv);
/*    */     }
/* 73 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\Comandos\Requisitos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */