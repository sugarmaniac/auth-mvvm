//package com.example.authmvvm.views;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.activity.OnBackPressedCallback;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProviders;
//import androidx.navigation.Navigation;
//
//import com.example.authmvvm.R;
//import com.example.authmvvm.viewmodel.LoggedInViewModel;
//
//
//public class AfterLoginFragment extends Fragment {
//    private TextView textView;
//    private Button logOutButton;
//    private LoggedInViewModel loggedInViewModel;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//            }
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
//
//        loggedInViewModel = ViewModelProviders.of(this).get(LoggedInViewModel.class);
//        loggedInViewModel.getUserMutableLiveData().observe(this, firebaseUser -> {
//            if (firebaseUser != null){
//                textView.setText("User : "+ firebaseUser.getEmail());
//            }
//        });
//
//        loggedInViewModel.getLoggedOutMutableLiveData().observe(this, aBoolean -> {
//            if(aBoolean){
//                Navigation.findNavController(getView()).navigate(R.id.action_afterLogin_to_loginPage);
//            }
//        });
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_after_login, container,false);
//
//        textView = view.findViewById(R.id.textView);
//        logOutButton = view.findViewById(R.id.logOutButton);
//
//        logOutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loggedInViewModel.logout();
//            }
//        });
//        return view;
//    }
//}