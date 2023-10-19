package com.example.mypokemonapplication.model.pokemon.pokemondetail;

import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;

public class PokemonHeldItemVersion {
    private NamedAPIResource version;
    private int rarity;

    public PokemonHeldItemVersion() {
    }

    public PokemonHeldItemVersion(NamedAPIResource version, int rarity) {
        this.version = version;
        this.rarity = rarity;
    }

    public NamedAPIResource getVersion() {
        return version;
    }

    public void setVersion(NamedAPIResource version) {
        this.version = version;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }
}
