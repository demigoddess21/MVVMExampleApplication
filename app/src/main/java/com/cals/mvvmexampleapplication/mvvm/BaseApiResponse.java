package com.cals.mvvmexampleapplication.mvvm;

public class BaseApiResponse {
    int status;
    String message;

    public BaseApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
