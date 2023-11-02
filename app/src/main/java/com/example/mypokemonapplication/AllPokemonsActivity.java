package com.example.mypokemonapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;

import com.example.mypokemonapplication.adapter.PokemonFromApiAdapter;
import com.example.mypokemonapplication.adapter.PokemonFromJsonAdapter;
import com.example.mypokemonapplication.clients.RetrofitClient;
import com.example.mypokemonapplication.interfaces.RetrofitService;
import com.example.mypokemonapplication.model.AllPokemon;
import com.example.mypokemonapplication.model.AllPokemonFromJson;
import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPokemonsActivity extends AppCompatActivity {

    //    RecyclerView
    private RecyclerView rvPokemonsList;

    //    Adapter
    private PokemonFromJsonAdapter pokemonFromJsonAdapter;
    private PokemonFromApiAdapter pokemonFromApiAdapter;

    //    SearchView
    private SearchView searchView;

//
    private List<AllPokemonFromJson> allPokemonFromJson;

    private RetrofitService retrofitService;
    private boolean isInternetConnection;

    private List<NamedAPIResource> listPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_pokemons);

        isInternetConnection = isInternetConnected();

//        Recycler view
        rvPokemonsList = findViewById(R.id.rv_pokemon_list);

        rvPokemonsList.setLayoutManager(new GridLayoutManager(AllPokemonsActivity.this, 2));

        if (isInternetConnection) {
            retrofitService = RetrofitClient.getClient().create(RetrofitService.class);
            Call<AllPokemon> pokemonCall = retrofitService.allPokemon("https://pokeapi.co/api/v2/pokemon?limit=100000&offset=0");
            pokemonCall.enqueue(new Callback<AllPokemon>() {
                @Override
                public void onResponse(Call<AllPokemon> call, Response<AllPokemon> response) {
                    if (response.body() != null) {
                        listPokemon = response.body().getResults();
                    }

                    pokemonFromApiAdapter = new PokemonFromApiAdapter(AllPokemonsActivity.this, listPokemon);
                    rvPokemonsList.setAdapter(pokemonFromApiAdapter);
                }

                @Override
                public void onFailure(Call<AllPokemon> call, Throwable t) {

                }
            });
        } else {
            allPokemonFromJson = com.example.mypokemonapplication.JsonReader.convertJsonToObject(AllPokemonsActivity.this);
            pokemonFromJsonAdapter = new PokemonFromJsonAdapter(AllPokemonsActivity.this, allPokemonFromJson);
            rvPokemonsList.setAdapter(pokemonFromJsonAdapter);
        }

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
                if (isInternetConnection) {
                    pokemonFromApiAdapter.getFilter().filter(query);
                } else {
                    pokemonFromJsonAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (isInternetConnection) {
                    pokemonFromApiAdapter.getFilter().filter(newText);
                } else {
                    pokemonFromJsonAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });
        return true;
    }

    private boolean isInternetConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        return (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);
    }
}