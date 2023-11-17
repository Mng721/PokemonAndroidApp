package com.example.mypokemonapplication.model.pokemon.pokemondetail;

import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.google.gson.annotations.SerializedName;

public class PokemonStat {
    private NamedAPIResource stat;
    private int effort;
    @SerializedName("base_stat")
    private int baseStat;

    public PokemonStat(NamedAPIResource stat, int effort, int baseStat) {
        this.stat = stat;
        this.effort = effort;
        this.baseStat = baseStat;
    }

    public PokemonStat() {
    }

    public NamedAPIResource getStat() {
        return stat;
    }

    public void setStat(NamedAPIResource stat) {
        this.stat = stat;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    public int getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(int baseStat) {
        this.baseStat = baseStat;
    }
}
