package com.example.mypokemonapplication.model.pokemon.pokemondetail;

import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonMove {
    private NamedAPIResource move;
    @SerializedName("version_group_details")
    private List<PokemonMoveVersion> versionGroupDetails;

    public NamedAPIResource getMove() {
        return move;
    }

    public void setMove(NamedAPIResource move) {
        this.move = move;
    }

    public List<PokemonMoveVersion> getVersionGroupDetails() {
        return versionGroupDetails;
    }

    public void setVersionGroupDetails(List<PokemonMoveVersion> versionGroupDetails) {
        this.versionGroupDetails = versionGroupDetails;
    }

    public PokemonMove(NamedAPIResource move, List<PokemonMoveVersion> versionGroupDetails) {
        this.move = move;
        this.versionGroupDetails = versionGroupDetails;
    }

    public PokemonMove() {
    }
}
