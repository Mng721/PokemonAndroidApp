package com.example.mypokemonapplication.adapter;

import static org.apache.commons.lang3.StringUtils.capitalize;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypokemonapplication.R;
import com.example.mypokemonapplication.model.AllPokemon;
import com.example.mypokemonapplication.model.AllPokemonFromJson;
import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.example.mypokemonapplication.view.PokemonDetailActivity;
import com.example.mypokemonapplication.viewholder.PokemonViewHolder;

import java.util.ArrayList;
import java.util.List;

public class PokemonFromApiAdapter  extends RecyclerView.Adapter<PokemonViewHolder> implements Filterable {

    private Activity activity;
    private List<NamedAPIResource> results;
    private List<NamedAPIResource> oldResults;

    public PokemonFromApiAdapter(Activity activity, List<NamedAPIResource> results) {
        this.activity = activity;
        this.results = results;
        this.oldResults = oldResults;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.pokemon_cardview_item, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        NamedAPIResource pokemon = results.get(position);

        String pokemonName = pokemon.getName();
        holder.setImgUrlFromPosition(pokemonName.toLowerCase());
        if (pokemonName != null) {
            holder.pokemonName.setVisibility(View.VISIBLE);
            holder.pokemonName.setText("No. " + String.valueOf(position + 1) + ": " + capitalize(pokemonName));
        } else {
            holder.pokemonName.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, PokemonDetailActivity.class);
            intent.putExtra("pokemon_detail_url", "https://pokeapi.co/api/v2/pokemon/" + pokemon.getName() + "/");
            intent.putExtra("pokemon_name", pokemon.getName());
            activity.startActivity(intent);

//                Animation change layout
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String stringSearch = charSequence.toString();
                if (stringSearch.isEmpty()){
                    results = oldResults;
                } else {
                    List<NamedAPIResource> list = new ArrayList<>();
                    for (NamedAPIResource pokemon: oldResults){
                        if (pokemon.getName().toLowerCase().contains(stringSearch.toLowerCase())){
                            list.add(pokemon);
                        };
                    }
                    results = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = results;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                results = (List<NamedAPIResource>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}