package com.ruhulamin.callambulance.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ruhulamin.callambulance.R;
import com.ruhulamin.callambulance.User.UserDashboard;

public class Login extends AppCompatActivity implements View.OnClickListener {

    //Variables
    private Button login, ceateAccount, forgetPass;
    private TextInputLayout username, loginpassword;
    private CheckBox remember;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_login);
        mAuth = FirebaseAuth.getInstance();

        //Hooks
        login = findViewById(R.id.login_user_btn);
        ceateAccount = findViewById(R.id.login_singup_btn);
        forgetPass = findViewById(R.id.login_forget_pass);
        username = findViewById(R.id.login_username);
        loginpassword = findViewById(R.id.login_pass);
        remember =findViewById(R.id.login_remember_me);

        ceateAccount.setOnClickListener(this);
        login.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login_user_btn:
                userLogin();
                break;

            case R.id.login_singup_btn:
                 Intent intent = new Intent(getApplicationContext(), Signup.class);
                 startActivity(intent);
                 break;
        }
    }

    //Validation Check
    private boolean validateEmail() {
        String val = username.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            username.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            username.setError("Invalid Email!");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatePassword() {
        String val = loginpassword.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lowercase letter
                //"(?=.*[A-Z])" +         //at least 1 uppercase letter
                "(?=.*[a-zA-Z])" +        //any letter
                "(?=.*[@#$%^&+=])" +      //at least 1 special letter
                "(?=\\S+$)" +             //no white spaces
                ".{4,}" +                 //at least 4 characters
                "$";

        if (val.isEmpty()) {
            loginpassword.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            loginpassword.setError("Password should contain 4 characters and 1 special character!");
            return false;
        } else {
            loginpassword.setError(null);
            loginpassword.setErrorEnabled(false);
            return true;
        }

    }

    private void userLogin() {


        String email = username.getEditText().getText().toString().trim();
        String password = loginpassword.getEditText().getText().toString().trim();

        if (!validateEmail() | !validatePassword()) {
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Login Unsuccessful! Try Again.",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}