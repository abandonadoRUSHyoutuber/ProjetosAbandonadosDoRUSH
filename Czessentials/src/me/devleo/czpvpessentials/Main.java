/*     */ package me.devleo.czpvpessentials;
/*     */ 
/*     */ import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
/*     */ import com.sk89q.worldguard.protection.ApplicableRegionSet;
/*     */ import com.sk89q.worldguard.protection.flags.DefaultFlag;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import java.util.logging.Logger;
/*     */ import me.devleo.czpvpessentials.Comandos.Changelog;
/*     */ import me.devleo.czpvpessentials.Comandos.Convidado;
/*     */ import me.devleo.czpvpessentials.Comandos.CzSpawners;
/*     */ import me.devleo.czpvpessentials.Comandos.Loja;
/*     */ import me.devleo.czpvpessentials.Comandos.Reportar;
/*     */ import me.devleo.czpvpessentials.Comandos.Requisitos;
/*     */ import me.devleo.czpvpessentials.Comandos.Tickets;
/*     */ import me.devleo.czpvpessentials.Comandos.Toggle;
/*     */ import me.devleo.czpvpessentials.Comandos.Warps;
/*     */ import me.devleo.czpvpessentials.MySQL.EndDB;
/*     */ import me.devleo.czpvpessentials.MySQL.LojaManager;
/*     */ import me.devleo.czpvpessentials.MySQL.MSManager;
/*     */ import me.devleo.czpvpessentials.MySQL.TicketManager;
/*     */ import net.milkbowl.vault.economy.Economy;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.PluginCommand;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.plugin.RegisteredServiceProvider;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ 
/*     */ public class Main extends JavaPlugin
/*     */ {
/*     */   public static Main plugin;
/*  44 */   public static ReentrantLock lock = new ReentrantLock(true);
/*  45 */   public static String SQL_HOST = null;
/*  46 */   public static String SQL_PORT = null;
/*  47 */   public static String SQL_USER = null;
/*  48 */   public static String SQL_PASS = null;
/*  49 */   public static String SQL_DATA = null;
/*  50 */   public static Boolean SQL_DSC = Boolean.valueOf(false);
/*     */   private static Connection con;
/*  52 */   public MSManager mysql = new MSManager(this);
/*  53 */   public TicketManager mysql2 = new TicketManager(this);
/*  54 */   public LojaManager mysql3 = new LojaManager(this);
/*  55 */   public boolean database = false;
/*  56 */   public static Economy money = null;
/*     */   
/*     */   public void onEnable() {
/*  59 */     plugin = this;
/*  60 */     saveDefaultConfig();
/*     */     try {
/*  62 */       this.mysql.iniciarDatabase();
/*  63 */       this.mysql2.iniciarDatabase();
/*  64 */       this.mysql3.iniciarDatabase();
/*     */     } catch (SQLException e) {
/*  66 */       this.database = false;
/*  67 */       getLogger().warning("Nao foi possivel ativar o MySQL.");
/*  68 */       getLogger().warning("Motivo: " + e.getMessage());
/*  69 */       setEnabled(false);
/*  70 */       return;
/*     */     }
/*  72 */     setupEconomy();
/*  73 */     getCommand("anunciar").setExecutor(new me.devleo.czpvpessentials.Comandos.Anunciar());
/*  74 */     getCommand("changelog").setExecutor(new Changelog());
/*  75 */     getServer().getPluginManager().registerEvents(new Convidado(), this);
/*  76 */     getCommand("convidado").setExecutor(new Convidado());
/*  77 */     getCommand("reparar").setExecutor(new me.devleo.czpvpessentials.Comandos.Reparar());
/*  78 */     getServer().getPluginManager().registerEvents(new Reportar(), this);
/*  79 */     getCommand("reportar").setExecutor(new Reportar());
/*  80 */     getCommand("reportes").setExecutor(new Reportar());
/*  81 */     getServer().getPluginManager().registerEvents(new Warps(), this);
/*  82 */     getCommand("minas").setExecutor(new Warps());
/*  83 */     getCommand("lojas").setExecutor(new Warps());
/*  84 */     getCommand("arenas").setExecutor(new Warps());
/*  85 */     getCommand("warps").setExecutor(new Warps());
/*  86 */     getServer().getPluginManager().registerEvents(new Requisitos(), this);
/*  87 */     getCommand("requisitos").setExecutor(new Requisitos());
/*  88 */     getServer().getPluginManager().registerEvents(new Tickets(), this);
/*  89 */     getCommand("ticket").setExecutor(new Tickets());
/*  90 */     getCommand("tickets").setExecutor(new Tickets());
/*  91 */     getServer().getPluginManager().registerEvents(new Loja(), this);
/*  92 */     getCommand("irloja").setExecutor(new Loja());
/*  93 */     getCommand("setloja").setExecutor(new Loja());
/*  94 */     getCommand("delloja").setExecutor(new Loja());
/*  95 */     getServer().getPluginManager().registerEvents(new Toggle(), this);
/*  96 */     getCommand("toggle").setExecutor(new Toggle());
/*  97 */     getServer().getPluginManager().registerEvents(new CzSpawners(), this);
/*  98 */     getCommand("mobs").setExecutor(new CzSpawners());
/*  99 */     getServer().getPluginManager().registerEvents(new CombatLog(), this);
/* 100 */     getServer().getPluginManager().registerEvents(new Eventos(), this);
/* 101 */     gerarMsgs();
/* 102 */     Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather clear");
/* 103 */     getLogger().info("Plugin ativado");
/* 104 */     new BukkitRunnable() {
/*     */       public void run() {
/* 106 */         Bukkit.getWorld("Spawn").setTime(0L);
/* 107 */         Bukkit.getWorld("Arenas").setTime(0L);
/* 108 */         Bukkit.getWorld("VoidConstrucoes").setTime(0L);
/*     */       }
/* 110 */     }.runTaskTimer(plugin, 0L, 100L);
/*     */   }
/*     */   
/*     */   public void onDisable() {
/* 114 */     if (this.database) {
/* 115 */       this.mysql.closeDB();
/* 116 */       this.mysql2.closeDB();
/* 117 */       this.mysql3.closeDB();
/*     */     }
/* 119 */     getLogger().info("Plugin desativado");
/*     */   }
/*     */   
/*     */   private boolean setupEconomy() {
/* 123 */     RegisteredServiceProvider<Economy> economyProvider = plugin.getServer().getServicesManager()
/* 124 */       .getRegistration(Economy.class);
/* 125 */     if (economyProvider != null) {
/* 126 */       money = (Economy)economyProvider.getProvider();
/*     */     }
/* 128 */     return money != null;
/*     */   }
/*     */   
/*     */   public static WorldGuardPlugin getWorldGuard() {
/* 132 */     Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
/* 133 */     if ((plugin == null) || (!(plugin instanceof WorldGuardPlugin))) {
/* 134 */       return null;
/*     */     }
/* 136 */     return (WorldGuardPlugin)plugin;
/*     */   }
/*     */   
/*     */   public static boolean areaPvP(Player p) {
/* 140 */     ApplicableRegionSet region = getWorldGuard().getRegionManager(p.getWorld())
/* 141 */       .getApplicableRegions(p.getLocation());
/* 142 */     if (region.allows(DefaultFlag.PVP)) {
/* 143 */       return true;
/*     */     }
/* 145 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void gerarMsgs()
/*     */   {
/* 177 */     new BukkitRunnable()
/*     */     {
/*     */       public void run()
/*     */       {
/* 151 */         List<String> msgs = new ArrayList();
/* 152 */         msgs.add("§b§lVIPs §epodem vender itens mais caros através do comando §b/vender§e!");
/* 153 */         msgs.add("§eAdquira §b§lVIP §eem nosso site: §b§nwww.czpvp.com.br§e.");
/* 154 */         msgs.add("§ePrecisando de suporte in-gaming? Tire suas dúvidas através do comando §b/ajuda§e!");
/* 155 */         msgs.add("§eNossa equipe nunca pedirá seus dados pessoais!");
/* 156 */         msgs.add("§eVocê pode ver os requisitos para youtuber através do comando §b/youtuber§e!");
/* 157 */         msgs.add("§ePrecisando de suporte? Acesse nosso TeamSpeak: §bts.czpvp.com.br");
/* 158 */         msgs.add(
/* 159 */           "§eQuerendo aumentar a renda? Que tal investir em máquinas?! Saiba mais através do comando §b/maquinas§e!");
/* 160 */         msgs.add("§eSe encontrar um jogador suspeito utilize §b/reportar §epara denuncia-lo!");
/* 161 */         msgs.add("§eVocê pode utilizar seu §6CASH §epara comprar diversos itens no §b/shop§e!");
/* 162 */         msgs.add("§eVocê pode comprar MobSpawners através do comando §b/mobs§e!");
/* 163 */         msgs.add("§eFique atento ao CHAT do servidor, eventos podem ocorrer em horários aleátorios!");
/* 164 */         msgs.add("§eO §5VIP§6++ §eestá com desconto de §b30% §enos planos Mensal e Trimestral!");
/* 165 */         msgs.add("§eO §5VIP§6++ §eestá com desconto de §b40% §eno plano ETERNO!");
/* 166 */         msgs.add("§eVocê pode saber de todas as novidades através do comando §b/changelog§e!");
/* 167 */         msgs.add("§eVocê pode vender seus itens através do comando §b/mercado§e!");
/* 168 */         msgs.add("§eVocê pode visitar a loja de um jogador VIP através do comando §b/irloja§e!");
/* 169 */         msgs.add("§eO servidor é reiniciado todos os dias às §b14:30 §epara evitar LAG!");
/* 170 */         msgs.add(
/* 171 */           "§eTrouxe seu amigo para o servidor? Peça para ele usar o comando §b/convidado §ee ganhe recompensas!");
/* 172 */         Random r = new Random();
/* 173 */         r.nextInt(msgs.size());
/* 174 */         String o = (String)msgs.get(new Random().nextInt(msgs.size()));
/* 175 */         Bukkit.broadcastMessage(o);
/*     */       }
/* 177 */     }.runTaskTimer(plugin, 0L, 2000L);
/*     */   }
/*     */   
/*     */   public static synchronized void SQLconnect() {
/*     */     try {
/* 182 */       plugin.getLogger().info("Conectando ao MySQL...");
/* 183 */       Class.forName("com.mysql.jdbc.Driver").newInstance();
/* 184 */       String conn = "jdbc:mysql://" + SQL_HOST + ":" + SQL_PORT + "/" + SQL_DATA;
/* 185 */       con = DriverManager.getConnection(conn, SQL_USER, SQL_PASS);
/*     */     } catch (ClassNotFoundException ex) {
/* 187 */       plugin.getLogger().warning("MySQL Driver nao encontrado!");
/*     */     } catch (SQLException ex) {
/* 189 */       plugin.getLogger().warning("Erro ao tentar conectar ao Mysql!");
/*     */     } catch (Exception ex) {
/* 191 */       plugin.getLogger().warning("Erro desconhecido enquanto tentava conectar ao MySQL.");
/*     */     }
/*     */   }
/*     */   
/*     */   public static void SQLdisconnect() {
/* 196 */     EndDB end = new EndDB(plugin, plugin.getLogger(), con);
/* 197 */     ExecutorService executor = Executors.newCachedThreadPool();
/* 198 */     executor.execute(end);
/* 199 */     executor.shutdown();
/*     */   }
/*     */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */