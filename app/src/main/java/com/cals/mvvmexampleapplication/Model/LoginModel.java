package com.cals.mvvmexampleapplication.Model;

import java.io.Serializable;

public class LoginModel implements Serializable {

    String email;
    String pass;

    public LoginModel(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
