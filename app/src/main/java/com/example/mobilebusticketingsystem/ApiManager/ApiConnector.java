package com.example.mobilebusticketingsystem.ApiManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;



public class ApiConnector {

    public static String AUTH;
    String URL = "https://bus-ticketing-app.herokuapp.com";

    private static Retrofit instance;

    public static Retrofit getInstance(){



        if(instance == null)

            instance = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:3000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                    .build();

        return instance;

    }
}
