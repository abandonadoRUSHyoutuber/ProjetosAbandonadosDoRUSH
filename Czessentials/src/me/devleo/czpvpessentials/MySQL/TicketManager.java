/*    */ package me.devleo.czpvpessentials.MySQL;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ import me.devleo.czpvpessentials.Main;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ 
/*    */ public class TicketManager
/*    */ {
/*    */   private static MySQL db;
/*    */   public Main plugin;
/*    */   
/*    */   public TicketManager(Main plugin2)
/*    */   {
/* 17 */     this.plugin = plugin2;
/*    */   }
/*    */   
/*    */   public void iniciarDatabase() throws SQLException {
/* 21 */     db = new MySQL(this.plugin, Main.plugin.getConfig().getString("mysql.host-name"), 
/* 22 */       Main.plugin.getConfig().getString("mysql.porta"), Main.plugin.getConfig().getString("mysql.database"), 
/* 23 */       Main.plugin.getConfig().getString("mysql.usuario"), Main.plugin.getConfig().getString("mysql.senha"));
/* 24 */     db.openConnection();
/* 25 */     Statement statement = db.getConnection().createStatement();
/* 26 */     statement.executeUpdate("CREATE TABLE IF NOT EXISTS `tickets` (`UUID` varchar(255), `Duvida` varchar(255))");
/*    */     
/* 28 */     this.plugin.database = true;
/*    */   }
/*    */   
/*    */   public static void addDuvida(org.bukkit.entity.Player p, String duvida) {
/* 32 */     if (!db.checkConnection()) {
/* 33 */       db.openConnection();
/*    */     }
/*    */     try {
/* 36 */       Statement s = db.getConnection().createStatement();
/* 37 */       s.executeUpdate(
/* 38 */         "INSERT INTO tickets (`UUID`, `Duvida`) VALUES ('" + p.getUniqueId() + "', '" + duvida + "');");
/*    */     } catch (SQLException e) {
/* 40 */       Main.plugin.getLogger().severe("Nao foi possivel salvar a duvida do jogador: " + p.getName());
/*    */     }
/*    */   }
/*    */   
/*    */   public static void removerDuvida(String jogador) {
/* 45 */     if (!db.checkConnection()) {
/* 46 */       db.openConnection();
/*    */     }
/*    */     try {
/* 49 */       Statement s = db.getConnection().createStatement();
/* 50 */       ResultSet rs = s.executeQuery("SELECT * FROM tickets WHERE `UUID`='" + jogador + "';");
/* 51 */       if (!rs.next()) {
/* 52 */         return;
/*    */       }
/* 54 */       s.executeUpdate("DELETE FROM tickets WHERE `UUID`='" + jogador + "';");
/*    */     } catch (SQLException e) {
/* 56 */       Main.plugin.getLogger().severe("Nao foi possivel deletar a duvida do jogador: " + jogador);
/*    */     }
/*    */   }
/*    */   
/*    */   public static String getDuvida(String jogador) {
/* 61 */     if (!db.checkConnection()) {
/* 62 */       db.openConnection();
/*    */     }
/*    */     try
/*    */     {
/* 66 */       Statement s = db.getConnection().createStatement();
/* 67 */       ResultSet rs = s.executeQuery("SELECT * FROM tickets WHERE `UUID`='" + jogador + "';");
/* 68 */       if (!rs.next()) {
/* 69 */         return "Inválida";
/*    */       }
/* 71 */       return rs.getString("Duvida");
/*    */     } catch (SQLException e) {
/* 73 */       Main.plugin.getLogger().severe("Nao foi possivel carregar as duvidas do jogador: " + jogador);
/*    */     }
/* 75 */     return "Inválida";
/*    */   }
/*    */   
/*    */   public static boolean containsDuvida(String jogador) {
/* 79 */     if (!db.checkConnection()) {
/* 80 */       db.openConnection();
/*    */     }
/*    */     try
/*    */     {
/* 84 */       Statement s = db.getConnection().createStatement();
/* 85 */       ResultSet rs = s.executeQuery("SELECT * FROM tickets WHERE `UUID`='" + jogador + "';");
/* 86 */       if (!rs.next()) {
/* 87 */         return false;
/*    */       }
/* 89 */       return true;
/*    */     } catch (SQLException e) {
/* 91 */       Main.plugin.getLogger().severe("Nao foi possivel carregar as duvidas do jogador: " + jogador);
/*    */     }
/* 93 */     return false;
/*    */   }
/*    */   
/*    */   public void closeDB() {
/* 97 */     db.closeConnection();
/*    */   }
/*    */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\MySQL\TicketManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */