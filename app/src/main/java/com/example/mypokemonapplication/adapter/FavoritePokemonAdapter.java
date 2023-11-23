package com.example.mypokemonapplication.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypokemonapplication.R;
import com.example.mypokemonapplication.view.PokemonDetailActivity;
import com.example.mypokemonapplication.viewholder.PokemonViewHolder;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

public class FavoritePokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {
    private Activity activity;
    private List<String> results;

    public FavoritePokemonAdapter(Activity activity, List<String> results) {
        this.activity = activity;
        this.results = results;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.pokemon_cardview_item, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        String pokemon = results.get(position);
        holder.setImgUrlFromName(pokemon);
        holder.pokemonName.setText(WordUtils.capitalize(pokemon));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, PokemonDetailActivity.class);
            intent.putExtra("pokemon_detail_url", "https://pokeapi.co/api/v2/pokemon/" + pokemon + "/");
            intent.putExtra("pokemon_name", pokemon);
            activity.startActivity(intent);

//                Animation change layout
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}
