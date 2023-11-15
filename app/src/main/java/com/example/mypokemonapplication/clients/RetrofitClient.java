package com.example.mypokemonapplication.clients;

import com.example.mypokemonapplication.interfaces.RetrofitService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String baseUrl = "https://pokeapi.co/api/v2/";

    private RetrofitService myApi;

    private static RetrofitClient instance;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(RetrofitService.class);
    }

    public static synchronized RetrofitClient getInstance(){
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public RetrofitService getMyApi(){ return myApi; }
}
