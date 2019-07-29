/*    */ package me.devleo.czpvpessentials.MySQL;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLException;
/*    */ import java.util.logging.Logger;
/*    */ import me.devleo.czpvpessentials.Main;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class EndDB extends Thread
/*    */ {
/*    */   private Logger log;
/*    */   private Connection con;
/*    */   
/*    */   public EndDB(Plugin plugin, Logger log, Connection con)
/*    */   {
/* 16 */     setDaemon(false);
/* 17 */     this.log = log;
/* 18 */     this.con = con;
/*    */   }
/*    */   
/*    */   public void run()
/*    */   {
/* 23 */     Main.lock.lock();
/*    */     try {
/* 25 */       Main.plugin.getLogger().warning("Desconetando do MYSQL");
/* 26 */       this.con.close();
/*    */     } catch (SQLException ex) {
/* 28 */       Main.plugin.getLogger().warning("Erro ao fechar sua conexao");
/* 29 */       Main.plugin.getLogger().warning(ex.getMessage());
/*    */     } catch (NullPointerException ex) {
/* 31 */       Main.plugin.getLogger().warning("Erro ao fechar sua conexao");
/*    */     }
/* 33 */     if (!Main.SQL_DSC.booleanValue()) {
/* 34 */       this.log.info("Reconectando ao MYSQL...");
/* 35 */       Main.SQLconnect();
/*    */     }
/* 37 */     Main.lock.unlock();
/*    */   }
/*    */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\MySQL\EndDB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */