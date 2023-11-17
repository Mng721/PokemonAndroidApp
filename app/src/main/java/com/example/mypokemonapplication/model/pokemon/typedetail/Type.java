package com.example.mypokemonapplication.model.pokemon.typedetail;

import com.example.mypokemonapplication.model.utility.common_models.GenerationGameIndex;
import com.example.mypokemonapplication.model.utility.common_models.Name;
import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Type {
    private int id;
    private String name;
    @SerializedName("damage_relations")
    private TypeRelations damageRelation;
    @SerializedName("past_damage_relations")
    private List<TypeRelationsPast> pastDamageRelations;
    @SerializedName("game_indices")
    private List<GenerationGameIndex> gameIndices;
    private NamedAPIResource generation;
    @SerializedName("move_damage_class")
    private NamedAPIResource moveDamageClass;
    private List<Name> names;
    private List<TypePokemon> pokemon;
    private List<NamedAPIResource> moves;

    public Type() {
    }

    public Type(int id, String name, TypeRelations damageRelation, List<TypeRelationsPast> pastDamageRelations, List<GenerationGameIndex> gameIndices, NamedAPIResource generation, NamedAPIResource moveDamageClass, List<Name> names, List<TypePokemon> pokemon, List<NamedAPIResource> moves) {
        this.id = id;
        this.name = name;
        this.damageRelation = damageRelation;
        this.pastDamageRelations = pastDamageRelations;
        this.gameIndices = gameIndices;
        this.generation = generation;
        this.moveDamageClass = moveDamageClass;
        this.names = names;
        this.pokemon = pokemon;
        this.moves = moves;
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

    public TypeRelations getDamageRelation() {
        return damageRelation;
    }

    public void setDamageRelation(TypeRelations damageRelation) {
        this.damageRelation = damageRelation;
    }

    public List<TypeRelationsPast> getPastDamageRelations() {
        return pastDamageRelations;
    }

    public void setPastDamageRelations(List<TypeRelationsPast> pastDamageRelations) {
        this.pastDamageRelations = pastDamageRelations;
    }

    public List<GenerationGameIndex> getGameIndices() {
        return gameIndices;
    }

    public void setGameIndices(List<GenerationGameIndex> gameIndices) {
        this.gameIndices = gameIndices;
    }

    public NamedAPIResource getGeneration() {
        return generation;
    }

    public void setGeneration(NamedAPIResource generation) {
        this.generation = generation;
    }

    public NamedAPIResource getMoveDamageClass() {
        return moveDamageClass;
    }

    public void setMoveDamageClass(NamedAPIResource moveDamageClass) {
        this.moveDamageClass = moveDamageClass;
    }

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }

    public List<TypePokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<TypePokemon> pokemon) {
        this.pokemon = pokemon;
    }

    public List<NamedAPIResource> getMoves() {
        return moves;
    }

    public void setMoves(List<NamedAPIResource> moves) {
        this.moves = moves;
    }
}
