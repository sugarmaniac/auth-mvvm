package com.example.mylibrary.model;

import android.graphics.Bitmap;

public class User {
    private final static String VALIDATE_OK = "1";

    public String name;
    public String user_tckno;
    public String email;
    public String password;
    public Bitmap bitmap;

    public User(String name, String user_tckno, String email, String password, Bitmap bitmap,
                String rePassword) throws Exception{
        String res = validate(name, user_tckno,  password, rePassword);
        if (!res.equals(VALIDATE_OK)) {
            throw new Exception(res);
        }
        this.email = email;
        this.name = name;
        this.password = password;
        this.user_tckno = user_tckno;
        this.bitmap = bitmap;
    }

    public String validate(String name, String user_tckno, String password, String rePassword){
        if(user_tckno.length() != 11){
            return "Wrong TC Kim No";
        }

        if (!rePassword.equals(password)){
            return "passwords are not equal";
        }

        if (name.length() == 0){
            return  "Name cannot be empty";
        }

        return VALIDATE_OK;
    }
}
