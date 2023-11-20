package com.example.mypokemonapplication.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.mypokemonapplication.converters.PokemonsFavConverter;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "users")
@TypeConverters({PokemonsFavConverter.class})
public class LoginUser {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long uid;
    private String name;
    private String username;
    private String password;

    public ArrayList<String> pokemonsFav;

    public LoginUser(Long uid, String name, String username, String password) {
        this.uid = uid;
        this.name = name;
        this.username = username;
        this.password = password;
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
