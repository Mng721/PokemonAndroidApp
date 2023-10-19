package com.example.mypokemonapplication.model.utility.common_models;

import com.google.gson.annotations.SerializedName;

public class VersionGameIndex {
    @SerializedName("game_index")
    private int gameIndex;
    private NamedAPIResource version;

    public VersionGameIndex(int gameIndex, NamedAPIResource version) {
        this.gameIndex = gameIndex;
        this.version = version;
    }

    public VersionGameIndex() {
    }

    public int getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(int gameIndex) {
        this.gameIndex = gameIndex;
    }

    public NamedAPIResource getVersion() {
        return version;
    }

    public void setVersion(NamedAPIResource version) {
        this.version = version;
    }
}
