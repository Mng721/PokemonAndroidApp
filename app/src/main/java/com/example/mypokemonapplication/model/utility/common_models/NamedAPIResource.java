package com.example.mypokemonapplication.model.utility.common_models;

public class NamedAPIResource {
    private String name;
    private String url;

    public NamedAPIResource() {
    }

    public NamedAPIResource(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
