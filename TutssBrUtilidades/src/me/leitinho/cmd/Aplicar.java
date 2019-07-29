package me.leitinho.cmd;

import org.bukkit.command.*;
import org.bukkit.entity.*;

import me.leitinho.Main;

public class Aplicar implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("aplicar")) {
            p.sendMessage(Main.Form);
        }
        return false;
    }
}
