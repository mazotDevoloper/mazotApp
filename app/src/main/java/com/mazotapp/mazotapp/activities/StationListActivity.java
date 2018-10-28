package com.mazotapp.mazotapp.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mazotapp.mazotapp.adapters.PrivateAdapter;
import com.mazotapp.mazotapp.R;
import com.mazotapp.mazotapp.models.StationModel;

import java.util.ArrayList;
import java.util.List;

public class StationListActivity extends AppCompatActivity {


    ImageView backIcon;
    private ListView lvStations;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    String fuelType;
    Boolean boolGasoline, boolDiesel, boolLPG, boolLowPrice, boolDistance, boolToilet;
    List<StationModel> addStation = new ArrayList<StationModel>();
    PrivateAdapter stationAdapter;
    Query queryStationList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list);
        backIcon = findViewById(R.id.imgBack_icon);


        stationAdapter = new PrivateAdapter(StationListActivity.this, addStation);

        Bundle userChoice = getIntent().getExtras();


        // database = FirebaseDatabase.getInstance();
        // databaseReference = database.getReference().child("Stations");


        //boolean ları bundle la diğer tarafından çekilen yer

        boolDiesel = userChoice.getBoolean("boolDiesel");
        boolDistance = userChoice.getBoolean("boolDistance");
        boolGasoline = userChoice.getBoolean("boolGasoline");
        boolLowPrice = userChoice.getBoolean("boolLowPrice");
        boolLPG = userChoice.getBoolean("boolLPG");
        boolToilet = userChoice.getBoolean("boolToilet");

        lvStations = findViewById(R.id.lvStations);

        fuelType = "";

        if (boolDiesel) {
            fuelType += "Dizel";
        } else if (boolGasoline) {
            fuelType += "Benzin";
        } else if (boolLPG) {
            fuelType += "LPG";
        }



        queryStationList = FirebaseDatabase.getInstance().getReference().child("Stations")
                .orderByChild("stationPrice");







        queryStationList.addListenerForSingleValueEvent(valueEventListener);



        lvStations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //istasyon bilgilerini Bundle la diğer sayfaya aktarıyorum

                String infoStation = addStation.get(position).getStationInfo();
                String infoStName = addStation.get(position).getStationName();
                int infoStPrice = addStation.get(position).getStationPrice();

                String infoStPhoto = addStation.get(position).getStPhoto();

                double infoStPositionX = addStation.get(position).getStPositionX();
                double infoStPositionY = addStation.get(position).getStPositionY();


                Bundle stationInformations = new Bundle();
                stationInformations.putDouble("infoStPositionX", infoStPositionX);
                stationInformations.putDouble("infoStPositionY", infoStPositionY);
                stationInformations.putString("infoStPhoto", infoStPhoto);
                stationInformations.putString("infoStName", infoStName);
                stationInformations.putInt("infoStPrice", infoStPrice);
                stationInformations.putString("infoStation", infoStation);

                Intent stationInformation = new Intent(StationListActivity.this, InformationStationActivity.class);
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


    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            addStation.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot listSnap : dataSnapshot.getChildren()) {
                    StationModel value = listSnap.getValue(StationModel.class);

                    String stationName = value.getStationName();
                    String stDistance = value.getStDistance();
                    int stPrice = value.getStationPrice();
                    Double stPositionX = value.getStPositionX();
                    Double stPositionY = value.getStPositionY();
                    String stInfo = value.getStationInfo();
                    String stPhoto = value.getStPhoto();
                    String stLogo = value.getStationLogo();

                    addStation.add(new StationModel(stLogo,stPhoto,stationName,stPrice,stDistance,stInfo,stPositionX,stPositionY));

                    stationAdapter.notifyDataSetChanged();
                }
                lvStations.setAdapter(stationAdapter);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}



