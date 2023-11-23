package com.example.mypokemonapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.method.TextKeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.mypokemonapplication.R;
import com.example.mypokemonapplication.adapter.PokemonUseAbilityAdapter;
import com.example.mypokemonapplication.clients.RetrofitClient;
import com.example.mypokemonapplication.model.pokemon.Ability.Ability;
import com.example.mypokemonapplication.model.pokemon.Ability.AbilityPokemon;
import com.example.mypokemonapplication.model.pokemon.pokemondetail.PokemonAbility;
import com.example.mypokemonapplication.model.pokemon.typedetail.Type;
import com.example.mypokemonapplication.model.utility.common_models.Name;
import com.example.mypokemonapplication.model.utility.common_models.VerboseEffect;
import com.example.mypokemonapplication.viewmodels.AbilityDetailViewModel;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbilityDetailActivity extends AppCompatActivity {

    private AbilityDetailViewModel abilityDetailViewModel;

    private String urlAbility;
    private TextView tvAbilityTitle, tvEffect;
    private RecyclerView recyclerViewPokemon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ability_detail);
        abilityDetailViewModel = new ViewModelProvider(this).get(AbilityDetailViewModel.class);
        tvAbilityTitle = findViewById(R.id.tv_ability_title);
        tvEffect = findViewById(R.id.tv_ability_effect);
        recyclerViewPokemon = findViewById(R.id.pokemon_with);
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
            for (VerboseEffect effect: ability.getEffectEntries()){
                if (effect.getLanguage().getName().equals("en")){
                    tvEffect.setText("\t\t\t" + effect.getEffect());
                }
            }

            setListPokemon(ability);
        });
    }

    private void setListPokemon(Ability ability){
        List<AbilityPokemon> abilityPokemons = ability.getPokemon();
        LinearLayoutManager layoutManager = new LinearLayoutManager(AbilityDetailActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerViewPokemon.setLayoutManager(layoutManager);
        PokemonUseAbilityAdapter pokemonUseAbilityAdapter = new PokemonUseAbilityAdapter(AbilityDetailActivity.this, abilityPokemons);
        recyclerViewPokemon.setAdapter(pokemonUseAbilityAdapter);
    }

//    private void setLanguageLinearLayout(List<Name> names){
//        languageLinearLayout = findViewById(R.id.other_languages);
//        LayoutInflater inflater = LayoutInflater.from(this);
//        for (Name name : names){
//            View view = inflater.inflate(R.layout.ability_language, languageLinearLayout, false);
//            TextView tvNation = view.findViewById(R.id.tv_nation);
//            TextView tvNationName = view.findViewById(R.id.tv_nation_name);
//            tvNation.setText(name.getLanguages().getName());
//            tvNationName.setText(name.getName());
//        }
//    }
}