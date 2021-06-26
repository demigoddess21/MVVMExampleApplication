package com.cals.mvvmexampleapplication.mvvm.viewmodel;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.cals.mvvmexampleapplication.mvvm.BaseApiResponse;
import com.cals.mvvmexampleapplication.mvvm.repository.LoginRepository;

public class LoginViewModel extends ViewModel {
    LoginRepository mLoginRepository;
    public MutableLiveData<BaseApiResponse> responseMutableLiveData = new MutableLiveData();
    public MutableLiveData<String> fieldValidationLivedata = new MutableLiveData();
    public ObservableField _userName = new ObservableField<String>();
    public ObservableField _password = new ObservableField<String>();
    private String mUserName,mUserPassword;

    public LoginViewModel(LoginRepository loginRepository) {
        mLoginRepository = loginRepository;
    }

    public void onLoginClicked() {
      if (validateLogin()) {
          //Get the value from repo
          MutableLiveData<BaseApiResponse> baseApiResponseFromRepo =
                  mLoginRepository.getLoginDetails(mUserName, mUserPassword);
          //Pass the value to view
          responseMutableLiveData.setValue(baseApiResponseFromRepo.getValue());
      } else {
          fieldValidationLivedata.setValue("Fields cannot be empty");
      }
    }

    private boolean validateLogin() {
        mUserName = _userName.get() == null ? "" : _userName.get().toString();
        mUserPassword = _password.get() == null ? "" :_password.get().toString();
        if (!mUserName.isEmpty() && !mUserPassword.isEmpty()) {
            return true;
        }
        return false;
    }

}
