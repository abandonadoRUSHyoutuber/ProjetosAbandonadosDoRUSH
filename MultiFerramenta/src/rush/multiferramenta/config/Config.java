package rush.multiferramenta.config;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public final class Config {

	public static String PLAYER_OFFLINE, COMANDO_INCORRETO, GIVADO_SUCESSO;
	public static ItemStack MULTI_FERRAMENTA;
	public static Boolean DROPAR;
	
	public Config(Plugin plugin) {
		FileConfiguration config = plugin.getConfig();
		PLAYER_OFFLINE = config.getString("Player-Offline").replace('&', '§');
		COMANDO_INCORRETO = config.getString("Comando-Incorreto").replace('&', '§');
		GIVADO_SUCESSO = config.getString("Ferramenta-Enviada").replace('&', '§');
		DROPAR = config.getBoolean("Dropar-Itens");
		
		MULTI_FERRAMENTA = new ItemStack(Material.SHEARS);
		ItemMeta meta = MULTI_FERRAMENTA.getItemMeta();
		meta.setDisplayName(config.getString("Item-Nome").replace('&', '§'));
		meta.addItemFlags(ItemFlag.values());
		meta.addEnchant(Enchantment.DURABILITY, Byte.MAX_VALUE, true);
		meta.setLore(Arrays.asList(
				"§c ",
				"§fEste item pode ser usado para ",
				"§fquebrar todo tipo de bloco.",
				"§fDurabilidade: " + config.getInt("Durabilidade-Inicial")));
		MULTI_FERRAMENTA.setItemMeta(meta);
	}
	
}
