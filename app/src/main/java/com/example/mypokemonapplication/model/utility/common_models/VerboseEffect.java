package com.example.mypokemonapplication.model.utility.common_models;

import com.google.gson.annotations.SerializedName;

public class VerboseEffect {
    private String effect;

    @SerializedName("short_effect")
    private String shortEffect;

    private NamedAPIResource language;

    public VerboseEffect() {
    }

    public VerboseEffect(String effect, String shortEffect, NamedAPIResource language) {
        this.effect = effect;
        this.shortEffect = shortEffect;
        this.language = language;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getShortEffect() {
        return shortEffect;
    }

    public void setShortEffect(String shortEffect) {
        this.shortEffect = shortEffect;
    }

    public NamedAPIResource getLanguage() {
        return language;
    }

    public void setLanguage(NamedAPIResource language) {
        this.language = language;
    }
}
