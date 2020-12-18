package com.ruhulamin.callambulance.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.ruhulamin.callambulance.R;
import com.ruhulamin.callambulance.User.UserDashboard;

public class Login extends AppCompatActivity {

    //Variables
    Button login, ceateAccount, forgetPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_login);

        //Hooks
        login = findViewById(R.id.login_user_btn);
        ceateAccount = findViewById(R.id.login_singup_btn);
        forgetPass = findViewById(R.id.login_forget_pass);


    }

    public void loginToUser(View view){
        Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
        startActivity(intent);
    }

    public void loginToCreateAcc(View view){
        Intent intent = new Intent(getApplicationContext(), Signup.class);
        startActivity(intent);
    }
}