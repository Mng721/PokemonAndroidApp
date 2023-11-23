package com.example.mypokemonapplication.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypokemonapplication.R;
import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.squareup.picasso.Picasso;

public class PokemonUseAbilityViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivPokemon;
    public TextView tvPokemonID;
    public TextView tvPokemonName;
    public PokemonUseAbilityViewHolder(@NonNull View itemView) {
        super(itemView);

        ivPokemon = itemView.findViewById(R.id.iv_pokemon_img);
        tvPokemonID = itemView.findViewById(R.id.tv_pokemon_id);
        tvPokemonName = itemView.findViewById(R.id.tv_pokemon_name);
    }

    public void setImageCardView(String name){
        Picasso.get().load("https://img.pokemondb.net/sprites/sword-shield/icon/"+ name +".png").error(R.drawable.download).resize(56,56).into(ivPokemon);
    }
}
