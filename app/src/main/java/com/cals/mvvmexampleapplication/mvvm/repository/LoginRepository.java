package com.cals.mvvmexampleapplication.mvvm.repository;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.cals.mvvmexampleapplication.Interface.RetrofitAPI;
import com.cals.mvvmexampleapplication.MainActivity;
import com.cals.mvvmexampleapplication.Model.LoginModel;
import com.cals.mvvmexampleapplication.mvvm.AppConstants;
import com.cals.mvvmexampleapplication.mvvm.BaseApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRepository {

    private MutableLiveData<BaseApiResponse> baseResponseLiveData = new MutableLiveData();

    public MutableLiveData<BaseApiResponse> getLoginDetails(String userName,String password) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        LoginModel modal = new LoginModel(userName, password);
        Call<LoginModel> call = retrofitAPI.createPost(modal);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.code() == 200) {
                    LoginModel responseFromAPI = response.body();
                    baseResponseLiveData.postValue(new BaseApiResponse(
                            AppConstants.API_SUCCESS,
                            AppConstants.API_SUCCESS_MESSAGE));
                } else {
                    baseResponseLiveData.postValue(new BaseApiResponse(
                            AppConstants.API_FAILURE,
                            response.message()));
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                baseResponseLiveData.postValue(new BaseApiResponse(
                        AppConstants.API_FAILURE,
                        AppConstants.API_FAILURE_MESSAGE));
            }
        });

        return baseResponseLiveData;
    }
}
