package com.example.admin.viewshared;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.viewshared.customes.LoginLayout;
import com.example.admin.viewshared.customes.MyAndroidView;
import com.example.admin.viewshared.customes.MyView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String My_SHARED_PREF = "loginRegistration";
    public static final String USER_DOESNT_EXIST = "The user does not exists";
    private LoginLayout loginLayout;
    boolean shape;
    SharedPreferences preferences;
    Button logIn;
    EditText user,pass;
    MyView photo;

    @TargetApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shape = false;

        final MyAndroidView myAdroidButton = findViewById(R.id.shapper);
        myAdroidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnimatedButton1Clicked(myAdroidButton);
            }
        });
        buttonToSquare(myAdroidButton);
        addLoginLayout();
        addSharePreferences();



    }

    @SuppressLint("ResourceAsColor")
    private void addLoginLayout() {
        LayoutInflater layoutInflater = getLayoutInflater();
        loginLayout = findViewById(R.id.lyLogin);
        layoutInflater.inflate(R.layout.mylayout, null, false);
        photo = new MyView(this);
        photo.setBackgroundResource(R.drawable.eye);
        user = new EditText(this);
        user.setHint("User");
        user.setWidth(1000);
        pass = new EditText(this);
        pass.setHint("password");
        pass.setWidth(1000);
        pass.setTransformationMethod(new PasswordTransformationMethod());
        logIn = new Button(this);
        logIn.setText("Log In");
        logIn.setTextColor(R.color.bt_purple_dark);
        logIn.setGravity(Gravity.CENTER_HORIZONTAL);
        logIn.setOnClickListener(this);
        loginLayout.addView(photo);
        loginLayout.addView(user);
        loginLayout.addView(pass);
        loginLayout.addView(logIn);
    }

    private void addSharePreferences() {
        preferences = getSharedPreferences(My_SHARED_PREF, Context.MODE_PRIVATE);
        preferences.edit().putString("Mario", "nombre").apply();
    }


    private void onAnimatedButton1Clicked(final MyAndroidView btnAnimated) {
        if (shape) {
            buttonToSquare(btnAnimated);
        } else {
            buttontofail(btnAnimated);
        }
        shape = !shape;
    }

    private void buttonToSquare(final MyAndroidView btnMorph) {
        MyAndroidView.Params square = MyAndroidView.Params.create()
                .cornerRadius(R.dimen.bt_corner_radius_2)
                .duration(500)
                .width(500)
                .height(120)
                .color(R.color.bt_purple)
                .colorPressed(R.color.bt_purple_dark)
                .text("ChangeShape");
        btnMorph.animation(square);
    }

    private void buttontofail(final MyAndroidView btnMorph) {
        MyAndroidView.Params circle = MyAndroidView.Params.create()
                .duration(500)
                .cornerRadius(120)
                .width(120)
                .height(120)
                .icon(R.drawable.skull)
                .color(R.color.colorAccent)
                .colorPressed(R.color.colorPrimary);
        btnMorph.animation(circle);

    }

    @Override
    public void onClick(View view) {
        
        
        String toLog = preferences.getString(user.getText().toString(), USER_DOESNT_EXIST);
        if (toLog.equals(USER_DOESNT_EXIST)) {
            Toast.makeText(this, toLog, Toast.LENGTH_SHORT).show();
        } else if (toLog.equals(pass.getText().toString())) {
            Toast.makeText(this, "Sucess", Toast.LENGTH_SHORT).show();
            photo.setBackgroundResource(R.drawable.eye);

        } else {
            Toast.makeText(this, "Password Incorrect, Try again", Toast.LENGTH_SHORT).show();
            photo.setBackgroundResource(R.drawable.proface);
        } 
        
    }
}
    

