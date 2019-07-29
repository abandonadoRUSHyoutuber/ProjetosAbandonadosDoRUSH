/*    */ package me.devleo.czpvpessentials.MySQL;
/*    */ 
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public abstract class Database
/*    */ {
/*    */   protected Plugin plugin;
/*    */   
/*    */   protected Database(Plugin plugin) {
/* 10 */     this.plugin = plugin;
/*    */   }
/*    */   
/*    */   public abstract java.sql.Connection openConnection();
/*    */   
/*    */   public abstract boolean checkConnection();
/*    */   
/*    */   public abstract java.sql.Connection getConnection();
/*    */   
/*    */   public abstract void closeConnection();
/*    */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\MySQL\Database.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */