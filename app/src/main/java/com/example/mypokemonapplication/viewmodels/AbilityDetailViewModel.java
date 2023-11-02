package com.example.mypokemonapplication.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.mypokemonapplication.clients.RetrofitClient;
import com.example.mypokemonapplication.interfaces.RetrofitService;
import com.example.mypokemonapplication.model.pokemon.Ability.Ability;
import com.example.mypokemonapplication.view.AbilityDetailActivity;

import retrofit2.Call;

public class AbilityDetailViewModel extends ViewModel {

    private Ability ability;

    private RetrofitService retrofitService = RetrofitClient.getClient().create(RetrofitService.class);
    public void getType(View view){
    }

//    private String urlAbility = AbilityDetailActivity.class.

}
