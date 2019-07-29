/*     */ package me.devleo.czpvpessentials.Comandos;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.devleo.czpvpessentials.Main;
/*     */ import me.devleo.czpvpessentials.Manager;
/*     */ import net.milkbowl.vault.economy.Economy;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ 
/*     */ public class CzSpawners implements CommandExecutor, org.bukkit.event.Listener
/*     */ {
/*     */   @EventHandler
/*     */   private void clicarMenu(InventoryClickEvent e)
/*     */   {
/*  27 */     if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)) {
/*  28 */       Inventory inv = e.getInventory();
/*  29 */       Player p = (Player)e.getWhoClicked();
/*  30 */       if (inv.getTitle().equals("Loja - Spawners")) {
/*  31 */         p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 5.0F, 5.0F);
/*  32 */         e.setCancelled(true);
/*  33 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cVoltar")) {
/*  34 */           p.closeInventory();
/*  35 */           p.chat("/lojas");
/*     */         }
/*  37 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eSpawner de Porco")) {
/*  38 */           if (p.hasPermission("spawner.vip")) {
/*  39 */             if (Main.money.getBalance(p.getName()) < 75000.0D) {
/*  40 */               p.closeInventory();
/*  41 */               p.sendMessage("§cVocê não possui coins suficiente!");
/*  42 */               return;
/*     */             }
/*  44 */             Main.money.withdrawPlayer(p.getName(), 75000.0D);
/*  45 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " pig 1");
/*  46 */             p.sendMessage("§aSpawner comprado com sucesso!");
/*  47 */             return;
/*     */           }
/*  49 */           if (p.hasPermission("spawner.vip2")) {
/*  50 */             if (Main.money.getBalance(p.getName()) < 50000.0D) {
/*  51 */               p.closeInventory();
/*  52 */               p.sendMessage("§cVocê não possui coins suficiente!");
/*  53 */               return;
/*     */             }
/*  55 */             Main.money.withdrawPlayer(p.getName(), 50000.0D);
/*  56 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " pig 1");
/*  57 */             p.sendMessage("§aSpawner comprado com sucesso!");
/*  58 */             return;
/*     */           }
/*  60 */           if (Main.money.getBalance(p.getName()) < 100000.0D) {
/*  61 */             p.closeInventory();
/*  62 */             p.sendMessage("§cVocê não possui coins suficiente!");
/*  63 */             return;
/*     */           }
/*  65 */           Main.money.withdrawPlayer(p.getName(), 100000.0D);
/*  66 */           Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " pig 1");
/*  67 */           p.sendMessage("§aSpawner comprado com sucesso!");
/*  68 */           return;
/*     */         }
/*  70 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eSpawner de Vaca")) {
/*  71 */           if (p.hasPermission("spawner.vip")) {
/*  72 */             if (Main.money.getBalance(p.getName()) < 225000.0D) {
/*  73 */               p.closeInventory();
/*  74 */               p.sendMessage("§cVocê não possui coins suficiente!");
/*  75 */               return;
/*     */             }
/*  77 */             Main.money.withdrawPlayer(p.getName(), 225000.0D);
/*  78 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " cow 1");
/*  79 */             p.sendMessage("§aSpawner comprado com sucesso!");
/*  80 */             return;
/*     */           }
/*  82 */           if (p.hasPermission("spawner.vip2")) {
/*  83 */             if (Main.money.getBalance(p.getName()) < 125000.0D) {
/*  84 */               p.closeInventory();
/*  85 */               p.sendMessage("§cVocê não possui coins suficiente!");
/*  86 */               return;
/*     */             }
/*  88 */             Main.money.withdrawPlayer(p.getName(), 125000.0D);
/*  89 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " cow 1");
/*  90 */             p.sendMessage("§aSpawner comprado com sucesso!");
/*  91 */             return;
/*     */           }
/*  93 */           if (Main.money.getBalance(p.getName()) < 250000.0D) {
/*  94 */             p.closeInventory();
/*  95 */             p.sendMessage("§cVocê não possui coins suficiente!");
/*  96 */             return;
/*     */           }
/*  98 */           Main.money.withdrawPlayer(p.getName(), 250000.0D);
/*  99 */           Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " cow 1");
/* 100 */           p.sendMessage("§aSpawner comprado com sucesso!");
/* 101 */           return;
/*     */         }
/* 103 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eSpawner de Zombie")) {
/* 104 */           if (p.hasPermission("spawner.vip")) {
/* 105 */             if (Main.money.getBalance(p.getName()) < 275000.0D) {
/* 106 */               p.closeInventory();
/* 107 */               p.sendMessage("§cVocê não possui coins suficiente!");
/* 108 */               return;
/*     */             }
/* 110 */             Main.money.withdrawPlayer(p.getName(), 275000.0D);
/* 111 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " zombie 1");
/* 112 */             p.sendMessage("§aSpawner comprado com sucesso!");
/* 113 */             return;
/*     */           }
/* 115 */           if (p.hasPermission("spawner.vip2")) {
/* 116 */             if (Main.money.getBalance(p.getName()) < 150000.0D) {
/* 117 */               p.closeInventory();
/* 118 */               p.sendMessage("§cVocê não possui coins suficiente!");
/* 119 */               return;
/*     */             }
/* 121 */             Main.money.withdrawPlayer(p.getName(), 150000.0D);
/* 122 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " zombie 1");
/* 123 */             p.sendMessage("§aSpawner comprado com sucesso!");
/* 124 */             return;
/*     */           }
/* 126 */           if (Main.money.getBalance(p.getName()) < 300000.0D) {
/* 127 */             p.closeInventory();
/* 128 */             p.sendMessage("§cVocê não possui coins suficiente!");
/* 129 */             return;
/*     */           }
/* 131 */           Main.money.withdrawPlayer(p.getName(), 300000.0D);
/* 132 */           Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " zombie 1");
/* 133 */           p.sendMessage("§aSpawner comprado com sucesso!");
/* 134 */           return;
/*     */         }
/* 136 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eSpawner de Aranha")) {
/* 137 */           if (p.hasPermission("spawner.vip")) {
/* 138 */             if (Main.money.getBalance(p.getName()) < 475000.0D) {
/* 139 */               p.closeInventory();
/* 140 */               p.sendMessage("§cVocê não possui coins suficiente!");
/* 141 */               return;
/*     */             }
/* 143 */             Main.money.withdrawPlayer(p.getName(), 475000.0D);
/* 144 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " spider 1");
/* 145 */             p.sendMessage("§aSpawner comprado com sucesso!");
/* 146 */             return;
/*     */           }
/* 148 */           if (p.hasPermission("spawner.vip2")) {
/* 149 */             if (Main.money.getBalance(p.getName()) < 300000.0D) {
/* 150 */               p.closeInventory();
/* 151 */               p.sendMessage("§cVocê não possui coins suficiente!");
/* 152 */               return;
/*     */             }
/* 154 */             Main.money.withdrawPlayer(p.getName(), 300000.0D);
/* 155 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " spider 1");
/* 156 */             p.sendMessage("§aSpawner comprado com sucesso!");
/* 157 */             return;
/*     */           }
/* 159 */           if (Main.money.getBalance(p.getName()) < 500000.0D) {
/* 160 */             p.closeInventory();
/* 161 */             p.sendMessage("§cVocê não possui coins suficiente!");
/* 162 */             return;
/*     */           }
/* 164 */           Main.money.withdrawPlayer(p.getName(), 500000.0D);
/* 165 */           Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " spider 1");
/* 166 */           p.sendMessage("§aSpawner comprado com sucesso!");
/* 167 */           return;
/*     */         }
/* 169 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eSpawner de Creeper")) {
/* 170 */           if (p.hasPermission("spawner.vip")) {
/* 171 */             if (Main.money.getBalance(p.getName()) < 750000.0D) {
/* 172 */               p.closeInventory();
/* 173 */               p.sendMessage("§cVocê não possui coins suficiente!");
/* 174 */               return;
/*     */             }
/* 176 */             Main.money.withdrawPlayer(p.getName(), 750000.0D);
/* 177 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " creeper 1");
/* 178 */             p.sendMessage("§aSpawner comprado com sucesso!");
/* 179 */             return;
/*     */           }
/* 181 */           if (p.hasPermission("spawner.vip2")) {
/* 182 */             if (Main.money.getBalance(p.getName()) < 400000.0D) {
/* 183 */               p.closeInventory();
/* 184 */               p.sendMessage("§cVocê não possui coins suficiente!");
/* 185 */               return;
/*     */             }
/* 187 */             Main.money.withdrawPlayer(p.getName(), 400000.0D);
/* 188 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " creeper 1");
/* 189 */             p.sendMessage("§aSpawner comprado com sucesso!");
/* 190 */             return;
/*     */           }
/* 192 */           if (Main.money.getBalance(p.getName()) < 800000.0D) {
/* 193 */             p.closeInventory();
/* 194 */             p.sendMessage("§cVocê não possui coins suficiente!");
/* 195 */             return;
/*     */           }
/* 197 */           Main.money.withdrawPlayer(p.getName(), 800000.0D);
/* 198 */           Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " creeper 1");
/* 199 */           p.sendMessage("§aSpawner comprado com sucesso!");
/* 200 */           return;
/*     */         }
/* 202 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eSpawner de Esqueleto")) {
/* 203 */           if (p.hasPermission("spawner.vip")) {
/* 204 */             if (Main.money.getBalance(p.getName()) < 975000.0D) {
/* 205 */               p.closeInventory();
/* 206 */               p.sendMessage("§cVocê não possui coins suficiente!");
/* 207 */               return;
/*     */             }
/* 209 */             Main.money.withdrawPlayer(p.getName(), 975000.0D);
/* 210 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " skeleton 1");
/* 211 */             p.sendMessage("§aSpawner comprado com sucesso!");
/* 212 */             return;
/*     */           }
/* 214 */           if (p.hasPermission("spawner.vip2")) {
/* 215 */             if (Main.money.getBalance(p.getName()) < 600000.0D) {
/* 216 */               p.closeInventory();
/* 217 */               p.sendMessage("§cVocê não possui coins suficiente!");
/* 218 */               return;
/*     */             }
/* 220 */             Main.money.withdrawPlayer(p.getName(), 600000.0D);
/* 221 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " skeleton 1");
/* 222 */             p.sendMessage("§aSpawner comprado com sucesso!");
/* 223 */             return;
/*     */           }
/* 225 */           if (Main.money.getBalance(p.getName()) < 1000000.0D) {
/* 226 */             p.closeInventory();
/* 227 */             p.sendMessage("§cVocê não possui coins suficiente!");
/* 228 */             return;
/*     */           }
/* 230 */           Main.money.withdrawPlayer(p.getName(), 1000000.0D);
/* 231 */           Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " skeleton 1");
/* 232 */           p.sendMessage("§aSpawner comprado com sucesso!");
/* 233 */           return;
/*     */         }
/* 235 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eSpawner de Blaze")) {
/* 236 */           if (p.hasPermission("spawner.vip")) {
/* 237 */             if (Main.money.getBalance(p.getName()) < 4500000.0D) {
/* 238 */               p.closeInventory();
/* 239 */               p.sendMessage("§cVocê não possui coins suficiente!");
/* 240 */               return;
/*     */             }
/* 242 */             Main.money.withdrawPlayer(p.getName(), 4500000.0D);
/* 243 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " blaze 1");
/* 244 */             p.sendMessage("§aSpawner comprado com sucesso!");
/* 245 */             return;
/*     */           }
/* 247 */           if (p.hasPermission("spawner.vip2")) {
/* 248 */             if (Main.money.getBalance(p.getName()) < 1500000.0D) {
/* 249 */               p.closeInventory();
/* 250 */               p.sendMessage("§cVocê não possui coins suficiente!");
/* 251 */               return;
/*     */             }
/* 253 */             Main.money.withdrawPlayer(p.getName(), 1500000.0D);
/* 254 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " blaze 1");
/* 255 */             p.sendMessage("§aSpawner comprado com sucesso!");
/* 256 */             return;
/*     */           }
/* 258 */           if (Main.money.getBalance(p.getName()) < 2500000.0D) {
/* 259 */             p.closeInventory();
/* 260 */             p.sendMessage("§cVocê não possui coins suficiente!");
/* 261 */             return;
/*     */           }
/* 263 */           Main.money.withdrawPlayer(p.getName(), 2500000.0D);
/* 264 */           Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " blaze 1");
/* 265 */           p.sendMessage("§aSpawner comprado com sucesso!");
/* 266 */           return;
/*     */         }
/* 268 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eSpawner de PigZombie")) {
/* 269 */           if (p.hasPermission("spawner.vip")) {
/* 270 */             if (Main.money.getBalance(p.getName()) < 4500000.0D) {
/* 271 */               p.closeInventory();
/* 272 */               p.sendMessage("§cVocê não possui coins suficiente!");
/* 273 */               return;
/*     */             }
/* 275 */             Main.money.withdrawPlayer(p.getName(), 4500000.0D);
/* 276 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " pigzombie 1");
/* 277 */             p.sendMessage("§aSpawner comprado com sucesso!");
/* 278 */             return;
/*     */           }
/* 280 */           if (p.hasPermission("spawner.vip2")) {
/* 281 */             if (Main.money.getBalance(p.getName()) < 3000000.0D) {
/* 282 */               p.closeInventory();
/* 283 */               p.sendMessage("§cVocê não possui coins suficiente!");
/* 284 */               return;
/*     */             }
/* 286 */             Main.money.withdrawPlayer(p.getName(), 3000000.0D);
/* 287 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " pigzombie 1");
/* 288 */             p.sendMessage("§aSpawner comprado com sucesso!");
/* 289 */             return;
/*     */           }
/* 291 */           if (Main.money.getBalance(p.getName()) < 5000000.0D) {
/* 292 */             p.closeInventory();
/* 293 */             p.sendMessage("§cVocê não possui coins suficiente!");
/* 294 */             return;
/*     */           }
/* 296 */           Main.money.withdrawPlayer(p.getName(), 5000000.0D);
/* 297 */           Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " pigzombie 1");
/* 298 */           p.sendMessage("§aSpawner comprado com sucesso!");
/* 299 */           return;
/*     */         }
/* 301 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eSpawner de IronGolem")) {
/* 302 */           if (p.hasPermission("spawner.vip")) {
/* 303 */             if (Main.money.getBalance(p.getName()) < 9000000.0D) {
/* 304 */               p.closeInventory();
/* 305 */               p.sendMessage("§cVocê não possui coins suficiente!");
/* 306 */               return;
/*     */             }
/* 308 */             Main.money.withdrawPlayer(p.getName(), 9000000.0D);
/* 309 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " 99 1");
/* 310 */             p.sendMessage("§aSpawner comprado com sucesso!");
/* 311 */             return;
/*     */           }
/* 313 */           if (p.hasPermission("spawner.vip2")) {
/* 314 */             if (Main.money.getBalance(p.getName()) < 7500000.0D) {
/* 315 */               p.closeInventory();
/* 316 */               p.sendMessage("§cVocê não possui coins suficiente!");
/* 317 */               return;
/*     */             }
/* 319 */             Main.money.withdrawPlayer(p.getName(), 7500000.0D);
/* 320 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " 99 1");
/* 321 */             p.sendMessage("§aSpawner comprado com sucesso!");
/* 322 */             return;
/*     */           }
/* 324 */           if (Main.money.getBalance(p.getName()) < 1.0E7D) {
/* 325 */             p.closeInventory();
/* 326 */             p.sendMessage("§cVocê não possui coins suficiente!");
/* 327 */             return;
/*     */           }
/* 329 */           Main.money.withdrawPlayer(p.getName(), 1.0E7D);
/* 330 */           Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mgive " + p.getName() + " 99 1");
/* 331 */           p.sendMessage("§aSpawner comprado com sucesso!");
/* 332 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
/* 339 */     if (!(sender instanceof Player)) {
/* 340 */       return true;
/*     */     }
/* 342 */     Player p = (Player)sender;
/* 343 */     if (cmd.getName().equalsIgnoreCase("mobs")) {
/* 344 */       Inventory inv = Bukkit.createInventory(p, 36, "Loja - Spawners");
/* 345 */       List<String> lore = new ArrayList();
/*     */       
/* 347 */       lore.add("§7Voltar para menu de lojas.");
/* 348 */       inv.setItem(0, Manager.criarItem(Material.ARROW, "§cVoltar", lore));
/*     */       
/* 350 */       ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
/* 351 */       SkullMeta head2 = (SkullMeta)head.getItemMeta();
/* 352 */       head2.setOwner("MHF_Pig");
/* 353 */       head2.setDisplayName("§eSpawner de Porco");
/* 354 */       lore.clear();
/* 355 */       lore.add(" ");
/* 356 */       lore.add("§7Preço de Compra: §b100.000 Coins");
/* 357 */       lore.add(" ");
/* 358 */       lore.add("§7Preço de Compra para VIP:");
/* 359 */       lore.add("  §7Vip: §b75.000 Coins");
/* 360 */       lore.add("  §7Vip§6++§7: §b50.000 Coins");
/* 361 */       lore.add(" ");
/* 362 */       head2.setLore(lore);
/* 363 */       head.setItemMeta(head2);
/* 364 */       inv.setItem(11, head);
/*     */       
/* 366 */       head2.setOwner("MHF_Cow");
/* 367 */       head2.setDisplayName("§eSpawner de Vaca");
/* 368 */       lore.clear();
/* 369 */       lore.add(" ");
/* 370 */       lore.add("§7Preço de Compra: §b250.000 Coins");
/* 371 */       lore.add(" ");
/* 372 */       lore.add("§7Preço de Compra para VIP:");
/* 373 */       lore.add("  §7Vip: §b225.000 Coins");
/* 374 */       lore.add("  §7Vip§6++§7: §b125.000 Coins");
/* 375 */       lore.add(" ");
/* 376 */       head2.setLore(lore);
/* 377 */       head.setItemMeta(head2);
/* 378 */       inv.setItem(12, head);
/*     */       
/* 380 */       head2.setOwner("Zombie");
/* 381 */       head2.setDisplayName("§eSpawner de Zombie");
/* 382 */       lore.clear();
/* 383 */       lore.add(" ");
/* 384 */       lore.add("§7Preço de Compra: §b300.000 Coins");
/* 385 */       lore.add(" ");
/* 386 */       lore.add("§7Preço de Compra para VIP:");
/* 387 */       lore.add("  §7Vip: §b275.000 Coins");
/* 388 */       lore.add("  §7Vip§6++§7: §b150.000 Coins");
/* 389 */       lore.add(" ");
/* 390 */       head2.setLore(lore);
/* 391 */       head.setItemMeta(head2);
/* 392 */       inv.setItem(13, head);
/*     */       
/* 394 */       head2.setOwner("MHF_Spider");
/* 395 */       head2.setDisplayName("§eSpawner de Aranha");
/* 396 */       lore.clear();
/* 397 */       lore.add(" ");
/* 398 */       lore.add("§7Preço de Compra: §b500.000 Coins");
/* 399 */       lore.add(" ");
/* 400 */       lore.add("§7Preço de Compra para VIP:");
/* 401 */       lore.add("  §7Vip: §b475.000 Coins");
/* 402 */       lore.add("  §7Vip§6++§7: §b300.000 Coins");
/* 403 */       lore.add(" ");
/* 404 */       head2.setLore(lore);
/* 405 */       head.setItemMeta(head2);
/* 406 */       inv.setItem(14, head);
/*     */       
/* 408 */       head2.setOwner("Creeper");
/* 409 */       head2.setDisplayName("§eSpawner de Creeper");
/* 410 */       lore.clear();
/* 411 */       lore.add(" ");
/* 412 */       lore.add("§7Preço de Compra: §b800.000 Coins");
/* 413 */       lore.add(" ");
/* 414 */       lore.add("§7Preço de Compra para VIP:");
/* 415 */       lore.add("  §7Vip: §b750.000 Coins");
/* 416 */       lore.add("  §7Vip§6++§7: §b400.000 Coins");
/* 417 */       lore.add(" ");
/* 418 */       head2.setLore(lore);
/* 419 */       head.setItemMeta(head2);
/* 420 */       inv.setItem(15, head);
/*     */       
/* 422 */       head2.setOwner("Skeleton");
/* 423 */       head2.setDisplayName("§eSpawner de Esqueleto");
/* 424 */       lore.clear();
/* 425 */       lore.add(" ");
/* 426 */       lore.add("§7Preço de Compra: §b1.000.000 Coins");
/* 427 */       lore.add(" ");
/* 428 */       lore.add("§7Preço de Compra para VIP:");
/* 429 */       lore.add("  §7Vip: §b975.000 Coins");
/* 430 */       lore.add("  §7Vip§6++§7: §b600.000 Coins");
/* 431 */       lore.add(" ");
/* 432 */       head2.setLore(lore);
/* 433 */       head.setItemMeta(head2);
/* 434 */       inv.setItem(20, head);
/*     */       
/* 436 */       head2.setOwner("MHF_Blaze");
/* 437 */       head2.setDisplayName("§eSpawner de Blaze");
/* 438 */       lore.clear();
/* 439 */       lore.add(" ");
/* 440 */       lore.add("§7Preço de Compra: §b2.500.000 Coins");
/* 441 */       lore.add(" ");
/* 442 */       lore.add("§7Preço de Compra para VIP:");
/* 443 */       lore.add("  §7Vip: §b2.000.000 Coins");
/* 444 */       lore.add("  §7Vip§6++§7: §b1.500.000 Coins");
/* 445 */       lore.add(" ");
/* 446 */       head2.setLore(lore);
/* 447 */       head.setItemMeta(head2);
/* 448 */       inv.setItem(21, head);
/*     */       
/* 450 */       head2.setOwner("MHF_PigZombie");
/* 451 */       head2.setDisplayName("§eSpawner de PigZombie");
/* 452 */       lore.clear();
/* 453 */       lore.add(" ");
/* 454 */       lore.add("§7Preço de Compra: §b5.000.000 Coins");
/* 455 */       lore.add(" ");
/* 456 */       lore.add("§7Preço de Compra para VIP:");
/* 457 */       lore.add("  §7Vip: §b4.500.000 Coins");
/* 458 */       lore.add("  §7Vip§6++§7: §b3.000.000 Coins");
/* 459 */       lore.add(" ");
/* 460 */       head2.setLore(lore);
/* 461 */       head.setItemMeta(head2);
/* 462 */       inv.setItem(22, head);
/*     */       
/* 464 */       head2.setOwner("MHF_Golem");
/* 465 */       head2.setDisplayName("§eSpawner de IronGolem");
/* 466 */       lore.clear();
/* 467 */       lore.add(" ");
/* 468 */       lore.add("§7Preço de Compra: §b10.000.000 Coins");
/* 469 */       lore.add(" ");
/* 470 */       lore.add("§7Preço de Compra para VIP:");
/* 471 */       lore.add("  §7Vip: §b9.000.000 Coins");
/* 472 */       lore.add("  §7Vip§6++§7: §b7.500.000 Coins");
/* 473 */       lore.add(" ");
/* 474 */       head2.setLore(lore);
/* 475 */       head.setItemMeta(head2);
/* 476 */       inv.setItem(23, head);
/*     */       
/* 478 */       head2.setOwner("Wither");
/* 479 */       head2.setDisplayName("§eSpawner de Wither");
/* 480 */       lore.clear();
/* 481 */       lore.add(" ");
/* 482 */       lore.add("§7Preço de Compra: §cExclusivo VIP");
/* 483 */       lore.add(" ");
/* 484 */       lore.add("§7Preço de Compra para VIP:");
/* 485 */       lore.add("  §7Vip: §b50.000.000 Coins");
/* 486 */       lore.add("  §7Vip§6++§7: §b49.200.000 Coins");
/* 487 */       lore.add(" ");
/* 488 */       head2.setLore(lore);
/* 489 */       head.setItemMeta(head2);
/* 490 */       inv.setItem(24, head);
/*     */       
/* 492 */       p.openInventory(inv);
/*     */     }
/* 494 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\Comandos\CzSpawners.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */