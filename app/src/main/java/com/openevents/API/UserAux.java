package com.openevents.API;

import android.text.Editable;

import java.io.Serializable;

public class UserAux implements Serializable {
    private String email;
    private String password;


    public UserAux(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
