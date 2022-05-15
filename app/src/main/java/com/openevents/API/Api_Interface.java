package com.openevents.API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api_Interface {

    String REGISTER = "/users";
    @POST(REGISTER)
    Call<User> register(String name, String last_name, String email, String password, String image);

    String LOGIN = "users/login";
    @POST(LOGIN)
    Call<loginResponse> login(@Body UserAux user);

    String GET_USER = "/users";
    @GET(GET_USER)
    Call<User> getUser(@Header("Authorization") String token);

}
