package com.example.authmvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class LoginPageViewModel extends AuthBaseViewModel {

    public LoginPageViewModel(@NonNull Application application) {
        super(application);
    }

    public void login(String email, String password){
        authService.login(email,password, getApplication());
    }

    public MutableLiveData<String> getLoginErrorLiveData() {
        return authService.getLoginErrorLiveData();
    }
}
