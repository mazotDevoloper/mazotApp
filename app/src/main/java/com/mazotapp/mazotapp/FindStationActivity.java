package com.mazotapp.mazotapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class FindStationActivity extends AppCompatActivity {

     Button btnFindStation,btnSocialFacility,btnBrands;
     ImageView backIcon;
     CheckBox cbLowPrice,cbDistance,cbBestToilet;
     RadioGroup rgCarFuel;
     RadioButton rbGasoline,rbDiesel,rbLPG,rb;
     Boolean boolGasoline,boolDiesel,boolLPG,boolLowPrice,boolDistance,boolToilet,boolAirWater,boolMarket,boolRestaurant,boolATM,boolOpet,boolShell,
             boolTotal,boolBp,boolPetrolOffice,boolAytemiz,boolEnergy,boolSpNothingBrands,boolSpNothingFacilities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_station);

        boolDiesel = false;
        boolDistance = false;
        boolGasoline = false;
        boolLowPrice = false;
        boolLPG = false;
        boolToilet =  false;




        btnBrands = findViewById(R.id.btnBrands);
        btnSocialFacility = findViewById(R.id.btnSocialFacility);
        btnFindStation = findViewById(R.id.btnFindStation);

        backIcon = findViewById(R.id.imgBack_icon);

        cbBestToilet = findViewById(R.id.cbBestToilet);
        cbDistance = findViewById(R.id.cbDistance);
        cbLowPrice = findViewById(R.id.cbLowPrice);

        rgCarFuel = findViewById(R.id.rgCarFuel);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenthome = new Intent(FindStationActivity.this,HomeActivity.class);
                startActivity(intenthome);
            }
        });

        btnSocialFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChooseFacility = new Intent(FindStationActivity.this,ChooseSocialFacility.class);
                startActivity(intentChooseFacility);
            }
        });

        btnBrands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChooseBrands = new Intent(FindStationActivity.this,ChooseBrands.class);
                startActivity(intentChooseBrands);
            }
        });

        btnFindStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioButtonId = rgCarFuel.getCheckedRadioButtonId();

                rb = findViewById(radioButtonId);

                if(rb == null){
                    Toast.makeText(FindStationActivity.this, "Aracınızın akaryakıt türünü boş bırakamassınız!", Toast.LENGTH_SHORT).show();
                }else{
                    if( cbBestToilet.isChecked() || cbDistance.isChecked() || cbLowPrice.isChecked()){

                         //Akaryakıt türlerinin kontrolü

                        if(rb.getText().equals("Dizel")){
                            boolDiesel = true;
                        }else{
                            boolDiesel = false;
                        }
                        if(rb.getText().equals("LPG")){
                            boolLPG = true;
                        }else{
                            boolLPG = false;
                        }
                        if(rb.getText().equals("Benzin")){
                            boolGasoline = true;
                        }else{
                            boolGasoline = false;
                        }

                        //İstasyon kriterlerinin kontrolü

                        if(cbLowPrice.isChecked()){
                            boolLowPrice = true;
                        }else{
                            boolLowPrice = false;
                        }
                        if(cbDistance.isChecked()){
                            boolDistance = true;
                        }else{
                            boolDistance = false;
                        }
                        if(cbBestToilet.isChecked()){
                            boolToilet = true;
                        }else {
                            boolToilet = false;
                        }

                        Bundle userChoice = new Bundle();

                        userChoice.putBoolean("boolGasoline",boolGasoline);
                        userChoice.putBoolean("boolDiesel",boolDiesel);
                        userChoice.putBoolean("boolDistance",boolDistance);
                        userChoice.putBoolean("boolLowPrice",boolLowPrice);
                        userChoice.putBoolean("boolLPG",boolLPG);
                        userChoice.putBoolean("boolToilet",boolToilet);

                        Intent stationList = new Intent(FindStationActivity.this,StationListActivity.class);
                        stationList.putExtras(userChoice);
                        startActivity(stationList);


                    }else{
                        Toast.makeText(FindStationActivity.this, "İstasyon kriterlerinden en az birini seçmelisiniz!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
