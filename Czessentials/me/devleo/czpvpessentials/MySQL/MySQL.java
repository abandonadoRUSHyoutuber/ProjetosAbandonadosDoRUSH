/*     */ package me.devleo.czpvpessentials.MySQL;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import me.devleo.czpvpessentials.Main;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class MySQL extends Database
/*     */ {
/*     */   private final String user;
/*     */   private final String database;
/*     */   private final String password;
/*     */   private final String port;
/*     */   private final String hostname;
/*     */   private Connection connection;
/*     */   
/*     */   public MySQL(Plugin plugin, String hostname, String port, String database, String username, String password)
/*     */   {
/*  23 */     super(plugin);
/*  24 */     this.hostname = hostname;
/*  25 */     this.port = port;
/*  26 */     this.database = database;
/*  27 */     this.user = username;
/*  28 */     this.password = password;
/*  29 */     this.connection = null;
/*     */   }
/*     */   
/*     */   public Connection openConnection()
/*     */   {
/*     */     try {
/*  35 */       Class.forName("com.mysql.jdbc.Driver");
/*  36 */       this.connection = java.sql.DriverManager.getConnection(
/*  37 */         "jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database, this.user, this.password);
/*     */     } catch (SQLException e) {
/*  39 */       Main.plugin.getLogger().log(Level.SEVERE, 
/*  40 */         "Nao foi possivel conectar-se ao servidor MySQL, motivo: " + e.getMessage());
/*     */     } catch (ClassNotFoundException e) {
/*  42 */       Main.plugin.getLogger().log(Level.SEVERE, "Driver JDBC nao encontrado!");
/*     */     }
/*  44 */     return this.connection;
/*     */   }
/*     */   
/*     */   public boolean checkConnection()
/*     */   {
/*  49 */     if (this.connection != null) {
/*  50 */       return true;
/*     */     }
/*  52 */     return false;
/*     */   }
/*     */   
/*     */   public Connection getConnection()
/*     */   {
/*  57 */     return this.connection;
/*     */   }
/*     */   
/*     */   public void closeConnection()
/*     */   {
/*  62 */     if (this.connection != null) {
/*     */       try {
/*  64 */         this.connection.close();
/*     */       } catch (SQLException e) {
/*  66 */         this.plugin.getLogger().log(Level.SEVERE, "Error closing the MySQL Connection!");
/*  67 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public ResultSet querySQL(String query) {
/*  73 */     Connection c = null;
/*  74 */     c = checkConnection() ? getConnection() : openConnection();
/*  75 */     Statement s = null;
/*     */     try {
/*  77 */       s = c.createStatement();
/*     */     } catch (SQLException e1) {
/*  79 */       e1.printStackTrace();
/*     */     }
/*  81 */     ResultSet ret = null;
/*     */     try {
/*  83 */       ret = s.executeQuery(query);
/*     */     } catch (SQLException e) {
/*  85 */       e.printStackTrace();
/*     */     }
/*  87 */     closeConnection();
/*  88 */     return ret;
/*     */   }
/*     */   
/*     */   public void updateSQL(String update) {
/*  92 */     Connection c = null;
/*  93 */     c = checkConnection() ? getConnection() : openConnection();
/*  94 */     Statement s = null;
/*     */     try {
/*  96 */       s = c.createStatement();
/*  97 */       s.executeUpdate(update);
/*     */     } catch (SQLException e1) {
/*  99 */       e1.printStackTrace();
/*     */     }
/* 101 */     closeConnection();
/*     */   }
/*     */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\MySQL\MySQL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */