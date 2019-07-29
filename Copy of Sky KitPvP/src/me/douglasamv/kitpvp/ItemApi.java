package me.douglasamv.kitpvp;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemApi {

	private ItemStack item;
	private Material material;
	private Boolean inquebravel;
	private Integer quantia;
	private short id;
	private String nome;
	private ArrayList<String> descricao;

	public ItemApi(Material material, Boolean inquebravel, Integer quantia, short id, String nome,
			ArrayList<String> descricao) {
		this.material = material;
		this.inquebravel = inquebravel;
		this.quantia = quantia;
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	public ItemStack criar() {
		this.item = new ItemStack(this.material, this.quantia, (short) this.id);
		ItemMeta meta = this.item.getItemMeta();
		meta.spigot().setUnbreakable(this.inquebravel);
		meta.setDisplayName(this.nome);
		this.item.setItemMeta(meta);
		return this.item;
	}

	public ItemStack criarDesc() {
		this.item = new ItemStack(this.material, this.quantia, (short) this.id);
		ItemMeta meta = this.item.getItemMeta();
		meta.spigot().setUnbreakable(this.inquebravel);
		List<String> Lore = new ArrayList<>();
		for (String loreString : this.descricao) {
			Lore.add(loreString.replace("&", "§"));
		}
		meta.setLore(Lore);
		meta.setDisplayName(this.nome);
		this.item.setItemMeta(meta);
		return this.item;
	}

}
