/*     */ package me.devleo.czpvpessentials.Comandos;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import me.devleo.czpvpessentials.Main;
/*     */ import me.devleo.czpvpessentials.MySQL.MSManager;
/*     */ import net.md_5.bungee.api.chat.ClickEvent;
/*     */ import net.md_5.bungee.api.chat.ClickEvent.Action;
/*     */ import net.md_5.bungee.api.chat.ComponentBuilder;
/*     */ import net.md_5.bungee.api.chat.HoverEvent;
/*     */ import net.md_5.bungee.api.chat.HoverEvent.Action;
/*     */ import net.md_5.bungee.api.chat.TextComponent;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Player.Spigot;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ 
/*     */ public class Reportar implements org.bukkit.command.CommandExecutor, org.bukkit.event.Listener
/*     */ {
/*     */   @EventHandler
/*     */   private void clicarMenu(InventoryClickEvent e)
/*     */   {
/*  33 */     if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)) {
/*  34 */       Inventory inv = e.getInventory();
/*  35 */       Player p = (Player)e.getWhoClicked();
/*  36 */       if (inv.getTitle().equals("Denúncias")) {
/*  37 */         p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 5.0F, 5.0F);
/*  38 */         e.setCancelled(true);
/*  39 */         for (Player p2 : Bukkit.getOnlinePlayers()) {
/*  40 */           if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§c" + p2.getName())) {
/*  41 */             abrirInfo(p, p2);
/*     */           }
/*     */         }
/*     */       }
/*  45 */       for (Player p2 : Bukkit.getOnlinePlayers()) {
/*  46 */         if (inv.getTitle().equals("Denúncias - " + p2.getName())) {
/*  47 */           p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 5.0F, 5.0F);
/*  48 */           e.setCancelled(true);
/*  49 */           if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aStatus da(s) Denúncia(s)")) {
/*  50 */             p.teleport(p2.getLocation());
/*  51 */             p.closeInventory();
/*     */           }
/*  53 */           if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cRecusar Denúncia(s)")) {
/*  54 */             MSManager.removerDenuncia(p2.getUniqueId().toString());
/*  55 */             p.closeInventory();
/*     */           }
/*  57 */           if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aAceitar Denúncia(s)")) {
/*  58 */             MSManager.removerDenuncia(p2.getUniqueId().toString());
/*  59 */             p.closeInventory();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
/*  67 */     if (!(sender instanceof Player)) {
/*  68 */       return true;
/*     */     }
/*  70 */     Player p = (Player)sender;
/*  71 */     if (cmd.getName().equalsIgnoreCase("reportar")) {
/*  72 */       if (delay.contains(p)) {
/*  73 */         p.sendMessage("§6[Denúncia] §cAguarde para enviar outro report!");
/*  74 */         return true;
/*     */       }
/*  76 */       if (args.length == 0) {
/*  77 */         p.sendMessage("§6[Denúncia] §cUtilize /reportar <jogador>!");
/*  78 */         return true;
/*     */       }
/*  80 */       Player p2 = Bukkit.getPlayer(args[0]);
/*  81 */       if (p2 == null) {
/*  82 */         p.sendMessage("§6[Denúncia] §cJogador não encontrado!");
/*  83 */         return true;
/*     */       }
/*  85 */       if (p2 == p) {
/*  86 */         p.sendMessage("§6[Denúncia] §cVocê não pode se auto-denúnciar!");
/*  87 */         return true;
/*     */       }
/*  89 */       if ((args.length < 2) || (args.length > 2)) {
/*  90 */         p.sendMessage(" ");
/*  91 */         p.sendMessage("§6[Denúncia] §bEscolha um motivo:");
/*     */         
/*  93 */         TextComponent msg = new TextComponent("§7  • ForceField");
/*  94 */         msg.setClickEvent(
/*  95 */           new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/reportar " + p2.getName() + " ForceField"));
/*  96 */         msg.setHoverEvent(
/*  97 */           new HoverEvent(HoverEvent.Action.SHOW_TEXT, 
/*  98 */           new ComponentBuilder(
/*  99 */           "§7Clique para denunciar o jogador " + p2.getName() + " por ForceField!")
/* 100 */           .create()));
/* 101 */         p.spigot().sendMessage(msg);
/* 102 */         msg = new TextComponent("§7  • Fly");
/* 103 */         msg.setClickEvent(
/* 104 */           new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/reportar " + p2.getName() + " fly"));
/* 105 */         msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, 
/* 106 */           new ComponentBuilder("§7Clique para denunciar o jogador " + p2.getName() + " por Fly!")
/* 107 */           .create()));
/* 108 */         p.spigot().sendMessage(msg);
/* 109 */         msg = new TextComponent("§7  • Ofensa");
/* 110 */         msg.setClickEvent(
/* 111 */           new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/reportar " + p2.getName() + " Ofensa"));
/* 112 */         msg.setHoverEvent(
/* 113 */           new HoverEvent(HoverEvent.Action.SHOW_TEXT, 
/* 114 */           new ComponentBuilder(
/* 115 */           "§7Clique para denunciar o jogador " + p2.getName() + " por Ofensa!")
/* 116 */           .create()));
/* 117 */         p.spigot().sendMessage(msg);
/* 118 */         msg = new TextComponent("§7  • Divulgação");
/* 119 */         msg.setClickEvent(
/* 120 */           new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/reportar " + p2.getName() + " divulgacao"));
/* 121 */         msg.setHoverEvent(
/* 122 */           new HoverEvent(HoverEvent.Action.SHOW_TEXT, 
/* 123 */           new ComponentBuilder(
/* 124 */           "§7Clique para denunciar o jogador " + p2.getName() + " por Divulgação!")
/* 125 */           .create()));
/* 126 */         p.spigot().sendMessage(msg);
/* 127 */         msg = new TextComponent("§7  • Macro");
/* 128 */         msg.setClickEvent(
/* 129 */           new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/reportar " + p2.getName() + " Macro"));
/* 130 */         msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, 
/* 131 */           new ComponentBuilder("§7Clique para denunciar o jogador " + p2.getName() + " por Macro!")
/* 132 */           .create()));
/* 133 */         p.spigot().sendMessage(msg);
/* 134 */         msg = new TextComponent("§7  • Speed");
/* 135 */         msg.setClickEvent(
/* 136 */           new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/reportar " + p2.getName() + " speed"));
/* 137 */         msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, 
/* 138 */           new ComponentBuilder("§7Clique para denunciar o jogador " + p2.getName() + " por Speed!")
/* 139 */           .create()));
/* 140 */         p.spigot().sendMessage(msg);
/* 141 */         return true;
/*     */       }
/* 143 */       if (args[1].equalsIgnoreCase("forcefield")) {
/* 144 */         p.sendMessage("§aDenúncia enviada, um staff irá checar seu report em breve!");
/* 145 */         p.sendMessage("§aLembre-se: O mau uso deste sistema poderá resultar em banimento temporário!");
/* 146 */         MSManager.addReport(p2, "FF");
/* 147 */         avisar();
/* 148 */         delay(p);
/*     */       }
/* 150 */       if (args[1].equalsIgnoreCase("fly")) {
/* 151 */         p.sendMessage("§aDenúncia enviada, um staff irá checar seu report em breve!");
/* 152 */         p.sendMessage("§aLembre-se: O mau uso deste sistema poderá resultar em banimento temporário!");
/* 153 */         MSManager.addReport(p2, "Fly");
/* 154 */         avisar();
/* 155 */         delay(p);
/*     */       }
/* 157 */       if (args[1].equalsIgnoreCase("ofensa")) {
/* 158 */         p.sendMessage("§aDenúncia enviada, um staff irá checar seu report em breve!");
/* 159 */         p.sendMessage("§aLembre-se: O mau uso deste sistema poderá resultar em banimento temporário!");
/* 160 */         MSManager.addReport(p2, "Ofensa");
/* 161 */         avisar();
/* 162 */         delay(p);
/*     */       }
/* 164 */       if (args[1].equalsIgnoreCase("divulgacao")) {
/* 165 */         p.sendMessage("§aDenúncia enviada, um staff irá checar seu report em breve!");
/* 166 */         p.sendMessage("§aLembre-se: O mau uso deste sistema poderá resultar em banimento temporário!");
/* 167 */         MSManager.addReport(p2, "Divulgacao");
/* 168 */         avisar();
/* 169 */         delay(p);
/*     */       }
/* 171 */       if (args[1].equalsIgnoreCase("macro")) {
/* 172 */         p.sendMessage("§aDenúncia enviada, um staff irá checar seu report em breve!");
/* 173 */         p.sendMessage("§aLembre-se: O mau uso deste sistema poderá resultar em banimento temporário!");
/* 174 */         MSManager.addReport(p2, "Macro");
/* 175 */         avisar();
/* 176 */         delay(p);
/*     */       }
/* 178 */       if (args[1].equalsIgnoreCase("speed")) {
/* 179 */         p.sendMessage("§aDenúncia enviada, um staff irá checar seu report em breve!");
/* 180 */         p.sendMessage("§aLembre-se: O mau uso deste sistema poderá resultar em banimento temporário!");
/* 181 */         MSManager.addReport(p2, "Speed");
/* 182 */         avisar();
/* 183 */         delay(p);
/*     */       }
/*     */     }
/* 186 */     if ((cmd.getName().equalsIgnoreCase("reportes")) && 
/* 187 */       (p.hasPermission("reporte.ver"))) {
/* 188 */       Inventory inv = Bukkit.createInventory(p, 54, "Denúncias");
/*     */       
/* 190 */       ItemStack vidro = new ItemStack(Material.THIN_GLASS);
/* 191 */       inv.setItem(0, vidro);
/* 192 */       inv.setItem(1, vidro);
/* 193 */       inv.setItem(2, vidro);
/* 194 */       inv.setItem(3, vidro);
/* 195 */       inv.setItem(4, vidro);
/* 196 */       inv.setItem(5, vidro);
/* 197 */       inv.setItem(6, vidro);
/* 198 */       inv.setItem(7, vidro);
/* 199 */       inv.setItem(8, vidro);
/*     */       
/* 201 */       for (Player p2 : Bukkit.getOnlinePlayers()) {
/* 202 */         if (MSManager.containsDenuncia(p2.getUniqueId().toString())) {
/* 203 */           ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
/* 204 */           SkullMeta head2 = (SkullMeta)head.getItemMeta();
/* 205 */           head2.setOwner(p2.getName());
/* 206 */           head2.setDisplayName("§c" + p2.getName());
/* 207 */           List<String> lore = new ArrayList();
/* 208 */           lore.add(" ");
/* 209 */           lore.add("§7Total de denúncias: §e" + MSManager.getAllDenuncia(p2.getUniqueId().toString()));
/* 210 */           lore.add(" ");
/* 211 */           lore.add("§7Clique para ver mais informações!");
/* 212 */           head2.setLore(lore);
/* 213 */           head.setItemMeta(head2);
/* 214 */           inv.addItem(new ItemStack[] { head });
/*     */         }
/*     */       }
/* 217 */       vidro = new ItemStack(Material.AIR);
/* 218 */       inv.setItem(0, vidro);
/* 219 */       inv.setItem(1, vidro);
/* 220 */       inv.setItem(2, vidro);
/* 221 */       inv.setItem(3, vidro);
/* 222 */       inv.setItem(4, vidro);
/* 223 */       inv.setItem(5, vidro);
/* 224 */       inv.setItem(6, vidro);
/* 225 */       inv.setItem(7, vidro);
/* 226 */       inv.setItem(8, vidro);
/*     */       
/* 228 */       p.openInventory(inv);
/*     */     }
/*     */     
/* 231 */     return false;
/*     */   }
/*     */   
/*     */   private static void avisar() {
/* 235 */     for (Player p : ) {
/* 236 */       if (p.hasPermission("reporte.ver")) {
/* 237 */         me.devleo.czpvpessentials.Manager.sendActionBar(p, "§aUma nova denúncia foi enviada!");
/* 238 */         p.playSound(p.getLocation(), Sound.CREEPER_HISS, 1.0F, 1.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/* 243 */   public static ArrayList<Player> delay = new ArrayList();
/*     */   
/*     */   private static void delay(Player p) {
/* 246 */     delay.add(p);
/* 247 */     Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
/*     */       public void run() {
/* 249 */         if (Reportar.delay.contains(Reportar.this)) {
/* 250 */           Reportar.delay.remove(Reportar.this);
/*     */         }
/*     */       }
/* 253 */     }, 4800L);
/*     */   }
/*     */   
/*     */   private static void abrirInfo(Player p, Player reportado) {
/* 257 */     Inventory inv = Bukkit.createInventory(p, 27, "Denúncias - " + reportado.getName());
/*     */     
/* 259 */     ItemStack negar = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
/* 260 */     ItemMeta negar2 = negar.getItemMeta();
/* 261 */     negar2.setDisplayName("§cRecusar Denúncia(s)");
/* 262 */     List<String> lore = new ArrayList();
/* 263 */     lore.add("§7Clique para recusar á(s) denúncia(s)!");
/* 264 */     negar2.setLore(lore);
/* 265 */     negar.setItemMeta(negar2);
/* 266 */     inv.setItem(11, negar);
/*     */     
/* 268 */     ItemStack info = new ItemStack(Material.ENCHANTED_BOOK);
/* 269 */     ItemMeta info2 = info.getItemMeta();
/* 270 */     info2.setDisplayName("§aStatus da(s) Denúncia(s)");
/* 271 */     lore.clear();
/* 272 */     lore.add("§7Total de Denúncias: " + MSManager.getAllDenuncia(reportado.getUniqueId().toString()));
/* 273 */     lore.add("§1");
/* 274 */     lore.add("§7Denúncias:");
/* 275 */     lore.add("§7 ForceField: §c" + MSManager.getDenuncia(reportado.getUniqueId().toString(), "FF"));
/* 276 */     lore.add("§7 Fly: §c" + MSManager.getDenuncia(reportado.getUniqueId().toString(), "Fly"));
/* 277 */     lore.add("§7 Ofensa: §c" + MSManager.getDenuncia(reportado.getUniqueId().toString(), "Ofensa"));
/* 278 */     lore.add("§7 Macro: §c" + MSManager.getDenuncia(reportado.getUniqueId().toString(), "Macro"));
/* 279 */     lore.add("§7 Speed: §c" + MSManager.getDenuncia(reportado.getUniqueId().toString(), "Speed"));
/* 280 */     lore.add("§2");
/* 281 */     lore.add("§aClique para se teletransportar até o jogador!");
/* 282 */     info2.setLore(lore);
/* 283 */     info.setItemMeta(info2);
/* 284 */     inv.setItem(13, info);
/*     */     
/* 286 */     ItemStack aceitar = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
/* 287 */     ItemMeta aceitar2 = aceitar.getItemMeta();
/* 288 */     aceitar2.setDisplayName("§aAceitar Denúncia(s)");
/* 289 */     lore.clear();
/* 290 */     lore.add("§7Clique para aceitar á(s) denúncia(s)!");
/* 291 */     aceitar2.setLore(lore);
/* 292 */     aceitar.setItemMeta(aceitar2);
/* 293 */     inv.setItem(15, aceitar);
/*     */     
/* 295 */     p.openInventory(inv);
/*     */   }
/*     */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\Comandos\Reportar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */