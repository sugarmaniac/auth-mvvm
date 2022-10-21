package com.example.authmvvm.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.authmvvm.R;
import com.example.authmvvm.viewmodel.AuthBaseViewModel;

public class MainActivity extends AppCompatActivity {

    private AuthBaseViewModel authBaseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        authBaseViewModel = ViewModelProviders.of(this).get(AuthBaseViewModel.class);

        authBaseViewModel.getIsAuthMutableLiveData().observe(this, aBoolean -> {
            if(aBoolean){
                //after logine intent atilacak
            }
        });


    }
}