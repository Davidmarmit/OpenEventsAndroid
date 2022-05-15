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


}
