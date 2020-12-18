package com.ruhulamin.callambulance.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.ruhulamin.callambulance.HelperClasses.DashboardAdapter.CategoriesAdapter;
import com.ruhulamin.callambulance.HelperClasses.DashboardAdapter.CategoriesHelperClass;
import com.ruhulamin.callambulance.HelperClasses.DashboardAdapter.FeaturedAdapter;
import com.ruhulamin.callambulance.HelperClasses.DashboardAdapter.FeaturedHelperClass;
import com.ruhulamin.callambulance.HelperClasses.DashboardAdapter.MostRecentAdapter;
import com.ruhulamin.callambulance.HelperClasses.DashboardAdapter.RecentHelperClass;
import com.ruhulamin.callambulance.R;
import com.ruhulamin.callambulance.User.Features.Hire;
import com.ruhulamin.callambulance.User.Features.Payment;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView featuredRecycler;
    RecyclerView featuredMostView;
    RecyclerView featuredCategories;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3;
    ImageView menuIcon;

    //Features
    ImageView payment_Btn, hire_Btn;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //Hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        featuredMostView = findViewById(R.id.featured_most_view);
        featuredCategories = findViewById(R.id.featured_categories);
        menuIcon = findViewById(R.id.menu_icon);

        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        //Payment Hooks and Functions
        payment_Btn = findViewById(R.id.btn_payment);
        payment_Btn.bringToFront();
        payment_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent payment = new Intent(UserDashboard.this, Payment.class);
                startActivity(payment);
            }
        });

        // Hire Hooks And Functions
        hire_Btn = findViewById(R.id.btn_hire);
        hire_Btn.bringToFront();
        hire_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hire = new Intent(UserDashboard.this, Hire.class);
                startActivity(hire);
            }
        });

        //Navigation Menu Function Calls
        navigationDrawer();

        //Recycler Views Function Calls
        featuredRecycler();
        featuredMostView();
        featuredCategories();
    }

    //Navigation Drawer Function
    private void navigationDrawer() {

        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.nav_home:
                Intent home = new Intent(getApplicationContext(),UserDashboard.class);
                startActivity(home);
                break;

            case R.id.nav_all_categories:
                Intent allCategories = new Intent(getApplicationContext(),AllCategories.class);
                startActivity(allCategories);
                break;

            case R.id.nav_about:
                Intent creator = new Intent(getApplicationContext(), Creator.class);
                startActivity(creator);
                break;

            case R.id.nav_payment:
                Intent payment = new Intent(getApplicationContext(), Payment.class);
                startActivity(payment);
                break;

            case R.id.nav_hire:
                Intent hire = new Intent(getApplicationContext(), Hire.class);
                startActivity(hire);
                break;


        }
        return true;
    }


    private void featuredCategories() {

        featuredCategories.setHasFixedSize(true);
        featuredCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();


        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.normal, "Normal"));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.medium, "Medium"));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.delux, "Delux"));

        adapter = new CategoriesAdapter(categoriesHelperClasses);
        featuredCategories.setAdapter(adapter);

        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffaff600, 0xffeff400});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff600, 0xffaff400});

    }

    private void featuredMostView() {
        featuredMostView.setHasFixedSize(true);
        featuredMostView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<RecentHelperClass> featuredMostViews = new ArrayList<>();

        featuredMostViews.add(new RecentHelperClass(R.drawable.ambulance_six, "Delux Ambulance", "This is delux ambulance, this very comfortable to ride."));
        featuredMostViews.add(new RecentHelperClass(R.drawable.ambulance_six, "Normal Ambulance", "This is normal ambulance, this very comfortable to ride."));
        featuredMostViews.add(new RecentHelperClass(R.drawable.ambulance_six, "Medium Ambulance", "This is medium ambulance, this very comfortable to ride."));

        adapter = new MostRecentAdapter(featuredMostViews);
        featuredMostView.setAdapter(adapter);

    }

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.ambulance_back_two, "Delux Ambulance", "This is an awosome ambulance service, I like this delux ambulance most."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.ambulance_back_two, "Normal Ambulance", "This is an awosome ambulance service, I like this normal ambulance."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.ambulance_back_two, "Medium Ambulance", "This is an awosome ambulance service, I like this medium ambulance."));


        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);


    }


}