package com.cals.mvvmexampleapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cals.mvvmexampleapplication.Interface.RetrofitAPI;
import com.cals.mvvmexampleapplication.Model.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    
    EditText email_et,pass_et;
    Button login_btn;
    String email_str,pass_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        email_et = findViewById(R.id.email_et);
        pass_et = findViewById(R.id.pass_et);
        login_btn = findViewById(R.id.login_btn);
        
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email_et.getText().toString().length() != 0 && pass_et.getText().toString().length() != 0){

                    email_str = email_et.getText().toString();
                    pass_str = pass_et.getText().toString();
                    
                    loginUser(email_str,pass_str);
                }
            }
        });
        
    }

    private void loginUser(String email_str, String pass_str) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        LoginModel modal = new LoginModel(email_str, pass_str);
        Call<LoginModel> call = retrofitAPI.createPost(modal);

        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();

                LoginModel responseFromAPI = response.body();

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Error found is : " + t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

    }
