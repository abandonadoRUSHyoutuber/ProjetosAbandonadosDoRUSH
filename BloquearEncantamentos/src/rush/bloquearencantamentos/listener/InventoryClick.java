package rush.bloquearencantamentos.listener;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

import rush.bloquearencantamentos.Main;
import rush.bloquearencantamentos.util.BukkitUtil;
import rush.bloquearencantamentos.util.EnchantmentName;

public class InventoryClick implements Listener {

	private String repairError;
	private String junctionError;
	private boolean checkMaterial;
	private List<Material> materials;
	private Map<Enchantment, List<Integer>> enchants;
	
	public InventoryClick(Main main) {
		FileConfiguration config = main.getConfig();
		repairError = config.getString("Encantamento-Bloqueado-Reparacao", "").replace('&', '§');
		junctionError = config.getString("Encantamento-Bloqueado-Juncao", "").replace('&', '§');
		checkMaterial = !config.getBoolean("Bloquear-Encantamentos-Em-Todos-Materiais");
		materials = BukkitUtil.parseMaterials(config.getStringList("Lista-De-Materiais-Com-Encantamentos-Bloqueados"));
		enchants = BukkitUtil.parseEnchantments(config.getStringList("Encantamentos-Bloqueados-Bigorna"));
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		// Verificando se o inventario é uma bigorna
		if (e.getInventory() instanceof AnvilInventory) {
			
			// Verificando se o slot é o final
			if (e.getSlotType() == SlotType.RESULT) {
				
				// Pegando o item e verificando se é valido
				ItemStack item = e.getCurrentItem();
				if (item != null && item.getType() != Material.AIR) {
					
					// Verificando se o item não esta sendo apenas renomeado
					ItemStack consume = e.getInventory().getItem(1);
					if (consume == null || consume.getType() == Material.AIR) return;
					
					// Pegando o player e verificando se ele tem permissão de bypass
					Player p = (Player) e.getWhoClicked();
					if (p.hasPermission("bloquearencantamentos.bypass")) return;
					
					// Verificando se o materia é valido
					if (!checkMaterial || (checkMaterial && materials.contains(item.getType()))) {
						
						// Fazendo um loop por todos os encantamentos do bloqueados
						Map<Enchantment, Integer> itemEnchants = item.getEnchantments();
						for (Entry<Enchantment, List<Integer>> entry : enchants.entrySet()) {
							
							// Pegando o encantamento e o nível e verificando se eles estão bloqueados
							Enchantment ench = entry.getKey();
							List<Integer> levels = entry.getValue();
							if (itemEnchants.containsKey(ench) && (levels.isEmpty() || levels.contains(itemEnchants.get(ench)))) {
								
								// Cancelando o evento
								e.setCancelled(true);
								e.setResult(Result.DENY);
								
								// Pegando o nível do item
								String level = BukkitUtil.romanNumber(itemEnchants.get(ench));
								
								// Verificando se o item esta sendo reparado ou agrupado
								if (consume.getType().getMaxDurability() == 0 && consume.getType() != Material.ENCHANTED_BOOK) 
									p.sendMessage(repairError.replace("{l}", level).replace("{e}", EnchantmentName.valueOf(ench).getName()));
								else 
									p.sendMessage(junctionError.replace("{l}", level).replace("{e}", EnchantmentName.valueOf(ench).getName()));
								return;
							}
						}
					}
				}
			}
		}
	}
}