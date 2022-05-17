package com.openevents.API;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private String accessToken;


    public String getAccessToken() {
        return accessToken;
    }
}
