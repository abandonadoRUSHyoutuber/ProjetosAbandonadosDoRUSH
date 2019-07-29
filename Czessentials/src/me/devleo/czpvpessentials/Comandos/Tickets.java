/*     */ package me.devleo.czpvpessentials.Comandos;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import me.devleo.czpvpessentials.Manager;
/*     */ import me.devleo.czpvpessentials.ManagerCentered;
/*     */ import me.devleo.czpvpessentials.MySQL.TicketManager;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ 
/*     */ public class Tickets implements CommandExecutor, Listener
/*     */ {
/*  28 */   private static HashMap<Player, Player> resposta = new HashMap();
/*     */   
/*     */   @EventHandler
/*     */   private void clicarMenu(InventoryClickEvent e) {
/*  32 */     if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)) {
/*  33 */       Inventory inv = e.getInventory();
/*  34 */       Player p = (Player)e.getWhoClicked();
/*  35 */       if (inv.getTitle().equals("Dúvidas")) {
/*  36 */         p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 5.0F, 5.0F);
/*  37 */         e.setCancelled(true);
/*  38 */         for (Player p2 : Bukkit.getOnlinePlayers()) {
/*  39 */           if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§c" + p2.getName())) {
/*  40 */             p.closeInventory();
/*  41 */             p.sendMessage("§aEscreva a resposta da dúvida no chat!");
/*  42 */             p.sendMessage("§aPara cancelar digite 'cancelar'");
/*  43 */             resposta.put(p, p2);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void chat(AsyncPlayerChatEvent e) {
/*  52 */     if (resposta.containsKey(e.getPlayer())) {
/*  53 */       e.setCancelled(true);
/*  54 */       if (e.getMessage().equalsIgnoreCase("cancelar")) {
/*  55 */         resposta.remove(e.getPlayer());
/*  56 */         e.getPlayer().sendMessage("§aResposta cancelada!");
/*  57 */         return;
/*     */       }
/*  59 */       Player p2 = (Player)resposta.get(e.getPlayer());
/*  60 */       if (!TicketManager.containsDuvida(p2.getUniqueId().toString())) {
/*  61 */         e.getPlayer().sendMessage("§cEsta dúvida já foi respondida por outro staff!");
/*  62 */         resposta.remove(e.getPlayer());
/*  63 */         return;
/*     */       }
/*  65 */       ManagerCentered.sendCenteredMessage(p2, "§f§lDÚVIDA RESPONDIDA");
/*  66 */       p2.sendMessage(" ");
/*  67 */       ManagerCentered.sendCenteredMessage(p2, "§8Resposta: §7" + e.getMessage());
/*  68 */       ManagerCentered.sendCenteredMessage(p2, "§8Staff que respondeu: §7" + e.getPlayer().getName());
/*  69 */       resposta.remove(e.getPlayer());
/*  70 */       e.getPlayer().sendMessage("§aResposta enviada e dúvida deletada!");
/*  71 */       TicketManager.removerDuvida(p2.getUniqueId().toString());
/*  72 */       return;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
/*  77 */     if (!(sender instanceof Player)) {
/*  78 */       return true;
/*     */     }
/*  80 */     Player p = (Player)sender;
/*  81 */     if (cmd.getName().equalsIgnoreCase("ticket")) {
/*  82 */       if (args.length == 0) {
/*  83 */         p.sendMessage("§cUso correto: /ticket <dúvida>!");
/*  84 */         return true;
/*     */       }
/*  86 */       if (TicketManager.containsDuvida(p.getUniqueId().toString())) {
/*  87 */         p.sendMessage("§7[Ticket] §cVocê já tem uma dúvida aguardando resposta!");
/*  88 */         return true;
/*     */       }
/*  90 */       String mensagem = "";
/*  91 */       for (int i = 0; i <= args.length - 1; i++) {
/*  92 */         mensagem = mensagem + args[i];
/*  93 */         if (i != args.length - 1) {
/*  94 */           mensagem = mensagem + " ";
/*     */         }
/*     */       }
/*  97 */       p.sendMessage("§aDúvida enviada, um staff irá responder seu ticket em breve!");
/*  98 */       p.sendMessage("§aLembre-se: O mau uso deste sistema poderá resultar em banimento temporário!");
/*  99 */       TicketManager.addDuvida(p, mensagem);
/* 100 */       avisar();
/*     */     }
/* 102 */     if ((cmd.getName().equalsIgnoreCase("tickets")) && 
/* 103 */       (p.hasPermission("ticket.ver"))) {
/* 104 */       Inventory inv = Bukkit.createInventory(p, 54, "Dúvidas");
/*     */       
/* 106 */       ItemStack vidro = new ItemStack(Material.THIN_GLASS);
/* 107 */       inv.setItem(0, vidro);
/* 108 */       inv.setItem(1, vidro);
/* 109 */       inv.setItem(2, vidro);
/* 110 */       inv.setItem(3, vidro);
/* 111 */       inv.setItem(4, vidro);
/* 112 */       inv.setItem(5, vidro);
/* 113 */       inv.setItem(6, vidro);
/* 114 */       inv.setItem(7, vidro);
/* 115 */       inv.setItem(8, vidro);
/*     */       
/* 117 */       for (Player p2 : Bukkit.getOnlinePlayers()) {
/* 118 */         if (TicketManager.containsDuvida(p2.getUniqueId().toString())) {
/* 119 */           ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
/* 120 */           SkullMeta head2 = (SkullMeta)head.getItemMeta();
/* 121 */           head2.setOwner(p2.getName());
/* 122 */           head2.setDisplayName("§c" + p2.getName());
/* 123 */           List<String> lore = new ArrayList();
/* 124 */           lore.add("§7Dúvida:");
/* 125 */           lore.add("§f" + TicketManager.getDuvida(p2.getUniqueId().toString()));
/* 126 */           lore.add(" ");
/* 127 */           lore.add("§7Clique para responder o ticket!");
/* 128 */           head2.setLore(lore);
/* 129 */           head.setItemMeta(head2);
/* 130 */           inv.addItem(new ItemStack[] { head });
/*     */         }
/*     */       }
/* 133 */       vidro = new ItemStack(Material.AIR);
/* 134 */       inv.setItem(0, vidro);
/* 135 */       inv.setItem(1, vidro);
/* 136 */       inv.setItem(2, vidro);
/* 137 */       inv.setItem(3, vidro);
/* 138 */       inv.setItem(4, vidro);
/* 139 */       inv.setItem(5, vidro);
/* 140 */       inv.setItem(6, vidro);
/* 141 */       inv.setItem(7, vidro);
/* 142 */       inv.setItem(8, vidro);
/*     */       
/* 144 */       p.openInventory(inv);
/*     */     }
/*     */     
/* 147 */     return false;
/*     */   }
/*     */   
/*     */   private static void avisar() {
/* 151 */     for (Player p : ) {
/* 152 */       if (p.hasPermission("ticket.ver")) {
/* 153 */         Manager.sendActionBar(p, "§aUma nova dúvida foi enviada!");
/* 154 */         p.playSound(p.getLocation(), Sound.CAT_HISS, 1.0F, 1.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\Comandos\Tickets.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */