package com.example.mypokemonapplication.model.pokemon.Ability;

import com.example.mypokemonapplication.model.utility.common_models.Effect;
import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AbilityEffectChange {
    @SerializedName("effect_entries")
    private List<Effect> effectEntries;

    @SerializedName("version_group")
    private NamedAPIResource versionGroup;

    public AbilityEffectChange(List<Effect> effectEntries, NamedAPIResource versionGroup) {
        this.effectEntries = effectEntries;
        this.versionGroup = versionGroup;
    }

    public AbilityEffectChange() {
    }

    public List<Effect> getEffectEntries() {
        return effectEntries;
    }

    public void setEffectEntries(List<Effect> effectEntries) {
        this.effectEntries = effectEntries;
    }

    public NamedAPIResource getVersionGroup() {
        return versionGroup;
    }

    public void setVersionGroup(NamedAPIResource versionGroup) {
        this.versionGroup = versionGroup;
    }
}
