package com.example.mypokemonapplication.model;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class LoginUser {
    private String name;
    private String username;
    private String password;

    private String dateOfBirth;
    private String gender;
    private HashMap<String, Boolean> favPokemon;

    public LoginUser(String name, String username) {
        this.name = name;
        this.username = username;
        this.favPokemon = new HashMap<>();
        this.password = null;
    }

    public LoginUser(String name, String username, String dateOfBirth, String gender) {
        this.name = name;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public LoginUser() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public HashMap<String, Boolean> getFavPokemon() {
        return favPokemon;
    }

    public void setFavPokemon(HashMap<String, Boolean> favPokemon) {
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
