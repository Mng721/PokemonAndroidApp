package com.example.mypokemonapplication.model.utility.languages;

import com.example.mypokemonapplication.model.utility.common_models.Name;
import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;

import java.util.List;

public class Language {
    private int id;
    private String name;
    private Boolean official;
    private String iso639;
    private String iso3166;
    private List<Name> names;

    public Language(int id, String name, Boolean official, String iso639, String iso3166, List<Name> names) {
        this.id = id;
        this.name = name;
        this.official = official;
        this.iso639 = iso639;
        this.iso3166 = iso3166;
        this.names = names;
    }

    public Language() {
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

    public Boolean getOfficial() {
        return official;
    }

    public void setOfficial(Boolean official) {
        this.official = official;
    }

    public String getIso639() {
        return iso639;
    }

    public void setIso639(String iso639) {
        this.iso639 = iso639;
    }

    public String getIso3166() {
        return iso3166;
    }

    public void setIso3166(String iso3166) {
        this.iso3166 = iso3166;
    }

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }
}
