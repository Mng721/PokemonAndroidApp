package com.example.mypokemonapplication.interfaces;

import com.example.mypokemonapplication.model.ResourceLists;
import com.example.mypokemonapplication.model.pokemon.pokemondetail.Pokemon;
import com.example.mypokemonapplication.model.pokemon.typedetail.Type;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetrofitService {
//    https://pokeapi.co/api/v2/

    @GET("pokemon/")
    Call<ResourceLists> getAllPokemons(@Query("offset") int offset, @Query("limit") int limit);

    @GET
    Call<Pokemon> pokemonDetail(@Url String url);

    @GET
    Call<Type> typeDetail(@Url String url);
}
