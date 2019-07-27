package rush.warpapbr.command;

import java.lang.reflect.Constructor;

import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import rush.warpapbr.Main;
import rush.warpapbr.utils.CommandUtils;
import rush.warpapbr.utils.ConfigManager;

public class Command {
	
	public Command(String command) {
        try 
        {
        	ConfigurationSection config = ConfigManager.getConfig("config").getConfigurationSection("Comandos." + command);
        	String name = config.getString("Comando").replace("/", "").trim().toLowerCase();
        	String perm = config.getString("Permissao").replace(" ", "").trim().toLowerCase();
        	String desc = config.getString("Descricao").replace("&", "§").trim();
        	String erro = config.getString("SemPermissao").replace("&", "§").trim();
        	String repl = config.getString("Nome").replace('&', '§');
        	
            Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            c.setAccessible(true);
            
            PluginCommand cmd = c.newInstance(name, Main.get());
            if (!perm.isEmpty()) cmd.setPermission(perm);
            cmd.setDescription(desc);
            cmd.setPermissionMessage(erro);
            cmd.setExecutor(new FinalCommand(command, repl));
            
            CommandUtils.registrePluginCommand(cmd);
        } 
        catch (Throwable e) {
            e.printStackTrace();
        }		
	}
	
}