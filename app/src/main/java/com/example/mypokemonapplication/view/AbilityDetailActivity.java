package com.example.mypokemonapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.method.TextKeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mypokemonapplication.R;
import com.example.mypokemonapplication.clients.RetrofitClient;
import com.example.mypokemonapplication.model.pokemon.Ability.Ability;
import com.example.mypokemonapplication.model.pokemon.typedetail.Type;
import com.example.mypokemonapplication.model.utility.common_models.Name;
import com.example.mypokemonapplication.viewmodels.AbilityDetailViewModel;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbilityDetailActivity extends AppCompatActivity {

    private AbilityDetailViewModel abilityDetailViewModel;

    private String urlAbility;
    private TextView tvAbilityTitle;
    private LinearLayout languageLinearLayout;
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
            setLanguageLinearLayout(ability.getNames());
        });
    }

    private void setLanguageLinearLayout(List<Name> names){
        languageLinearLayout = findViewById(R.id.other_languages);
        LayoutInflater inflater = LayoutInflater.from(this);
        for (Name name : names){
            View view = inflater.inflate(R.layout.ability_language, languageLinearLayout, false);
            TextView tvNation = view.findViewById(R.id.tv_nation);
            TextView tvNationName = view.findViewById(R.id.tv_nation_name);
            tvNation.setText(name.getLanguages().getName());
            tvNationName.setText(name.getName());
        }
    }
}