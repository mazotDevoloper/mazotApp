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

     Button btnFindStation;
     ImageView backIcon;
     CheckBox cbLowPrice,cbDistance,cbBestToilet;
     RadioGroup rgCarFuel;
     Spinner spStationBrands,spStationFacilities;
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


        btnFindStation = findViewById(R.id.btnFindStation);

        backIcon = findViewById(R.id.imgBack_icon);

        cbBestToilet = findViewById(R.id.cbBestToilet);
        cbDistance = findViewById(R.id.cbDistance);
        cbLowPrice = findViewById(R.id.cbLowPrice);

        rgCarFuel = findViewById(R.id.rgCarFuel);

        spStationBrands = findViewById(R.id.spStationBrands);
        spStationFacilities = findViewById(R.id.spStationFacility);

        //spinner yani seçilebilir liste için adaptör tanımlıyorum ve onu kullanıyorum

        ArrayAdapter stBrandsAdapter = ArrayAdapter.createFromResource(this,R.array.brands,android.R.layout.simple_spinner_item);
        stBrandsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStationBrands.setAdapter(stBrandsAdapter);

        ArrayAdapter stFacilitiesAdapter = ArrayAdapter.createFromResource(this,R.array.facilities,android.R.layout.simple_spinner_item);
        stFacilitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStationFacilities.setAdapter(stFacilitiesAdapter);


        //seçilebilir listeden hangisini seçtiyse ona göre listeleme yapıyorum

        spStationBrands.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] brandsArray = getResources().getStringArray(R.array.brands);

                if(brandsArray[position].equals("Opet")){
                    boolOpet = true;
                }
                if(brandsArray[position].equals("Shell")){
                    boolShell = true;
                }
                if(brandsArray[position].equals("Petrol Ofisi")){
                    boolPetrolOffice = true;
                }
                if(brandsArray[position].equals("Bp")){
                    boolBp = true;
                }
                if(brandsArray[position].equals("Total")){
                    boolTotal = true;
                }
                if(brandsArray[position].equals("Energy")){
                    boolEnergy = true;
                }
                if(brandsArray[position].equals("Aytemiz")){
                    boolAytemiz = true;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                boolSpNothingBrands = true;
            }
        });

        spStationFacilities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] facilitiesArray = getResources().getStringArray(R.array.facilities);

                if(facilitiesArray[position].equals("Market")){
                    boolMarket = true;
                }
                if(facilitiesArray[position].equals("ATM")){
                    boolATM = true;
                }
                if(facilitiesArray[position].equals("Hava ve su")){
                    boolAirWater = true;
                }
                if(facilitiesArray[position].equals("Restaurant")){
                    boolRestaurant = true;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                boolSpNothingFacilities = true;
            }
        });

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenthome = new Intent(FindStationActivity.this,HomeActivity.class);
                startActivity(intenthome);
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
