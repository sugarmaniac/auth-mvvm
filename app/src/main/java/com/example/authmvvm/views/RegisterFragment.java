package com.example.authmvvm.views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.authmvvm.R;
import com.example.authmvvm.viewmodel.RegisterPageViewModel;

import java.io.IOException;

public class RegisterFragment extends Fragment {
    String name;
    String tckno;
    String email;
    String password;
    String rePassword;

    private static final int PICK_IMAGE = 1;
    private ImageView profilePicture;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText rePasswordEditText;
    private EditText tckNoEditText;
    private EditText nameEditText;

    private RegisterPageViewModel registerPageViewModel;

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.putExtra("crop", "true");
        i.putExtra("aspectX", 100);
        i.putExtra("aspectY", 100);
        i.putExtra("outputX", 256);
        i.putExtra("outputY", 356);

        startActivityForResult(Intent.createChooser(i, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            Uri imageUri = data.getData();
            Bitmap bitmap;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),
                        imageUri);
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                profilePicture.setImageBitmap(Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap,
                        0,0, bitmap.getWidth(), bitmap.getHeight(), matrix, false),
                        400 ,400 ,false));
                bitmap.recycle();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerPageViewModel = ViewModelProviders.of(this)
                .get(RegisterPageViewModel.class);
        registerPageViewModel.getRegisterErrorLiveData().observe(this, s -> {
            if(s != null){
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                registerPageViewModel.getRegisterErrorLiveData().postValue(null);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        emailEditText = view.findViewById(R.id.loginPageEmail);
        passwordEditText = view.findViewById(R.id.loginPagePassword);
        rePasswordEditText = view.findViewById(R.id.loginPageRePassword);
        tckNoEditText = view.findViewById(R.id.tcKimNo);
        nameEditText = view.findViewById(R.id.loginPageName);
        Button loginButton = view.findViewById(R.id.buttonLogin);
        Button registerButton = view.findViewById(R.id.buttonRegister);
        profilePicture = view.findViewById(R.id.profilePicImageView);


        profilePicture.setOnClickListener(v -> selectImage());

        loginButton.setOnClickListener(v -> Navigation.findNavController(getView())
                .navigate(R.id.action_registerFragment_to_loginPage));

        registerButton.setOnClickListener(v -> {
            email = emailEditText.getText().toString();
            password = passwordEditText.getText().toString();
            rePassword = rePasswordEditText.getText().toString();
            name = nameEditText.getText().toString();
            tckno = tckNoEditText.getText().toString();

            registerPageViewModel.register(email, password, rePassword, name, tckno,
                    profilePicture.getDrawingCache());

        });

        return view;
    }
}