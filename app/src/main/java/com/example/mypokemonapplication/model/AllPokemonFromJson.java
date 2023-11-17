package com.example.mypokemonapplication.model;

import java.util.List;

public class AllPokemonFromJson {
    private int id;
    private String name;
    private List<String> versions;

    public AllPokemonFromJson() {
    }

    public AllPokemonFromJson(int id, String name, List<String> versions) {
        this.id = id;
        this.name = name;
        this.versions = versions;
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

    public List<String> getVersions() {
        return versions;
    }

    public void setVersions(List<String> versions) {
        this.versions = versions;
    }
}
