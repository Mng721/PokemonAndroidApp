package com.example.mypokemonapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mypokemonapplication.AllPokemonsActivity;
import com.example.mypokemonapplication.R;
import com.example.mypokemonapplication.adapter.FavoritePokemonAdapter;
import com.example.mypokemonapplication.viewmodels.FavoritePokemonViewModel;

public class FavoritePokemonActivity extends AppCompatActivity {

    FavoritePokemonViewModel viewModel;

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_pokemon);
        viewModel = new ViewModelProvider(this).get(FavoritePokemonViewModel.class);

        recyclerView = findViewById(R.id.rv_fav_pokemon_list);
        recyclerView.setLayoutManager(new GridLayoutManager(FavoritePokemonActivity.this, 2));

        viewModel.getPokemonFavName().observe(this, pokemon ->{
            FavoritePokemonAdapter adapter = new FavoritePokemonAdapter(this, pokemon);
            recyclerView.setAdapter(adapter);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        recreate();
    }
}