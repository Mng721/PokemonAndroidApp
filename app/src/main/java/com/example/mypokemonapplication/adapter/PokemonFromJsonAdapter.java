package com.example.mypokemonapplication.adapter;

import static org.apache.commons.lang3.StringUtils.capitalize;

import android.annotation.SuppressLint;
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
import com.example.mypokemonapplication.model.AllPokemonFromJson;
import com.example.mypokemonapplication.model.utility.common_models.NamedAPIResource;
import com.example.mypokemonapplication.view.PokemonDetailActivity;
import com.example.mypokemonapplication.viewholder.PokemonViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PokemonFromJsonAdapter extends RecyclerView.Adapter<PokemonViewHolder> implements Filterable {

    private Activity activity;
    private List<AllPokemonFromJson> results;
    private List<AllPokemonFromJson> oldResults;

    public PokemonFromJsonAdapter(Activity activity, List<AllPokemonFromJson> results) {
        this.activity = activity;
        this.results = results;
        this.oldResults = results;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.pokemon_cardview_item, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        AllPokemonFromJson allPokemonFromJson = results.get(position);

        int pokemonId = allPokemonFromJson.getId();

        String pokemonName = allPokemonFromJson.getName();
        holder.setImgUrlFromName(pokemonName);
        if (pokemonName != null) {
            holder.pokemonName.setVisibility(View.VISIBLE);
            holder.pokemonName.setText("No. " + String.valueOf(pokemonId) + ": " + capitalize(pokemonName));
        } else {
            holder.pokemonName.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, PokemonDetailActivity.class);
            intent.putExtra("pokemon_detail_url", "https://pokeapi.co/api/v2/pokemon/" + String.valueOf(pokemonId) + "/");
            intent.putExtra("pokemon_name", allPokemonFromJson.getName());
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
                    List<AllPokemonFromJson> list = new ArrayList<>();
                    for (AllPokemonFromJson pokemon: oldResults){
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
                results = (List<AllPokemonFromJson>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
