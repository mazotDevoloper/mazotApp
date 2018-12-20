package com.mazotapp.mazotapp.activities;


import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mazotapp.mazotapp.R;
import com.mazotapp.mazotapp.fragments.CampaignFragment;
import com.mazotapp.mazotapp.fragments.HomeFragment;
import com.mazotapp.mazotapp.fragments.SettingsFragment;
import com.mazotapp.mazotapp.models.UserModel;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    FirebaseAuth auth;
    FirebaseUser user;

    ImageView restartIcon;

    private HomeFragment homeFragment;
    private SettingsFragment settingsFragment;
    private CampaignFragment campaignFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        restartIcon = findViewById(R.id.restart_icon);

        mMainNav = findViewById(R.id.main_nav);
        mMainFrame = findViewById(R.id.main_frame);

        homeFragment = new HomeFragment();
        settingsFragment = new SettingsFragment();
        campaignFragment = new CampaignFragment();

        setFragment(homeFragment);

        mMainNav.setSelectedItemId(R.id.nav_home);

        restartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                fragmentTransaction.detach(homeFragment);
                fragmentTransaction.attach(homeFragment);
                fragmentTransaction.commit();
            }
        });

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        setFragment(homeFragment);
                        restartIcon.setVisibility(View.VISIBLE);
                        return true;

                    case R.id.nav_campaign:
                        setFragment(campaignFragment);
                        restartIcon.setVisibility(View.INVISIBLE);
                        return true;

                    case R.id.nav_settings:
                        setFragment(settingsFragment);
                        restartIcon.setVisibility(View.INVISIBLE);
                        return true;

                    default:
                        return false;

                }
            }
        });
    }

    @Override
    protected void onResume() {
        if (auth.getCurrentUser() == null) {
            Intent intent1 = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent1);
            finish();
        }
        super.onResume();
    }
    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.main_frame,fragment);
        //fragmentTransaction.add(R.id.main_frame,fragment);
        fragmentTransaction.commit();

    }
}



