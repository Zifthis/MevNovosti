package com.example.mevnovosti.service;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {


    private static Retrofit retrofit = null;
    public static String BASE_URL = "http://192.168.1.100:8080";

    public static Retrofit getModel() {

        if (retrofit == null) {

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
