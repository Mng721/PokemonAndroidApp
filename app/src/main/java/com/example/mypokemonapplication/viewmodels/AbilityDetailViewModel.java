package com.example.mypokemonapplication.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mypokemonapplication.clients.RetrofitClient;
import com.example.mypokemonapplication.interfaces.RetrofitService;
import com.example.mypokemonapplication.model.pokemon.Ability.Ability;
import com.example.mypokemonapplication.view.AbilityDetailActivity;

import org.apache.commons.lang3.mutable.Mutable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbilityDetailViewModel extends ViewModel {

    private Ability ability;

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }
}

