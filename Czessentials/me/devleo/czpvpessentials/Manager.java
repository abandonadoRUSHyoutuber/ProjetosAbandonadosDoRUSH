/*    */ package me.devleo.czpvpessentials;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
/*    */ import net.minecraft.server.v1_8_R3.PlayerConnection;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ public class Manager
/*    */ {
/*    */   public static void sendActionBar(Player p, String msg)
/*    */   {
/* 17 */     net.minecraft.server.v1_8_R3.IChatBaseComponent cbc = net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
/* 18 */     PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte)2);
/* 19 */     ((CraftPlayer)p).getHandle().playerConnection.sendPacket(ppoc);
/*    */   }
/*    */   
/*    */   public static ItemStack criarItem(Material material, String nome, String lore) {
/* 23 */     ItemStack stack = new ItemStack(material);
/* 24 */     ItemMeta stack2 = stack.getItemMeta();
/* 25 */     stack2.setDisplayName(nome);
/* 26 */     List<String> lores = new ArrayList();
/* 27 */     lores.add(lore);
/* 28 */     stack2.setLore(lores);
/* 29 */     stack.setItemMeta(stack2);
/* 30 */     return stack;
/*    */   }
/*    */   
/*    */   public static ItemStack criarItem(Material material, String nome, List lore) {
/* 34 */     ItemStack stack = new ItemStack(material);
/* 35 */     ItemMeta stack2 = stack.getItemMeta();
/* 36 */     stack2.setDisplayName(nome);
/* 37 */     stack2.setLore(lore);
/* 38 */     stack.setItemMeta(stack2);
/* 39 */     return stack;
/*    */   }
/*    */ }


/* Location:              C:\Users\João\Desktop\CzEssentials.jar!\me\devleo\czpvpessentials\Manager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */