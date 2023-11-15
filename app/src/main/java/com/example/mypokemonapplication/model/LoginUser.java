package com.example.mypokemonapplication.model;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import java.util.List;

public class LoginUser {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long uid;
    private String name;
    private String username;
    private String password;
    private List<String> favPokemon;

    public LoginUser(Long uid, String name, String username, String password, List<String> favPokemon) {
        this.uid = uid;
        this.name = name;
        this.username = username;
        this.password = password;
        this.favPokemon = favPokemon;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
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
