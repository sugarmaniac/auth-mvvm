package com.example.mylibrary.service;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;

import com.example.mylibrary.model.User;

public interface IAuthService {

    void register(String email, String password, String rePassword, String name,
                         String tckno, Bitmap bitmap, Application application);

    void login(String email, String password,  Application application);

    void logout();

    MutableLiveData<String> getLoginErrorLiveData();

    MutableLiveData<Boolean> getIsAuthMutableLiveData();

    MutableLiveData<String> getRegisterErrorLiveData();

    void writeNewUser(String uId, User user);

}
