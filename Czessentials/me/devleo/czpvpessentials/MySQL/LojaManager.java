/*     */ package me.devleo.czpvpessentials.MySQL;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.logging.Logger;
/*     */ import me.devleo.czpvpessentials.Main;
/*     */ 
/*     */ public class LojaManager
/*     */ {
/*     */   private static MySQL db;
/*     */   public Main plugin;
/*     */   
/*     */   public LojaManager(Main plugin2)
/*     */   {
/*  17 */     this.plugin = plugin2;
/*     */   }
/*     */   
/*     */   public void iniciarDatabase() throws SQLException {
/*  21 */     db = new MySQL(this.plugin, Main.plugin.getConfig().getString("mysql.host-name"), 
/*  22 */       Main.plugin.getConfig().getString("mysql.porta"), Main.plugin.getConfig().getString("mysql.database"), 
/*  23 */       Main.plugin.getConfig().getString("mysql.usuario"), Main.plugin.getConfig().getString("mysql.senha"));
/*  24 */     db.openConnection();
/*  25 */     Statement statement = db.getConnection().createStatement();
/*  26 */     statement.executeUpdate(
/*  27 */       "CREATE TABLE IF NOT EXISTS `lojas` (`UUID` varchar(255), `Local` varchar(255) , `Visitas` int)");
/*     */     
/*  29 */     this.plugin.database = true;
/*     */   }
/*     */   
/*     */   public static void criarLoja(org.bukkit.entity.Player p, String local) {
/*  33 */     if (!db.checkConnection()) {
/*  34 */       db.openConnection();
/*     */     }
/*     */     try {
/*  37 */       Statement s = db.getConnection().createStatement();
/*  38 */       s.executeUpdate("INSERT INTO lojas (`UUID`, `Local`, `Visitas`) VALUES ('" + p.getUniqueId() + "', '" + 
/*  39 */         local + "', '0');");
/*     */     } catch (SQLException e) {
/*  41 */       Main.plugin.getLogger().severe("Nao foi possivel salvar a loja do jogador: " + p.getName());
/*     */     }
/*     */   }
/*     */   
/*     */   public static void removerLoja(String jogador) {
/*  46 */     if (!db.checkConnection()) {
/*  47 */       db.openConnection();
/*     */     }
/*     */     try {
/*  50 */       Statement s = db.getConnection().createStatement();
/*  51 */       ResultSet rs = s.executeQuery("SELECT * FROM lojas WHERE `UUID`='" + jogador + "';");
/*  52 */       if (!rs.next()) {
/*  53 */         return;
/*     */       }
/*  55 */       s.executeUpdate("DELETE FROM lojas WHERE `UUID`='" + jogador + "';");
/*     */     } catch (SQLException e) {
/*  57 */       Main.plugin.getLogger().severe("Nao foi possivel deletar a loja do jogador: " + jogador);
/*     */     }
/*     */   }
/*     */   
/*     */   public static String getLoja(String jogador) {
/*  62 */     if (!db.checkConnection()) {
/*  63 */       db.openConnection();
/*     */     }
/*     */     try
/*     */     {
/*  67 */       Statement s = db.getConnection().createStatement();
/*  68 */       ResultSet rs = s.executeQuery("SELECT * FROM lojas WHERE `UUID`='" + jogador + "';");
/*  69 */       if (!rs.next()) {
/*  70 */         return "Inválida";
/*     */       }
/*  72 */       return rs.getString("Local");
/*     */     } catch (SQLException e) {
/*  74 */       Main.plugin.getLogger().severe("Nao foi possivel carregar a loja do jogador: " + jogador);
/*     */     }
/*  76 */     return "Inválida";
/*     */   }
/*     */   
/*     */   public static void setVisita(String jogador, int quantidade) {
/*  80 */     if (!db.checkConnection()) {
/*  81 */       db.openConnection();
/*     */     }
/*     */     try
/*     */     {
/*  85 */       Statement s = db.getConnection().createStatement();
/*  86 */       s.executeUpdate("UPDATE lojas SET `Visitas`='" + quantidade + "' WHERE `UUID`='" + jogador + "';");
/*     */     } catch (SQLException e) {
/*  88 */       Main.plugin.getLogger().severe("Nao foi possivel carregar a loja do jogador: " + jogador);
/*     */     }
/*     */   }
/*     */   
/*     */   public static int getVisita(String jogador) {
/*  93 */     if (!db.checkConnection()) {
/*  94 */       db.openConnection();
/*     */     }
/*     */     try
/*     */     {
/*  98 */       Statement s = db.getConnection().createStatement();
/*  99 */       ResultSet rs = s.executeQuery("SELECT * FROM lojas WHERE `UUID`='" + jogador + "';");
/* 100 */       if (!rs.next()) {
/* 101 */         return 0;
/*     */       }
/* 103 */       return rs.getInt("Visitas");
/*     */     } catch (SQLException e) {
/* 105 */       Main.plugin.getLogger().severe("Nao foi possivel carregar a loja do jogador: " + jogador);
/*     */     }
/* 107 */     return 0;
/*     */   }
/*     */   
/*     */   public static boolean containsLoja(String jogador) {
/* 111 */     if (!db.checkConnection()) {
/* 112 */       db.openConnection();
/*     */     }
/*     */     try
/*     */     {
/* 116 */       Statement s = db.getConnection().createStatement();
/* 117 */       ResultSet rs = s.executeQuery("SELECT * FROM lojas WHERE `UUID`='" + jogador + "';");
/* 118 */       if (!rs.next()) {
/* 119 */         return false;
/*     */       }
/* 121 */       return true;
/*     */     } catch (SQLException e) {
/* 123 */       Main.plugin.getLogger().severe("Nao foi possivel carregar a loja do jogador: " + jogador);
/*     */     }
/* 125 */     return false;
/*     */   }
/*     */   
/*     */   public void closeDB() {
/* 129 */     db.closeConnection();
/*     */   }
/*     */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\MySQL\LojaManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */