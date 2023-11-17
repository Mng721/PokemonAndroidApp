package com.example.mypokemonapplication.model.pokemon.typedetail;

import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;

public class TypePokemon {
    private int slot;
    private NamedAPIResource pokemon;

    public TypePokemon() {
    }

    public TypePokemon(int slot, NamedAPIResource pokemon) {
        this.slot = slot;
        this.pokemon = pokemon;
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
