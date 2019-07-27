package rush.stackmobs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	private static int MAX_STACK = 0;
	private static String NAME = "§e%quantia%x %tipo%";
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		MAX_STACK = getConfig().getInt("Limite-Stack");
		NAME = getConfig().getString("Nome-Do-Stack").replace('&', '§');
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onSpawn(CreatureSpawnEvent e) {
		
		LivingEntity spawned = e.getEntity();
		boolean stack = spawned.hasMetadata("stack");
		SpawnReason reason = e.getSpawnReason();
		
		if ((reason == SpawnReason.EGG) || (reason == SpawnReason.CUSTOM && !stack)) return;
		
		EntityType spawnedType = e.getEntityType();
		for (Entity entity : spawned.getNearbyEntities(15D, 15D, 15D)) {
			if (entity.getType() == spawnedType && !entity.isDead()) {
				e.setCancelled(true);
				int amount = 1;
				LivingEntity living = (LivingEntity) entity;
				
				if (entity.hasMetadata("stack")) 
					amount += entity.getMetadata("stack").isEmpty() ? 0 : entity.getMetadata("stack").get(0).asInt();
				
				if (stack) 
					amount += living.getMetadata("stack").isEmpty() ? 0 : living.getMetadata("stack").get(0).asInt();
				
				if (amount > MAX_STACK) {
					e.setCancelled(true);
					return;
				}
				
				String type = EntityName.valueOf(e.getEntityType()).getName();
				living.setCustomName(NAME.replace("%tipo%", type).replace("%quantia%", String.valueOf(amount)));
				living.setCustomNameVisible(true);
				living.setMetadata("stack",  new FixedMetadataValue(this, amount));
				return;
			}
		}
		
		if (!spawned.hasMetadata("stack")) {
			String type = EntityName.valueOf(e.getEntityType()).getName();
			spawned.setCustomName(NAME.replace("%tipo%", type).replace("%quantia%", String.valueOf(1)));
			spawned.setCustomNameVisible(true);
			spawned.setMetadata("stack",  new FixedMetadataValue(this, 1));
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onDeath(EntityDeathEvent e) {
		LivingEntity entity = e.getEntity();
		if (entity.hasMetadata("stack")) {
			int cont = entity.getMetadata("stack").isEmpty() ? 0 : entity.getMetadata("stack").get(0).asInt();
			if (cont > 1) {
				if (entity.getKiller() != null && entity.getKiller().isSneaking()) {
					e.setDroppedExp(e.getDroppedExp() * cont);
					for (ItemStack drop : e.getDrops()) {
						if (drop.getType().getMaxDurability() == 0) {
							drop.setAmount(drop.getAmount() * cont);
						}
					}
				} else {
					String type = EntityName.valueOf(e.getEntityType()).getName();
					LivingEntity spawned = (LivingEntity) entity.getWorld().spawnEntity(entity.getLocation(), e.getEntityType());
					spawned.setCustomName(NAME.replace("%tipo%", type).replace("%quantia%", String.valueOf(--cont)));
					spawned.setCustomNameVisible(true);
					spawned.setMetadata("stack", new FixedMetadataValue(this, cont));
				}
			}
		}
	}
	
}

enum EntityName {

	AREA_EFFECT_CLOUD("Área de Efeito de Poção"),
	ARMOR_STAND("Suporte para Armaduras"),
	ARROW("Flecha"),
	BAT("Morcego"),
	BLAZE("Blaze"),
	BOAT("Barco"),
	CAVE_SPIDER("Aranha da Caverna"),
	CHICKEN("Galinha"),
	COD("Bacalhau"),
	COMPLEX_PART("Desconhecido"),
	COW("Vaca"),
	CREEPER("Creeper"),
	DOLPHIN("Golfinho"),
	DONKEY("Burro"),
	DRAGON_FIREBALL("Bola de Fogo"),
	DROPPED_ITEM("Item dropado"),
	DROWNED("Afogado"),
	EGG("Ovo"),
	ELDER_GUARDIAN("Guardião Mestre"),
	ENDER_CRYSTAL("Cristal do End"),
	ENDER_DRAGON("Dragão do Fim"),
	ENDER_PEARL("Pérola do Fim"),
	ENDER_SIGNAL("Olho do Fim"),
	ENDERMAN("Enderman"),
	ENDERMITE("Endermite"),
	EVOKER("Invocador"),
	EVOKER_FANGS("Presas do Invocador"),
	EXPERIENCE_ORB("Orb de Experiência"),
	FALLING_BLOCK("Bloco Caindo"),
	FIREBALL("Bola de Fogo"),
	FIREWORK("Fogos de Artifício"),
	FISHING_HOOK("Isca da Vara de Pesca"),
	GHAST("Ghast"),
	GIANT("Zumbi Gigante"),
	GUARDIAN("Guardião"),
	HORSE("Cavalo"),
	HUSK("Zumbi do Deserto"),
	ILLUSIONER("Ilusionista"),
	IRON_GOLEM("Golem de Ferro"),
	ITEM_FRAME("Moldura"),
	LEASH_HITCH("Desconhecido"),
	LIGHTNING("Raio"),
	LINGERING_POTION("Poção"),
	LLAMA("Lhama"),
	LLAMA_SPIT("Cuspe de Lhama"),
	MAGMA_CUBE("Cubo de Magma"),
	MINECART("Carrinho"),
	MINECART_CHEST("Carrinho com Baú"),
	MINECART_COMMAND("Carrinho com Bloco de Comando"),
	MINECART_FURNACE("Carrinho com Fornalha"),
	MINECART_HOPPER("Carrinho com Funil"),
	MINECART_MOB_SPAWNER("Carrinho com Gerador de Monstros"),
	MINECART_TNT("Carrinho com Dinamite"),
	MULE("Mula"),
	MUSHROOM_COW("Vaca de Cogumelo"),
	OCELOT("Jaguatirica"),
	PAINTING("Pintura"),
	PARROT("Papagaio"),
	PHANTOM("Phantom"),
	PIG("Porco"),
	PIG_ZOMBIE("Porco Zumbi"),
	PLAYER("Player"),
	POLAR_BEAR("Urso Polar"),
	PRIMED_TNT("Dinamite"),
	PUFFERFISH("Baiacu"),
	RABBIT("Coelho"),
	SALMON("Salmão"),
	SHEEP("Ovelha"),
	SHULKER("Shulker"),
	SHULKER_BULLET("Dardo de Shulker"),
	SILVERFISH("Silverfish"),
	SKELETON("Esqueleto"),
	SKELETON_HORSE("Cavalo Esqueleto"),
	SLIME("Slime"),
	SMALL_FIREBALL("Bola de Fogo Pequena"),
	SNOWBALL("Bola de Neve"),
	SNOWMAN("Golem de Neve"),
	SPECTRAL_ARROW("Flecha Espectral"),
	SPIDER("Aranha"),
	SPLASH_POTION("Poção Arremessável"),
	SQUID("Lula"),
	STRAY("Esqueleto Vagante"),
	THROWN_EXP_BOTTLE("Frasco de Experiência"),
	TIPPED_ARROW("Flecha"),
	TRIDENT("Tridente"),
	TROPICAL_FISH("Peixe Tropical"),	 
	TURTLE("Tartaruga"),
	UNKNOWN("Desconhecido"),
	VEX("Fantasma"),
	VILLAGER("Vilager"),
	VINDICATOR("Vingador"),
	WEATHER("Chuva"),
	WITCH("Bruxa"),
	WITHER("Wither"),
	WITHER_SKELETON("Esqueleto Wither"),
	WITHER_SKULL("Cabeça do Wither"),
	WOLF("Lobo"),
	ZOMBIE("Zumbi"),
	ZOMBIE_HORSE("Cavalo Zumbi"),
	ZOMBIE_VILLAGER("Aldeão Zumbi");

	private String name;
	
	EntityName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static EntityName valueOf(Entity entity) {
		return valueOf(entity.getType());
	}
	
	public static EntityName valueOf(EntityType entityType) {
		return valueOf(entityType.name());
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
}