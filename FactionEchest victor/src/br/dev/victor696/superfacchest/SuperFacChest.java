package br.dev.victor696.superfacchest;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import br.dev.victor696.superfacchest.command.FacchestCommand;
import br.dev.victor696.superfacchest.database.Sql;
import br.dev.victor696.superfacchest.hook.VaultEconomy;
import br.dev.victor696.superfacchest.listeners.Listeners;
import br.dev.victor696.superfacchest.object.Faccao;
import br.dev.victor696.superfacchest.utils.Methods;

public class SuperFacChest extends JavaPlugin {

	public static SuperFacChest instance;
	public Sql sql;
	public VaultEconomy economy;
	public HashMap<String, Faccao> faccao;

	public static SuperFacChest getInstance() {
		return instance;
	}

	public SuperFacChest() {
		instance = this;
		sql = new Sql();
		economy = new VaultEconomy();
		faccao = new HashMap<>();
	}

	@Override
	public void onEnable() {
		saveDefaultConfig();

		sql.abrirConexao();
		sql.criarTabelas();
		
		Methods.loadFaccoes();
		
		economy.setup();
		Bukkit.getPluginManager().registerEvents(new Listeners(), this);
		getCommand("facchest").setExecutor(new FacchestCommand());
		Bukkit.getConsoleSender().sendMessage("§e[SuperFacChest] Iniciado by Victor696");
	}

	@Override
	public void onDisable() {

		for (String faccao : this.faccao.keySet()) {
			Faccao f = this.faccao.get(faccao);
			f.saveChest();
		}
		sql.fecharConexao();
	}

}
