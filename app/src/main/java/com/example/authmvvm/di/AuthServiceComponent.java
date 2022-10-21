package com.example.authmvvm.di;


import com.example.authmvvm.viewmodel.AuthBaseViewModel;
import com.example.mylibrary.di.AuthServiceModule;

import dagger.Component;

@Component(modules = {AuthServiceModule.class})
public interface AuthServiceComponent{
    void inject(AuthBaseViewModel authBaseViewModel);
}
