package com.ruhulamin.callambulance.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.hbb20.CountryCodePicker;
import com.ruhulamin.callambulance.R;
import com.ruhulamin.callambulance.User.Creator;
import com.ruhulamin.callambulance.User.UserDashboard;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    //Variables
    TextInputLayout fullName, username, signupemail, signuppassword;
    ImageView backBtn;
    Button next, login;
    TextView titleText;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_signup);

        mAuth = FirebaseAuth.getInstance();

        //Hooks
        backBtn = findViewById(R.id.signup_back_btn);
        next = findViewById(R.id.signup_next_btn);
        login = findViewById(R.id.signup_login_btn);
        titleText = findViewById(R.id.signup_title_btn);

        //Hooks for getting data
        fullName = findViewById(R.id.signup_fullname);
        username = findViewById(R.id.signup_username);
        signupemail = findViewById(R.id.signup_email);
        signuppassword = findViewById(R.id.signup_pass);


        //Back Button To Log In
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getApplicationContext(), Login.class);
                startActivity(login);
            }
        });


        login.setOnClickListener(this);
        next.setOnClickListener(this);

    }


    /* Validation Functions */
    private boolean validateFullName() {
        String val = fullName.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            fullName.setError("Field can not be empty");
            return false;
        } else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateUserName() {
        String val = username.getEditText().getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";

        if (val.isEmpty()) {
            username.setError("Field can not be empty");
            return false;
        } else if (val.length() > 20) {
            username.setError("Username is too large!");
            return false;
        } else if (!val.matches(checkspaces)) {
            username.setError("No White spaces are allowed!");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateEmail() {
        String val = signupemail.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            signupemail.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
           signupemail.setError("Invalid Email!");
            return false;
        } else {
            signupemail.setError(null);
            signupemail.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatePassword() {
        String val = signuppassword.getEditText().getText().toString().trim();
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
            signuppassword.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            signuppassword.setError("Password should contain 4 characters and 1 special character!");
            return false;
        } else {
            signuppassword.setError(null);
            signuppassword.setErrorEnabled(false);
            return true;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.signup_next_btn:
                userRegister();
                break;

            case R.id.signup_login_btn:
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                break;
        }
    }

    private void userRegister() {

        String email = signupemail.getEditText().getText().toString().trim();
        String password = signuppassword.getEditText().getText().toString().trim();

        if(!validateFullName() | !validateUserName() | !validateEmail() | !validatePassword()){
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                     if(task.getException() instanceof FirebaseAuthUserCollisionException)
                     {
                         Toast.makeText(getApplicationContext(),"User is already Registered.", Toast.LENGTH_SHORT).show();
                     }else{
                         Toast.makeText(getApplicationContext(),"Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                     }
                }

            }
        });


        //Add Transition
        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View, String>(backBtn, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(login, "transition_login_btn");
        pairs[3] = new Pair<View, String>(titleText, "transition_title_btn");



    }
}