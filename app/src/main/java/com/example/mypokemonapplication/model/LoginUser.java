package com.example.mypokemonapplication.model;

import com.example.mypokemonapplication.model.pokemon.pokemondetail.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class LoginUser {
    private String name;
    private String username;
    private String password;
    private List<String> favPokemon;

    public LoginUser(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.favPokemon = new ArrayList<String>();
    }

    public LoginUser() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFavPokemon() {
        return favPokemon;
    }

    public void setFavPokemon(List<String> favPokemon) {
        this.favPokemon = favPokemon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
