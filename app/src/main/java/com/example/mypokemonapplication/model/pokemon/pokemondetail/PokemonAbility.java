package com.example.mypokemonapplication.model.pokemon.pokemondetail;

import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.google.gson.annotations.SerializedName;

public class PokemonAbility {
    @SerializedName("is_hidden")
    private boolean isHidden;
    private int slot;
    private NamedAPIResource ability;

    public PokemonAbility(boolean isHidden, int slot, NamedAPIResource ability) {
        this.isHidden = isHidden;
        this.slot = slot;
        this.ability = ability;
    }

    public PokemonAbility() {
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

    public NamedAPIResource getAbility() {
        return ability;
    }

    public void setAbility(NamedAPIResource ability) {
        this.ability = ability;
    }
}
