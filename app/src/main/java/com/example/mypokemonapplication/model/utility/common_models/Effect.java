package com.example.mypokemonapplication.model.utility.common_models;

public class Effect {
    private String effect;
    private NamedAPIResource language;

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public NamedAPIResource getLanguage() {
        return language;
    }

    public void setLanguage(NamedAPIResource language) {
        this.language = language;
    }

    public Effect() {
    }

    public Effect(String effect, NamedAPIResource language) {
        this.effect = effect;
        this.language = language;
    }
}
