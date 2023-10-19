package com.example.mypokemonapplication.viewholder;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypokemonapplication.R;

public class PokemonTypeViewHolder extends RecyclerView.ViewHolder {
    public TextView pokemonDetailType;

    public PokemonTypeViewHolder(@NonNull View itemView) {
        super(itemView);

        pokemonDetailType = itemView.findViewById(R.id.cv_pokemon_detail_type);
    }

    public void setText(String type) {
        pokemonDetailType.setText(type.toUpperCase());
    }

    public void setTintColor(@NonNull TextView textView, String color) {
        textView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
    }


    public String getColor(String type) {
        switch (type) {
            case "normal":
                return "#A8A77A";
            case "fire":
                return "#EE8130";

            case "water":
                return "#6390F0";

            case "electric":
                return "#F7D02C";

            case "grass":
                return "#7AC74C";

            case "ice":
                return "#96D9D6";

            case "fighting":
                return "#C22E28";

            case "poison":
                return "#A33EA1";

            case "ground":
                return "#E2BF65";

            case "flying":
                return "#A98FF3";

            case "psychic":
                return "#F95587";

            case "bug":
                return "#A6B91A";

            case "rock":
                return "#B6A136";

            case "ghost":
                return "#735797";

            case "dragon":
                return "#6F35FC";

            case "dark":
                return "#705746";

            case "steel":
                return "#B7B7CE";

            case "fairy":
                return "#D685AD";
        }
        return "";
    }
}