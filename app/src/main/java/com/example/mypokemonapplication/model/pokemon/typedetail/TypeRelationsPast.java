package com.example.mypokemonapplication.model.pokemon.typedetail;

import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.google.gson.annotations.SerializedName;

public class TypeRelationsPast {
    private NamedAPIResource generation;
    @SerializedName("damage_relations")
    private TypeRelations damageRelations;

    public TypeRelationsPast() {
    }

    public TypeRelationsPast(NamedAPIResource generation, TypeRelations damageRelations) {
        this.generation = generation;
        this.damageRelations = damageRelations;
    }

    public NamedAPIResource getGeneration() {
        return generation;
    }

    public void setGeneration(NamedAPIResource generation) {
        this.generation = generation;
    }

    public TypeRelations getDamageRelations() {
        return damageRelations;
    }

    public void setDamageRelations(TypeRelations damageRelations) {
        this.damageRelations = damageRelations;
    }
}
