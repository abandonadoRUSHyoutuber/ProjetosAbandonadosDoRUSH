/*     */ package me.devleo.czpvpessentials.Comandos;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import me.devleo.czpvpessentials.Manager;
/*     */ import me.devleo.czpvpessentials.MySQL.LojaManager;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ 
/*     */ public class Loja implements Listener, CommandExecutor
/*     */ {
/*     */   @EventHandler
/*     */   private void clicarMenu(InventoryClickEvent e)
/*     */   {
/*  28 */     if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)) {
/*  29 */       Inventory inv = e.getInventory();
/*  30 */       Player p = (Player)e.getWhoClicked();
/*  31 */       if (inv.getTitle().equals("Loja de VIPs")) {
/*  32 */         p.playSound(p.getLocation(), org.bukkit.Sound.DOOR_CLOSE, 5.0F, 5.0F);
/*  33 */         e.setCancelled(true);
/*  34 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cVoltar")) {
/*  35 */           p.closeInventory();
/*  36 */           p.chat("/lojas");
/*     */         }
/*  38 */         for (Player p2 : Bukkit.getOnlinePlayers()) {
/*  39 */           if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§a" + p2.getName())) {
/*  40 */             p.closeInventory();
/*  41 */             String[] local = LojaManager.getLoja(p2.getUniqueId().toString()).split(";");
/*  42 */             Location loc = new Location(Bukkit.getWorld(local[0]), Integer.valueOf(local[1]).intValue(), 
/*  43 */               Integer.valueOf(local[2]).intValue(), Integer.valueOf(local[3]).intValue());
/*  44 */             loc.setPitch(Float.valueOf(local[4]).floatValue());
/*  45 */             loc.setYaw(Float.valueOf(local[5]).floatValue());
/*  46 */             p.teleport(loc);
/*  47 */             if (p != p2) {
/*  48 */               LojaManager.setVisita(p2.getUniqueId().toString(), 
/*  49 */                 LojaManager.getVisita(p2.getUniqueId().toString()) + 1);
/*     */             }
/*  51 */             p.sendMessage("§aTeletransportado com sucesso à loja do jogador " + p2.getName() + "!");
/*  52 */             return;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
/*  60 */     if (!(sender instanceof Player)) {
/*  61 */       return true;
/*     */     }
/*  63 */     Player p = (Player)sender;
/*  64 */     if (cmd.getName().equalsIgnoreCase("irloja")) {
/*  65 */       if (args.length == 0) {
/*  66 */         p.sendMessage("§cUso correto: /irloja [jogador]");
/*  67 */         return true;
/*     */       }
/*  69 */       Player p2 = Bukkit.getPlayer(args[0]);
/*  70 */       if (p2 == null) {
/*  71 */         p.sendMessage("§cJogador não encontrado!");
/*  72 */         return true;
/*     */       }
/*  74 */       if (!LojaManager.containsLoja(p2.getUniqueId().toString())) {
/*  75 */         p.sendMessage("§cEsse jogador não possui uma loja!");
/*  76 */         return true;
/*     */       }
/*  78 */       String[] local = LojaManager.getLoja(p2.getUniqueId().toString()).split(";");
/*  79 */       Location loc = new Location(Bukkit.getWorld(local[0]), Integer.valueOf(local[1]).intValue(), Integer.valueOf(local[2]).intValue(), 
/*  80 */         Integer.valueOf(local[3]).intValue());
/*  81 */       loc.setPitch(Float.valueOf(local[4]).floatValue());
/*  82 */       loc.setYaw(Float.valueOf(local[5]).floatValue());
/*  83 */       p.teleport(loc);
/*  84 */       if (p != p2) {
/*  85 */         LojaManager.setVisita(p2.getUniqueId().toString(), 
/*  86 */           LojaManager.getVisita(p2.getUniqueId().toString()) + 1);
/*     */       }
/*  88 */       p.sendMessage("§aTeletransportado com sucesso à loja do jogador " + p2.getName() + "!");
/*     */     }
/*  90 */     if (cmd.getName().equalsIgnoreCase("setloja")) {
/*  91 */       if (!p.hasPermission("loja.set")) {
/*  92 */         p.sendMessage("§cComando disponível para jogadores VIPS!");
/*  93 */         p.sendMessage("§cAdquira seu VIP em nossa loja:");
/*  94 */         p.sendMessage("§7www.czpvp.com.br");
/*  95 */         return true;
/*     */       }
/*  97 */       if (LojaManager.containsLoja(p.getUniqueId().toString())) {
/*  98 */         p.sendMessage("§cVocê já tem uma loja setada!");
/*  99 */         return true;
/*     */       }
/* 101 */       String local = p.getLocation().getWorld().getName() + ";" + p.getLocation().getBlockX() + ";" + 
/* 102 */         p.getLocation().getBlockY() + ";" + p.getLocation().getBlockZ() + ";" + p.getLocation().getPitch() + 
/* 103 */         ";" + p.getLocation().getYaw();
/* 104 */       LojaManager.criarLoja(p, local);
/* 105 */       p.sendMessage("§aLoja criada com sucesso!");
/*     */     }
/* 107 */     if (cmd.getName().equalsIgnoreCase("delloja")) {
/* 108 */       if (!p.hasPermission("loja.del")) {
/* 109 */         p.sendMessage("§cComando disponível para jogadores VIPS!");
/* 110 */         p.sendMessage("§cAdquira seu VIP em nossa loja:");
/* 111 */         p.sendMessage("§7www.czpvp.com.br");
/* 112 */         return true;
/*     */       }
/* 114 */       if (!LojaManager.containsLoja(p.getUniqueId().toString())) {
/* 115 */         p.sendMessage("§cVocê não tem uma loja setada!");
/* 116 */         return true;
/*     */       }
/* 118 */       LojaManager.removerLoja(p.getUniqueId().toString());
/* 119 */       p.sendMessage("§aLoja deletada com sucesso!");
/*     */     }
/* 121 */     return false;
/*     */   }
/*     */   
/*     */   public static void verLojas(Player p) {
/* 125 */     Inventory inv = Bukkit.createInventory(p, 54, "Loja de VIPs");
/* 126 */     List<String> lore = new ArrayList();
/*     */     
/* 128 */     ItemStack vidro = new ItemStack(Material.THIN_GLASS);
/* 129 */     inv.setItem(0, vidro);
/* 130 */     inv.setItem(1, vidro);
/* 131 */     inv.setItem(2, vidro);
/* 132 */     inv.setItem(3, vidro);
/* 133 */     inv.setItem(4, vidro);
/* 134 */     inv.setItem(5, vidro);
/* 135 */     inv.setItem(6, vidro);
/* 136 */     inv.setItem(7, vidro);
/* 137 */     inv.setItem(8, vidro);
/* 138 */     inv.setItem(9, vidro);
/* 139 */     inv.setItem(17, vidro);
/* 140 */     inv.setItem(18, vidro);
/* 141 */     inv.setItem(26, vidro);
/* 142 */     inv.setItem(27, vidro);
/* 143 */     inv.setItem(35, vidro);
/* 144 */     inv.setItem(36, vidro);
/* 145 */     inv.setItem(44, vidro);
/* 146 */     inv.setItem(45, vidro);
/* 147 */     inv.setItem(46, vidro);
/* 148 */     inv.setItem(47, vidro);
/* 149 */     inv.setItem(48, vidro);
/* 150 */     inv.setItem(49, vidro);
/* 151 */     inv.setItem(50, vidro);
/* 152 */     inv.setItem(51, vidro);
/* 153 */     inv.setItem(52, vidro);
/* 154 */     inv.setItem(53, vidro);
/*     */     
/* 156 */     for (Player p2 : Bukkit.getOnlinePlayers()) {
/* 157 */       if (LojaManager.containsLoja(p2.getUniqueId().toString())) {
/* 158 */         ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
/* 159 */         SkullMeta head2 = (SkullMeta)head.getItemMeta();
/* 160 */         head2.setOwner(p2.getName());
/* 161 */         head2.setDisplayName("§a" + p2.getName());
/* 162 */         lore.clear();
/* 163 */         lore.add("§7Clique para visitar a loja.");
/* 164 */         lore.add(" ");
/* 165 */         lore.add("§7Essa loja já foi visitada §f" + LojaManager.getVisita(p2.getUniqueId().toString()) + 
/* 166 */           " §7vezes!");
/* 167 */         head2.setLore(lore);
/* 168 */         head.setItemMeta(head2);
/* 169 */         inv.addItem(new ItemStack[] { head });
/*     */       }
/*     */     }
/*     */     
/* 173 */     lore.clear();
/* 174 */     lore.add("§7Voltar para menu de lojas.");
/* 175 */     inv.setItem(0, Manager.criarItem(Material.ARROW, "§cVoltar", lore));
/* 176 */     inv.setItem(1, null);
/* 177 */     inv.setItem(2, null);
/* 178 */     inv.setItem(3, null);
/* 179 */     inv.setItem(4, null);
/* 180 */     inv.setItem(5, null);
/* 181 */     inv.setItem(6, null);
/* 182 */     inv.setItem(7, null);
/* 183 */     inv.setItem(8, null);
/* 184 */     inv.setItem(9, null);
/* 185 */     inv.setItem(17, null);
/* 186 */     inv.setItem(18, null);
/* 187 */     inv.setItem(26, null);
/* 188 */     inv.setItem(27, null);
/* 189 */     inv.setItem(35, null);
/* 190 */     inv.setItem(36, null);
/* 191 */     inv.setItem(44, null);
/* 192 */     inv.setItem(45, null);
/* 193 */     inv.setItem(46, null);
/* 194 */     inv.setItem(47, null);
/* 195 */     inv.setItem(48, null);
/* 196 */     inv.setItem(49, null);
/* 197 */     inv.setItem(50, null);
/* 198 */     inv.setItem(51, null);
/* 199 */     inv.setItem(52, null);
/* 200 */     inv.setItem(53, null);
/* 201 */     p.openInventory(inv);
/*     */   }
/*     */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\Comandos\Loja.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */