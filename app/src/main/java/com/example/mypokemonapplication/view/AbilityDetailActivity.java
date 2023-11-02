package com.example.mypokemonapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.mypokemonapplication.R;
import com.example.mypokemonapplication.clients.RetrofitClient;
import com.example.mypokemonapplication.databinding.ActivityAbilityDetailBinding;
import com.example.mypokemonapplication.interfaces.RetrofitService;
import com.example.mypokemonapplication.model.pokemon.typedetail.Type;
import com.example.mypokemonapplication.viewmodels.AbilityDetailViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbilityDetailActivity extends AppCompatActivity {

    AbilityDetailViewModel abilityDetailViewModel;

    ActivityAbilityDetailBinding binding;

    private String urlAbility;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        abilityDetailViewModel = new ViewModelProvider(this).get(AbilityDetailViewModel.class);
        binding = DataBindingUtil.setContentView(AbilityDetailActivity.this, R.layout.activity_ability_detail);
        binding.setLifecycleOwner(this);
        binding.setAbilityDetailViewModel(abilityDetailViewModel);
        getUrlBundle();
    }
    private void getUrlBundle(){
        final Bundle bundle = getIntent().getExtras();
        if (bundle.getString("ability_detail_url") != null) {
            urlAbility = bundle.getString("ability_detail_url");
        }
    }

    public String getUrlAbility() {
        return urlAbility;
    }
}