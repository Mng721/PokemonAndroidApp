package com.example.mypokemonapplication.model.pokemon.pokemondetail;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;

public class PokemonType {
    private int slot;
    private NamedAPIResource type;

    public PokemonType(int slot, NamedAPIResource type) {
        this.slot = slot;
        this.type = type;
    }

    public PokemonType() {
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public NamedAPIResource getType() {
        return type;
    }

    public void setType(NamedAPIResource type) {
        this.type = type;
    }
}
