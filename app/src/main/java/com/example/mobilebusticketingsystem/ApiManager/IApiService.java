package com.example.mobilebusticketingsystem.ApiManager;

import com.example.mobilebusticketingsystem.Balance;
import com.example.mobilebusticketingsystem.Model.Bus;
import com.example.mobilebusticketingsystem.Model.BusList;
import com.example.mobilebusticketingsystem.Model.Travel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import javax.xml.transform.Result;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Field;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface IApiService {

    @POST("/passenger-details/")
    @FormUrlEncoded
    Call<JsonObject> registerUser(@Field("firstName") String firstName,
                                  @Field("lastName") String lastName,
                                  @Field("NIC") String NIC,
                                  @Field("contactNumber") String contactNumber,
                                  @Field("email") String email,
                                  @Field("password") String password);

    @POST("/user/login")
    @FormUrlEncoded
    Call<JsonObject> loginUser(@Field("email") String email,
                                 @Field("password") String password);

    @POST("/reset")
    @FormUrlEncoded
    Observable<String> resetPassword(@Field("email") String email,
                                 @Field("password") String password);

    @GET("/travel-history-details/")
    Call<List<Travel>> getTravelsDetails();

    @POST("/travel-history-details/passenger/")
    @FormUrlEncoded
    Call<List<Travel>> getTravelsDetailsById(@Field("passengerId") String passengerId);

    @POST("/bus-details/search-result")
    @FormUrlEncoded
    Call<BusList> getBusDetails(@Field("pickupPoint") String pickupPoint,@Field("destination") String destination);

    @GET("/bus-details/")
    Call<BusList> getBusesDetails();


    @GET("/token/{id}")
    Call<List<com.example.mobilebusticketingsystem.Model.Balance>> getBalenceById(@Path("id") String id);

    @GET("/api/post/")
    Call<JsonObject> postCall(@Header("auth-token") String auth);


    @PUT("/token/reload/{id}")
    @FormUrlEncoded
    Call<JsonObject> reloadBalance(@Path("id") String id,@Field("pin") String pin);


}
