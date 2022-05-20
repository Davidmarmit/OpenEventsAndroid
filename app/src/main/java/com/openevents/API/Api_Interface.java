package com.openevents.API;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface Api_Interface {

    String REGISTER = "users";
    @POST(REGISTER)
    Call<RegisterResponse> register(@Body User user);

    String LOGIN = "users/login";
    @POST(LOGIN)
    Call<LoginResponse> login(@Body UserAux user);

    String GET_USER = "users/";
    @GET(GET_USER)
    Call<User> getUser(@Header("Authorization") String token);

    String GET_EVENTS = "events/";
    @GET(GET_EVENTS)
    Call<ArrayList<Event>> getEvents(@Header("Authorization") String token);

    String UPDATE_USER = "users";
    @PUT(UPDATE_USER)
    Call<User> updateUser(@Header("Authorization") String token, @Body User user);

    String SEARCH_USER = "users/search/";
    @GET(SEARCH_USER)
    Call<ArrayList<User>> searchUser(@Header("Authorization") String token, @Query("s") String search);

}
