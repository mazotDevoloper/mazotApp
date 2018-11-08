package com.mazotapp.mazotapp.activities;


import android.content.Intent;
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

    private HomeFragment homeFragment;
    private SettingsFragment settingsFragment;
    private CampaignFragment campaignFragment;

    ImageView imgAdmin_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imgAdmin_icon = findViewById(R.id.imgAdmin_icon);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        mMainNav = findViewById(R.id.main_nav);
        mMainFrame = findViewById(R.id.main_frame);

        if(user.getEmail().equalsIgnoreCase("bertugabali@hotmail.com") || user.getEmail().equalsIgnoreCase("atanurduman@hotmail.com")){
            imgAdmin_icon.setVisibility(View.VISIBLE);
        }

        homeFragment = new HomeFragment();
        settingsFragment = new SettingsFragment();
        campaignFragment = new CampaignFragment();

        setFragment(homeFragment);

        mMainNav.setSelectedItemId(R.id.nav_home);

        imgAdmin_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdmin = new Intent(HomeActivity.this,AdminActivity.class);
                startActivity(intentAdmin);
            }
        });

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;

                    case R.id.nav_campaign:
                        setFragment(campaignFragment);
                        return true;

                    case R.id.nav_settings:
                        setFragment(settingsFragment);
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



