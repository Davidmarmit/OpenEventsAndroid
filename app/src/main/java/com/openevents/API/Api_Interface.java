package com.openevents.API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api_Interface {

    String REGISTER = "users";
    @POST(REGISTER)
    Call<RegisterResponse> register(@Body User user);

    String LOGIN = "users/login";
    @POST(LOGIN)
    Call<LoginResponse> login(@Body UserAux user);

    String GET_USER = "users";
    @GET(GET_USER)
    Call<User> getUser(@Header("Authorization") String token);

}
