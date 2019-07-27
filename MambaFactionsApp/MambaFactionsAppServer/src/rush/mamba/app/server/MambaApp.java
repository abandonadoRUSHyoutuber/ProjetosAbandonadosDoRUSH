package rush.mamba.app.server;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import rush.mamba.app.server.api.App;
import rush.mamba.app.server.listener.FactionsEvents;

public class MambaApp extends JavaPlugin {

	private static App app;

	@Override
	public void onEnable() {
		System.out.println("----------{ MambaApp }---------");
		enable();
		System.out.println("----------{ MambaApp }---------");
	}
	
	private void enable() {
		System.out.println("Iniciando plugin e servidor....");
		app = new App();
		Bukkit.getPluginManager().registerEvents(new FactionsEvents(), this);
		System.out.println("Plugin e servidor iniciados com sucesso!");
	}

	public static App getApp() {
		return app;
	}

}