package com.example.mypokemonapplication;

import android.content.Context;
import android.widget.Toast;

import com.example.mypokemonapplication.model.AllPokemonFromJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JsonReader {
    public static ArrayList<AllPokemonFromJson> convertJsonToObject(Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.all_pokemon);
        String jsonString = "";

        byte[] data = new byte[0];
        try {
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();

            jsonString = new String(data, "UTF-8");

            Toast.makeText(context.getApplicationContext(), jsonString,Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Gson().fromJson(jsonString, new TypeToken<ArrayList<AllPokemonFromJson>>(){}.getType());
    }
}
