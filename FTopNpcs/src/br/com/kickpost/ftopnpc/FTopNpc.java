package br.com.kickpost.ftopnpc;

import org.bukkit.plugin.java.JavaPlugin;

import br.com.kickpost.ftopnpc.command.SetNpcCommand;
import br.com.kickpost.ftopnpc.manager.ConfigurationManager;
import br.com.kickpost.ftopnpc.manager.NPCStarter;
import br.com.kickpost.ftopnpc.manager.npc.NPCManager;

public class FTopNpc extends JavaPlugin {

	@Override
	public void onEnable() {
		loadConfiguration();
		loadCommand();
	}

	@Override
	public void onDisable() {
		NPCManager.NPCs.stream().filter(r -> r != null).forEach(npc -> npc.delete());
		NPCManager.HOLOGRAMS.stream().filter(r -> r != null).forEach(npc -> npc.delete());
	}

	private void loadConfiguration() {
		saveDefaultConfig();
		new ConfigurationManager();
		new NPCStarter().runTaskTimerAsynchronously(this, 0L, 20L);
	}

	private void loadCommand() {
		getCommand("setnpc").setExecutor(new SetNpcCommand());
	}

	public static FTopNpc getPlugin() {
		return JavaPlugin.getPlugin(FTopNpc.class);
	}

}
