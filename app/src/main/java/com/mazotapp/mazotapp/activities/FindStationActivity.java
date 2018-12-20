package com.mazotapp.mazotapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mazotapp.mazotapp.R;
import com.mazotapp.mazotapp.models.StationModel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class FindStationActivity extends AppCompatActivity {

    Button btnFindStation;
    ImageView backIcon;
    RadioGroup rgCarFuel,rgBrands;
    RadioButton rbFuel,rbBrands;
    TextView tvNearName,tvNearDistance;
    ImageView imgNearLogo;
    Intent intentInfoSt;

    Boolean boolGasoline,boolDiesel,boolLPG,boolTotal,boolOpet,boolPO,boolShell,boolTP,boolBP,bool24Hour,boolMarket,boolCafe,boolWc,boolOil,boolCarwash,boolMescit,boolMigros,boolTire,boolATM,boolFood,boolRedBull;

    CheckBox cbMarket,cbWc,cbOil,cbCafe,cbCarwash,cb24Hour,cbMigros,cbTire,cbMescit,cbATM,cbFood,cbRedBull;
    DatabaseReference databaseReference;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_station);

        boolDiesel = false;
        boolGasoline = false;
        boolLPG = false;

        bool24Hour = false;
        boolCafe = false;
        boolCarwash = false;
        boolMarket = false;
        boolOil = false;
        boolWc = false;
        boolTire = false;
        boolMigros = false;
        boolMescit = false;
        boolATM = false;
        boolRedBull = false;
        boolFood = false;

        boolTP = false;
        boolBP = false;
        boolOpet = false;
        boolShell = false;
        boolTotal = false;
        boolPO = false;

        cb24Hour = findViewById(R.id.cb24Hour);
        cbCafe = findViewById(R.id.cbCafe);
        cbCarwash = findViewById(R.id.cbCarWash);
        cbOil = findViewById(R.id.cbOil);
        cbMarket = findViewById(R.id.cbMarket);
        cbWc = findViewById(R.id.cbWc);
        cbMescit = findViewById(R.id.cbMescit);
        cbATM = findViewById(R.id.cbATM);
        cbMigros =findViewById(R.id.cbMigros);
        cbTire = findViewById(R.id.cbTire);
        cbFood = findViewById(R.id.cbFood);
        cbRedBull = findViewById(R.id.cbRedBull);

        tvNearDistance = findViewById(R.id.tvNearDistance);
        tvNearName = findViewById(R.id.tvNearName);

        imgNearLogo = findViewById(R.id.imgStNear);

        btnFindStation = findViewById(R.id.btnFindStation);

        intentInfoSt = new Intent(FindStationActivity.this,InformationStationActivity.class);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Stations");

        backIcon = findViewById(R.id.imgBack_icon);

        rgBrands = findViewById(R.id.rgBrands);

        rgCarFuel = findViewById(R.id.rgCarFuel);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnFindStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioButtonIdBrands = rgBrands.getCheckedRadioButtonId();
                int radioButtonIdFuel = rgCarFuel.getCheckedRadioButtonId();

                rbBrands = findViewById(radioButtonIdBrands);
                rbFuel = findViewById(radioButtonIdFuel);

                if(rbFuel == null){
                    Toast.makeText(FindStationActivity.this, "Aracınızın akaryakıt türünü boş bırakamazsınız!", Toast.LENGTH_SHORT).show();
                }else{

                    //Marka türlerinin kontrolü

                    if(rbBrands != null){
                        if(rbBrands.getId() == findViewById(R.id.total).getId()){
                            boolTotal = true;
                        }else{
                            boolTotal = false;
                        }
                        if(rbBrands.getId() == findViewById(R.id.opet).getId()){
                            boolOpet = true;
                        }else{
                            boolOpet = false;
                        }
                        if(rbBrands.getId() == findViewById(R.id.shell).getId()){
                            boolShell = true;
                        }else{
                            boolShell = false;
                        }
                        if(rbBrands.getId() == findViewById(R.id.TP).getId()){
                            boolTP = true;
                        }else{
                            boolTP = false;
                        }
                        if(rbBrands.getId() == findViewById(R.id.BP).getId()){
                            boolBP = true;
                        }else{
                            boolBP = false;
                        }
                        if(rbBrands.getId() == findViewById(R.id.PO).getId()){
                            boolPO = true;
                        }else{
                            boolPO = false;
                        }
                    }

                    //Akaryakıt türlerinin kontrolü

                    if(rbFuel.getId() == findViewById(R.id.rbDiesel).getId()){
                        boolDiesel = true;
                    }else{
                        boolDiesel = false;
                    }
                    if(rbFuel.getId() == findViewById(R.id.rbLPG).getId()){
                        boolLPG = true;
                    }else{
                        boolLPG = false;
                    }
                    if(rbFuel.getId() == findViewById(R.id.rbGasoline).getId()){
                        boolGasoline = true;
                    }else{
                        boolGasoline = false;
                    }

                    //Özelliklerin kontrolü

                    if(cb24Hour.isChecked()){
                        bool24Hour = true;
                    }else{
                        bool24Hour = false;
                    }
                    if(cbCafe.isChecked()){
                        boolCafe = true;
                    }else{
                        boolCafe = false;
                    }
                    if(cbCarwash.isChecked()){
                        boolCarwash = true;
                    }else{
                        boolCarwash = false;
                    }
                    if(cbMarket.isChecked()){
                        boolMarket = true;
                    }else{
                        boolMarket = false;
                    }
                    if(cbWc.isChecked()){
                        boolWc = true;
                    }else{
                        boolWc = false;
                    }
                    if(cbOil.isChecked()){
                        boolOil = true;
                    }else{
                        boolOil = false;
                    }
                    if(cbTire.isChecked()){
                        boolTire = true;
                    }else{
                        boolTire = false;
                    }
                    if(cbMescit.isChecked()){
                        boolMescit = true;
                    }else{
                        boolMescit = false;
                    }
                    if(cbMigros.isChecked()){
                        boolMigros = true;
                    }else{
                        boolMigros = false;
                    }
                    if(cbATM.isChecked()){
                        boolATM = true;
                    }else{
                        boolATM = false;
                    }
                    if(cbRedBull.isChecked()){
                        boolRedBull = true;
                    }else{
                        boolRedBull = false;
                    }
                    if(cbFood.isChecked()){
                        boolFood = true;
                    }else{
                        boolFood = false;
                    }

                    Bundle userChoice = new Bundle();

                    userChoice.putBoolean("boolShell",boolShell);
                    userChoice.putBoolean("boolOpet",boolOpet);
                    userChoice.putBoolean("boolTotal",boolTotal);
                    userChoice.putBoolean("boolBP",boolBP);
                    userChoice.putBoolean("boolTP",boolTP);
                    userChoice.putBoolean("boolPO",boolPO);

                    userChoice.putBoolean("boolMarket",boolMarket);
                    userChoice.putBoolean("bool24Hour",bool24Hour);
                    userChoice.putBoolean("boolWc",boolWc);
                    userChoice.putBoolean("boolCarwash",boolCarwash);
                    userChoice.putBoolean("boolOil",boolOil);
                    userChoice.putBoolean("boolCafe",boolCafe);
                    userChoice.putBoolean("boolTire",boolTire);
                    userChoice.putBoolean("boolMigros",boolMigros);
                    userChoice.putBoolean("boolMescit",boolMescit);
                    userChoice.putBoolean("boolATM",boolATM);
                    userChoice.putBoolean("boolRedBull",boolRedBull);
                    userChoice.putBoolean("boolFood",boolFood);

                    userChoice.putBoolean("boolGasoline",boolGasoline);
                    userChoice.putBoolean("boolDiesel",boolDiesel);
                    userChoice.putBoolean("boolLPG",boolLPG);


                    Intent stationList = new Intent(FindStationActivity.this,StationListActivity.class);
                    stationList.putExtras(userChoice);
                    startActivity(stationList);
                }
            }
        });
    }
}
