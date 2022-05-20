package com.openevents.API;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    @SerializedName("last_name")
    private String lastname;
    private String email;
    private String password;
    private String image;


    public User(String name, String last_name, String email, String password, String image) {
        this.name = name;
        this.email = email;
        this.lastname = last_name;
        this.image = image;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String toString) {
        this.password = password;
    }
}
