package com.openevents.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api_Class {

    private static Retrofit MyApi = null;

    public static Retrofit getInstance() {
        if (MyApi == null) {
            MyApi = new Retrofit.Builder()
                    .baseUrl("http://puigmal.salle.url.edu/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return MyApi;
    }
}
