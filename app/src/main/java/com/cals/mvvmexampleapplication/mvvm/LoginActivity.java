package com.cals.mvvmexampleapplication.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.cals.mvvmexampleapplication.R;
import com.cals.mvvmexampleapplication.databinding.ActivityLoginBinding;
import com.cals.mvvmexampleapplication.mvvm.repository.LoginRepository;
import com.cals.mvvmexampleapplication.mvvm.viewmodel.LoginViewModel;
import com.cals.mvvmexampleapplication.mvvm.viewmodel.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel mLoginViewModel;
    private LoginViewModelFactory mLoginViewModelFactory;
    private ActivityLoginBinding mLoginBinding;
    private LoginRepository mLoginRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initBinding();
        loginResponseObserver();
        loginValidation();
    }


    private void initBinding() {
        mLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mLoginRepository = new LoginRepository();
        mLoginViewModelFactory = new LoginViewModelFactory(mLoginRepository);
        mLoginViewModel = new ViewModelProvider(this, mLoginViewModelFactory).get(LoginViewModel.class);
        mLoginBinding.setLoginViewModel(mLoginViewModel);
    }

    private void loginResponseObserver() {
        mLoginViewModel.responseMutableLiveData.observe(this, baseApiResponse -> {
            if (baseApiResponse != null) {
                if(baseApiResponse.status == AppConstants.API_SUCCESS) {
                    AppUtils.showToast(this, AppConstants.API_SUCCESS_MESSAGE);
                } else if (baseApiResponse.status == AppConstants.API_FAILURE) {
                    AppUtils.showToast(this, AppConstants.API_FAILURE_MESSAGE);
                }
            }
        });
    }

    private void loginValidation() {
        mLoginViewModel.fieldValidationLivedata.observe(this, errorMessage -> {
            AppUtils.showToast(this, errorMessage);
        });
    }
}