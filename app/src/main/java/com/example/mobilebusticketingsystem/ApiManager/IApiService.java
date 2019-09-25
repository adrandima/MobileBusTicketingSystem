package com.example.mobilebusticketingsystem.ApiManager;

import com.example.mobilebusticketingsystem.Model.Travel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Field;


public interface IApiService {

    @POST("/api/user/register")
    @FormUrlEncoded
    Observable<String> registerUser(@Field("email") String email,
                                    @Field("name") String name,
                                    @Field("password") String password);


    @POST("/login")
    @FormUrlEncoded
    Observable<String> loginUser(@Field("email") String email,
                                 @Field("password") String password);

    @GET("/travel-history-details/")
    Call<List<Travel>> getTravelsDetails();


}
