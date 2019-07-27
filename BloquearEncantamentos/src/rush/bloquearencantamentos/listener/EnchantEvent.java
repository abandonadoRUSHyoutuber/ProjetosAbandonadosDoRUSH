package rush.bloquearencantamentos.listener;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;

import rush.bloquearencantamentos.Main;
import rush.bloquearencantamentos.core.RandomEnchanter;
import rush.bloquearencantamentos.util.BukkitUtil;

public class EnchantEvent implements Listener {
	
	private boolean checkMaterial;
	private List<Material> materials;
	private Map<Enchantment, List<Integer>> enchants;
	
	public EnchantEvent(Main main) {
		FileConfiguration config = main.getConfig();
		checkMaterial = !config.getBoolean("Bloquear-Encantamentos-Em-Todos-Materiais");
		materials = BukkitUtil.parseMaterials(config.getStringList("Lista-De-Materiais-Com-Encantamentos-Bloqueados"));
		enchants = BukkitUtil.parseEnchantments(config.getStringList("Encantamentos-Bloqueados-Mesa"));
	}
	
	@EventHandler
	public void onEnchant(EnchantItemEvent e) {
		// Pegando o item e verificando se é valido
		ItemStack item = e.getItem();
		if (item != null && item.getType() != Material.AIR) {
			
			// Pegando o player e verificando se ele tem permissão de bypass
			Player p = e.getEnchanter();
			if (p.hasPermission("bloquearencantamentos.bypass")) return;
			
			// Verificando se o materia é valido
			if (!checkMaterial || (checkMaterial && materials.contains(item.getType()))) {
				
				// Fazendo um loop por todos os encantamentos bloqueados
				Map<Enchantment, Integer> addEnchants = e.getEnchantsToAdd();
				for (Entry<Enchantment, List<Integer>> entry : enchants.entrySet()) {
					
					// Pegando o encantamento e o nível e verificando se eles estão bloqueados
					Enchantment ench = entry.getKey();
					List<Integer> levels = entry.getValue();
					if (addEnchants.containsKey(ench) && (levels.isEmpty() || levels.contains(addEnchants.get(ench)))) {
						
						// Removendo o encantamento bloqueado
						addEnchants.remove(ench);
						
						// Peganado o novo encantamento e adicionado ele se possivel
						Map<Enchantment, Integer> randomEnchant = RandomEnchanter.next(addEnchants, ench, e.getExpLevelCost());
						if (randomEnchant != null) {
							addEnchants.putAll(randomEnchant);
						}
					}
				}
			}
		}
	}

}