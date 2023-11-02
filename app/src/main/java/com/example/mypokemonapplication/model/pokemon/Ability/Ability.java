package com.example.mypokemonapplication.model.pokemon.Ability;

import com.example.mypokemonapplication.model.utility.common_models.Name;
import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.example.mypokemonapplication.model.utility.common_models.VerboseEffect;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ability {
    private int id;
    private String name;
    @SerializedName("is_main_series")
    private boolean isMainSeries;
    private NamedAPIResource generation;
    private List<Name> names;

    @SerializedName("effect_entries")
    private List<VerboseEffect> effectEntries;

    @SerializedName("effect_changes")
    private List<AbilityEffectChange> effectChanges;

    @SerializedName("flavor_text_entries")
    private List<AbilityFlavorText> flavorTextEntries;

    private List<AbilityPokemon> pokemon;

    public Ability(int id, String name, boolean isMainSeries, NamedAPIResource generation, List<Name> names, List<VerboseEffect> effectEntries, List<AbilityEffectChange> effectChanges, List<AbilityFlavorText> flavorTextEntries, List<AbilityPokemon> pokemon) {
        this.id = id;
        this.name = name;
        this.isMainSeries = isMainSeries;
        this.generation = generation;
        this.names = names;
        this.effectEntries = effectEntries;
        this.effectChanges = effectChanges;
        this.flavorTextEntries = flavorTextEntries;
        this.pokemon = pokemon;
    }

    public Ability() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMainSeries() {
        return isMainSeries;
    }

    public void setMainSeries(boolean mainSeries) {
        isMainSeries = mainSeries;
    }

    public NamedAPIResource getGeneration() {
        return generation;
    }

    public void setGeneration(NamedAPIResource generation) {
        this.generation = generation;
    }

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }

    public List<VerboseEffect> getEffectEntries() {
        return effectEntries;
    }

    public void setEffectEntries(List<VerboseEffect> effectEntries) {
        this.effectEntries = effectEntries;
    }

    public List<AbilityEffectChange> getEffectChanges() {
        return effectChanges;
    }

    public void setEffectChanges(List<AbilityEffectChange> effectChanges) {
        this.effectChanges = effectChanges;
    }

    public List<AbilityFlavorText> getFlavorTextEntries() {
        return flavorTextEntries;
    }

    public void setFlavorTextEntries(List<AbilityFlavorText> flavorTextEntries) {
        this.flavorTextEntries = flavorTextEntries;
    }

    public List<AbilityPokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<AbilityPokemon> pokemon) {
        this.pokemon = pokemon;
    }
}
