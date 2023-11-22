package com.example.mypokemonapplication.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mypokemonapplication.clients.RetrofitClient;
import com.example.mypokemonapplication.interfaces.RetrofitService;
import com.example.mypokemonapplication.model.pokemon.Ability.Ability;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbilityRepository {

    private final MutableLiveData<Ability> abilityMutableLiveData = new MutableLiveData<>();

    private Application application;
    public AbilityRepository(Application application){
        this.application = application;
    }
    public MutableLiveData<Ability> getAbilityMutableLiveData(String url){
        Call<Ability> abilityCall = RetrofitClient.getInstance().getMyApi().abilityDetail(url);
        abilityCall.enqueue(new Callback<Ability>() {
            @Override
            public void onResponse(Call<Ability> call, Response<Ability> response) {
                abilityMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Ability> call, Throwable t) {
                abilityMutableLiveData.postValue(null);
                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return abilityMutableLiveData;
    }
}
