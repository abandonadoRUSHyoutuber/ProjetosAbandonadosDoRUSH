/*    */ package me.devleo.czpvpessentials.MySQL;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ import java.util.logging.Logger;
/*    */ import me.devleo.czpvpessentials.Main;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class Query extends Thread
/*    */ {
/*    */   private String sql;
/*    */   private Logger log;
/*    */   private Connection con;
/*    */   
/*    */   public Query(String sql, Logger log, Connection con, Plugin plugin)
/*    */   {
/* 18 */     setDaemon(false);
/* 19 */     this.sql = sql;
/* 20 */     setLog(log);
/* 21 */     this.con = con;
/*    */   }
/*    */   
/*    */   public void run()
/*    */   {
/* 26 */     Main.lock.lock();
/*    */     try {
/* 28 */       Statement stmt = this.con.createStatement();
/* 29 */       stmt.executeUpdate(this.sql);
/* 30 */       stmt.close();
/*    */     } catch (SQLException ex) {
/* 32 */       Main.plugin.getLogger().warning("Error with following query: " + this.sql);
/* 33 */       Main.plugin.getLogger().warning(ex.getMessage());
/* 34 */       Main.SQLdisconnect();
/*    */     } catch (NullPointerException ex) {
/* 36 */       Main.plugin.getLogger().warning("Error while performing a query. (NullPointerException)");
/*    */     }
/* 38 */     Main.lock.unlock();
/*    */   }
/*    */   
/*    */   public Logger getLog() {
/* 42 */     return this.log;
/*    */   }
/*    */   
/*    */   public void setLog(Logger log) {
/* 46 */     this.log = log;
/*    */   }
/*    */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\MySQL\Query.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */