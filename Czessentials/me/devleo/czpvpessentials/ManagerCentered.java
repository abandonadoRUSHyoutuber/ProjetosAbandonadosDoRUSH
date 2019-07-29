/*    */ package me.devleo.czpvpessentials;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class ManagerCentered
/*    */ {
/*    */   private static final int CENTER_PX = 154;
/*    */   
/*    */   public static void sendCenteredMessage(Player player, String message)
/*    */   {
/* 11 */     if ((message == null) || (message.equals("")))
/* 12 */       player.sendMessage("");
/* 13 */     message = org.bukkit.ChatColor.translateAlternateColorCodes('&', message);
/*    */     
/* 15 */     int messagePxSize = 0;
/* 16 */     boolean previousCode = false;
/* 17 */     boolean isBold = false;
/*    */     char[] arrayOfChar;
/* 19 */     int j = (arrayOfChar = message.toCharArray()).length; for (int i = 0; i < j; i++) { char c = arrayOfChar[i];
/* 20 */       if (c == '§') {
/* 21 */         previousCode = true;
/*    */       }
/* 23 */       else if (previousCode) {
/* 24 */         previousCode = false;
/* 25 */         if ((c == 'l') || (c == 'L')) {
/* 26 */           isBold = true;
/*    */         }
/*    */         else
/* 29 */           isBold = false;
/*    */       } else {
/* 31 */         DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
/* 32 */         messagePxSize += (isBold ? dFI.getBoldLength() : dFI.getLength());
/* 33 */         messagePxSize++;
/*    */       }
/*    */     }
/*    */     
/* 37 */     int halvedMessageSize = messagePxSize / 2;
/* 38 */     int toCompensate = 154 - halvedMessageSize;
/* 39 */     int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
/* 40 */     int compensated = 0;
/* 41 */     StringBuilder sb = new StringBuilder();
/* 42 */     while (compensated < toCompensate) {
/* 43 */       sb.append(" ");
/* 44 */       compensated += spaceLength;
/*    */     }
/* 46 */     player.sendMessage(sb.toString() + message);
/*    */   }
/*    */   
/*    */   public static String getCenteredMessage(String message) {
/* 50 */     message = org.bukkit.ChatColor.translateAlternateColorCodes('&', message);
/*    */     
/* 52 */     int messagePxSize = 0;
/* 53 */     boolean previousCode = false;
/* 54 */     boolean isBold = false;
/*    */     char[] arrayOfChar;
/* 56 */     int j = (arrayOfChar = message.toCharArray()).length; for (int i = 0; i < j; i++) { char c = arrayOfChar[i];
/* 57 */       if (c == '§') {
/* 58 */         previousCode = true;
/*    */       }
/* 60 */       else if (previousCode) {
/* 61 */         previousCode = false;
/* 62 */         if ((c == 'l') || (c == 'L')) {
/* 63 */           isBold = true;
/*    */         }
/*    */         else
/* 66 */           isBold = false;
/*    */       } else {
/* 68 */         DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
/* 69 */         messagePxSize += (isBold ? dFI.getBoldLength() : dFI.getLength());
/* 70 */         messagePxSize++;
/*    */       }
/*    */     }
/*    */     
/* 74 */     int halvedMessageSize = messagePxSize / 2;
/* 75 */     int toCompensate = 154 - halvedMessageSize;
/* 76 */     int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
/* 77 */     int compensated = 0;
/* 78 */     StringBuilder sb = new StringBuilder();
/* 79 */     while (compensated < toCompensate) {
/* 80 */       sb.append(" ");
/* 81 */       compensated += spaceLength;
/*    */     }
/* 83 */     return sb.toString() + message;
/*    */   }
/*    */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\ManagerCentered.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */