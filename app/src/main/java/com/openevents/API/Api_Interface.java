package com.openevents.API;

import com.openevents.API.receiveclass.LoginResponse;
import com.openevents.API.receiveclass.PostResponse;
import com.openevents.API.receiveclass.RegisterResponse;
import com.openevents.API.sendclass.*;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api_Interface {

    String REGISTER = "users";
    @POST(REGISTER)
    Call<RegisterResponse> register(@Body User user);

    String POST_EVENT = "events";
    @POST(POST_EVENT)
    Call<Event> postEvent(@Header("Authorization") String token,@Body Event event);

    String LOGIN = "users/login";
    @POST(LOGIN)
    Call<LoginResponse> login(@Body UserAux user);

    String GET_USER = "users/";
    @GET(GET_USER)
    Call<ArrayList<User>> getUsers(@Header("Authorization") String token);

    String GET_EVENTS = "events/";
    @GET(GET_EVENTS)
    Call<ArrayList<Event>> getEvents(@Header("Authorization") String token);

    String UPDATE_USER = "users";
    @PUT(UPDATE_USER)
    Call<User> updateUser(@Header("Authorization") String token, @Body User user);

    String UPDATE_USER_NAME = "users";
    @PUT(UPDATE_USER_NAME)
    Call<User> updateUserName(@Header("Authorization") String token, @Body SendName sn);

    String UPDATE_USER_LASTNAME = "users";
    @PUT(UPDATE_USER_LASTNAME)
    Call<User> updateUserLastname(@Header("Authorization") String token, @Body SendLastname sl);

    String UPDATE_USER_EMAIL = "users";
    @PUT(UPDATE_USER_EMAIL)
    Call<User> updateUserEmail(@Header("Authorization") String token, @Body SendEmail se);

    String UPDATE_USER_PASSWORD = "users";
    @PUT(UPDATE_USER_PASSWORD)
    Call<User> updateUserPassword(@Header("Authorization") String token, @Body SendPassword sp);

    String UPDATE_USER_IMAGE = "users";
    @PUT(UPDATE_USER_IMAGE)
    Call<User> updateUserImage(@Header("Authorization") String token, @Body SendImage si);

    @GET
    Call<ArrayList<User>> getUsersAssistingEvent(@Header("Authorization") String token, @Url String url);

    @POST
    Call<PostResponse> addUserAssistingEvent(@Header("Authorization") String token, @Url String url);

    @DELETE
    Call<PostResponse> deleteUserAssistingEvent(@Header("Authorization") String token, @Url String url);

    String SEARCH_USER = "users/search/";
    @GET(SEARCH_USER)
    Call<ArrayList<User>> searchUser(@Header("Authorization") String token, @Query("s") String search);

}
