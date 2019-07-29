package me.leitinho.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatStaff
  implements CommandExecutor
{
  public String[] aliases = { "sc" };
  public String description = "Chat da staff.";
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player player = (Player)sender;
    if (player.hasPermission("system.chatstaff"))
    {
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < args.length; i++) {
        builder.append(args[i]).append(" ");
      }
      String message = builder.toString();
      if ((sender instanceof Player))
      {
        if (args.length > 0)
        {
          Player[] arrayOfPlayer;
          @SuppressWarnings("deprecation")
		int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length;
          for (int i = 0; i < j; i++)
          {
            Player p = arrayOfPlayer[i];
            if (p.hasPermission("cmd.staff")) {
              p.sendMessage("§c&l*** §7" + player.getName() + ChatColor.RESET + ": §c"+ message);
            }
          }
        }
        else
        {
          player.sendMessage("§7 Use /staff <mensagem>')");
        }
      }
      else {
        sender.sendMessage(ChatColor.RED + "So players podem usar este comando!");
      }
    }
    else
    {
      player.sendMessage("§cNão");
    }
    return false;
  }
}
