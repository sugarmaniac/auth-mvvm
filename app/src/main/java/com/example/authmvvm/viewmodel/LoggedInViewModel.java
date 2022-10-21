//package com.example.authmvvm.viewmodel;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.MutableLiveData;
//
//import com.example.mylibrary.service.AuthService;
//import com.google.firebase.auth.FirebaseUser;
//
//public class LoggedInViewModel extends AndroidViewModel {
//
//    private AuthService authService;
//    private MutableLiveData<FirebaseUser> userMutableLiveData;
//    private MutableLiveData<Boolean> loggedOutMutableLiveData;
//
//    public LoggedInViewModel(@NonNull Application application) {
//        super(application);
//
//        authService = new AuthService(application);
//        userMutableLiveData = authService.getUserMutableLiveData();
//        loggedOutMutableLiveData = authService.getLoggedOutMutableLiveData();
//    }
//
//    public void logout(){
//        authService.logout();
//    }
//
//    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
//        return userMutableLiveData;
//    }
//
//    public MutableLiveData<Boolean> getLoggedOutMutableLiveData() {
//        return loggedOutMutableLiveData;
//    }
//
//
//
//}
