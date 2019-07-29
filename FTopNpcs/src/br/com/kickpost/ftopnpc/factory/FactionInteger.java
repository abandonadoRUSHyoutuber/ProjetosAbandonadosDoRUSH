package br.com.kickpost.ftopnpc.factory;

import com.massivecraft.factions.entity.Faction;

public class FactionInteger {

	private Faction clan;
	private Double fortuna;

	public FactionInteger(Faction clan, Double fortuna) {
		this.clan = clan;
		this.fortuna = fortuna;
	}

	public Faction getFaction() {
		return clan;
	}

	public void setFaction(Faction clan) {
		this.clan = clan;
	}

	public Double getFortuna() {
		return fortuna;
	}

	public void setFortuna(double fortuna) {
		this.fortuna = fortuna;
	}

}
