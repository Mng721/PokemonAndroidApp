package com.example.mypokemonapplication.model.utility.common_models;

import com.example.mypokemonapplication.model.utility.languages.Language;

public class Name {
    private String name;
    private NamedAPIResource languages;

    public Name(String name, NamedAPIResource languages) {
        this.name = name;
        this.languages = languages;
    }

    public Name() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NamedAPIResource getLanguages() {
        return languages;
    }

    public void setLanguages(NamedAPIResource languages) {
        this.languages = languages;
    }
}
