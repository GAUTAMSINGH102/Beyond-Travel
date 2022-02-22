package com.example.traveliker.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit RETROFIT = null;

    public static Retrofit getclient() {

        if(RETROFIT == null) {

            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            RETROFIT = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.5/travelapis/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();
        }

        return RETROFIT;
    }

}
