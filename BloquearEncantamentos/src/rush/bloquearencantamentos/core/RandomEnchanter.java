package rush.bloquearencantamentos.core;

import static org.bukkit.enchantments.Enchantment.ARROW_DAMAGE;
import static org.bukkit.enchantments.Enchantment.ARROW_FIRE;
import static org.bukkit.enchantments.Enchantment.ARROW_INFINITE;
import static org.bukkit.enchantments.Enchantment.ARROW_KNOCKBACK;
import static org.bukkit.enchantments.Enchantment.DAMAGE_ALL;
import static org.bukkit.enchantments.Enchantment.DAMAGE_ARTHROPODS;
import static org.bukkit.enchantments.Enchantment.DAMAGE_UNDEAD;
import static org.bukkit.enchantments.Enchantment.DEPTH_STRIDER;
import static org.bukkit.enchantments.Enchantment.DIG_SPEED;
import static org.bukkit.enchantments.Enchantment.DURABILITY;
import static org.bukkit.enchantments.Enchantment.FIRE_ASPECT;
import static org.bukkit.enchantments.Enchantment.KNOCKBACK;
import static org.bukkit.enchantments.Enchantment.LOOT_BONUS_BLOCKS;
import static org.bukkit.enchantments.Enchantment.LOOT_BONUS_MOBS;
import static org.bukkit.enchantments.Enchantment.LUCK;
import static org.bukkit.enchantments.Enchantment.LURE;
import static org.bukkit.enchantments.Enchantment.OXYGEN;
import static org.bukkit.enchantments.Enchantment.PROTECTION_ENVIRONMENTAL;
import static org.bukkit.enchantments.Enchantment.PROTECTION_EXPLOSIONS;
import static org.bukkit.enchantments.Enchantment.PROTECTION_FALL;
import static org.bukkit.enchantments.Enchantment.PROTECTION_FIRE;
import static org.bukkit.enchantments.Enchantment.PROTECTION_PROJECTILE;
import static org.bukkit.enchantments.Enchantment.SILK_TOUCH;
import static org.bukkit.enchantments.Enchantment.THORNS;
import static org.bukkit.enchantments.Enchantment.WATER_WORKER;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;

import rush.bloquearencantamentos.Main;
import rush.bloquearencantamentos.util.BukkitUtil;
import rush.bloquearencantamentos.util.Sets;

public class RandomEnchanter {
	
	private static final Set<Enchantment> GENERAL;
	private static final Set<Enchantment> ARMOR;
	private static final Set<Enchantment> ARMOR_FEET;
	private static final Set<Enchantment> ARMOR_HEAD;
	private static final Set<Enchantment> ARMOR_BODY;
	private static final Set<Enchantment> WEAPON; 
	private static final Set<Enchantment> TOOL;
	private static final Set<Enchantment> BOW;
	private static final Set<Enchantment> FISHING;
	private static final Set<Set<Enchantment>> REFERENCES;
	private static final Random RANDOM = new Random();
	
	static {
		GENERAL = Sets.newHashSet(DURABILITY);
		ARMOR = Sets.union(GENERAL, Sets.newHashSet(PROTECTION_ENVIRONMENTAL, PROTECTION_EXPLOSIONS, PROTECTION_FIRE, PROTECTION_PROJECTILE));
		ARMOR_FEET = Sets.union(ARMOR, Sets.newHashSet(PROTECTION_FALL, DEPTH_STRIDER));
		ARMOR_HEAD = Sets.union(ARMOR, Sets.newHashSet(WATER_WORKER, OXYGEN));
		ARMOR_BODY = Sets.union(ARMOR, Sets.newHashSet(THORNS));
		WEAPON = Sets.union(GENERAL, Sets.newHashSet(DAMAGE_ALL, DAMAGE_ARTHROPODS, DAMAGE_UNDEAD, KNOCKBACK, FIRE_ASPECT, LOOT_BONUS_MOBS));
		TOOL = Sets.union(GENERAL, Sets.newHashSet(DIG_SPEED, SILK_TOUCH, LOOT_BONUS_BLOCKS));
		BOW = Sets.union(GENERAL, Sets.newHashSet(ARROW_KNOCKBACK, ARROW_DAMAGE, ARROW_INFINITE, ARROW_FIRE));
		FISHING = Sets.union(GENERAL, Sets.newHashSet(LUCK, LURE));
		REFERENCES = Sets.newHashSet(GENERAL, ARMOR, ARMOR_FEET, ARMOR_HEAD, ARMOR_BODY, WEAPON, TOOL, BOW, FISHING);
	}
	
	public RandomEnchanter(Main main) {
		List<String> abs = main.getConfig().getStringList("Encantamentos-Bloqueados-Mesa");
		List<Enchantment> removes = BukkitUtil.parseSampleEnchantments(abs);
		for (Set<Enchantment> set : REFERENCES) {
			set.removeAll(removes);
		}
	}
	
	public static Map<Enchantment, Integer> next(Map<Enchantment, Integer> addEnchants, Enchantment oldEnchant, Integer xpCost) {
		Enchantment newEnchant = randomEnchantment(oldEnchant);
		int notInfinite = 0;
		
		while (addEnchants.containsKey(newEnchant)) {
			newEnchant = randomEnchantment(oldEnchant);
			if (notInfinite++ == 10) break;
		}
		Integer newLevel = randomLevel(newEnchant, xpCost);
		
		return Collections.singletonMap(newEnchant, newLevel);
	}

	private static Enchantment randomEnchantment(Enchantment enchant) {
		EnchantmentTarget target = enchant.getItemTarget();
		if (target == null) target= EnchantmentTarget.ALL;
		switch(target) {
			case ALL: return Sets.randomValue(GENERAL);
			case ARMOR: return Sets.randomValue(ARMOR);
			case ARMOR_FEET: return Sets.randomValue(ARMOR_FEET);
			case ARMOR_HEAD: return Sets.randomValue(ARMOR_HEAD);
			case ARMOR_LEGS: return Sets.randomValue(ARMOR_BODY);
			case ARMOR_TORSO: return Sets.randomValue(ARMOR_BODY);
			case WEAPON: return Sets.randomValue(WEAPON);
			case TOOL: return Sets.randomValue(TOOL);
			case BOW: return Sets.randomValue(BOW);
			case FISHING_ROD: return Sets.randomValue(FISHING);
			default: return Sets.randomValue(GENERAL);		
		}
	}

	private static Integer randomLevel(Enchantment newEnchant, Integer level) {
		int cost = (level <= 10) ? 1 : ((level <= 20) ? 2 : 3);
		int maxLevel = newEnchant.getMaxLevel();
		switch (cost) {
			case 1: cost += RANDOM.nextInt(1);
				break;
			case 2: cost += RANDOM.nextInt(2);
				break;
			case 3: cost += RANDOM.nextInt(2) + 1;
				break;
		}
		return cost > maxLevel ? maxLevel : cost;
	}
}	