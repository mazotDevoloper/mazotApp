package com.mazotapp.mazotapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class ChooseSocialFacility extends AppCompatActivity {

    CheckBox cbMarket,cbRestaurant,cbATM,cbAirWater;
    Button btnConfirm;
    Boolean boolMarket,boolRestaurant,boolAirWater,boolATM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_social_facility);

        btnConfirm =findViewById(R.id.btnConfirm);

        cbAirWater = findViewById(R.id.cbAirWater);
        cbATM = findViewById(R.id.cbATM);
        cbMarket = findViewById(R.id.cbMarket);
        cbRestaurant = findViewById(R.id.cbRestaurant);

        if(cbRestaurant.isChecked()){
            boolRestaurant = true;
        }else{
            boolRestaurant = false;
        }

        if(cbMarket.isChecked()){
            boolMarket = true;
        }else{
            boolMarket = false;
        }

        if(cbATM.isChecked()){
            boolATM = true;
        }else{
            boolATM = false;
        }
        if(cbAirWater.isChecked()){
            boolAirWater = true;
        }else{
            boolAirWater = false;
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle socialFacilities = new Bundle();

                socialFacilities.putBoolean("boolAirWater",boolAirWater);
                socialFacilities.putBoolean("boolATM",boolATM);
                socialFacilities.putBoolean("boolMarket",boolMarket);
                socialFacilities.putBoolean("boolRestaurant",boolRestaurant);

                Intent intentFindStation = new Intent(ChooseSocialFacility.this,FindStationActivity.class);
                intentFindStation.putExtras(socialFacilities);
                startActivity(intentFindStation);
            }
        });

    }
}
