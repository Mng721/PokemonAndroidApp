package com.example.mypokemonapplication.model.pokemon.pokemondetail;

import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonHeldItem {
    private NamedAPIResource item;
    @SerializedName("version_details")
    private List<PokemonHeldItemVersion> vesionDetails;

    public PokemonHeldItem(NamedAPIResource item, List<PokemonHeldItemVersion> vesionDetails) {
        this.item = item;
        this.vesionDetails = vesionDetails;
    }

    public PokemonHeldItem() {
    }

    public NamedAPIResource getItem() {
        return item;
    }

    public void setItem(NamedAPIResource item) {
        this.item = item;
    }

    public List<PokemonHeldItemVersion> getVesionDetails() {
        return vesionDetails;
    }

    public void setVesionDetails(List<PokemonHeldItemVersion> vesionDetails) {
        this.vesionDetails = vesionDetails;
    }
}
