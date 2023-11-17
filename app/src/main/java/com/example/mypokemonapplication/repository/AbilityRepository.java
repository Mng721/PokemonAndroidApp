package com.example.mypokemonapplication.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.mypokemonapplication.clients.RetrofitClient;
import com.example.mypokemonapplication.interfaces.RetrofitService;
import com.example.mypokemonapplication.model.pokemon.Ability.Ability;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbilityRepository {

    private static RetrofitService myInterface = null;
    private final MutableLiveData<Ability> abilityMutableLiveData = new MutableLiveData<>();

    private static AbilityRepository newsAbilityRepository;

    private static AbilityRepository getInstance(){
        if (newsAbilityRepository == null){
            newsAbilityRepository = new AbilityRepository();
        }
        return newsAbilityRepository;
    }

    public AbilityRepository(){
    }

    public MutableLiveData<Ability> getAbilityMutableLiveData(String url){
        Call<Ability> abilityCall = myInterface.abilityDetail(url);
        abilityCall.enqueue(new Callback<Ability>() {
            @Override
            public void onResponse(Call<Ability> call, Response<Ability> response) {
                abilityMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Ability> call, Throwable t) {
                abilityMutableLiveData.postValue(null);
            }
        });
        return abilityMutableLiveData;
    }
}
