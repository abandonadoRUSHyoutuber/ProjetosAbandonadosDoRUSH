package rush.bloquearencantamentos.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public final class BukkitUtil {

	public static List<Enchantment> parseSampleEnchantments(List<String> abs) {
		List<Enchantment> list = new ArrayList<>();
		for (String str : abs)
			list.add(Enchantment.getByName(str.toUpperCase().split(":")[0]));
		return list;
	}

	public static Map<Enchantment, List<Integer>> parseEnchantments(List<String> abs) {
		Map<Enchantment, List<Integer>> map = new HashMap<>();
		for (String str : abs) {
			Enchantment ench = Enchantment.getByName(str.split(":")[0].toUpperCase());
			if (map.containsKey(ench)) {
				map.get(ench).add(Integer.parseInt(str.split(":")[1]));
			} else {
				map.put(ench, new ArrayList<>());
				String level = str.split(":")[1];
				if (level.equals("*")) map.get(ench).clear();
				else map.get(ench).add(Integer.parseInt(level));
			}
		}
		return map;
	}
	
	public static List<Material> parseMaterials(List<String> abs) {
		List<Material> list = new ArrayList<>();
		for (String str : abs)
			list.add(Material.valueOf(str.toUpperCase().split(":")[0]));
		return list;
	}
	
	public static String romanNumber(Integer arabic) {
		switch (arabic) {
			case 1: return "I";
			case 2: return "II";
			case 3:	return "III";
			case 4:	return "IV";
			case 5: return "V";
			case 6: return "VI";
			case 7: return "VII";
			case 8: return "VIII";
			case 9: return "IX";
			case 10: return "X";
			default: return arabic.toString();
		}
	}

	
	
}