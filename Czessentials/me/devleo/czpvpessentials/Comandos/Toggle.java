/*    */ package me.devleo.czpvpessentials.Comandos;
/*    */ 
/*    */ import br.com.devpaulo.legendchat.api.events.PrivateMessageEvent;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.devleo.czpvpessentials.Manager;
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
/*    */ public class Toggle implements Listener, CommandExecutor
/*    */ {
/*    */   @EventHandler
/*    */   private void clicarMenu(InventoryClickEvent e)
/*    */   {
/* 25 */     if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)) {
/* 26 */       Inventory inv = e.getInventory();
/* 27 */       Player p = (Player)e.getWhoClicked();
/* 28 */       if (inv.getTitle().equals("Preferências")) {
/* 29 */         p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 5.0F, 5.0F);
/* 30 */         e.setCancelled(true);
/* 31 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aMensagens Privadas")) {
/* 32 */           tell.add(p);
/* 33 */           p.closeInventory();
/* 34 */           p.chat("/toggle");
/* 35 */           return;
/*    */         }
/* 37 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cMensagens Privadas")) {
/* 38 */           tell.remove(p);
/* 39 */           p.closeInventory();
/* 40 */           p.chat("/toggle");
/* 41 */           return;
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/* 47 */   public static ArrayList<Player> tell = new ArrayList();
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
/* 50 */     if (!(sender instanceof Player)) {
/* 51 */       return true;
/*    */     }
/* 53 */     Player p = (Player)sender;
/* 54 */     if (cmd.getName().equalsIgnoreCase("toggle")) {
/* 55 */       if (!p.hasPermission("toggle.usar")) {
/* 56 */         p.sendMessage("§cComando disponível para jogadores VIPS!");
/* 57 */         p.sendMessage("§cAdquira seu VIP em nossa loja:");
/* 58 */         p.sendMessage("§7www.czpvp.com.br");
/* 59 */         return true;
/*    */       }
/* 61 */       Inventory inv = org.bukkit.Bukkit.createInventory(p, 45, "Preferências");
/* 62 */       List<String> lore = new ArrayList();
/*    */       
/* 64 */       lore.clear();
/* 65 */       lore.add("§7Ao deixar essa opção ativa você");
/* 66 */       lore.add("§7irá receber mensagens privadas!");
/* 67 */       inv.setItem(20, Manager.criarItem(Material.MAP, "§6Mensagens Privadas", lore));
/* 68 */       if (!tell.contains(p)) {
/* 69 */         inv.setItem(21, 
/* 70 */           Manager.criarItem(Material.STAINED_GLASS_PANE, "§aMensagens Privadas", "§7Estado: §aAtivado"));
/* 71 */         inv.getItem(21).setDurability((short)5);
/* 72 */       } else if (tell.contains(p)) {
/* 73 */         inv.setItem(21, Manager.criarItem(Material.STAINED_GLASS_PANE, "§cMensagens Privadas", 
/* 74 */           "§7Estado: §cDesativado"));
/* 75 */         inv.getItem(21).setDurability((short)14);
/*    */       }
/*    */       
/* 78 */       lore.clear();
/* 79 */       lore.add("§7Ao deixar essa opção ativa você");
/* 80 */       lore.add("§7irá receber pedidos de teletransporte!");
/* 81 */       inv.setItem(11, Manager.criarItem(Material.PAINTING, "§6Pedidos de Teletransporte §7(Em Breve)", lore));
/* 82 */       inv.setItem(12, Manager.criarItem(Material.STAINED_GLASS_PANE, "§aPedidos de Teletransporte", 
/* 83 */         "§7Estado: §aAtivado"));
/* 84 */       inv.getItem(12).setDurability((short)5);
/*    */       
/* 86 */       p.openInventory(inv);
/*    */     }
/* 88 */     return false;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   private void chat(PrivateMessageEvent e) {
/* 93 */     if (tell.contains(e.getReceiver())) {
/* 94 */       Player p = (Player)e.getSender();
/* 95 */       p.sendMessage("§cEste jogador desativou o recebimento de mensagens privadas!");
/* 96 */       p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0F, 1.0F);
/* 97 */       e.setCancelled(true);
/* 98 */       return;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\Comandos\Toggle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */