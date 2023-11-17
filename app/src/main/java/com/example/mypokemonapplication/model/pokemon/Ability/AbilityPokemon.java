package com.example.mypokemonapplication.model.pokemon.Ability;

import com.example.mypokemonapplication.model.pokemon.pokemondetail.Pokemon;
import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.google.gson.annotations.SerializedName;

public class AbilityPokemon {
    @SerializedName("is_hidden")
    private boolean isHidden;

    private int slot;

    private NamedAPIResource pokemon;
}
