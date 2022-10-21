package com.example.authmvvm.viewmodel;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class RegisterPageViewModel extends AuthBaseViewModel{

    public RegisterPageViewModel(@NonNull Application application) {
        super(application);
    }

    public void register(String email, String password, String rePassword, String name,
                         String tckno, Bitmap bitmap){
        authService.register(email, password, rePassword, name, tckno, bitmap, getApplication());
    }

    public MutableLiveData<String> getRegisterErrorLiveData() {
        return authService.getRegisterErrorLiveData();
    }

}
