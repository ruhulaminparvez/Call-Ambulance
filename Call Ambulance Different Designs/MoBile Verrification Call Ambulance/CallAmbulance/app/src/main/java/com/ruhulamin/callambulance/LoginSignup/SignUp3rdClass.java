package com.ruhulamin.callambulance.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;
import com.ruhulamin.callambulance.R;

public class SignUp3rdClass extends AppCompatActivity {

    //Variales
    ImageView backBtn;
    Button next, login;
    TextView titleText;
    TextInputLayout phoneNumber;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up3rd_class);

        //Hooks
        backBtn = findViewById(R.id.signup_back_btn);
        next = findViewById(R.id.signup_next_btn);
        login = findViewById(R.id.signup_login_btn);
        titleText = findViewById(R.id.signup_title_btn);

        countryCodePicker = findViewById(R.id.country_code_picker);
        phoneNumber = findViewById(R.id.phoneNumber);

        //Back Button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp3rdClass.super.onBackPressed();
            }
        });

    }

    //Press LogIn to LogIn Page
    public void loginBack(View view) {
        Intent loginBack = new Intent(getApplicationContext(), Login.class);
        startActivity(loginBack);
    }

    public void callVerifyScreen(View view) {


        Intent intent = new Intent(getApplicationContext(), VerifyOTP.class);



        //Get all values passed from previous screens using Intent
        String _fullName = getIntent().getStringExtra("fullName");
        String _email = getIntent().getStringExtra("email");
        String _username = getIntent().getStringExtra("username");
        String _password = getIntent().getStringExtra("password");
        String _gender = getIntent().getStringExtra("gender");

        String _getUserEnteredPhoneNumber = phoneNumber.getEditText().getText().toString().trim();//get phone number
        String _phoneNo = "+"+countryCodePicker.getFullNumber()+_getUserEnteredPhoneNumber;


        Intent verifyOTP = new Intent(getApplicationContext(), VerifyOTP.class);

        //Pass all fields to the activity
        intent.putExtra("fullName", _fullName);
        intent.putExtra("email", _email);
        intent.putExtra("username", _username);
        intent.putExtra("password", _password);
        intent.putExtra("gender", _gender);
        intent.putExtra("phoneNo", _phoneNo);


        //Add Transition
        Pair[] pairs = new Pair[1];

        pairs[0] = new Pair<View, String>(backBtn, "transition_back_arrow_btn");


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp3rdClass.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }

    }


}
