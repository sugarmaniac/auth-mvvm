package com.example.mylibrary.service;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;

import com.example.mylibrary.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthService implements IAuthService{
    private final DatabaseReference mDataBase;
    private final FirebaseAuth firebaseAuth;
    private final MutableLiveData<Boolean> isAuthMutableLiveData;
    private final MutableLiveData<String> registerErrorLiveData;
    private final MutableLiveData<String> loginErrorLiveData;

    private static AuthService instance;

    public static AuthService getInstance(){
        if(instance == null){
            instance = new AuthService();
        }
        return instance;
    }

    private AuthService(){

        mDataBase = FirebaseDatabase.getInstance().getReference("users");
        firebaseAuth = FirebaseAuth.getInstance();
        isAuthMutableLiveData = new MutableLiveData<>();
        registerErrorLiveData = new MutableLiveData<>();
        loginErrorLiveData = new MutableLiveData<>();

        if (firebaseAuth.getCurrentUser() != null){
            isAuthMutableLiveData.postValue(false);
        }
        else {
            isAuthMutableLiveData.postValue(true);
        }
    }

    @Override
    public void register(String email, String password, String rePassword, String name,
                         String tckno, Bitmap bitmap, Application application){
        try {
            User user = new User(name, tckno, email, password, bitmap, rePassword);

            firebaseAuth.createUserWithEmailAndPassword(email,password).
                    addOnCompleteListener(application.getMainExecutor(), task -> {
                        if(task.isSuccessful()){
                            writeNewUser(firebaseAuth.getCurrentUser().getUid(), user);
                            isAuthMutableLiveData.postValue(true);
                        } else {
                            registerErrorLiveData.postValue("Register Failed: " +
                                    task.getException().getMessage());
                        }
                    });

        } catch (Exception e) {
            registerErrorLiveData.postValue(e.getMessage());
        }


    }

    @Override
    public void login(String email, String password, Application application){
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(application.getMainExecutor(), task -> {
                    if (task.isSuccessful()) {
                        isAuthMutableLiveData.postValue(true);
                    } else {
                        loginErrorLiveData.postValue("Login Failed:" +
                                task.getException().getMessage());
                    }
                });
    }

    @Override
    public void logout(){
        firebaseAuth.signOut();
        isAuthMutableLiveData.postValue(false);
    }

    @Override
    public MutableLiveData<String> getLoginErrorLiveData() {
        return loginErrorLiveData;
    }

    @Override
    public MutableLiveData<Boolean> getIsAuthMutableLiveData() {
        return isAuthMutableLiveData;
    }

    @Override
    public MutableLiveData<String> getRegisterErrorLiveData() {
        return registerErrorLiveData;
    }

    @Override
    public void writeNewUser(String uId, User user){
        mDataBase.child(uId).setValue(user);
    }
}
