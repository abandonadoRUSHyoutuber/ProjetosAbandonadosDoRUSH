package rush.warpapbr.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;

public class CommandUtils {
	
    public static void registrePluginCommand(PluginCommand command) {
        try {
            Field f = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            CommandMap commandMap = (CommandMap) f.get(Bukkit.getPluginManager());
            commandMap.register("warp", command);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    
    public static Map<String, Location> loadLocations(String command) {
    	final Map<String, Location> locations = new HashMap<>();
    	
    	try 
    	{
	    	File file = DataManager.getFile(command, "locations");
	    	FileConfiguration config = DataManager.getConfiguration(file);
	    	
	    	if (file.exists()) {
	    		for (String s : config.getConfigurationSection("Localizacoes").getKeys(false)) {
	        		locations.put(s, deserializeLocation(config.getString("Localizacoes." + s)));
	    		}
	        	
	    	} else { 
		    	DataManager.createFile(file);
		    	config.createSection("Localizacoes");
				config.save(file);
	    	}
		} 
		catch (Throwable e) {
			e.printStackTrace();
		}
    
    	return locations;
    }
    
    public static void setLocation(String command, Location l, String identifier) {
    	try 
    	{
	    	File file = DataManager.getFile(command, "locations");
	    	FileConfiguration config = DataManager.getConfiguration(file);
	    	config.set("Localizacoes." + identifier, serializeLocation(l));
			config.save(file);
    	}
    	catch (Throwable e) {
    		e.printStackTrace();
    	}
    }
    
    public static void deleteLocation(String command, String identifier) {
    	try 
    	{
	    	File file = DataManager.getFile(command, "locations");
	    	FileConfiguration config = DataManager.getConfiguration(file);
	    	config.set("Localizacoes." + identifier, null);
			config.save(file);
    	}
    	catch (Throwable e) {
    		e.printStackTrace();
    	}
    }
    
    public static String serializeLocation(Location l) {
    	return 
    			l.getWorld().getName() + "," + 
    			l.getX()               + "," + 
    			l.getY()               + "," + 
    			l.getZ()               + "," + 
    			l.getYaw()             + "," + 
    			l.getPitch()                 ;
    }
    
    public static Location deserializeLocation(String s) {
    	String[] locationSplitted = s.split(",");
		return new Location (
			   Bukkit.getWorld(locationSplitted[0])    ,
			   Double.parseDouble(locationSplitted[1]) ,
			   Double.parseDouble(locationSplitted[2]) ,
			   Double.parseDouble(locationSplitted[3]) ,
			   Float.parseFloat(locationSplitted[4])   ,
			   Float.parseFloat(locationSplitted[5]))  ;
    }
	
}