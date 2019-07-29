package me.ep.extratags;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.ep.extratags.events.LegendChat;

public class ExtraTags extends JavaPlugin{		
	
	public HashMap<String, String> tags = new HashMap<>();
	
	@Override
	public void onEnable() {
		
		getLogger().info("Plugin habilitado com sucesso!");
		getLogger().info("Desenvolvido por: ExtraPlays");
		
		new LegendChat(this);
		saveDefaultConfig();
		
		loadTags();
		
		if (Bukkit.getPluginManager().getPlugin("Legendchat") == null){
			getLogger().severe("LegendChat não encontrado, desabilitando este plugin!");
			Bukkit.getPluginManager().DisablePlugin(this);
		}
		
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Plugin desabilitado com sucesso!");
		getLogger().info("Desenvolvido por: ExtraPlays");
		
		
	}
	
	public void loadTags(){
		for (String s : getConfig().getConfigurationSection("Tags").getKeys(false)){
			String tag = s;
			String valor = getConfig().getString("Tags." + tag + ".Valor");
			
			tags.put(tag, valor);
		}
	}
	
	public void saveTags(){		
		for (Entry<String, String> tag : tags.entrySet()){
			getConfig().set("Tags." + tag.getKey() + ".Valor", tag.getValue());
			saveConfig();
		}		
	}
	
}
