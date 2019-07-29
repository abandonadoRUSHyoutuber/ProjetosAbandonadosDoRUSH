package br.itensespeciais.main;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import br.itensespeciais.comandos.GiveEspecial;
import br.itensespeciais.comandos.PegarEspeciais;
import br.itensespeciais.funcionalidades.Armadilha;
import br.itensespeciais.funcionalidades.Capsula;
import br.itensespeciais.funcionalidades.CreeperEletrizado;
import br.itensespeciais.funcionalidades.FragmentoPoder;
import br.itensespeciais.funcionalidades.Lancador;
import br.itensespeciais.funcionalidades.PoderInstantaneo;
import br.itensespeciais.funcionalidades.PoderMaximo;
import br.itensespeciais.funcionalidades.Purificador;
import br.itensespeciais.funcionalidades.RaioMestre;
import br.itensespeciais.funcionalidades.SilkTouch;
import br.itensespeciais.itemstack.ItemManager;

public class Main extends JavaPlugin{
	
	private static Main i;
	public static Main get() { return i; }
	public Main() { Main.i = this; }

	
	public static ItemStack purificador;
	public static ItemStack capsula;
	public static ItemStack raiomestre;
	public static ItemStack creepereletrico;
	public static ItemStack fragmentodepoder;
	public static ItemStack podermaximo;
	public static ItemStack silktouch;
	public static ItemStack lancador;
	public static ItemStack poderinstantaneo;
	public static ItemStack armadilha;
	
	public void onEnable() {
		saveDefaultConfig();
		
		purificador = ItemManager.getPurificador();
		capsula = ItemManager.getCapsula();
		raiomestre = ItemManager.getRaioMestre();
		creepereletrico = ItemManager.getCreeperEletrico();
		fragmentodepoder = ItemManager.getFragmentoPoder();
		podermaximo = ItemManager.getPoderMaximo();
		silktouch = ItemManager.getSilkTouch();
		lancador = ItemManager.getLancador();
		poderinstantaneo = ItemManager.getPoderInstantaneo();
		armadilha = ItemManager.getArmadilha();
		
		Bukkit.getPluginManager().registerEvents(new PegarEspeciais(), this);
		Bukkit.getPluginManager().registerEvents(new Capsula(), this);
		Bukkit.getPluginManager().registerEvents(new Lancador(), this);
		Bukkit.getPluginManager().registerEvents(new RaioMestre(), this);
		Bukkit.getPluginManager().registerEvents(new SilkTouch(), this);
		Bukkit.getPluginManager().registerEvents(new CreeperEletrizado(), this);
		Bukkit.getPluginManager().registerEvents(new PoderMaximo(), this);
		Bukkit.getPluginManager().registerEvents(new PoderInstantaneo(), this);
		Bukkit.getPluginManager().registerEvents(new Armadilha(), this);
		Bukkit.getPluginManager().registerEvents(new Purificador(), this);
		Bukkit.getPluginManager().registerEvents(new FragmentoPoder(), this);
		
		getCommand("pegarespeciais").setExecutor(new PegarEspeciais());
		getCommand("giveespecial").setExecutor(new GiveEspecial());
		Bukkit.getConsoleSender().sendMessage("§aPlugin incializado com sucesso!");
	}
	
	public void onDisable() {
		
	}
	
}