package com.example.mypokemonapplication.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mypokemonapplication.repository.FavoritePokemonRepository;

import java.util.List;

public class FavoritePokemonViewModel extends AndroidViewModel {

    FavoritePokemonRepository favoritePokemonRepository;
    public FavoritePokemonViewModel(@NonNull Application application) {
        super(application);
        favoritePokemonRepository = new FavoritePokemonRepository(application);
    }

    public LiveData<List<String>> getPokemonFavName(){
        return favoritePokemonRepository.getMutableLiveData();
    }
}
