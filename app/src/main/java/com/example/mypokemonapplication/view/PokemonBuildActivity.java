package com.example.mypokemonapplication.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mypokemonapplication.R;
import com.example.mypokemonapplication.clients.RetrofitClient;
import com.example.mypokemonapplication.api.RetrofitService;
import com.example.mypokemonapplication.model.pokemon.pokemondetail.Pokemon;
import com.example.mypokemonapplication.model.pokemon.pokemondetail.PokemonStat;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonBuildActivity extends AppCompatActivity {

    //    String
    private String pokemonUrl;
    private String pokemonName;

//    List
    private List<String> pokemonNatures;

    //    Float
    private float atkNature = 1.0f;
    private float defNature = 1.0f;
    private float spaNature = 1.0f;
    private float spdNature = 1.0f;
    private float speNature = 1.0f;

    //    Int
    private static int remainingEv = 508;
    private int levelPokemon = 50;
    private int hpEV = 0;
    private int atkEV = 0;
    private int defEV = 0;
    private int spaEV = 0;
    private int spdEV = 0;
    private int speEV = 0;
    private int hpIV = 31;
    private int atkIV = 31;
    private int defIV = 31;
    private int spaIV = 31;
    private int spdIV = 31;
    private int speIV = 31;
    private int baseHPStat;
    private int baseAtkStat;
    private int baseDefStat;
    private int baseSpaStat;
    private int baseSpdStat;
    private int baseSpeStat;

    //    Imageview
    private ImageView ivPokemon;

//    Radio group
    private RadioGroup rgIsShiny;

//    Radio button
    private RadioButton rbShinyYes;
    private RadioButton rbShinyNo;

    //    Textview
    private TextView tvPokemonHPBuildStat;
    private TextView tvPokemonAtkBuildStat;
    private TextView tvPokemonDefBuildStat;
    private TextView tvPokemonSpaBuildStat;
    private TextView tvPokemonSpdBuildStat;
    private TextView tvPokemonSpeBuildStat;

    //    Edittext
    private EditText etPokemonLevel;
    private EditText etPokemonHPIvStat;
    private EditText etPokemonAtkIvStat;
    private EditText etPokemonDefIvStat;
    private EditText etPokemonSpaIvStat;
    private EditText etPokemonSpdIvStat;
    private EditText etPokemonSpeIvStat;
    private EditText etPokemonHPEvStat;
    private EditText etPokemonAtkEvStat;
    private EditText etPokemonDefEvStat;
    private EditText etPokemonSpaEvStat;
    private EditText etPokemonSpdEvStat;
    private EditText etPokemonSpeEvStat;

    //    Progress Indicator
    private LinearProgressIndicator pbPokemonHPBuildStat;
    private LinearProgressIndicator pbPokemonAtkBuildStat;
    private LinearProgressIndicator pbPokemonDefBuildStat;
    private LinearProgressIndicator pbPokemonSpaBuildStat;
    private LinearProgressIndicator pbPokemonSpdBuildStat;
    private LinearProgressIndicator pbPokemonSpeBuildStat;

    //    SeekBar
    private SeekBar sbPokemonHP;
    private SeekBar sbPokemonAtk;
    private SeekBar sbPokemonDef;
    private SeekBar sbPokemonSpa;
    private SeekBar sbPokemonSpd;
    private SeekBar sbPokemonSpe;

    //    Retrofit
    private RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_build);

//        List
        pokemonNatures = new ArrayList<>();
        pokemonNatures.add("Hardy");
        pokemonNatures.add("Lonely (+Atk, -Def)");
        pokemonNatures.add("Brave (+Atk, -Spe)");
        pokemonNatures.add("Adamant (+Atk, -SpA)");
        pokemonNatures.add("Naughty (+Atk, -SpD)");
        pokemonNatures.add("Bold (+Def, -Atk)");
        pokemonNatures.add("Docile");
        pokemonNatures.add("Relaxed (+Def, -Spe)");
        pokemonNatures.add("Impish (+Def, -SpA)");
        pokemonNatures.add("Lax (+Def, -SpD)");
        pokemonNatures.add("Timid (+Spe, -Atk)");
        pokemonNatures.add("Hasty (+Spe, -Def)");
        pokemonNatures.add("Serious");
        pokemonNatures.add("Jolly (+Spe, -SpA)");
        pokemonNatures.add("Naive (+Spe, -SpD)");
        pokemonNatures.add("Modest (+SpA, -Atk)");
        pokemonNatures.add("Mild (+SpA, -Def)");
        pokemonNatures.add("Quiet (+SpA, -Spe)");
        pokemonNatures.add("Bashful");
        pokemonNatures.add("Rash (+SpA, -SpD)");
        pokemonNatures.add("Calm (+SpD, -Atk)");
        pokemonNatures.add("Gentle (+SpD, -Def)");
        pokemonNatures.add("Gentle (+SpD, -Spe)");
        pokemonNatures.add("Careful (+SpD, -SpA)");
        pokemonNatures.add("Quirky");

//        Imageview
        ivPokemon = findViewById(R.id.iv_pokemon_build_detail);

//        Radio button
        rbShinyYes = findViewById(R.id.rb_shiny_yes);
        rbShinyNo = findViewById(R.id.rb_shiny_no);

//        Textview
        tvPokemonHPBuildStat = findViewById(R.id.tv_pokemon_hp_build_stat);
        tvPokemonAtkBuildStat = findViewById(R.id.tv_pokemon_atk_build_stat);
        tvPokemonDefBuildStat = findViewById(R.id.tv_pokemon_def_build_stat);
        tvPokemonSpaBuildStat = findViewById(R.id.tv_pokemon_spa_build_stat);
        tvPokemonSpdBuildStat = findViewById(R.id.tv_pokemon_spd_build_stat);
        tvPokemonSpeBuildStat = findViewById(R.id.tv_pokemon_spe_build_stat);

//        Edittext
        etPokemonHPIvStat = findViewById(R.id.et_pokemon_hp_iv_stat);
        etPokemonAtkIvStat = findViewById(R.id.et_pokemon_atk_iv_stat);
        etPokemonDefIvStat = findViewById(R.id.et_pokemon_def_iv_stat);
        etPokemonSpaIvStat = findViewById(R.id.et_pokemon_spa_iv_stat);
        etPokemonSpdIvStat = findViewById(R.id.et_pokemon_spd_iv_stat);
        etPokemonSpeIvStat = findViewById(R.id.et_pokemon_spe_iv_stat);
        etPokemonHPEvStat = findViewById(R.id.et_pokemon_hp_ev_stat);
        etPokemonAtkEvStat = findViewById(R.id.et_pokemon_atk_ev_stat);
        etPokemonDefEvStat = findViewById(R.id.et_pokemon_def_ev_stat);
        etPokemonSpaEvStat = findViewById(R.id.et_pokemon_spa_ev_stat);
        etPokemonSpdEvStat = findViewById(R.id.et_pokemon_spd_ev_stat);
        etPokemonSpeEvStat = findViewById(R.id.et_pokemon_spe_ev_stat);
        etPokemonLevel = findViewById(R.id.et_pokemon_level);


//        ProgressIndicator
        pbPokemonHPBuildStat = findViewById(R.id.pb_pokemon_hp_build_stat);
        pbPokemonAtkBuildStat = findViewById(R.id.pb_pokemon_atk_build_stat);
        pbPokemonDefBuildStat = findViewById(R.id.pb_pokemon_def_build_stat);
        pbPokemonSpaBuildStat = findViewById(R.id.pb_pokemon_spa_build_stat);
        pbPokemonSpdBuildStat = findViewById(R.id.pb_pokemon_spd_build_stat);
        pbPokemonSpeBuildStat = findViewById(R.id.pb_pokemon_spe_build_stat);

//        SeekBar
        sbPokemonHP = findViewById(R.id.sb_pokemon_hp);
        sbPokemonAtk = findViewById(R.id.sb_pokemon_atk);
        sbPokemonDef = findViewById(R.id.sb_pokemon_def);
        sbPokemonSpa = findViewById(R.id.sb_pokemon_spa);
        sbPokemonSpd = findViewById(R.id.sb_pokemon_spd);
        sbPokemonSpe = findViewById(R.id.sb_pokemon_spe);

//          Bundle
        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("pokemon_detail_url") != null) {
            pokemonUrl = bundle.getString("pokemon_detail_url");
            pokemonName = bundle.getString("pokemon_name");
        }

//

        Call<Pokemon> pokemonCall = RetrofitClient.getInstance().getMyApi().pokemonDetail(pokemonUrl);
        pokemonCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Pokemon pokemon = response.body();
                Picasso.get().load(pokemon.getSprites().getFrontDefault()).into(ivPokemon);

                rbShinyYes.setOnCheckedChangeListener((compoundButton, b) -> Picasso.get().load(pokemon.getSprites().getFrontDefault()).into(ivPokemon));

                rbShinyNo.setOnCheckedChangeListener((compoundButton, b) -> Picasso.get().load(pokemon.getSprites().getFrontShiny()).into(ivPokemon));

                if (etPokemonLevel.getText().toString() == "") {
                    etPokemonLevel.requestFocus();
                }
                etPokemonLevel.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        try {
                            String etStringValue = String.valueOf(etPokemonLevel.getText());
                            int level = Integer.parseInt(etStringValue);
                            if (level > 100) {
                                etPokemonLevel.setText("100");
                                level = 100;
                            }
                            levelPokemon = level;
                        } catch (NumberFormatException e) {
                            etPokemonLevel.setText("1");
                            levelPokemon = 1;
                        }
                        int hpByLevel = pokemon.hpStatCalculator(baseHPStat, hpIV, hpEV, levelPokemon);
                        pbPokemonHPBuildStat.setProgress(hpByLevel);
                        pbPokemonHPBuildStat.setIndicatorColor(getIndicatorColor(hpByLevel));
                        tvPokemonHPBuildStat.setText(String.valueOf(hpByLevel));

                        int atkByLevel = pokemon.otherStatCalculator(baseAtkStat, atkIV, atkEV, levelPokemon, atkNature);
                        pbPokemonAtkBuildStat.setProgress(atkByLevel);
                        pbPokemonAtkBuildStat.setIndicatorColor(getIndicatorColor(atkByLevel));
                        tvPokemonAtkBuildStat.setText(String.valueOf(atkByLevel));

                        int defByLevel = pokemon.otherStatCalculator(baseDefStat, defIV, defEV, levelPokemon, defNature);
                        pbPokemonDefBuildStat.setProgress(defByLevel);
                        pbPokemonDefBuildStat.setIndicatorColor(getIndicatorColor(defByLevel));
                        tvPokemonDefBuildStat.setText(String.valueOf(defByLevel));

                        int spaByLevel = pokemon.otherStatCalculator(baseSpaStat, spaIV, spaEV, levelPokemon, spaNature);
                        pbPokemonSpaBuildStat.setProgress(spaByLevel);
                        pbPokemonSpaBuildStat.setIndicatorColor(getIndicatorColor(spaByLevel));
                        tvPokemonSpaBuildStat.setText(String.valueOf(spaByLevel));

                        int spdByLevel = pokemon.otherStatCalculator(baseSpdStat, spdIV, spdEV, levelPokemon, spdNature);
                        pbPokemonSpdBuildStat.setProgress(spdByLevel);
                        pbPokemonSpdBuildStat.setIndicatorColor(getIndicatorColor(spdByLevel));
                        tvPokemonSpdBuildStat.setText(String.valueOf(spdByLevel));

                        int speByLevel = pokemon.otherStatCalculator(baseSpeStat, speIV, speEV, levelPokemon, speNature);
                        pbPokemonSpeBuildStat.setProgress(speByLevel);
                        pbPokemonSpeBuildStat.setIndicatorColor(getIndicatorColor(speByLevel));
                        tvPokemonSpeBuildStat.setText(String.valueOf(speByLevel));
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                setPokemonBuildStats(pokemon);
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });
//          Paper
//        Paper.init(this);
//
//        if (Paper.book().read(pokemonName) != null) {
//            String pokemonJsonString = Paper.book().read("pokemon_name");
//            Pokemon pokemon = new Gson().fromJson(pokemonJsonString, Pokemon.class);
//            Picasso.get().load(pokemon.getSprites().getFrontDefault()).into(ivPokemon);
//        }
        ;

    }

    private void setPokemonBuildStats(Pokemon pokemon) {
//        if (remainingEv == 0){
//            pbPokemonHPBuildStat.setEnabled(true);
//            etPokemonHPEvStat.setEnabled(true);
//            pbPokemonAtkBuildStat.setEnabled(true);
//            etPokemonAtkEvStat.setEnabled(true);
//            pbPokemonDefBuildStat.setEnabled(true);
//            etPokemonDefEvStat.setEnabled(true);
//            pbPokemonSpaBuildStat.setEnabled(true);
//            etPokemonSpaEvStat.setEnabled(true);
//            pbPokemonSpdBuildStat.setEnabled(true);
//            etPokemonSpdEvStat.setEnabled(true);
//            pbPokemonSpeBuildStat.setEnabled(true);
//            etPokemonSpeEvStat.setEnabled(true);
//        }
        List<PokemonStat> pokemonStats = pokemon.getStats();
        for (PokemonStat stat : pokemonStats
        ) {
            String pokemonStatName = stat.getStat().getName();
            switch (pokemonStatName) {
                case "hp":
                    baseHPStat = stat.getBaseStat();
                    setHPAllValue(baseHPStat, pokemon);
                    break;
                case "attack":
                    baseAtkStat = stat.getBaseStat();
                    setAtkAllValue(baseAtkStat, pokemon);
                    break;
                case "defense":
                    baseDefStat = stat.getBaseStat();
                    setDefAllValue(baseDefStat, pokemon);
                    break;
                case "special-attack":
                    baseSpaStat = stat.getBaseStat();
                    setSpaAllValue(baseSpaStat, pokemon);
                    break;
                case "special-defense":
                    baseSpdStat = stat.getBaseStat();
                    setSpdAllValue(baseSpdStat, pokemon);
                    break;
                case "speed":
                    baseSpeStat = stat.getBaseStat();
                    setSpeAllValue(baseSpeStat, pokemon);
                    break;
            }
        }
    }

    private void setHPAllValue(int baseHPStat, Pokemon pokemon) {
        setHPProgressWithCurrentEV(pokemon, baseHPStat, hpIV, hpEV);
        etPokemonHPIvStat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    String editText = etPokemonHPIvStat.getText().toString();
                    int editValue = Integer.valueOf(editText);
                    if (editValue > 31) {
                        etPokemonHPIvStat.setText("31");
                        editValue = 31;
                    }
                    hpIV = editValue;
                } catch (NumberFormatException numberFormatException) {
                    hpIV = 0;
                    etPokemonHPIvStat.setText("0");
                }
                setHPProgressWithCurrentEV(pokemon, baseHPStat, hpIV, hpEV);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        etPokemonHPEvStat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    String editText = etPokemonHPEvStat.getText().toString();
                    int editValue = Integer.valueOf(editText);
                    if (editValue > 255) {
                        sbPokemonHP.setProgress(255);
                        etPokemonHPEvStat.setText("255");
                    }
                    hpEV = editValue;
                    sbPokemonHP.setProgress(editValue);
                } catch (NumberFormatException numberFormatException) {
                    sbPokemonHP.setProgress(0);
                    etPokemonHPEvStat.setText("0");
                    hpEV = 0;
                }
                remainingEv -= hpEV;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sbPokemonHP.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                hpEV = i;
                setHPProgressWithCurrentEV(pokemon, baseHPStat, hpIV, hpEV);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setAtkAllValue(int baseAtkStat, Pokemon pokemon) {
        setAtkProgressWithCurrentEV(pokemon, baseAtkStat, atkIV, atkEV);
        etPokemonAtkIvStat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    String editText = etPokemonAtkIvStat.getText().toString();
                    int editValue = Integer.valueOf(editText);
                    if (editValue > 31) {
                        etPokemonAtkIvStat.setText("31");
                        editValue = 31;
                    }
                    atkIV = editValue;
                } catch (NumberFormatException numberFormatException) {
                    etPokemonAtkIvStat.setText("0");
                    atkIV = 0;
                }
                setAtkProgressWithCurrentEV(pokemon, baseAtkStat, atkIV, atkEV);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        etPokemonAtkEvStat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    String editText = etPokemonAtkEvStat.getText().toString();
                    int editValue = Integer.valueOf(editText);
                    if (editValue > 255) {
                        sbPokemonAtk.setProgress(255);
                        etPokemonAtkEvStat.setText("255");
                    }
                    atkEV = editValue;
                    sbPokemonAtk.setProgress(editValue);
                } catch (NumberFormatException numberFormatException) {
                    sbPokemonAtk.setProgress(0);
                    etPokemonAtkEvStat.setText("0");
                    atkEV = 0;
                }
                remainingEv -= atkEV;
            }

            ;

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        sbPokemonAtk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                atkEV = i;
                remainingEv -= i;
                setAtkProgressWithCurrentEV(pokemon, baseAtkStat, atkIV, atkEV);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setDefAllValue(int baseDefStat, Pokemon pokemon) {
        setDefProgressWithCurrentEV(pokemon, baseDefStat, defIV, defEV);
        etPokemonDefIvStat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    String editText = etPokemonDefIvStat.getText().toString();
                    int editValue = Integer.valueOf(editText);
                    if (editValue > 31) {
                        etPokemonDefIvStat.setText("31");
                        editValue = 31;
                    }
                    defIV = editValue;
                } catch (NumberFormatException numberFormatException) {
                    etPokemonDefIvStat.setText("0");
                    defIV = 0;
                }
                setDefProgressWithCurrentEV(pokemon, baseDefStat, defIV, defEV);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etPokemonDefEvStat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    String editText = etPokemonDefEvStat.getText().toString();
                    int editValue = Integer.valueOf(editText);
                    if (editValue > 255) {
                        sbPokemonDef.setProgress(255);
                        etPokemonDefEvStat.setText("255");
                    }
                    defEV = editValue;
                    sbPokemonDef.setProgress(editValue);
                } catch (NumberFormatException numberFormatException) {
                    sbPokemonDef.setProgress(0);
                    etPokemonDefEvStat.setText("0");
                    defEV = 0;
                }
                remainingEv -= defEV;
            }

            ;

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sbPokemonDef.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                defEV = i;
                remainingEv -= i;
                setDefProgressWithCurrentEV(pokemon, baseDefStat, defIV, defEV);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setSpaAllValue(int baseSpaStat, Pokemon pokemon) {
        setSpaProgressWithCurrentEV(pokemon, baseSpaStat, spaIV, spaEV);
        etPokemonSpaIvStat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    String editText = etPokemonSpaIvStat.getText().toString();
                    int editValue = Integer.valueOf(editText);
                    if (editValue > 31) {
                        etPokemonSpaIvStat.setText("31");
                        editValue = 31;
                    }
                    spaIV = editValue;
                } catch (NumberFormatException numberFormatException) {
                    etPokemonSpaIvStat.setText("0");
                    spaIV = 0;
                }
                setSpaProgressWithCurrentEV(pokemon, baseSpaStat, spaIV, spaEV);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etPokemonSpaEvStat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    String editText = etPokemonSpaEvStat.getText().toString();
                    int editValue = Integer.valueOf(editText);
                    if (editValue > 255) {
                        sbPokemonSpa.setProgress(255);
                        etPokemonSpaEvStat.setText("255");
                    }
                    spaEV = editValue;
                    sbPokemonSpa.setProgress(editValue);
                } catch (NumberFormatException numberFormatException) {
                    sbPokemonSpa.setProgress(0);
                    etPokemonSpaEvStat.setText("0");
                    spaEV = 0;
                }
                remainingEv -= spaEV;
            }

            ;

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sbPokemonSpa.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                spaEV = i;
                remainingEv -= i;
                setSpaProgressWithCurrentEV(pokemon, baseSpaStat, spaIV, spaEV);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setSpdAllValue(int baseSpdStat, Pokemon pokemon) {
        setSpdProgressWithCurrentEV(pokemon, baseSpdStat, spdIV, spdEV);
        etPokemonSpdIvStat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    String editText = etPokemonSpdIvStat.getText().toString();
                    int editValue = Integer.valueOf(editText);
                    if (editValue > 31) {
                        etPokemonSpdIvStat.setText("31");
                        editValue = 31;
                    }
                    spdIV = editValue;
                } catch (NumberFormatException numberFormatException) {
                    etPokemonSpdIvStat.setText("0");
                    spdIV = 0;
                }
                setSpdProgressWithCurrentEV(pokemon, baseSpdStat, spdIV, spdEV);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etPokemonSpdEvStat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    String editText = etPokemonSpdEvStat.getText().toString();
                    int editValue = Integer.valueOf(editText);
                    if (editValue > 255) {
                        sbPokemonSpd.setProgress(255);
                        etPokemonSpdEvStat.setText("255");
                    }
                    spdEV = editValue;
                    sbPokemonSpd.setProgress(editValue);
                } catch (NumberFormatException numberFormatException) {
                    sbPokemonSpd.setProgress(0);
                    etPokemonSpdEvStat.setText("0");
                    spdEV = 0;
                }
                remainingEv -= spdEV;
            }

            ;

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sbPokemonSpd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                spdEV = i;
                remainingEv -= i;
                setSpdProgressWithCurrentEV(pokemon, baseSpdStat, spdIV, spdEV);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setSpeAllValue(int baseSpeStat, Pokemon pokemon) {
        setSpeProgressWithCurrentEV(pokemon, baseSpeStat, speIV, speEV);
        etPokemonSpeIvStat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    String editText = etPokemonSpeIvStat.getText().toString();
                    int editValue = Integer.valueOf(editText);
                    if (editValue > 31) {
                        etPokemonSpeIvStat.setText("31");
                        editValue = 31;
                    }
                    speIV = editValue;
                } catch (NumberFormatException numberFormatException) {
                    etPokemonSpeIvStat.setText("0");
                    speIV = 0;
                }
                setSpeProgressWithCurrentEV(pokemon, baseSpeStat, speIV, speEV);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etPokemonSpeEvStat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    String editText = etPokemonSpeEvStat.getText().toString();
                    int editValue = Integer.valueOf(editText);
                    if (editValue > 255) {
                        sbPokemonSpe.setProgress(255);
                        etPokemonSpeEvStat.setText("255");
                    }
                    speEV = editValue;
                    sbPokemonSpe.setProgress(editValue);
                } catch (NumberFormatException numberFormatException) {
                    sbPokemonSpe.setProgress(0);
                    etPokemonSpeEvStat.setText("0");
                    speEV = 0;
                }
                remainingEv -= speEV;
            }

            ;

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sbPokemonSpe.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                speEV = i;
                remainingEv -= i;
                setSpeProgressWithCurrentEV(pokemon, baseSpeStat, speIV, speEV);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setHPProgressWithCurrentEV(Pokemon pokemon, int baseStat, int iv, int ev) {
        int progressHPBuildStat = pokemon.hpStatCalculator(baseStat, iv, ev, levelPokemon);
        tvPokemonHPBuildStat.setText(String.valueOf(progressHPBuildStat));
        etPokemonHPEvStat.setText(String.valueOf(ev));
        tvPokemonHPBuildStat.setText(String.valueOf(progressHPBuildStat));
        pbPokemonHPBuildStat.setProgress(progressHPBuildStat);
        pbPokemonHPBuildStat.setIndicatorColor(getIndicatorColor(progressHPBuildStat));
    }

    private void setAtkProgressWithCurrentEV(Pokemon pokemon, int baseStat, int iv, int ev) {
        int progressAtkBuildStat = pokemon.otherStatCalculator(baseStat, iv, ev, levelPokemon, atkNature);
        tvPokemonAtkBuildStat.setText(String.valueOf(progressAtkBuildStat));
        etPokemonAtkEvStat.setText(String.valueOf(ev));
        tvPokemonAtkBuildStat.setText(String.valueOf(progressAtkBuildStat));
        pbPokemonAtkBuildStat.setProgress(progressAtkBuildStat);
        pbPokemonAtkBuildStat.setIndicatorColor(getIndicatorColor(progressAtkBuildStat));
    }

    private void setDefProgressWithCurrentEV(Pokemon pokemon, int baseStat, int iv, int ev) {
        int progressDefBuildStat = pokemon.otherStatCalculator(baseStat, iv, ev, levelPokemon, defNature);
        tvPokemonDefBuildStat.setText(String.valueOf(progressDefBuildStat));
        etPokemonDefEvStat.setText(String.valueOf(ev));
        tvPokemonDefBuildStat.setText(String.valueOf(progressDefBuildStat));
        pbPokemonDefBuildStat.setProgress(progressDefBuildStat);
        pbPokemonDefBuildStat.setIndicatorColor(getIndicatorColor(progressDefBuildStat));
    }

    private void setSpaProgressWithCurrentEV(Pokemon pokemon, int baseStat, int iv, int ev) {
        int progressSpaBuildStat = pokemon.otherStatCalculator(baseStat, iv, ev, levelPokemon, spaNature);
        tvPokemonSpaBuildStat.setText(String.valueOf(progressSpaBuildStat));
        etPokemonSpaEvStat.setText(String.valueOf(ev));
        tvPokemonSpaBuildStat.setText(String.valueOf(progressSpaBuildStat));
        pbPokemonSpaBuildStat.setProgress(progressSpaBuildStat);
        pbPokemonSpaBuildStat.setIndicatorColor(getIndicatorColor(progressSpaBuildStat));
    }

    private void setSpdProgressWithCurrentEV(Pokemon pokemon, int baseStat, int iv, int ev) {
        int progressSpdBuildStat = pokemon.otherStatCalculator(baseStat, iv, ev, levelPokemon, spdNature);
        tvPokemonSpdBuildStat.setText(String.valueOf(progressSpdBuildStat));
        etPokemonSpdEvStat.setText(String.valueOf(ev));
        tvPokemonSpdBuildStat.setText(String.valueOf(progressSpdBuildStat));
        pbPokemonSpdBuildStat.setProgress(progressSpdBuildStat);
        pbPokemonSpdBuildStat.setIndicatorColor(getIndicatorColor(progressSpdBuildStat));
    }

    private void setSpeProgressWithCurrentEV(Pokemon pokemon, int baseStat, int iv, int ev) {
        int progressSpeBuildStat = pokemon.otherStatCalculator(baseStat, iv, ev, levelPokemon, speNature);
        tvPokemonSpeBuildStat.setText(String.valueOf(progressSpeBuildStat));
        etPokemonSpeEvStat.setText(String.valueOf(ev));
        tvPokemonSpeBuildStat.setText(String.valueOf(progressSpeBuildStat));
        pbPokemonSpeBuildStat.setProgress(progressSpeBuildStat);
        pbPokemonSpeBuildStat.setIndicatorColor(getIndicatorColor(progressSpeBuildStat));
    }

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
        if (stat < 400) {
            return getResources().getColor(R.color.black);
        }
        return 0;
    }
}