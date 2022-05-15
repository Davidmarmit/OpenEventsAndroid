package com.openevents.API;

import java.io.Serializable;

public class loginResponse implements Serializable {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
