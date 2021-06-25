package com.cals.mvvmexampleapplication.Interface;

import com.cals.mvvmexampleapplication.Model.LoginModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("users")
    Call<LoginModel> createPost(@Body LoginModel loginModel);
}
