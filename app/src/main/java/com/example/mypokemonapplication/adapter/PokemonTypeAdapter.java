package com.example.mypokemonapplication.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypokemonapplication.R;
import com.example.mypokemonapplication.model.pokemon.pokemondetail.PokemonType;
import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.example.mypokemonapplication.view.TypeDetailActivity;
import com.example.mypokemonapplication.viewholder.PokemonTypeViewHolder;

import java.util.List;

public class PokemonTypeAdapter extends RecyclerView.Adapter<PokemonTypeViewHolder> {
    private Activity activity;
    private List<PokemonType> results;
    private List<NamedAPIResource> results2;

    public PokemonTypeAdapter(Activity activity, List<PokemonType> results) {
        this.activity = activity;
        this.results = results;
    }

    public PokemonTypeAdapter(Activity activity, List<PokemonType> results, List<NamedAPIResource> results2) {
        this.activity = activity;
        this.results = results;
        this.results2 = results2;
    }

    @NonNull
    @Override
    public PokemonTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.type_item, parent, false);
        return new PokemonTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonTypeViewHolder holder, int position) {
        if (results != null) {
            PokemonType pokemonType = results.get(position);
            String type = pokemonType.getType().getName();

            if (type != null) {
                holder.setText(type);
                String typeColor = holder.getColor(type);
                holder.setTintColor(holder.pokemonDetailType, typeColor);
                holder.pokemonDetailType.setVisibility(View.VISIBLE);
            } else {
                holder.pokemonDetailType.setVisibility(View.GONE);
            }

            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(activity, TypeDetailActivity.class);
                intent.putExtra("type_detail_url", pokemonType.getType().getUrl());
                intent.putExtra("type_detail_name", pokemonType.getType().getName());
                activity.startActivity(intent);

                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            });
        }
        else if (results2 != null){
            NamedAPIResource pokemonType = results2.get(position);
            String type = pokemonType.getName();

            if (type != null) {
                holder.setText(type);
                String typeColor = holder.getColor(type);
                holder.setTintColor(holder.pokemonDetailType, typeColor);
                holder.pokemonDetailType.setVisibility(View.VISIBLE);
            } else {
                holder.pokemonDetailType.setVisibility(View.GONE);
            }

            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(activity, TypeDetailActivity.class);
                intent.putExtra("type_detail_url", pokemonType.getUrl());
                intent.putExtra("type_detail_name", pokemonType.getName());
                activity.startActivity(intent);

                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            });}
    }

    @Override
    public int getItemCount() {
        if (results != null) {
        return results.size();}else if (results2 != null){return results2.size();}return 0;
    }
}
