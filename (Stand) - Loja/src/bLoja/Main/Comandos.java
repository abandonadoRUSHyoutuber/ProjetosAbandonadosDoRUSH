package bLoja.Main;

import org.bukkit.command.*;
import org.bukkit.entity.*;

public class Comandos implements CommandExecutor
{
    public Main plugin;
    
    public Comandos(final Main plugin) {
        this.plugin = plugin;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("lojagui")) {
            if (sender instanceof Player) {
                final Player p = (Player)sender;
                if (p.isOp() || p.hasPermission("loja.adm")) {
                    if (this.plugin.core.lojas == null) {
                        p.sendMessage("§cSem lojas definidas");
                    }
                    else {
                        this.plugin.gui.abrirGUI(p, 1);
                    }
                }
            }
            return true;
        }
        return false;
    }
}
