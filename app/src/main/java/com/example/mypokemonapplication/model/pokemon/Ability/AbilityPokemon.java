package com.example.mypokemonapplication.model.pokemon.Ability;

import com.example.mypokemonapplication.model.pokemon.pokemondetail.Pokemon;
import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.google.gson.annotations.SerializedName;

public class AbilityPokemon {
    @SerializedName("is_hidden")
    private boolean isHidden;

    private int slot;

    private NamedAPIResource pokemon;

    public AbilityPokemon(boolean isHidden, int slot, NamedAPIResource pokemon) {
        this.isHidden = isHidden;
        this.slot = slot;
        this.pokemon = pokemon;
    }

    public AbilityPokemon() {
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public NamedAPIResource getPokemon() {
        return pokemon;
    }

    public void setPokemon(NamedAPIResource pokemon) {
        this.pokemon = pokemon;
    }
}
