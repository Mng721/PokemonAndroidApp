package com.example.mypokemonapplication.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.mypokemonapplication.AllPokemonsActivity;
import com.example.mypokemonapplication.adapter.PokemonFromApiAdapter;
import com.example.mypokemonapplication.clients.RetrofitClient;
import com.example.mypokemonapplication.model.AllPokemon;
import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPokemonsRepository {
    private Application application;

    private final MutableLiveData<AllPokemon> mutableLiveData = new MutableLiveData<>();

    public AllPokemonsRepository(Application application){
        this.application = application;
    }


    public MutableLiveData<AllPokemon> getMutableLiveData(){
        Call<AllPokemon> pokemonCall = RetrofitClient.getInstance().getMyApi().allPokemon("https://pokeapi.co/api/v2/pokemon?limit=100000&offset=0");
        pokemonCall.enqueue(new Callback<AllPokemon>() {
            @Override
            public void onResponse(Call<AllPokemon> call, Response<AllPokemon> response) {
                if (response.body() != null) {
                    mutableLiveData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<AllPokemon> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
