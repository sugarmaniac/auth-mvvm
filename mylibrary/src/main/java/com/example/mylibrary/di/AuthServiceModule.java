package com.example.mylibrary.di;

import com.example.mylibrary.service.AuthService;
import com.example.mylibrary.service.IAuthService;

import dagger.Module;
import dagger.Provides;

@Module
public class AuthServiceModule {

    @Provides
    public IAuthService provideAuthService() {
        return AuthService.getInstance();
    }
}