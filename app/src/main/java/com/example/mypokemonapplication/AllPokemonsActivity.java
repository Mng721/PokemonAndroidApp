package com.example.mypokemonapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

import com.example.mypokemonapplication.adapter.PokemonFromJsonAdapter;
import com.example.mypokemonapplication.model.AllPokemonFromJson;
import java.util.List;

public class AllPokemonsActivity extends AppCompatActivity {

    //    RecyclerView
    private RecyclerView rvPokemonsList;

    //    Adapter
    private PokemonFromJsonAdapter pokemonFromJsonAdapter;

    //    SearchView
    private SearchView searchView;

//
    private List<AllPokemonFromJson> allPokemonFromJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_pokemons);

        allPokemonFromJson = com.example.mypokemonapplication.JsonReader.convertJsonToObject(AllPokemonsActivity.this);

//        Recycler view
        rvPokemonsList = findViewById(R.id.rv_pokemon_list);

        rvPokemonsList.setLayoutManager(new GridLayoutManager(AllPokemonsActivity.this, 2));
        pokemonFromJsonAdapter = new PokemonFromJsonAdapter(AllPokemonsActivity.this, allPokemonFromJson);
        rvPokemonsList.setAdapter(pokemonFromJsonAdapter);

//
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                pokemonFromJsonAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                pokemonFromJsonAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}