package com.example.mypokemonapplication.model.pokemon.pokemondetail;

import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;

import java.util.List;

public class PokemonTypePast {
    private NamedAPIResource generation;
    private List<PokemonType> types;

    public PokemonTypePast(NamedAPIResource generation, List<PokemonType> types) {
        this.generation = generation;
        this.types = types;
    }

    public PokemonTypePast() {
    }

    public NamedAPIResource getGeneration() {
        return generation;
    }

    public void setGeneration(NamedAPIResource generation) {
        this.generation = generation;
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonType> types) {
        this.types = types;
    }
}
