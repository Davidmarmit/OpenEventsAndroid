package com.openevents.API.sendclass;

import com.google.gson.annotations.SerializedName;

public class SendLastname {
    @SerializedName("last_name")
    private String lastname;

    public SendLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
