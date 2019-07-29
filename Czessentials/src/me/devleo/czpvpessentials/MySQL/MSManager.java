/*     */ package me.devleo.czpvpessentials.MySQL;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import me.devleo.czpvpessentials.Main;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class MSManager
/*     */ {
/*     */   private static MySQL db;
/*     */   public Main plugin;
/*     */   
/*     */   public MSManager(Main plugin2)
/*     */   {
/*  18 */     this.plugin = plugin2;
/*     */   }
/*     */   
/*     */   public void iniciarDatabase() throws SQLException {
/*  22 */     db = new MySQL(this.plugin, Main.plugin.getConfig().getString("mysql.host-name"), 
/*  23 */       Main.plugin.getConfig().getString("mysql.porta"), Main.plugin.getConfig().getString("mysql.database"), 
/*  24 */       Main.plugin.getConfig().getString("mysql.usuario"), Main.plugin.getConfig().getString("mysql.senha"));
/*  25 */     db.openConnection();
/*  26 */     Statement statement = db.getConnection().createStatement();
/*  27 */     statement.executeUpdate(
/*  28 */       "CREATE TABLE IF NOT EXISTS `reportes` (`UUID` varchar(255), `FF` int, `Fly` int, `Ofensa` int, `Divulgacao` int, `Macro` int, `Speed` int)");
/*     */     
/*  30 */     this.plugin.database = true;
/*     */   }
/*     */   
/*     */   public static void addReport(Player p, String tipo) {
/*  34 */     if (!db.checkConnection()) {
/*  35 */       db.openConnection();
/*     */     }
/*     */     try {
/*  38 */       Statement s = db.getConnection().createStatement();
/*  39 */       ResultSet rs = s.executeQuery("SELECT * FROM reportes WHERE `UUID`='" + p.getUniqueId() + "';");
/*  40 */       if (rs.next()) {
/*  41 */         int novo = rs.getInt(tipo) + 1;
/*  42 */         s.executeUpdate(
/*  43 */           "UPDATE reportes SET `" + tipo + "`=" + novo + " WHERE `UUID`='" + p.getUniqueId() + "';");
/*  44 */         if (getAllDenuncia(p.getUniqueId().toString()) > 15) {
/*  45 */           org.bukkit.Bukkit.dispatchCommand(org.bukkit.Bukkit.getConsoleSender(), 
/*  46 */             "tempban " + p.getName() + " 45 m Excesso de Denúncias");
/*     */         }
/*  48 */         return;
/*     */       }
/*  50 */       s.executeUpdate(
/*  51 */         "INSERT INTO reportes (`UUID`, `FF`, `Fly`, `Ofensa`, `Divulgacao`, `Macro`, `Speed`) VALUES ('" + 
/*  52 */         p.getUniqueId() + "', '0', '0', '0', '0', '0', '0');");
/*  53 */       s.executeUpdate("UPDATE reportes SET `" + tipo + "`=1 WHERE `UUID`='" + p.getUniqueId() + "';");
/*     */     } catch (SQLException e) {
/*  55 */       Main.plugin.getLogger().severe("Nao foi possivel salvar a denuncia do jogador: " + p.getName());
/*     */     }
/*     */   }
/*     */   
/*     */   public static void removerDenuncia(String jogador) {
/*  60 */     if (!db.checkConnection()) {
/*  61 */       db.openConnection();
/*     */     }
/*     */     try {
/*  64 */       Statement s = db.getConnection().createStatement();
/*  65 */       ResultSet rs = s.executeQuery("SELECT * FROM reportes WHERE `UUID`='" + jogador + "';");
/*  66 */       if (!rs.next()) {
/*  67 */         return;
/*     */       }
/*  69 */       s.executeUpdate("DELETE FROM reportes WHERE `UUID`='" + jogador + "';");
/*     */     } catch (SQLException e) {
/*  71 */       Main.plugin.getLogger().severe("Nao foi possivel deletar a denuncia do jogador: " + jogador);
/*     */     }
/*     */   }
/*     */   
/*     */   public static int getDenuncia(String jogador, String tipo) {
/*  76 */     if (!db.checkConnection()) {
/*  77 */       db.openConnection();
/*     */     }
/*     */     try
/*     */     {
/*  81 */       Statement s = db.getConnection().createStatement();
/*  82 */       ResultSet rs = s.executeQuery("SELECT * FROM reportes WHERE `UUID`='" + jogador + "';");
/*  83 */       if (!rs.next()) {
/*  84 */         return 0;
/*     */       }
/*  86 */       return rs.getInt(tipo);
/*     */     } catch (SQLException e) {
/*  88 */       Main.plugin.getLogger().severe("Nao foi possivel carregar as denuncias do jogador: " + jogador);
/*     */     }
/*  90 */     return 0;
/*     */   }
/*     */   
/*     */   public static int getAllDenuncia(String jogador) {
/*  94 */     if (!db.checkConnection()) {
/*  95 */       db.openConnection();
/*     */     }
/*     */     try {
/*  98 */       Statement s = db.getConnection().createStatement();
/*  99 */       ResultSet rs = s.executeQuery("SELECT * FROM reportes WHERE `UUID`='" + jogador + "';");
/* 100 */       if (!rs.next()) {
/* 101 */         return 0;
/*     */       }
/* 103 */       int quantidade = 0;
/* 104 */       quantidade += rs.getInt("FF");
/* 105 */       quantidade += rs.getInt("Fly");
/* 106 */       quantidade += rs.getInt("Ofensa");
/* 107 */       quantidade += rs.getInt("Divulgacao");
/* 108 */       quantidade += rs.getInt("Macro");
/* 109 */       return quantidade + rs.getInt("Speed");
/*     */     }
/*     */     catch (SQLException e) {
/* 112 */       Main.plugin.getLogger().severe("Nao foi possivel carregar as denuncias do jogador: " + jogador);
/*     */     }
/* 114 */     return 0;
/*     */   }
/*     */   
/*     */   public static boolean containsDenuncia(String jogador) {
/* 118 */     if (!db.checkConnection()) {
/* 119 */       db.openConnection();
/*     */     }
/*     */     try
/*     */     {
/* 123 */       Statement s = db.getConnection().createStatement();
/* 124 */       ResultSet rs = s.executeQuery("SELECT * FROM reportes WHERE `UUID`='" + jogador + "';");
/* 125 */       if (!rs.next()) {
/* 126 */         return false;
/*     */       }
/* 128 */       return true;
/*     */     } catch (SQLException e) {
/* 130 */       Main.plugin.getLogger().severe("Nao foi possivel carregar as denuncias do jogador: " + jogador);
/*     */     }
/* 132 */     return false;
/*     */   }
/*     */   
/*     */   public void closeDB() {
/* 136 */     db.closeConnection();
/*     */   }
/*     */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\MySQL\MSManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */