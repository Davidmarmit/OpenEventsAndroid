package com.openevents.API;

import com.openevents.API.receiveclass.LoginResponse;
import com.openevents.API.receiveclass.PostResponse;
import com.openevents.API.receiveclass.RegisterResponse;
import com.openevents.API.sendclass.*;
import com.openevents.API.sendclass.event.*;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
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

    String UPDATE_EVENT_NAME = "events/{id}";
    @PUT(UPDATE_EVENT_NAME)
    Call<Event> updateEventName(@Header("Authorization") String token, @Path("id") int id, @Body SendEventName sn);

    String UPDATE_EVENT_DESCRIPTION = "events/{id}";
    @PUT(UPDATE_EVENT_DESCRIPTION)
    Call<Event> updateEventDescription(@Header("Authorization") String token, @Path("id") int id, @Body SendEventDescription sd);

    String UPDATE_EVENT_LOCATION = "events/{id}";
    @PUT(UPDATE_EVENT_LOCATION)
    Call<Event> updateEventLocation(@Header("Authorization") String token, @Path("id") int id, @Body SendEventLocation sl);

    String UPDATE_EVENT_STARTDATE = "events/{id}";
    @PUT(UPDATE_EVENT_STARTDATE)
    Call<Event> updateEventStartDate(@Header("Authorization") String token, @Path("id") int id, @Body SendEventStartDate ss);

    String UPDATE_EVENT_ENDDATE = "events/{id}";
    @PUT(UPDATE_EVENT_ENDDATE)
    Call<Event> updateEventEndDate(@Header("Authorization") String token, @Path("id") int id, @Body SendEventEndDate se);

    String UPDATE_EVENT_IMAGE = "events/{id}";
    @PUT(UPDATE_EVENT_IMAGE)
    Call<Event> updateEventImage(@Header("Authorization") String token, @Path("id") int id, @Body SendEventImage si);

    String UPDATE_EVENT_PARTICIPANTS = "events/{id}";
    @PUT(UPDATE_EVENT_PARTICIPANTS)
    Call<Event> updateEventParticipants(@Header("Authorization") String token, @Path("id") int id, @Body SendMaxParticipants sp);

    String UPDATE_EVENT_CATEGORY = "events/{id}";
    @PUT(UPDATE_EVENT_CATEGORY)
    Call<Event> updateEventCategory(@Header("Authorization") String token, @Path("id") int id, @Body SendEventCategory sc);

    @GET
    Call<ArrayList<User>> getUsersAssistingEvent(@Header("Authorization") String token, @Url String url);

    @POST
    Call<PostResponse> addUserAssistingEvent(@Header("Authorization") String token, @Url String url);

    @DELETE
    Call<PostResponse> deleteUserAssistingEvent(@Header("Authorization") String token, @Url String url);

    @DELETE
    Call<PostResponse> deleteEvent(@Header("Authorization") String token, @Url String url);

    String SEARCH_USER = "users/search/";
    @GET(SEARCH_USER)
    Call<ArrayList<User>> searchUser(@Header("Authorization") String token, @Query("s") String search);

    String GET_BEST_EVENTS = "events/best";
    @GET(GET_BEST_EVENTS)
    Call<ArrayList<Event>> getBestEvents(@Header("Authorization") String token);

}
