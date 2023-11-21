package com.example.mypokemonapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mypokemonapplication.adapter.PokemonFromApiAdapter;
import com.example.mypokemonapplication.adapter.PokemonFromJsonAdapter;
import com.example.mypokemonapplication.clients.RetrofitClient;
import com.example.mypokemonapplication.interfaces.RetrofitService;
import com.example.mypokemonapplication.model.AllPokemon;
import com.example.mypokemonapplication.model.AllPokemonFromJson;
import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.example.mypokemonapplication.view.AccountSettingActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPokemonsActivity extends AppCompatActivity {

    //    RecyclerView
    private RecyclerView rvPokemonsList;

    //    Adapter
    private PokemonFromJsonAdapter pokemonFromJsonAdapter;
    private PokemonFromApiAdapter pokemonFromApiAdapter;

    //    ImageView

    private RetrofitService retrofitService;

    private AllPokemon allPokemon;

    private List<NamedAPIResource> listPokemon;


    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_pokemons);

        getDataFromApi();
        setUpToolbar();
        openDrawer();
        onClickDrawerItem();
    }



    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        //    SearchView
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (isInternetConnected()) {
                    pokemonFromApiAdapter.getFilter().filter(query);
                } else {
                    pokemonFromJsonAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (isInternetConnected()) {
                    pokemonFromApiAdapter.getFilter().filter(newText);
                } else {
                    pokemonFromJsonAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });
        return true;
    }

    private void setUpToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    private void openDrawer(){

        drawerLayout = (DrawerLayout) findViewById(R.id.my_drawer_layout);
        ImageView ivOpenDrawer = (ImageView) findViewById(R.id.iv_hamburger_toolbar);
        ivOpenDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void getDataFromApi(){
        Gson gson = new Gson();
//        Recycler view
        rvPokemonsList = findViewById(R.id.rv_pokemon_list);

        rvPokemonsList.setLayoutManager(new GridLayoutManager(AllPokemonsActivity.this, 2));

        if (isInternetConnected()) {
            Call<AllPokemon> pokemonCall = RetrofitClient.getInstance().getMyApi().allPokemon("https://pokeapi.co/api/v2/pokemon?limit=100000&offset=0");
            pokemonCall.enqueue(new Callback<AllPokemon>() {
                @Override
                public void onResponse(Call<AllPokemon> call, Response<AllPokemon> response) {
                    if (response.body() != null) {
                        listPokemon = response.body().getResults();
                        pokemonFromApiAdapter = new PokemonFromApiAdapter(AllPokemonsActivity.this, listPokemon);
                        rvPokemonsList.setAdapter(pokemonFromApiAdapter);
                    }
                }
                @Override
                public void onFailure(Call<AllPokemon> call, Throwable t) {

                }
            });
        } else {
            //
            List<AllPokemonFromJson> allPokemonFromJson = JsonReader.convertJsonToObject(AllPokemonsActivity.this);
            pokemonFromJsonAdapter = new PokemonFromJsonAdapter(AllPokemonsActivity.this, allPokemonFromJson);
            rvPokemonsList.setAdapter(pokemonFromJsonAdapter);
        }}
    private boolean isInternetConnected(){
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", Objects.requireNonNull(e.getMessage()));
        }
        return false;
    }

    private void onClickDrawerItem(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.nav_logout:
                        FirebaseAuth.getInstance().signOut();
                        finish();
                        Toast.makeText(AllPokemonsActivity.this, "Logout success.", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_account:
                        Intent intent = new Intent(AllPokemonsActivity.this, AccountSettingActivity.class);
                        startActivity(intent);
                        drawerLayout.close();
                        break;
                }
                return true;
            }
        });
    }
}