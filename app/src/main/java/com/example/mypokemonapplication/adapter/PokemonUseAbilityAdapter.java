package com.example.mypokemonapplication.adapter;

import android.app.Activity;
import android.content.Intent;
import android.text.method.TextKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypokemonapplication.R;
import com.example.mypokemonapplication.model.pokemon.Ability.AbilityPokemon;
import com.example.mypokemonapplication.view.PokemonDetailActivity;
import com.example.mypokemonapplication.viewholder.PokemonUseAbilityViewHolder;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

public class PokemonUseAbilityAdapter extends RecyclerView.Adapter<PokemonUseAbilityViewHolder> {

    private Activity activity;
    private List<AbilityPokemon> results;

    public PokemonUseAbilityAdapter(Activity activity, List<AbilityPokemon> results) {
        this.activity = activity;
        this.results = results;
    }

    @NonNull
    @Override
    public PokemonUseAbilityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.pokemon_use_ability_item, parent, false);
        return new PokemonUseAbilityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonUseAbilityViewHolder holder, int position) {
        AbilityPokemon pokemon = results.get(position);

        String pokemonName = pokemon.getPokemon().getName();
        String pokemonUrl = pokemon.getPokemon().getUrl();
        String pokemonId = pokemonUrl.substring(pokemonUrl.indexOf("mon/") + 4, pokemonUrl.length() - 1);
        pokemonId = pokemonId.length() >= 5 ? pokemonId : pokemonId.length() == 4 ? "0" + pokemonId : pokemonId.length() == 3 ? "00" + pokemonId : pokemonId.length() == 2 ? "000" + pokemonId : "0000"+ pokemonId;
        holder.tvPokemonName.setText(WordUtils.capitalize(pokemonName));
        holder.tvPokemonID.setText(pokemonId);
        holder.setImageCardView(pokemonName);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, PokemonDetailActivity.class);
            intent.putExtra("pokemon_detail_url", "https://pokeapi.co/api/v2/pokemon/" + pokemonName + "/");
            intent.putExtra("pokemon_name", pokemonName);
            activity.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return results.size();
    }
}
