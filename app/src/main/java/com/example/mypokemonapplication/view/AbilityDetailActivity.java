package com.example.mypokemonapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.method.TextKeyListener;
import android.util.Log;
import android.widget.TextView;

import com.example.mypokemonapplication.R;
import com.example.mypokemonapplication.clients.RetrofitClient;
import com.example.mypokemonapplication.model.pokemon.Ability.Ability;
import com.example.mypokemonapplication.model.pokemon.typedetail.Type;
import com.example.mypokemonapplication.viewmodels.AbilityDetailViewModel;

import org.apache.commons.lang3.text.WordUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbilityDetailActivity extends AppCompatActivity {

    private AbilityDetailViewModel abilityDetailViewModel;

    private String urlAbility;
    private TextView tvAbilityTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ability_detail);
        abilityDetailViewModel = new ViewModelProvider(this).get(AbilityDetailViewModel.class);
        tvAbilityTitle = findViewById(R.id.tv_ability_title);

        getUrlBundle();
        getAbility();
    }
    private void getUrlBundle(){
        final Bundle bundle = getIntent().getExtras();
        if (bundle.getString("ability_detail_url") != null) {
            urlAbility = bundle.getString("ability_detail_url");
        }
    }

    private void getAbility(){
        abilityDetailViewModel.getAbility(urlAbility).observe(this, ability -> {
            tvAbilityTitle.setText(WordUtils.capitalize(ability.getName().replace("-", " ")));
        });
    }
}