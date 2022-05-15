package com.openevents.API;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("accessToken")
    private String token;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("image")
    private String image;


    public User(String name, String email, String token, String last_name, String image) {
        this.name = name;
        this.email = email;
        this.token = token;
        this.last_name = last_name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}
