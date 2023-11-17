package com.example.mypokemonapplication.interfaces;

import com.example.mypokemonapplication.model.AllPokemon;
import com.example.mypokemonapplication.model.ResourceLists;
import com.example.mypokemonapplication.model.pokemon.Ability.Ability;
import com.example.mypokemonapplication.model.pokemon.pokemondetail.Pokemon;
import com.example.mypokemonapplication.model.pokemon.pokemondetail.PokemonAbility;
import com.example.mypokemonapplication.model.pokemon.typedetail.Type;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetrofitService {
//    https://pokeapi.co/api/v2/

    String BASE_URL = "https://pokeapi.co/api/v2/";
    @GET
    Call<AllPokemon> allPokemon(@Url String url);
    @GET
    Call<Pokemon> pokemonDetail(@Url String url);

    @GET
    Call<Type> typeDetail(@Url String url);

    @GET
    Call<Ability> abilityDetail(@Url String url);
}
