package com.example.mobilebusticketingsystem.ApiManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;



public class ApiConnector {

    private static Retrofit instance;

    public static Retrofit getInstance(){
        if(instance == null)
            instance = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                    .build();

        return instance;

    }
}
