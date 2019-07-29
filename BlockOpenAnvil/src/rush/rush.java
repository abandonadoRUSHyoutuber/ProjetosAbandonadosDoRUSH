
package rush;

import org.bukkit.event.EventHandler;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class rush extends JavaPlugin implements Listener {
    public rush() {
        super();
    }
    
	public void onEnable()
	  {
	    Bukkit.getConsoleSender().sendMessage("[BlockOpenAnvil] Plugin habilitado com sucesso.");
	    getServer().getPluginManager().registerEvents(this, this);
	    saveDefaultConfig();
	  }
	  
	  public void onDisable()
	  {
	    Bukkit.getConsoleSender().sendMessage("[BlockOpenAnvil] Plugin desabilitado com sucesso.");
	  }

   @EventHandler
   public void aoInteract(final PlayerInteractEvent e) {
       final Action action = e.getAction();
       final Player p = e.getPlayer();
       final Block block = e.getClickedBlock();
       if (action.equals((Object)Action.RIGHT_CLICK_BLOCK) && block.getType().equals((Object)Material.ANVIL)) {
           e.setCancelled(true);
          if (this.getConfig().getBoolean("Ativar_Mensagem")) {
              p.sendMessage(this.getConfig().getString("Mensagem").replaceAll("&", "§"));
      }

   }
  }
}
