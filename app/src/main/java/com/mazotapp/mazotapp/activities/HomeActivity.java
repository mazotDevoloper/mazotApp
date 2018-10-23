package com.mazotapp.mazotapp.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mazotapp.mazotapp.R;
import com.mazotapp.mazotapp.fragments.CampaignFragment;
import com.mazotapp.mazotapp.fragments.HomeFragment;
import com.mazotapp.mazotapp.fragments.SettingsFragment;
import com.mazotapp.mazotapp.models.UserModelRegister;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    FirebaseAuth auth;
    FirebaseUser user;

    String id;
    UserModelRegister registeredUser;

    DatabaseReference databaseReference;
    FirebaseDatabase database;

    private HomeFragment homeFragment;
    private SettingsFragment settingsFragment;
    private CampaignFragment campaignFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        //id = user.getUid();

        //database = FirebaseDatabase.getInstance();
        //databaseReference = database.getReference("Users");

        //Toast.makeText(HomeActivity.this, id, Toast.LENGTH_SHORT).show();

        mMainNav = findViewById(R.id.main_nav);
        mMainFrame = findViewById(R.id.main_frame);

        homeFragment = new HomeFragment();
        settingsFragment = new SettingsFragment();
        campaignFragment = new CampaignFragment();

        setFragment(homeFragment);

        mMainNav.setSelectedItemId(R.id.nav_home);

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

    /*

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                for(DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                    UserModelRegister registeredUser = userSnapshot.getValue(UserModelRegister.class);
                    //Log.d("lüleburgaz",registeredUser.getName());
                    Toast.makeText(HomeActivity.this, registeredUser.getName(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
    }
    */
}



