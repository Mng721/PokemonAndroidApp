package com.example.mypokemonapplication.model.utility.common_models;

import com.google.gson.annotations.SerializedName;

public class GenerationGameIndex {
    @SerializedName("game_index")
    private int gameIndex;
    private NamedAPIResource generation;

    public GenerationGameIndex() {
    }

    public GenerationGameIndex(int gameIndex, NamedAPIResource generation) {
        this.gameIndex = gameIndex;
        this.generation = generation;
    }

    public int getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(int gameIndex) {
        this.gameIndex = gameIndex;
    }

    public NamedAPIResource getGeneration() {
        return generation;
    }

    public void setGeneration(NamedAPIResource generation) {
        this.generation = generation;
    }
}
