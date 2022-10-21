package com.example.authmvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.authmvvm.di.DaggerAuthServiceComponent;
import com.example.mylibrary.service.IAuthService;

import javax.inject.Inject;


public class AuthBaseViewModel extends AndroidViewModel {

    @Inject
    public IAuthService authService;

    public AuthBaseViewModel(@NonNull Application application) {
        super(application);
        DaggerAuthServiceComponent.create().inject(this);
    }

    public MutableLiveData<Boolean> getIsAuthMutableLiveData() {
        return authService.getIsAuthMutableLiveData();
    }

}
