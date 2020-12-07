package com.ruhulamin.callambulance.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.WindowManager;

import com.ruhulamin.callambulance.HelperClasses.DashboardAdapter.FeaturedAdapter;
import com.ruhulamin.callambulance.HelperClasses.DashboardAdapter.FeaturedHelperClass;
import com.ruhulamin.callambulance.HelperClasses.DashboardAdapter.MostRecentAdapter;
import com.ruhulamin.callambulance.HelperClasses.DashboardAdapter.RecentHelperClass;
import com.ruhulamin.callambulance.R;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity {

    RecyclerView featuredRecycler;
    RecyclerView featuredMostView;
    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //Hooks
        featuredRecycler = findViewById(R.id.featured_recycler);

        featuredRecycler();

        //Hooks Most Recent Adapter
        featuredMostView = findViewById(R.id.featured_most_view);

        featuredMostView();
    }

    private void featuredMostView() {
        featuredMostView.setHasFixedSize(true);
        featuredMostView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<RecentHelperClass> featuredMostViews = new ArrayList<>();

        featuredMostViews.add(new RecentHelperClass(R.drawable.location_four,"Delux Ambulance","This is delux ambulance, this very comfortable to ride."));
        featuredMostViews.add(new RecentHelperClass(R.drawable.ambulance_two,"Normal Ambulance","This is normal ambulance, this very comfortable to ride."));
        featuredMostViews.add(new RecentHelperClass(R.drawable.ambulance_six,"Medium Ambulance","This is medium ambulance, this very comfortable to ride."));

        adapter = new MostRecentAdapter(featuredMostViews);
        featuredMostView.setAdapter(adapter);

    }

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.ambulance_back,"Delux Ambulance","This is an awosome ambulance service, I like this delux ambulance most."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.ambulance_back_two,"Normal Ambulance","This is an awosome ambulance service, I like this normal ambulance."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.ambulance_four,"Medium Ambulance","This is an awosome ambulance service, I like this medium ambulance."));


        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
    }
}