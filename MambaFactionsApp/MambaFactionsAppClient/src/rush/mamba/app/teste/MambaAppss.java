package rush.mamba.app.teste;

import org.bukkit.plugin.java.JavaPlugin;

import rush.mamba.app.teste.command.TestCommand;

public class MambaAppss extends JavaPlugin {

	@Override
	public void onEnable() {
		getCommand("test").setExecutor(new TestCommand());
	}

}