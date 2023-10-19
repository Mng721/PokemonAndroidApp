package com.example.mypokemonapplication.view;

import static org.apache.commons.lang3.StringUtils.capitalize;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import com.example.mypokemonapplication.R;
import com.example.mypokemonapplication.adapter.PokemonTypeAdapter;
import com.example.mypokemonapplication.clients.RetrofitClient;
import com.example.mypokemonapplication.interfaces.RetrofitService;
import com.example.mypokemonapplication.model.pokemon.pokemondetail.Pokemon;
import com.example.mypokemonapplication.model.pokemon.typedetail.Type;
import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.google.gson.Gson;

import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TypeDetailActivity extends AppCompatActivity {
//    String
    private String urlType;
    private String typeName;

//    TextView
    private TextView tvTypeDetailTitle;
    private TextView tvAtkProsConsTitle;
    private TextView tvAtkPros;
    private TextView tvAtkCons;
    private TextView tvAtkNoEffect;
    private TextView tvDefProsConsTitle;
    private TextView tvDefNoEffect;
    private TextView tvDefPros;
    private TextView tvDefCons;

//    RecyclerView
    private RecyclerView rvAtkPros;
    private RecyclerView rvAtkCons;
    private RecyclerView rvAtkNoEffect;
    private RecyclerView rvDefNoEffect;
    private RecyclerView rvDefPros;
    private RecyclerView rvDefCons;

//    Retrofit
    private RetrofitService retrofitService;

//    Adapter
    private PokemonTypeAdapter pokemonTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_detail);

//        TextView
        tvTypeDetailTitle = findViewById(R.id.tv_type_detail_title);
        tvAtkProsConsTitle = findViewById(R.id.tv_atk_pros_cons_title);
        tvAtkPros = findViewById(R.id.tv_atk_pros);
        tvAtkCons = findViewById(R.id.tv_atk_cons);
        tvAtkNoEffect = findViewById(R.id.tv_atk_no_effect);
        tvDefProsConsTitle = findViewById(R.id.tv_def_pros_cons_title);
        tvDefNoEffect = findViewById(R.id.tv_def_no_effect);
        tvDefPros = findViewById(R.id.tv_def_pros);
        tvDefCons = findViewById(R.id.tv_def_cons);

//        Recycler View
        rvAtkPros = findViewById(R.id.rv_atk_pros);
        rvAtkCons = findViewById(R.id.rv_atk_cons);
        rvAtkNoEffect = findViewById(R.id.rv_atk_no_effect);
        rvDefNoEffect = findViewById(R.id.rv_def_no_effect);
        rvDefPros = findViewById(R.id.rv_def_pros);
        rvDefCons = findViewById(R.id.rv_def_cons);

//        Bundle
        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("type_detail_url") != null){
            urlType = bundle.getString("type_detail_url");
            typeName = bundle.getString("type_detail_name");
        }

//    Paper
        Paper.init(this);
        if (Paper.book().read(typeName) != null){
            String results = Paper.book().read(typeName);
            Type type = new Gson().fromJson(results, Type.class);
            setupProsCons(type);
        }


//        Retrofit
        retrofitService = RetrofitClient.getClient().create(RetrofitService.class);
        Call<Type> typeCall = retrofitService.typeDetail(urlType);
        typeCall.enqueue(new Callback<Type>() {
            @Override
            public void onResponse(Call<Type> call, Response<Type> response) {
                Type type = response.body();
                setupProsCons(type);
                Paper.book().write(typeName, new Gson().toJson(type));
            }

            @Override
            public void onFailure(Call<Type> call, Throwable t) {

            }
        });
    }
    private void setupProsCons(Type type){
//                LayoutManager
        LinearLayoutManager layoutAtkProManager = new LinearLayoutManager(TypeDetailActivity.this, RecyclerView.HORIZONTAL, false);
        LinearLayoutManager layoutAtkConsManager = new LinearLayoutManager(TypeDetailActivity.this, RecyclerView.HORIZONTAL, false);
        LinearLayoutManager layoutDefProsManager = new LinearLayoutManager(TypeDetailActivity.this, RecyclerView.HORIZONTAL, false);
        LinearLayoutManager layoutDefConsManager = new LinearLayoutManager(TypeDetailActivity.this, RecyclerView.HORIZONTAL, false);
        LinearLayoutManager layoutAtkNoEffectManager = new LinearLayoutManager(TypeDetailActivity.this, RecyclerView.HORIZONTAL, false);
        LinearLayoutManager layoutDefNoEffectManager = new LinearLayoutManager(TypeDetailActivity.this, RecyclerView.HORIZONTAL, false);

//                Type title
        String typeTitleString = capitalize(typeName) + " (type)";
        Spannable spannableTypeTitle = new SpannableString(typeTitleString);
        spannableTypeTitle.setSpan(new ForegroundColorSpan(0xFF737373), typeTitleString.indexOf("("), typeTitleString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTypeDetailTitle.setText(spannableTypeTitle, TextView.BufferType.SPANNABLE);

//                Atk pros cons title
        String atkProsConsTitleString = "Attack pros & cons";
        Spannable spannableAtkProsCons = new SpannableString(atkProsConsTitleString);
        spannableAtkProsCons.setSpan(new StyleSpan(Typeface.ITALIC), atkProsConsTitleString.indexOf("pros"), atkProsConsTitleString.indexOf(" &"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableAtkProsCons.setSpan(new StyleSpan(Typeface.ITALIC), atkProsConsTitleString.indexOf("cons"), atkProsConsTitleString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvAtkProsConsTitle.setText(spannableAtkProsCons, TextView.BufferType.SPANNABLE);

//                Atk pros
        String atkProsString = capitalize(typeName) + " moves are super-effective against:";
        Spannable spannableAtkPros = new SpannableString(atkProsString);
        spannableAtkPros.setSpan(new StyleSpan(Typeface.ITALIC), 0, typeName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvAtkPros.setText(spannableAtkPros, TextView.BufferType.SPANNABLE);

//                RecyclerView atk pros
        rvAtkPros.setLayoutManager(layoutAtkProManager);
        List<NamedAPIResource> doubleDamageTo = type.getDamageRelation().getDoubleDamageTo();
        pokemonTypeAdapter = new PokemonTypeAdapter(TypeDetailActivity.this,null, doubleDamageTo);
        rvAtkPros.setAdapter(pokemonTypeAdapter);

//                Atk cons
        String atkConsString = capitalize(typeName) + " moves are not very effective against:";
        Spannable spannableAtkCons = new SpannableString(atkConsString);
        spannableAtkCons.setSpan(new StyleSpan(Typeface.ITALIC), 0, typeName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvAtkCons.setText(spannableAtkCons, TextView.BufferType.SPANNABLE);
//
//                RecyclerView atk cons
        rvAtkCons.setLayoutManager(layoutAtkConsManager);
        List<NamedAPIResource> halfDamageTo = type.getDamageRelation().getHalfDamageTo();
        pokemonTypeAdapter = new PokemonTypeAdapter(TypeDetailActivity.this,null, halfDamageTo);
        rvAtkCons.setAdapter(pokemonTypeAdapter);

//                Atk no damage
        List<NamedAPIResource> noDamageTo = type.getDamageRelation().getNoDamageTo();
        if (noDamageTo.size() == 0){
            tvAtkNoEffect.setVisibility(View.GONE);
            rvAtkNoEffect.setVisibility(View.GONE);
        }else {
            String atkNoEffectString = capitalize(typeName) + " moves have no effect on:";
            Spannable spannableAtkNoEffect = new SpannableString(atkNoEffectString);
            spannableAtkNoEffect.setSpan(new StyleSpan(Typeface.ITALIC), 0, typeName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvAtkNoEffect.setText(spannableAtkNoEffect, TextView.BufferType.SPANNABLE);
//                RecyclerView atk no damamge
            rvAtkNoEffect.setLayoutManager(layoutAtkNoEffectManager);
            pokemonTypeAdapter = new PokemonTypeAdapter(TypeDetailActivity.this, null, noDamageTo);
            rvAtkNoEffect.setAdapter(pokemonTypeAdapter);
        }
//                Def pros cons title
        String defProsConsTitleString = "Defense pros & cons";
        Spannable spannableDefProsCons = new SpannableString(defProsConsTitleString);
        spannableDefProsCons.setSpan(new StyleSpan(Typeface.ITALIC), defProsConsTitleString.indexOf("pros"), defProsConsTitleString.indexOf(" &"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableDefProsCons.setSpan(new StyleSpan(Typeface.ITALIC), defProsConsTitleString.indexOf("cons"), defProsConsTitleString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDefProsConsTitle.setText(spannableDefProsCons, TextView.BufferType.SPANNABLE);

//                Def no damage
        List<NamedAPIResource> noDamageFrom = type.getDamageRelation().getNoDamageFrom();
        if (noDamageTo.size() == 0){
            tvDefNoEffect.setVisibility(View.GONE);
            rvDefNoEffect.setVisibility(View.GONE);
        }else {
            String atkNoEffectString = capitalize(typeName) + " moves have no effect on:";
            Spannable spannableDefNoEffect = new SpannableString(atkNoEffectString);
            spannableDefNoEffect.setSpan(new StyleSpan(Typeface.ITALIC), 0, typeName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvDefNoEffect.setText(spannableDefNoEffect, TextView.BufferType.SPANNABLE);
//                RecyclerView atk no damamge
            rvDefNoEffect.setLayoutManager(layoutDefNoEffectManager);
            pokemonTypeAdapter = new PokemonTypeAdapter(TypeDetailActivity.this, null, noDamageTo);
            rvDefNoEffect.setAdapter(pokemonTypeAdapter);
        }
//                Def pros
        String defProsString =  "These types are not very effective against "+ capitalize(typeName) +" Pokémon:";
        Spannable spannableDefPros = new SpannableString(defProsString);
        spannableDefPros.setSpan(new StyleSpan(Typeface.ITALIC), defProsString.indexOf(capitalize(typeName)), defProsString.indexOf("Pok"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDefPros.setText(spannableDefPros, TextView.BufferType.SPANNABLE);

//                RecyclerView def pros
        rvDefPros.setLayoutManager(layoutDefProsManager);
        List<NamedAPIResource> halfDamageFrom = type.getDamageRelation().getHalfDamageFrom();
        pokemonTypeAdapter = new PokemonTypeAdapter(TypeDetailActivity.this,null, halfDamageFrom);
        rvDefPros.setAdapter(pokemonTypeAdapter);

//                Def cons
        String defConsString = "These types are super-effective against " + capitalize(typeName) + " Pokémon:";
        Spannable spannableDefCons = new SpannableString(defConsString);
        spannableDefCons.setSpan(new StyleSpan(Typeface.ITALIC), defConsString.indexOf(capitalize(typeName)), defConsString.indexOf("Pok"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDefCons.setText(spannableDefCons, TextView.BufferType.SPANNABLE);
//
//                RecyclerView def cons
        rvDefCons.setLayoutManager(layoutDefConsManager);
        List<NamedAPIResource> doubleDamageFrom = type.getDamageRelation().getDoubleDamageFrom();
        pokemonTypeAdapter = new PokemonTypeAdapter(TypeDetailActivity.this,null, doubleDamageFrom);
        rvDefCons.setAdapter(pokemonTypeAdapter);
    }
}