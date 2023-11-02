package com.example.mypokemonapplication.view;

import static org.apache.commons.lang3.StringUtils.capitalize;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypokemonapplication.R;
import com.example.mypokemonapplication.adapter.PokemonTypeAdapter;
import com.example.mypokemonapplication.clients.RetrofitClient;
import com.example.mypokemonapplication.interfaces.RetrofitService;
import com.example.mypokemonapplication.model.pokemon.pokemondetail.Pokemon;
import com.example.mypokemonapplication.model.pokemon.pokemondetail.PokemonAbility;
import com.example.mypokemonapplication.model.pokemon.pokemondetail.PokemonStat;
import com.example.mypokemonapplication.model.pokemon.pokemondetail.PokemonType;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDetailActivity extends AppCompatActivity {

    //    Int
    private int totalBaseStats;
    //    Retrofit
    private RetrofitService retrofitService;

    //    String
    private String urlPokemon;
    private String pokemonName;

    //    ImageView
    private ImageView ivPokemon;

    //    TextView
    private TextView tvPokemonName;
    private TextView tvPokemonNumber;
    private TextView tvPokemonSpecies;
    private TextView tvPokemonHeight;
    private TextView tvPokemonWeight;
    private TextView tvPokemonHPBaseStat;
    private TextView tvPokemonAtkBaseStat;
    private TextView tvPokemonDefBaseStat;
    private TextView tvPokemonSpaBaseStat;
    private TextView tvPokemonSpdBaseStat;
    private TextView tvPokemonSpeBaseStat;
    private TextView tvPokemonHPMinStat;
    private TextView tvPokemonAtkMinStat;
    private TextView tvPokemonDefMinStat;
    private TextView tvPokemonSpaMinStat;
    private TextView tvPokemonSpdMinStat;
    private TextView tvPokemonSpeMinStat;
    private TextView tvPokemonHPMaxStat;
    private TextView tvPokemonAtkMaxStat;
    private TextView tvPokemonDefMaxStat;
    private TextView tvPokemonSpaMaxStat;
    private TextView tvPokemonSpdMaxStat;
    private TextView tvPokemonSpeMaxStat;
    private TextView tvPokemonTotalBaseStat;

    //    Progress Indicator
    private LinearProgressIndicator pbPokemonHPStat;
    private LinearProgressIndicator pbPokemonAtkStat;
    private LinearProgressIndicator pbPokemonDefStat;
    private LinearProgressIndicator pbPokemonSpaStat;
    private LinearProgressIndicator pbPokemonSpdStat;
    private LinearProgressIndicator pbPokemonSpeStat;

    //    Adapter
    private PokemonTypeAdapter pokemonTypeAdapter;

    //    Linear layout
    private LinearLayout llPokemonAbilities;

    //    RecyclerView
    private RecyclerView rvPokemonTypeList;

//    Button
    private Button btnBuild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

//        ImageView
        ivPokemon = findViewById(R.id.iv_pokemon_default_detail);

//        TextView
        tvPokemonName = findViewById(R.id.tv_detail_pokemon_name);
        tvPokemonSpecies = findViewById(R.id.tv_pokemon_species);
        tvPokemonHeight = findViewById(R.id.tv_pokemon_height);
        tvPokemonWeight = findViewById(R.id.tv_pokemon_weight);
        tvPokemonNumber = findViewById(R.id.tv_pokemon_number);
        tvPokemonHPBaseStat = findViewById(R.id.tv_pokemon_hp_base_stat);
        tvPokemonAtkBaseStat = findViewById(R.id.tv_pokemon_atk_base_stat);
        tvPokemonDefBaseStat = findViewById(R.id.tv_pokemon_def_base_stat);
        tvPokemonSpaBaseStat = findViewById(R.id.tv_pokemon_spa_base_stat);
        tvPokemonSpdBaseStat = findViewById(R.id.tv_pokemon_spd_base_stat);
        tvPokemonSpeBaseStat = findViewById(R.id.tv_pokemon_spe_base_stat);
        tvPokemonHPMinStat = findViewById(R.id.tv_pokemon_hp_min_stat);
        tvPokemonAtkMinStat = findViewById(R.id.tv_pokemon_atk_min_stat);
        tvPokemonDefMinStat = findViewById(R.id.tv_pokemon_def_min_stat);
        tvPokemonSpaMinStat = findViewById(R.id.tv_pokemon_spa_min_stat);
        tvPokemonSpdMinStat = findViewById(R.id.tv_pokemon_spd_min_stat);
        tvPokemonSpeMinStat = findViewById(R.id.tv_pokemon_spe_min_stat);
        tvPokemonHPMaxStat = findViewById(R.id.tv_pokemon_hp_max_stat);
        tvPokemonAtkMaxStat = findViewById(R.id.tv_pokemon_atk_max_stat);
        tvPokemonDefMaxStat = findViewById(R.id.tv_pokemon_def_max_stat);
        tvPokemonSpaMaxStat = findViewById(R.id.tv_pokemon_spa_max_stat);
        tvPokemonSpdMaxStat = findViewById(R.id.tv_pokemon_spd_max_stat);
        tvPokemonSpeMaxStat = findViewById(R.id.tv_pokemon_spe_max_stat);
        tvPokemonTotalBaseStat = findViewById(R.id.tv_pokemon_total_base_stat);

//      Recycler view
        rvPokemonTypeList = findViewById(R.id.rv_pokemon_type);

//        Progress indicator
        pbPokemonHPStat = findViewById(R.id.pb_pokemon_hp_stat);
        pbPokemonAtkStat = findViewById(R.id.pb_pokemon_atk_stat);
        pbPokemonDefStat = findViewById(R.id.pb_pokemon_def_stat);
        pbPokemonSpaStat = findViewById(R.id.pb_pokemon_spa_stat);
        pbPokemonSpdStat = findViewById(R.id.pb_pokemon_spd_stat);
        pbPokemonSpeStat = findViewById(R.id.pb_pokemon_spe_stat);

//        Button
        btnBuild = findViewById(R.id.btn_build);

//        Linear layout
        llPokemonAbilities = findViewById(R.id.ll_pokemon_abilities);

        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("pokemon_detail_url") != null) {
            urlPokemon = bundle.getString("pokemon_detail_url");
            pokemonName = bundle.getString("pokemon_name");
        }

//        Retrofit
        retrofitService = RetrofitClient.getClient().create(RetrofitService.class);
        Call<Pokemon> pokemonCall = retrofitService.pokemonDetail(urlPokemon);
        pokemonCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(@NonNull Call<Pokemon> call, @NonNull Response<Pokemon> response) {
                Pokemon pokemon = response.body();
                setUpPokemonDetail(pokemon);
            }

            @Override
            public void onFailure(@NonNull Call<Pokemon> call, @NonNull Throwable t) {

            }
        });
    }

    private void setUpPokemonDetail(Pokemon pokemon) {
        if (pokemon != null) {
            totalBaseStats = 0;
//          pokemon name
            String pokemonName = pokemon.getName();
            tvPokemonName.setText(capitalize(pokemonName));
//          pokemon img
            Picasso.get().load(pokemon.getSprites().getFrontDefault()).into(ivPokemon);
//          pokemon nation number
            tvPokemonNumber.setText(pokemon.getNumber(pokemon.getId()));
//          pokemon type
            setPokemonDetailTypes(pokemon);
//          pokemon species
            tvPokemonSpecies.setText(capitalize(pokemon.getSpecies().getName()));
//          pokemon height
            int height = pokemon.getHeight();
            tvPokemonHeight.setText(pokemon.getHeightString(height));
//          pokemon weight
            int weight = pokemon.getWeight();
            tvPokemonWeight.setText(pokemon.getWeightString(weight));
//          pokemon abilities
            setPokemonDetailAbilities(pokemon);
//          pokemon stats
            setPokemonDetailStats(pokemon);
            buildPokemon();
        }
    }

    //    All function
    private int getIndicatorColor(int stat) {
        if (stat < 30) {
            return getResources().getColor(R.color.very_bad_stat);
        }
        if (stat < 60) {
            return getResources().getColor(R.color.bad_stat);
        }
        if (stat < 90) {
            return getResources().getColor(R.color.bad_mediocre_stat);
        }
        if (stat < 120) {
            return getResources().getColor(R.color.decent_good_stat);
        }
        if (stat < 150) {
            return getResources().getColor(R.color.very_good_stat);
        }
        if (stat < 256) {
            return getResources().getColor(R.color.phenomenal_stat);
        }
        return 0;
    }

    private void buildPokemon(){
        btnBuild.setOnClickListener(view -> {
            Intent intent = new Intent(PokemonDetailActivity.this, PokemonBuildActivity.class);
            intent.putExtra("pokemon_detail_url", urlPokemon);
            intent.putExtra("pokemon_name", pokemonName);
            startActivity(intent);

            PokemonDetailActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }

    private void setPokemonDetailTypes(Pokemon pokemon) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(PokemonDetailActivity.this, RecyclerView.HORIZONTAL, false);
        rvPokemonTypeList.setLayoutManager(layoutManager);
        List<PokemonType> pokemonTypes = pokemon.getTypes();
        pokemonTypeAdapter = new PokemonTypeAdapter(PokemonDetailActivity.this, pokemonTypes);
        rvPokemonTypeList.setAdapter(pokemonTypeAdapter);
    }

    private void setPokemonDetailAbilities(Pokemon pokemon) {
        List<PokemonAbility> pokemonAbilities = pokemon.getAbilities();
        LayoutInflater inflater = LayoutInflater.from(this);
        if (llPokemonAbilities.getChildCount() == 0) {
            for (PokemonAbility ability : pokemonAbilities
            ) {
                View view = inflater.inflate(R.layout.ability_item, llPokemonAbilities, false);
                TextView tvAbilityName = view.findViewById(R.id.ability_item_text);
                boolean abilityIsHidden = ability.isHidden();
                String abilityName = WordUtils.capitalize(ability.getAbility().getName().replace("-", " "));
                if (!abilityIsHidden) {
                    tvAbilityName.setText(String.valueOf(ability.getSlot()) + ". " + abilityName);
                } else {
                    String hiddenAbilityName = abilityName + " (hidden ability)";
                    Spannable spannable = new SpannableString(hiddenAbilityName);
                    spannable.setSpan(new ForegroundColorSpan(0xFF737373), hiddenAbilityName.indexOf("("), hiddenAbilityName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tvAbilityName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                    tvAbilityName.setText(spannable, TextView.BufferType.SPANNABLE);
                }
                tvAbilityName.setOnClickListener(view1 -> {
                    Intent intent = new Intent(PokemonDetailActivity.this, AbilityDetailActivity.class);
                    intent.putExtra("ability_detail_url", ability.getAbility().getUrl());
                    startActivity(intent);
                });
                llPokemonAbilities.addView(view);
            }
        }
    }

    private void setPokemonDetailStats(Pokemon pokemon) {
        List<PokemonStat> pokemonStats = pokemon.getStats();
        for (PokemonStat stat : pokemonStats
        ) {
            String pokemonStatName = stat.getStat().getName();
            switch (pokemonStatName) {
                case "hp":
                    int baseHPStat = stat.getBaseStat();
                    totalBaseStats += baseHPStat;
                    tvPokemonHPBaseStat.setText(String.valueOf(baseHPStat));
                    pbPokemonHPStat.setIndicatorColor(getIndicatorColor(baseHPStat));
                    pbPokemonHPStat.setProgress(baseHPStat);
                    tvPokemonHPMinStat.setText(String.valueOf(pokemon.hpStatCalculator(baseHPStat, 0, 0, 100)));
                    tvPokemonHPMaxStat.setText(String.valueOf(pokemon.hpStatCalculator(baseHPStat, 31, 252, 100)));
                    break;
                case "attack":
                    int baseAtkStat = stat.getBaseStat();
                    totalBaseStats += baseAtkStat;
                    tvPokemonAtkBaseStat.setText(String.valueOf(baseAtkStat));
                    pbPokemonAtkStat.setIndicatorColor(getIndicatorColor(baseAtkStat));
                    pbPokemonAtkStat.setProgress(baseAtkStat);
                    tvPokemonAtkMinStat.setText(String.valueOf(pokemon.otherStatCalculator(baseAtkStat, 0, 0, 100, 0.9f)));
                    tvPokemonAtkMaxStat.setText(String.valueOf(pokemon.otherStatCalculator(baseAtkStat, 31, 252, 100, 1.1f)));
                    break;
                case "defense":
                    int baseDefStat = stat.getBaseStat();
                    totalBaseStats += baseDefStat;
                    tvPokemonDefBaseStat.setText(String.valueOf(baseDefStat));
                    pbPokemonDefStat.setIndicatorColor(getIndicatorColor(baseDefStat));
                    pbPokemonDefStat.setProgress(baseDefStat);
                    tvPokemonDefMinStat.setText(String.valueOf(pokemon.otherStatCalculator(baseDefStat, 0, 0, 100, 0.9f)));
                    tvPokemonDefMaxStat.setText(String.valueOf(pokemon.otherStatCalculator(baseDefStat, 31, 252, 100, 1.1f)));
                    break;
                case "special-attack":
                    int baseSpaStat = stat.getBaseStat();
                    totalBaseStats += baseSpaStat;
                    tvPokemonSpaBaseStat.setText(String.valueOf(baseSpaStat));
                    pbPokemonSpaStat.setIndicatorColor(getIndicatorColor(baseSpaStat));
                    pbPokemonSpaStat.setProgress(baseSpaStat);
                    tvPokemonSpaMinStat.setText(String.valueOf(pokemon.otherStatCalculator(baseSpaStat, 0, 0, 100, 0.9f)));
                    tvPokemonSpaMaxStat.setText(String.valueOf(pokemon.otherStatCalculator(baseSpaStat, 31, 252, 100, 1.1f)));
                    break;
                case "special-defense":
                    int baseSpdStat = stat.getBaseStat();
                    totalBaseStats += baseSpdStat;
                    tvPokemonSpdBaseStat.setText(String.valueOf(baseSpdStat));
                    pbPokemonSpdStat.setIndicatorColor(getIndicatorColor(baseSpdStat));
                    pbPokemonSpdStat.setProgress(baseSpdStat);
                    tvPokemonSpdMinStat.setText(String.valueOf(pokemon.otherStatCalculator(baseSpdStat, 0, 0, 100, 0.9f)));
                    tvPokemonSpdMaxStat.setText(String.valueOf(pokemon.otherStatCalculator(baseSpdStat, 31, 252, 100, 1.1f)));
                    break;
                case "speed":
                    int baseSpeStat = stat.getBaseStat();
                    totalBaseStats += baseSpeStat;
                    tvPokemonSpeBaseStat.setText(String.valueOf(baseSpeStat));
                    pbPokemonSpeStat.setIndicatorColor(getIndicatorColor(baseSpeStat));
                    pbPokemonSpeStat.setProgress(baseSpeStat);
                    tvPokemonSpeMinStat.setText(String.valueOf(pokemon.otherStatCalculator(baseSpeStat, 0, 0, 100, 0.9f)));
                    tvPokemonSpeMaxStat.setText(String.valueOf(pokemon.otherStatCalculator(baseSpeStat, 31, 252, 100, 1.1f)));
                    break;
            }
        }
        tvPokemonTotalBaseStat.setText(String.valueOf(totalBaseStats));
    }
}