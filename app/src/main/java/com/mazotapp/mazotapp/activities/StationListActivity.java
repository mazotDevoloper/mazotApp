package com.mazotapp.mazotapp.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mazotapp.mazotapp.adapters.PrivateAdapter;
import com.mazotapp.mazotapp.R;
import com.mazotapp.mazotapp.models.StationModel;
import com.mazotapp.mazotapp.models.UserModelRegister;

import java.util.ArrayList;
import java.util.List;

public class StationListActivity extends AppCompatActivity {


    ImageView backIcon;
    private ListView lvStations;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    String fuelType;
    Boolean boolGasoline,boolDiesel,boolLPG,boolLowPrice,boolDistance,boolToilet,boolSocialFacility,boolStationBrands;
    private List<StationModel> addStation = new ArrayList<StationModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list);

        backIcon = findViewById(R.id.imgBack_icon);

        Bundle userChoice = getIntent().getExtras();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        //boolean ları bundle la diğer tarafından çekilen yer

        boolDiesel = userChoice.getBoolean("boolDiesel");
        boolDistance = userChoice.getBoolean("boolDistance");
        boolGasoline = userChoice.getBoolean("boolGasoline");
        boolLowPrice = userChoice.getBoolean("boolLowPrice");
        boolLPG = userChoice.getBoolean("boolLPG");
        boolSocialFacility = userChoice.getBoolean("boolSocialFacility");
        boolStationBrands = userChoice.getBoolean("boolStationBrands");
        boolToilet = userChoice.getBoolean("boolToilet");

        lvStations = findViewById(R.id.lvStations);

        fuelType = "";

        if(boolDiesel){
            fuelType += "Dizel";
        }else if(boolGasoline){
            fuelType += "Benzin";
        }else if(boolLPG){
            fuelType += "LPG";
        }
        lvStations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //istasyon bilgilerini Bundle la diğer sayfaya aktarıyorum

                String infoStation = addStation.get(position).getStationInfo();
                String infoStName = addStation.get(position).getStationName();
                String infoStPrice = addStation.get(position).getStationPrice();

                int infoStPhoto = addStation.get(position).getStPhoto();

                double infoStPositionX = addStation.get(position).getStPositionX();
                double infoStPositionY = addStation.get(position).getStPositionY();


                Bundle stationInformations = new Bundle();
                stationInformations.putDouble("infoStPositionX",infoStPositionX);
                stationInformations.putDouble("infoStPositionY",infoStPositionY);
                stationInformations.putInt("infoStPhoto",infoStPhoto);
                stationInformations.putString("infoStName",infoStName);
                stationInformations.putString("infoStPrice",infoStPrice);
                stationInformations.putString("infoStation",infoStation);

                Intent stationInformation = new Intent(StationListActivity.this,InformationStationActivity.class);
                stationInformation.putExtras(stationInformations);
                startActivity(stationInformation);
            }
        });

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        final PrivateAdapter station = new PrivateAdapter(StationListActivity.this,addStation);
        databaseReference.child("Stations").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //listeneme çoğalmaması için
                addStation.removeAll(addStation);

                for (DataSnapshot listSnap: dataSnapshot.getChildren()) {

                    StationModel value = listSnap.getValue(StationModel.class);
                    String stationName = value.getStationName();
                    String stDistance = value.getStDistance();
                    String stPrice = value.getStationPrice();
                    Double stPositionX = value.getStPositionX();
                    Double stPositionY = value.getStPositionY();
                    String stInfo = value.getStationInfo();

                    //String ImageUrl = value.getSaloonImageURL();

                    //Log.d("ImageURL",ImageUrl);
                    Log.d("stationName",stationName);
                    int a = 1;
                    addStation.add(new StationModel(R.drawable.aytemiz_logo,R.drawable.total_total_photo,stationName,stPrice,stDistance,stInfo,stPositionX,stPositionY));


                    station.notifyDataSetChanged();

                }
                lvStations.setAdapter(station);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}




