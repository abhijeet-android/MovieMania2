package com.abhi.moviemania.models;

import android.util.Patterns;

public class User {

    private String email;
    private String password;
    private boolean flag;

    public User(String email, String password,boolean flag) {
        this.email = email;
        this.password = password;
        this.flag = flag;
    }

    public String getEmail() {
        if (email == null) {
            return "";
        }
        return email;
    }


    public String getPassword() {
        if (password == null) {
            return "";
        }
        return password;
    }

    public boolean getFlag() {
        return flag;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    public boolean isPasswordLengthGreaterThan5() {
        return getPassword().length() > 5 && getPassword().length() < 11 ;
    }
}