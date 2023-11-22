package com.example.mypokemonapplication.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mypokemonapplication.model.AllPokemon;
import com.example.mypokemonapplication.repository.AllPokemonsRepository;

public class AllPokemonViewModel extends AndroidViewModel {
    AllPokemonsRepository allPokemonsRepository;
    public AllPokemonViewModel(@NonNull Application application) {
        super(application);
        allPokemonsRepository = new AllPokemonsRepository(application);
    }

    public LiveData<AllPokemon> getAllPokemons(){
        return allPokemonsRepository.getMutableLiveData();
    }
}
