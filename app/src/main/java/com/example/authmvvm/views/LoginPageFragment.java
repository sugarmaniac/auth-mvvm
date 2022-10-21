package com.example.authmvvm.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.authmvvm.R;
import com.example.authmvvm.viewmodel.LoginPageViewModel;


public class LoginPageFragment extends Fragment {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;

    private LoginPageViewModel loginPageViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPageViewModel = ViewModelProviders.of(this)
                .get(LoginPageViewModel.class);
        loginPageViewModel.getLoginErrorLiveData().observe(this, s -> {
            if(s != null){
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                loginPageViewModel.getLoginErrorLiveData().postValue(null);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_page, container, false);

        emailEditText = view.findViewById(R.id.loginPageEmail);
        passwordEditText = view.findViewById(R.id.loginPagePassword);
        loginButton = view.findViewById(R.id.buttonLogin);
        registerButton = view.findViewById(R.id.buttonRegister);
        registerButton.setOnClickListener(v -> Navigation.
                findNavController(getView()).navigate(R.id.action_loginPage_to_registerFragment));

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if(email.length() > 0 && password.length()> 0){
                loginPageViewModel.login(email, password);
            }
        });
        return view;
    }
}