package com.example.mypokemonapplication.model.pokemon.pokemondetail;

import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.google.gson.annotations.SerializedName;

public class PokemonMoveVersion {
    @SerializedName("move_learn_method")
    private NamedAPIResource moveLearnMethod;
    @SerializedName("version_group")
    private NamedAPIResource versionGroup;
    @SerializedName("level_learned_at")
    private int levelLearnedAt;

    public NamedAPIResource getMoveLearnMethod() {
        return moveLearnMethod;
    }

    public void setMoveLearnMethod(NamedAPIResource moveLearnMethod) {
        this.moveLearnMethod = moveLearnMethod;
    }

    public NamedAPIResource getVersionGroup() {
        return versionGroup;
    }

    public void setVersionGroup(NamedAPIResource versionGroup) {
        this.versionGroup = versionGroup;
    }

    public int getLevelLearnedAt() {
        return levelLearnedAt;
    }

    public void setLevelLearnedAt(int levelLearnedAt) {
        this.levelLearnedAt = levelLearnedAt;
    }

    public PokemonMoveVersion(NamedAPIResource moveLearnMethod, NamedAPIResource versionGroup, int levelLearnedAt) {
        this.moveLearnMethod = moveLearnMethod;
        this.versionGroup = versionGroup;
        this.levelLearnedAt = levelLearnedAt;
    }

    public PokemonMoveVersion() {
    }
}
