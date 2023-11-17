package com.example.mypokemonapplication.viewholder;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypokemonapplication.R;
import com.squareup.picasso.Picasso;

public class PokemonViewHolder extends RecyclerView.ViewHolder {
    private ImageView ivPokemon;
    public TextView pokemonName;

    public PokemonViewHolder(@NonNull View itemView) {
        super(itemView);
        ivPokemon = (ImageView) itemView.findViewById(R.id.cv_pokemon_image);
        pokemonName = (TextView) itemView.findViewById(R.id.cv_pokemon_name);
    }


    public void setImgUrlFromName(String name) {
        String url = "https://img.pokemondb.net/artwork/large/" + name + ".jpg";
        Picasso.get().load(url).error(R.drawable.download).into(ivPokemon);
    }


    public void setImgUrlFromId(int id) {
        String url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";
        Picasso.get().load(url).into(ivPokemon);
    }
}
