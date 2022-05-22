package com.openevents.API.receiveclass;

import java.io.Serializable;

public class RegisterResponse implements Serializable {
    private String name;
    private String last_name;
    private String email;
    private String image;

    public String getName() {
        return name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }
}
