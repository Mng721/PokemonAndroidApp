package com.example.mypokemonapplication.model.pokemon.pokemondetail;

import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.example.mypokemonapplication.model.utility.common_models.VersionGameIndex;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class Pokemon {
    private int id;
    private String name;
    @SerializedName("base_experience")
    private int baseExperience;
    private int height;
    @SerializedName("is_default")
    private boolean increasedStat;
    private int order;
    private int weight;
    private List<PokemonAbility> abilities;
    private List<NamedAPIResource> forms;
    @SerializedName("game_indices")
    private List<VersionGameIndex> gameIndices;
    @SerializedName("head_items")
    private List<PokemonHeldItem> heldItems;
    @SerializedName("location_area_encounters")
    private String locationAreaEncounters;
    private List<PokemonMove> moves;
    @SerializedName("past_types")
    private List<PokemonTypePast> pastTypes;
    private PokemonSprites sprites;
    private NamedAPIResource species;
    private List<PokemonStat> stats;
    private List<PokemonType> types;

    public Pokemon() {
    }

    public Pokemon(int id, String name, int baseExperience, int height, boolean increasedStat, int order, int weight, List<PokemonAbility> abilities, List<NamedAPIResource> forms, List<VersionGameIndex> gameIndices, List<PokemonHeldItem> heldItems, String locationAreaEncounters, List<PokemonMove> moves, List<PokemonTypePast> pastTypes, PokemonSprites sprites, NamedAPIResource species, List<PokemonStat> stats, List<PokemonType> types) {
        this.id = id;
        this.name = name;
        this.baseExperience = baseExperience;
        this.height = height;
        this.increasedStat = increasedStat;
        this.order = order;
        this.weight = weight;
        this.abilities = abilities;
        this.forms = forms;
        this.gameIndices = gameIndices;
        this.heldItems = heldItems;
        this.locationAreaEncounters = locationAreaEncounters;
        this.moves = moves;
        this.pastTypes = pastTypes;
        this.sprites = sprites;
        this.species = species;
        this.stats = stats;
        this.types = types;
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

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public int getHeight() {
        return height;
    }

    public String getHeightString(int height) {
        String heightString = Integer.toString(height);
        String heightText;
        if (height <10){
            heightText = "0." + heightString.substring(heightString.length()-1) + "m";
        } else {
            heightText = heightString.substring(0, heightString.length()-1) + "." + heightString.substring(heightString.length()-1) + " m";
        }
        return heightText;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isIncreasedStat() {
        return increasedStat;
    }

    public void setIncreasedStat(boolean increasedStat) {
        this.increasedStat = increasedStat;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getWeight() {
        return weight;
    }

    public String getWeightString(int weight) {
        String weightString = Integer.toString(weight);
        String weightText;
        if (weight < 10){
            weightText =  "0." + weightString.substring(weightString.length()-1) + " kg";
        } else {
            weightText = weightString.substring(0, weightString.length() -1) + "." + weightString.substring(weightString.length()-1) + " kg";
        }
        return weightText;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<PokemonAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<PokemonAbility> abilities) {
        this.abilities = abilities;
    }

    public List<NamedAPIResource> getForms() {
        return forms;
    }

    public void setForms(List<NamedAPIResource> forms) {
        this.forms = forms;
    }

    public List<VersionGameIndex> getGameIndices() {
        return gameIndices;
    }

    public void setGameIndices(List<VersionGameIndex> gameIndices) {
        this.gameIndices = gameIndices;
    }

    public List<PokemonHeldItem> getHeldItems() {
        return heldItems;
    }

    public void setHeldItems(List<PokemonHeldItem> heldItems) {
        this.heldItems = heldItems;
    }

    public String getLocationAreaEncounters() {
        return locationAreaEncounters;
    }

    public void setLocationAreaEncounters(String locationAreaEncounters) {
        this.locationAreaEncounters = locationAreaEncounters;
    }

    public List<PokemonMove> getMoves() {
        return moves;
    }

    public void setMoves(List<PokemonMove> moves) {
        this.moves = moves;
    }

    public List<PokemonTypePast> getPastTypes() {
        return pastTypes;
    }

    public void setPastTypes(List<PokemonTypePast> pastTypes) {
        this.pastTypes = pastTypes;
    }

    public PokemonSprites getSprites() {
        return sprites;
    }

    public void setSprites(PokemonSprites sprites) {
        this.sprites = sprites;
    }

    public NamedAPIResource getSpecies() {
        return species;
    }

    public void setSpecies(NamedAPIResource species) {
        this.species = species;
    }

    public List<PokemonStat> getStats() {
        return stats;
    }

    public void setStats(List<PokemonStat> stats) {
        this.stats = stats;
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonType> types) {
        this.types = types;
    }

    public String getNumber(int id) {
        if (id < 10) {
            return "00" + id;
        } else if (id < 100) {
            return "0" + id;
        } else if (id >= 10000) {
            return "0";
        }
        return Integer.toString(id);
    }

    public int hpStatCalculator(int base, int IV, int EV, int level) {
        return (((2 * base + IV + (EV / 4)) * level) / 100) + level + 10;
    }

    public int otherStatCalculator(int base, int IV, int EV, int level, float nature) {
        return (int) (((((2 * base + IV + (EV / 4)) * level) / 100) + 5) * nature);
    }
}
