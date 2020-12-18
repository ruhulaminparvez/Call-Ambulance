package com.ruhulamin.callambulance.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ruhulamin.callambulance.R;
import com.ruhulamin.callambulance.User.Creator;

public class SignUp2ndClass extends AppCompatActivity {

    //Variables
    ImageView backBtn;
    Button next, login;
    TextView titleText;
    RadioGroup radioGroup;
    RadioButton selectedGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up2nd_class);

        //Hooks
        backBtn = findViewById(R.id.signup_back_btn);
        next = findViewById(R.id.signup_next_btn);
        login = findViewById(R.id.signup_login_btn);
        titleText = findViewById(R.id.signup_title_btn);

        //Hooks for get data
        radioGroup = findViewById(R.id.SignUp_radio_group);

        //Back Button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp2ndClass.super.onBackPressed();
            }
        });

    }

    //Press LogIn to LogIn Page
    public void loginBack(View view) {
        Intent loginBack = new Intent(getApplicationContext(), Login.class);
        startActivity(loginBack);
    }

    public void call3rdSignupScreen(View view) {

        if(!validateGender()){
            return;
        }

        selectedGender = findViewById(radioGroup.getCheckedRadioButtonId());
        String _gender =  selectedGender.getText().toString();

        Intent intent = new Intent(getApplicationContext(), SignUp3rdClass.class);

        //Add Transition
        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View, String>(backBtn, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(login, "transition_login_btn");
        pairs[3] = new Pair<View, String>(titleText, "transition_title_btn");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2ndClass.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }


    }

    private boolean validateGender() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}