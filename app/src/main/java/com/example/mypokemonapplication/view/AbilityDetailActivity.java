package com.example.mypokemonapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.mypokemonapplication.R;
import com.example.mypokemonapplication.clients.RetrofitClient;
import com.example.mypokemonapplication.databinding.ActivityAbilityDetailBinding;
import com.example.mypokemonapplication.api.RetrofitService;
import com.example.mypokemonapplication.model.pokemon.Ability.Ability;
import com.example.mypokemonapplication.viewmodels.AbilityDetailViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbilityDetailActivity extends AppCompatActivity {

    AbilityDetailViewModel abilityDetailViewModel;

    ActivityAbilityDetailBinding binding;

    private String urlAbility;

    private RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        abilityDetailViewModel = new ViewModelProvider(this).get(AbilityDetailViewModel.class);
        binding = DataBindingUtil.setContentView(AbilityDetailActivity.this, R.layout.activity_ability_detail);
        binding.setLifecycleOwner(this);
        binding.setAbilityDetailViewModel(abilityDetailViewModel);
        getUrlBundle();
        doRequest();
    }
    private void getUrlBundle(){
        final Bundle bundle = getIntent().getExtras();
        if (bundle.getString("ability_detail_url") != null) {
            urlAbility = bundle.getString("ability_detail_url");
        }
    }

    public void doRequest(){
        Call<Ability> abilityCall = RetrofitClient.getInstance().getMyApi().abilityDetail(urlAbility);
        abilityCall.enqueue(new Callback<Ability>() {
            @Override
            public void onResponse(Call<Ability> call, Response<Ability> response) {
                Ability ability = response.body();
            }

            @Override
            public void onFailure(Call<Ability> call, Throwable t) {

            }
        });
    }

}