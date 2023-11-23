package com.example.mypokemonapplication.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mypokemonapplication.model.pokemon.pokemondetail.Pokemon;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavoritePokemonRepository {
    private Application application;

    private List<String> dataKeys = new ArrayList<>();
    public FavoritePokemonRepository(Application application){
        this.application = application;
    }

    private MutableLiveData<List<String>> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<String>> getMutableLiveData(){
        String currentUserUID = FirebaseAuth.getInstance().getUid();
        DatabaseReference db = FirebaseDatabase.getInstance("https://my-pokemon-application-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("users").child(currentUserUID).child("favPokemon");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println(snapshot.exists());
                if (snapshot.exists()){
                    for(DataSnapshot d: snapshot.getChildren()){
                        dataKeys.add(d.getKey());
                    }
                    mutableLiveData.setValue(dataKeys);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        db.addListenerForSingleValueEvent(valueEventListener);
        System.out.println(dataKeys);
        return mutableLiveData;
    }
}
