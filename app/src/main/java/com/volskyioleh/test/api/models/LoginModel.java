package com.volskyioleh.test.api.models;

import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("Login")
    public String login;

    @SerializedName("Password")
    public String password;

    public LoginModel(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
