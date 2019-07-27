package rush.bloquearencantamentos.util;

import org.bukkit.enchantments.Enchantment;

public enum EnchantmentName {
	
	ARROW_DAMAGE("For�a"),
	ARROW_FIRE("Chama"),
	ARROW_INFINITE("Infinidade"),
	ARROW_KNOCKBACK("Impacto"),
	BINDING_CURSE("Maldi��o da liga��o"),
	CHANNELING("Condutividade"),
	DAMAGE_ALL("Afia��o"),
	DAMAGE_ARTHROPODS("Ru�na dos artr�podes"),
	DAMAGE_UNDEAD("Julgamento"),
	DEPTH_STRIDER("Passos profundos"),
	DIG_SPEED("Efici�ncia"),
	DURABILITY("Inquebr�vel"),
	FIRE_ASPECT("Aspecto flamejante"),
	FROST_WALKER("Passos gelados"),
	IMPALING("Perfura��o"),
	KNOCKBACK("Repuls�o"),
	LOOT_BONUS_BLOCKS("Fortuna"),
	LOOT_BONUS_MOBS("Pilhagem"),
	LOYALTY("Lealdade"),
	LUCK("Sorte do mar"),
	LURE("Isca"),
	MENDING("Remendo"),
	OXYGEN("Respira��o"),
	PROTECTION_ENVIRONMENTAL("Prote��o"),
	PROTECTION_EXPLOSIONS("Prote��o contra explos�es"),
	PROTECTION_FALL("Peso pena"),
	PROTECTION_FIRE("Prote��o contra o fogo"),
	PROTECTION_PROJECTILE("Prote��o contra proj�teis"),
	RIPTIDE("Correnteza"),
	SILK_TOUCH("Toque suave"),
	SWEEPING_EDGE("Alcance"),
	THORNS("Espinhos"),
	VANISHING_CURSE("Maldi��o do desaparecimento"),
	WATER_WORKER("Afinidade aqu�tica");

	private String name;
	
	EnchantmentName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static EnchantmentName valueOf(Enchantment enchantment) {
		return EnchantmentName.valueOf(enchantment.getName());
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
}