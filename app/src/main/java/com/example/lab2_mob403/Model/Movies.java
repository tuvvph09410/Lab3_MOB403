package com.example.lab2_mob403.Model;

import com.google.gson.annotations.SerializedName;

public class Movies {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;


    public Movies(String name, String email) {
        this.name = name;
        this.email = email;
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
}
