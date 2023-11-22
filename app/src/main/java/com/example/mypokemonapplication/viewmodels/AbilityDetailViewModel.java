package com.example.mypokemonapplication.viewmodels;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mypokemonapplication.clients.RetrofitClient;
import com.example.mypokemonapplication.interfaces.RetrofitService;
import com.example.mypokemonapplication.model.pokemon.Ability.Ability;
import com.example.mypokemonapplication.repository.AbilityRepository;
import com.example.mypokemonapplication.view.AbilityDetailActivity;

import org.apache.commons.lang3.mutable.Mutable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbilityDetailViewModel extends AndroidViewModel {

    private AbilityRepository repository;

    public AbilityDetailViewModel(@NonNull Application application) {
        super(application);
        repository = new AbilityRepository(application);
    }

    public LiveData<Ability> getAbility(String url){
        return repository.getAbilityMutableLiveData(url);
    }
}

