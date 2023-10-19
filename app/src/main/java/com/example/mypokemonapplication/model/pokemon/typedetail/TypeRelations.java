package com.example.mypokemonapplication.model.pokemon.typedetail;

import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TypeRelations {
    @SerializedName("no_damage_to")
    private List<NamedAPIResource> noDamageTo;
    @SerializedName("half_damage_to")
    private List<NamedAPIResource> halfDamageTo;
    @SerializedName("double_damage_to")
    private List<NamedAPIResource> doubleDamageTo;
    @SerializedName("no_damage_from")
    private List<NamedAPIResource> noDamageFrom;
    @SerializedName("half_damage_from")
    private List<NamedAPIResource> halfDamageFrom;
    @SerializedName("double_damage_from")
    private List<NamedAPIResource> doubleDamageFrom;

    public TypeRelations() {
    }

    public TypeRelations(List<NamedAPIResource> noDamageTo, List<NamedAPIResource> halfDamageTo, List<NamedAPIResource> doubleDamageTo, List<NamedAPIResource> noDamageFrom, List<NamedAPIResource> halfDamageFrom, List<NamedAPIResource> doubleDamageFrom) {
        this.noDamageTo = noDamageTo;
        this.halfDamageTo = halfDamageTo;
        this.doubleDamageTo = doubleDamageTo;
        this.noDamageFrom = noDamageFrom;
        this.halfDamageFrom = halfDamageFrom;
        this.doubleDamageFrom = doubleDamageFrom;
    }

    public List<NamedAPIResource> getNoDamageTo() {
        return noDamageTo;
    }

    public void setNoDamageTo(List<NamedAPIResource> noDamageTo) {
        this.noDamageTo = noDamageTo;
    }

    public List<NamedAPIResource> getHalfDamageTo() {
        return halfDamageTo;
    }

    public void setHalfDamageTo(List<NamedAPIResource> halfDamageTo) {
        this.halfDamageTo = halfDamageTo;
    }

    public List<NamedAPIResource> getDoubleDamageTo() {
        return doubleDamageTo;
    }

    public void setDoubleDamageTo(List<NamedAPIResource> doubleDamageTo) {
        this.doubleDamageTo = doubleDamageTo;
    }

    public List<NamedAPIResource> getNoDamageFrom() {
        return noDamageFrom;
    }

    public void setNoDamageFrom(List<NamedAPIResource> noDamageFrom) {
        this.noDamageFrom = noDamageFrom;
    }

    public List<NamedAPIResource> getHalfDamageFrom() {
        return halfDamageFrom;
    }

    public void setHalfDamageFrom(List<NamedAPIResource> halfDamageFrom) {
        this.halfDamageFrom = halfDamageFrom;
    }

    public List<NamedAPIResource> getDoubleDamageFrom() {
        return doubleDamageFrom;
    }

    public void setDoubleDamageFrom(List<NamedAPIResource> doubleDamageFrom) {
        this.doubleDamageFrom = doubleDamageFrom;
    }
}
