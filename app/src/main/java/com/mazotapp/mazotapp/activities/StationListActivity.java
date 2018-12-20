package com.mazotapp.mazotapp.activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mazotapp.mazotapp.adapters.PrivateAdapter;
import com.mazotapp.mazotapp.R;
import com.mazotapp.mazotapp.models.StationModel;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StationListActivity extends AppCompatActivity {

    ImageView backIcon;
    TextView tvNothingFound;
    private ListView lvStations;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    Boolean boolGasoline,boolDiesel,boolLPG,boolTotal,boolOpet,boolPO,boolShell,boolTP,boolBP,bool24Hour,boolMarket,boolCafe,boolWc,boolOil,boolCarwash,boolMigros,boolATM,boolMescit,boolTire,boolRedBull,boolFood;
    List<StationModel> addStation = new ArrayList<StationModel>();
    PrivateAdapter stationAdapter;
    Query queryStationList;
    ImageView restart_icon;
    GPSTracker gps;
    double latitude,longitude;
    String cityName,countryName;
    Boolean[] userChoiceBools = new Boolean[10];
    Boolean[] stationBools = new Boolean[10];
    int b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list);

        backIcon = findViewById(R.id.imgBack_icon);
        tvNothingFound =  findViewById(R.id.tvNothingFound);

        stationAdapter = new PrivateAdapter(StationListActivity.this, addStation);

        Bundle userChoice = getIntent().getExtras();

        restart_icon = findViewById(R.id.restart_icon);


        gps = new GPSTracker(StationListActivity.this);

        if(gps.isNetworkEnabled){
                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();

                    cityName = null;
                    countryName = null;
                    Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                    List<Address> addresses;
                    try {
                        addresses = gcd.getFromLocation(latitude, longitude, 1);
                        if (addresses.size() > 0) {
                            //cityName = addresses.get(0).getAdminArea();      //il
                            countryName = addresses.get(0).getCountryName();
                            // cityName = addresses.get(0).getSubLocality();   //mahalle
                            cityName = addresses.get(0).getLocality();      //ilçe
                        }
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    database = FirebaseDatabase.getInstance();
                    //databaseReference = database.getReference().child("deneme").child(countryName).child(cityName);
                    databaseReference = database.getReference().child("Stations");

                    // Filtreleme yeri

                    queryStationList = databaseReference.limitToFirst(100);
                    queryStationList.addListenerForSingleValueEvent(valueEventListener);
        }else{
            Toast.makeText(StationListActivity.this, "Lütfen internet bağlantınızı kontrol  edin ve yukarıdaki yeniden başlat  butonuna basın", Toast.LENGTH_SHORT).show();
        }

        restart_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        //boolean ları bundle la diğer tarafından çekilen yer

        boolDiesel = userChoice.getBoolean("boolDiesel");
        boolGasoline = userChoice.getBoolean("boolGasoline");
        boolLPG = userChoice.getBoolean("boolLPG");

        bool24Hour = userChoice.getBoolean("bool24Hour");
        boolMarket = userChoice.getBoolean("boolMarket");
        boolWc = userChoice.getBoolean("boolWc");
        boolOil = userChoice.getBoolean("boolOil");
        boolCarwash = userChoice.getBoolean("boolCarwash");
        boolCafe = userChoice.getBoolean("boolCafe");
        boolTire = userChoice.getBoolean("boolTire");
        boolMigros = userChoice.getBoolean("boolMigros");
        boolMescit = userChoice.getBoolean("boolMescit");
        boolATM = userChoice.getBoolean("boolATM");
        boolRedBull = userChoice.getBoolean("boolRedBull");
        boolFood = userChoice.getBoolean("boolFood");

        boolOpet = userChoice.getBoolean("boolOpet");
        boolBP = userChoice.getBoolean("boolBP");
        boolTP = userChoice.getBoolean("boolTP");
        boolTotal = userChoice.getBoolean("boolTotal");
        boolShell = userChoice.getBoolean("boolShell");
        boolPO = userChoice.getBoolean("boolPO");

        userChoiceBools[0] = boolMarket;
        userChoiceBools[1] = boolOil;
        userChoiceBools[2] = boolWc;
        userChoiceBools[3] = boolCarwash;
        userChoiceBools[4] = boolCafe;
        userChoiceBools[5] = bool24Hour;
        userChoiceBools[6] = boolATM;
        userChoiceBools[7] = boolMescit;
        userChoiceBools[8] = boolMigros;
        userChoiceBools[9] = boolTire;
        userChoiceBools[10] = boolRedBull;
        userChoiceBools[11] = boolFood;


        lvStations = findViewById(R.id.lvStations);

        lvStations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //istasyon bilgilerini Bundle la diğer sayfaya aktarıyorum

                String infoStName = addStation.get(position).getStationName();
                String infoStPhoto = addStation.get(position).getStPhoto();

                double infoStPositionX = addStation.get(position).getStPositionX();
                double infoStPositionY = addStation.get(position).getStPositionY();

                Boolean infoBoolMarket = addStation.get(position).getStMarket();
                Boolean infoBoolWc = addStation.get(position).getStWc();
                Boolean infoBoolOil = addStation.get(position).getStOil();
                Boolean infoBool24hours = addStation.get(position).getSt24hour();
                Boolean infoBoolCafe = addStation.get(position).getStCafe();
                Boolean infoBoolCarwash = addStation.get(position).getStCarwash();


                Bundle stationInformations = new Bundle();
                stationInformations.putDouble("infoStPositionX", infoStPositionX);
                stationInformations.putDouble("infoStPositionY", infoStPositionY);
                stationInformations.putString("infoStPhoto", infoStPhoto);
                stationInformations.putString("infoStName", infoStName);

                stationInformations.putBoolean("infoBoolMarket",infoBoolMarket);
                stationInformations.putBoolean("infoBoolWc",infoBoolWc);
                stationInformations.putBoolean("infoBoolOil",infoBoolOil);
                stationInformations.putBoolean("infoBool24hours",infoBool24hours);
                stationInformations.putBoolean("infoBoolCafe",infoBoolCafe);
                stationInformations.putBoolean("infoBoolCarwash",infoBoolCarwash);


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
                    Double stPositionX = value.getStPositionX();
                    Double stPositionY = value.getStPositionY();
                    String stPhoto = value.getStPhoto();
                    String stLogo = value.getStationLogo();
                    String stBrand = value.getStBrand();

                    Boolean stMarket = value.getStMarket();
                    Boolean stCafe = value.getStCafe();
                    Boolean stWc = value.getStWc();
                    Boolean stOil = value.getStOil();
                    Boolean stCarwash = value.getStCarwash();
                    Boolean st24hour = value.getSt24hour();
                    Boolean stMigros = value.getStMigros();
                    Boolean stMescit = value.getStMescit();
                    Boolean stATM = value.getStATM();
                    Boolean stTire = value.getStTire();
                    Boolean stRedBull = value.getStRedBull();
                    Boolean stFood = value.getStFood();


                    stationBools[0] = stMarket;
                    stationBools[1] = stOil;
                    stationBools[2] = stWc;
                    stationBools[3] = stCarwash;
                    stationBools[4] = stCafe;
                    stationBools[5] = st24hour;
                    stationBools[6] = stATM;
                    stationBools[7] = stMescit;
                    stationBools[8] = stMigros;
                    stationBools[9] = stTire;
                    stationBools[10] = stRedBull;
                    stationBools[11] = stFood;


                    b = 0;

                    for(int a = 0; a<12; a++){

                        if(!userChoiceBools[a]){
                            b++;
                        } else if (userChoiceBools[a] == stationBools[a]) {
                            b++;
                        }
                    }

                    if(b == 12){
                        if(!boolOpet && !boolTotal && !boolShell && !boolTP && !boolBP && !boolPO){
                            addStation.add(new StationModel(stLogo,stPhoto,stationName,stPositionX,stPositionY,stBrand,st24hour,stCafe,stCarwash,stOil,stWc,stMarket,stTire,stMigros,stMescit,stATM,stRedBull,stFood));
                        }else{
                            if(boolBP && stBrand.equals("BP")){
                                addStation.add(new StationModel(stLogo,stPhoto,stationName,stPositionX,stPositionY,stBrand,st24hour,stCafe,stCarwash,stOil,stWc,stMarket,stTire,stMigros,stMescit,stATM,stRedBull,stFood));
                            }
                            if(boolTP && stBrand.equals("TP")){
                                addStation.add(new StationModel(stLogo,stPhoto,stationName,stPositionX,stPositionY,stBrand,st24hour,stCafe,stCarwash,stOil,stWc,stMarket,stTire,stMigros,stMescit,stATM,stRedBull,stFood));
                            }
                            if(boolShell && stBrand.equals("Shell")){
                                addStation.add(new StationModel(stLogo,stPhoto,stationName,stPositionX,stPositionY,stBrand,st24hour,stCafe,stCarwash,stOil,stWc,stMarket,stTire,stMigros,stMescit,stATM,stRedBull,stFood));
                            }
                            if(boolTotal && stBrand.equals("Total")){
                                addStation.add(new StationModel(stLogo,stPhoto,stationName,stPositionX,stPositionY,stBrand,st24hour,stCafe,stCarwash,stOil,stWc,stMarket,stTire,stMigros,stMescit,stATM,stRedBull,stFood));
                            }
                            if(boolPO && stBrand.equals("PO")){
                                addStation.add(new StationModel(stLogo,stPhoto,stationName,stPositionX,stPositionY,stBrand,st24hour,stCafe,stCarwash,stOil,stWc,stMarket,stTire,stMigros,stMescit,stATM,stRedBull,stFood));
                            }
                            if(boolOpet && stBrand.equals("Opet")){
                                addStation.add(new StationModel(stLogo,stPhoto,stationName,stPositionX,stPositionY,stBrand,st24hour,stCafe,stCarwash,stOil,stWc,stMarket,stTire,stMigros,stMescit,stATM,stRedBull,stFood));
                            }
                        }
                    }
                    stationAdapter.notifyDataSetChanged();
                }
                lvStations.setAdapter(stationAdapter);
            }

            // Eğer istasyon listesi yoksa bunu kullanıcıya haber vermeliyiz

            if (addStation.isEmpty()){
                tvNothingFound.setVisibility(View.VISIBLE);
                tvNothingFound.setText("Aradığınız özelliklerde yakın çevrenizde bir istasyon bulunamadı.");
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}



