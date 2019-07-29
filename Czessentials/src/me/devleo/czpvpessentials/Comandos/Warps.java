/*     */ package me.devleo.czpvpessentials.Comandos;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.devleo.czpvpessentials.Manager;
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
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ 
/*     */ public class Warps
/*     */   implements CommandExecutor, Listener
/*     */ {
/*     */   @EventHandler
/*     */   private void clicarMenu(InventoryClickEvent e)
/*     */   {
/*  27 */     if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)) {
/*  28 */       Inventory inv = e.getInventory();
/*  29 */       Player p = (Player)e.getWhoClicked();
/*  30 */       if (inv.getTitle().equals("Warps")) {
/*  31 */         p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 5.0F, 5.0F);
/*  32 */         e.setCancelled(true);
/*  33 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eMinas")) {
/*  34 */           minas(p);
/*     */         }
/*  36 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eMina PvP")) {
/*  37 */           p.closeInventory();
/*  38 */           p.chat("/essentials:warp minapvp");
/*     */         }
/*  40 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eArenas")) {
/*  41 */           arenas(p);
/*     */         }
/*  43 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eTerrenos")) {
/*  44 */           p.closeInventory();
/*  45 */           p.chat("/essentials:warp terrenos");
/*     */         }
/*  47 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eLojas")) {
/*  48 */           p.closeInventory();
/*  49 */           p.chat("/lojas");
/*     */         }
/*  51 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eÁrea de Encantamento")) {
/*  52 */           p.closeInventory();
/*  53 */           p.chat("/essentials:warp encantar");
/*     */         }
/*  55 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eCaixas Misteriosas")) {
/*  56 */           p.closeInventory();
/*  57 */           p.chat("/essentials:warp caixas");
/*     */         }
/*  59 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eÁrea §6§lVIP")) {
/*  60 */           vip(p);
/*     */         }
/*     */       }
/*  63 */       if (inv.getTitle().equals("Warps - Minas")) {
/*  64 */         p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 5.0F, 5.0F);
/*  65 */         e.setCancelled(true);
/*  66 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Mina §7Pedra")) {
/*  67 */           p.closeInventory();
/*  68 */           p.chat("/essentials:warp pedra");
/*     */         }
/*  70 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Mina §9Lapís")) {
/*  71 */           p.closeInventory();
/*  72 */           p.chat("/essentials:warp lapis");
/*     */         }
/*  74 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Mina §6Ouro")) {
/*  75 */           p.closeInventory();
/*  76 */           p.chat("/essentials:warp ouro");
/*     */         }
/*  78 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Mina §fFerro")) {
/*  79 */           p.closeInventory();
/*  80 */           p.chat("/essentials:warp ferro");
/*     */         }
/*  82 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Mina §3Platina")) {
/*  83 */           p.closeInventory();
/*  84 */           p.chat("/essentials:warp platina");
/*     */         }
/*  86 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Mina §fCristal")) {
/*  87 */           p.closeInventory();
/*  88 */           p.chat("/essentials:warp cristal");
/*     */         }
/*  90 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Mina §aEsmeralda")) {
/*  91 */           p.closeInventory();
/*  92 */           p.chat("/essentials:warp esmeralda");
/*     */         }
/*  94 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Mina §5Ametista")) {
/*  95 */           p.closeInventory();
/*  96 */           p.chat("/essentials:warp ametista");
/*     */         }
/*  98 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Mina §bSafira")) {
/*  99 */           p.closeInventory();
/* 100 */           p.chat("/essentials:warp safira");
/*     */         }
/* 102 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Mina §cRubi")) {
/* 103 */           p.closeInventory();
/* 104 */           p.chat("/essentials:warp rubi");
/*     */         }
/* 106 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cVoltar")) {
/* 107 */           p.closeInventory();
/* 108 */           p.chat("/warps");
/*     */         }
/*     */       }
/* 111 */       if (inv.getTitle().equals("Warps - Arenas")) {
/* 112 */         p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 5.0F, 5.0F);
/* 113 */         e.setCancelled(true);
/* 114 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eArena Floresta")) {
/* 115 */           p.closeInventory();
/* 116 */           p.chat("/essentials:warp arena");
/*     */         }
/* 118 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eArena Industrial")) {
/* 119 */           p.closeInventory();
/* 120 */           p.chat("/essentials:warp industrialarena");
/*     */         }
/* 122 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eArena Nether")) {
/* 123 */           p.closeInventory();
/* 124 */           p.chat("/essentials:warp netherarena");
/*     */         }
/* 126 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eArena FPS")) {
/* 127 */           p.closeInventory();
/* 128 */           p.chat("/essentials:warp fps");
/*     */         }
/* 130 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eArena 1v1")) {
/* 131 */           p.closeInventory();
/* 132 */           p.chat("/essentials:warp x1camarote");
/*     */         }
/* 134 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cVoltar")) {
/* 135 */           p.closeInventory();
/* 136 */           p.chat("/warps");
/*     */         }
/*     */       }
/* 139 */       if (inv.getTitle().equals("Warps - VIPs")) {
/* 140 */         p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 5.0F, 5.0F);
/* 141 */         e.setCancelled(true);
/* 142 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aMina §a§lVIP")) {
/* 143 */           p.closeInventory();
/* 144 */           p.chat("/essentials:warp minavip");
/*     */         }
/* 146 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aMina §5[VIP§6++§5]")) {
/* 147 */           p.closeInventory();
/* 148 */           p.chat("/essentials:warp minavip3");
/*     */         }
/* 150 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cVoltar")) {
/* 151 */           p.closeInventory();
/* 152 */           p.chat("/warps");
/*     */         }
/*     */       }
/* 155 */       if (inv.getTitle().equals("Warps - Lojas")) {
/* 156 */         p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 5.0F, 5.0F);
/* 157 */         e.setCancelled(true);
/* 158 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLoja")) {
/* 159 */           p.closeInventory();
/* 160 */           p.chat("/loja");
/*     */         }
/* 162 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLoja de Spawners")) {
/* 163 */           p.closeInventory();
/* 164 */           p.chat("/mobs");
/*     */         }
/* 166 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLoja de Players")) {
/* 167 */           p.closeInventory();
/* 168 */           Loja.verLojas(p);
/*     */         }
/* 170 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aMercado")) {
/* 171 */           p.closeInventory();
/* 172 */           p.chat("/mercado ver");
/*     */         }
/* 174 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cVoltar")) {
/* 175 */           p.closeInventory();
/* 176 */           p.chat("/warps");
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
/* 183 */     if (!(sender instanceof Player)) {
/* 184 */       return true;
/*     */     }
/* 186 */     Player p = (Player)sender;
/* 187 */     if (cmd.getName().equalsIgnoreCase("minas")) {
/* 188 */       minas(p);
/*     */     }
/* 190 */     if (cmd.getName().equalsIgnoreCase("arenas")) {
/* 191 */       arenas(p);
/*     */     }
/* 193 */     if (cmd.getName().equalsIgnoreCase("lojas")) {
/* 194 */       lojas(p);
/*     */     }
/* 196 */     if (cmd.getName().equalsIgnoreCase("warps")) {
/* 197 */       Inventory inv = Bukkit.createInventory(p, 36, "Warps");
/* 198 */       List<String> lore = new ArrayList();
/*     */       
/* 200 */       ItemStack mina = new ItemStack(Material.IRON_PICKAXE);
/* 201 */       ItemMeta mina2 = mina.getItemMeta();
/* 202 */       mina2.setDisplayName("§eMinas");
/* 203 */       lore.add("§8/minas");
/* 204 */       lore.add(" ");
/* 205 */       lore.add("§7Clique para ver todas as áreas de mineração!");
/* 206 */       mina2.setLore(lore);
/* 207 */       mina.setItemMeta(mina2);
/* 208 */       inv.setItem(11, mina);
/*     */       
/* 210 */       ItemStack minapvp = new ItemStack(Material.DIAMOND_PICKAXE);
/* 211 */       ItemMeta minapvp2 = minapvp.getItemMeta();
/* 212 */       minapvp2.setDisplayName("§eMina PvP");
/* 213 */       lore.clear();
/* 214 */       lore.add("§8/warp minapvp");
/* 215 */       lore.add(" ");
/* 216 */       lore.add("§7Clique para teleportar á uma");
/* 217 */       lore.add("§7área de mineração com o §4§lPvP§7 ativado!");
/* 218 */       minapvp2.setLore(lore);
/* 219 */       minapvp.setItemMeta(minapvp2);
/* 220 */       inv.setItem(12, minapvp);
/*     */       
/* 222 */       ItemStack arenas = new ItemStack(Material.IRON_CHESTPLATE);
/* 223 */       ItemMeta arenas2 = arenas.getItemMeta();
/* 224 */       arenas2.setDisplayName("§eArenas");
/* 225 */       lore.clear();
/* 226 */       lore.add("§8/arenas");
/* 227 */       lore.add(" ");
/* 228 */       lore.add("§7Clique para ver todas as arenas!");
/* 229 */       arenas2.setLore(lore);
/* 230 */       arenas.setItemMeta(arenas2);
/* 231 */       inv.setItem(13, arenas);
/*     */       
/* 233 */       ItemStack terrenos = new ItemStack(Material.GRASS);
/* 234 */       ItemMeta terrenos2 = terrenos.getItemMeta();
/* 235 */       terrenos2.setDisplayName("§eTerrenos");
/* 236 */       lore.clear();
/* 237 */       lore.add("§8/warp terrenos");
/* 238 */       lore.add(" ");
/* 239 */       lore.add("§7Nesta warp você pode comprar seu terreno");
/* 240 */       lore.add("§7e criar construções magnificas!");
/* 241 */       terrenos2.setLore(lore);
/* 242 */       terrenos.setItemMeta(terrenos2);
/* 243 */       inv.setItem(14, terrenos);
/*     */       
/* 245 */       ItemStack loja = new ItemStack(Material.EMERALD);
/* 246 */       ItemMeta loja2 = loja.getItemMeta();
/* 247 */       loja2.setDisplayName("§eLojas");
/* 248 */       lore.clear();
/* 249 */       lore.add("§8/lojas");
/* 250 */       lore.add(" ");
/* 251 */       lore.add("§7Clique para ver todas as lojas do servidor!");
/* 252 */       loja2.setLore(lore);
/* 253 */       loja.setItemMeta(loja2);
/* 254 */       inv.setItem(15, loja);
/*     */       
/* 256 */       ItemStack encantar = new ItemStack(Material.ENCHANTMENT_TABLE);
/* 257 */       ItemMeta encantar2 = encantar.getItemMeta();
/* 258 */       encantar2.setDisplayName("§eÁrea de Encantamento");
/* 259 */       lore.clear();
/* 260 */       lore.add("§8/warp encantar");
/* 261 */       lore.add(" ");
/* 262 */       lore.add("§7Aqui você pode encantar seus itens!");
/* 263 */       encantar2.setLore(lore);
/* 264 */       encantar.setItemMeta(encantar2);
/* 265 */       inv.setItem(21, encantar);
/*     */       
/* 267 */       ItemStack caixas = new ItemStack(Material.CHEST);
/* 268 */       ItemMeta caixas2 = caixas.getItemMeta();
/* 269 */       caixas2.setDisplayName("§eCaixas Misteriosas");
/* 270 */       lore.clear();
/* 271 */       lore.add("§8/warp caixas");
/* 272 */       lore.add(" ");
/* 273 */       lore.add("§7Aqui você encontra caixas misteriosas!");
/* 274 */       caixas2.setLore(lore);
/* 275 */       caixas.setItemMeta(caixas2);
/* 276 */       inv.setItem(22, caixas);
/*     */       
/* 278 */       ItemStack vip = new ItemStack(Material.DOUBLE_PLANT);
/* 279 */       ItemMeta vip2 = vip.getItemMeta();
/* 280 */       vip2.setDisplayName("§eÁrea §6§lVIP");
/* 281 */       lore.clear();
/* 282 */       lore.add("§8/vip");
/* 283 */       lore.add(" ");
/* 284 */       lore.add("§7Área disponível apenas para jogadores VIPS.");
/* 285 */       vip2.setLore(lore);
/* 286 */       vip.setItemMeta(vip2);
/* 287 */       inv.setItem(23, vip);
/*     */       
/* 289 */       p.openInventory(inv);
/*     */     }
/* 291 */     return false;
/*     */   }
/*     */   
/*     */   private static void minas(Player p) {
/* 295 */     Inventory inv = Bukkit.createInventory(p, 36, "Warps - Minas");
/* 296 */     List<String> lore = new ArrayList();
/*     */     
/* 298 */     lore.add("§7Voltar para menu de warps.");
/* 299 */     inv.setItem(0, Manager.criarItem(Material.ARROW, "§cVoltar", lore));
/*     */     
/* 301 */     inv.setItem(11, Manager.criarItem(Material.STONE, "§7Mina §7Pedra", "§7Clique para ir"));
/* 302 */     inv.setItem(12, Manager.criarItem(Material.LAPIS_ORE, "§7Mina §9Lapís", "§7Clique para ir"));
/* 303 */     inv.setItem(13, Manager.criarItem(Material.GOLD_ORE, "§7Mina §6Ouro", "§7Clique para ir"));
/* 304 */     inv.setItem(14, Manager.criarItem(Material.IRON_ORE, "§7Mina §fFerro", "§7Clique para ir"));
/* 305 */     inv.setItem(15, Manager.criarItem(Material.PRISMARINE, "§7Mina §3Platina", "§7Clique para ir"));
/* 306 */     inv.setItem(20, Manager.criarItem(Material.QUARTZ_ORE, "§7Mina §fCristal", "§7Clique para ir"));
/* 307 */     inv.setItem(21, Manager.criarItem(Material.EMERALD_ORE, "§7Mina §aEsmeralda", "§7Clique para ir"));
/* 308 */     inv.setItem(22, Manager.criarItem(Material.OBSIDIAN, "§7Mina §5Ametista", "§7Clique para ir"));
/* 309 */     inv.setItem(23, Manager.criarItem(Material.DIAMOND_ORE, "§7Mina §bSafira", "§7Clique para ir"));
/* 310 */     inv.setItem(24, Manager.criarItem(Material.DIAMOND_BLOCK, "§7Mina §cRubi", "§7Clique para ir"));
/*     */     
/* 312 */     p.openInventory(inv);
/*     */   }
/*     */   
/*     */   private static void arenas(Player p) {
/* 316 */     Inventory inv = Bukkit.createInventory(p, 36, "Warps - Arenas");
/* 317 */     List<String> lore = new ArrayList();
/*     */     
/* 319 */     lore.add("§7Voltar para menu de warps.");
/* 320 */     inv.setItem(0, Manager.criarItem(Material.ARROW, "§cVoltar", lore));
/*     */     
/* 322 */     lore.clear();
/* 323 */     lore.add("§7Batalhe em uma arena florestal.");
/* 324 */     inv.setItem(11, Manager.criarItem(Material.DIRT, "§eArena Floresta", lore));
/* 325 */     inv.getItem(11).setDurability((short)2);
/*     */     
/* 327 */     lore.clear();
/* 328 */     lore.add("§7Batalhe em uma arena industrial.");
/* 329 */     inv.setItem(12, Manager.criarItem(Material.FURNACE, "§eArena Industrial", lore));
/*     */     
/* 331 */     lore.clear();
/* 332 */     lore.add("§7Batalhe em uma arena nether.");
/* 333 */     inv.setItem(14, Manager.criarItem(Material.NETHER_BRICK, "§eArena Nether", lore));
/*     */     
/* 335 */     lore.clear();
/* 336 */     lore.add("§7Batalhe em uma arena focada no alto FPS.");
/* 337 */     inv.setItem(15, Manager.criarItem(Material.GLASS, "§eArena FPS", lore));
/*     */     
/* 339 */     lore.clear();
/* 340 */     lore.add("§7Local ideal para tirar x1!");
/* 341 */     inv.setItem(22, Manager.criarItem(Material.BLAZE_ROD, "§eArena 1v1", lore));
/*     */     
/* 343 */     p.openInventory(inv);
/*     */   }
/*     */   
/*     */   private static void vip(Player p) {
/* 347 */     Inventory inv = Bukkit.createInventory(p, 27, "Warps - VIPs");
/* 348 */     List<String> lore = new ArrayList();
/*     */     
/* 350 */     lore.add("§7Voltar para menu de warps.");
/* 351 */     inv.setItem(0, Manager.criarItem(Material.ARROW, "§cVoltar", lore));
/*     */     
/* 353 */     lore.clear();
/* 354 */     lore.add("§7Minere e ganhe MUITO dinheiro!");
/* 355 */     lore.add("§7Exclusiva para §a[VIP] §7e §b[VIP§6+§b]");
/* 356 */     inv.setItem(12, Manager.criarItem(Material.EMERALD_ORE, "§aMina §a§lVIP", lore));
/*     */     
/* 358 */     lore.clear();
/* 359 */     lore.add("§7Minere e ganhe MUITO dinheiro!");
/* 360 */     lore.add("§7Exclusiva para §5[VIP§6++§5]");
/* 361 */     inv.setItem(14, Manager.criarItem(Material.DIAMOND_BLOCK, "§aMina §5[VIP§6++§5]", lore));
/*     */     
/* 363 */     p.openInventory(inv);
/*     */   }
/*     */   
/*     */   private static void lojas(Player p) {
/* 367 */     Inventory inv = Bukkit.createInventory(p, 27, "Warps - Lojas");
/* 368 */     List<String> lore = new ArrayList();
/*     */     
/* 370 */     lore.add("§7Voltar para menu de warps.");
/* 371 */     inv.setItem(0, Manager.criarItem(Material.ARROW, "§cVoltar", lore));
/*     */     
/* 373 */     lore.clear();
/* 374 */     lore.add("§7Compre e venda diversos itens!");
/* 375 */     inv.setItem(11, Manager.criarItem(Material.EMERALD, "§aLoja", lore));
/*     */     
/* 377 */     lore.clear();
/* 378 */     lore.add("§7Aqui você pode comprar MobSpawners!");
/* 379 */     inv.setItem(12, Manager.criarItem(Material.MOB_SPAWNER, "§aLoja de Spawners", lore));
/*     */     
/* 381 */     lore.clear();
/* 382 */     lore.add("§7Clique para ver todas as lojas");
/* 383 */     lore.add("§7de jogadores §a§lVIPs §7disponíveis!");
/* 384 */     lore.add(" ");
/* 385 */     lore.add("§7Utilize §b/irloja [jogador] §7para ir mais rápido!");
/* 386 */     ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
/* 387 */     SkullMeta head2 = (SkullMeta)head.getItemMeta();
/* 388 */     head2.setOwner(p.getName());
/* 389 */     head2.setDisplayName("§aLoja de Players");
/* 390 */     head2.setLore(lore);
/* 391 */     head.setItemMeta(head2);
/* 392 */     inv.setItem(14, head);
/*     */     
/* 394 */     lore.clear();
/* 395 */     lore.add("§7Aqui você pode vender e comprar");
/* 396 */     lore.add("§7itens de outros jogadores!");
/* 397 */     lore.add(" ");
/* 398 */     lore.add("§7Utilize §b/mercado vender §7para vender um item!");
/* 399 */     inv.setItem(15, Manager.criarItem(Material.ENDER_CHEST, "§aMercado", lore));
/*     */     
/* 401 */     p.openInventory(inv);
/*     */   }
/*     */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\Comandos\Warps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */