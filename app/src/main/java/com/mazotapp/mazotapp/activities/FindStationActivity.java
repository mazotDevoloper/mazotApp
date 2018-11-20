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
     CheckBox cbLowPrice,cbDistance,cbBestToilet;
     RadioGroup rgCarFuel;
    String stationName,stInfo,stPhoto,stLogo,stMPrice,goodStDistance;
    RadioButton rb;
    double stPrice,stLPG,stDiesel,stGasoline,stPositionX,stPositionY;
    TextView tvNearName,tvNearPrice,tvNearDistance;
    ImageView imgNearLogo;
    Intent intentInfoSt;
    int stCleanToilet;
    CardView cardNear;
    GPSTracker gps;
    double userLatitude,userLongitude,distanceNumber;
    Bundle stationInformations;
    Query queryStationList;

    Boolean boolGasoline,boolDiesel,boolLPG,boolLowPrice,boolDistance,boolToilet;

    DatabaseReference databaseReference;
    FirebaseDatabase database;

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

        cardNear = findViewById(R.id.card_station);

        tvNearDistance = findViewById(R.id.tvNearDistance);
        tvNearName = findViewById(R.id.tvNearName);
        tvNearPrice = findViewById(R.id.tvNearPrice);

        imgNearLogo = findViewById(R.id.imgStNear);

        btnFindStation = findViewById(R.id.btnFindStation);

        intentInfoSt = new Intent(FindStationActivity.this,InformationStationActivity.class);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Stations");

        queryStationList = databaseReference;
        queryStationList.addListenerForSingleValueEvent(nearStationListener);

        backIcon = findViewById(R.id.imgBack_icon);

        cbBestToilet = findViewById(R.id.cbBestToilet);
        cbDistance = findViewById(R.id.cbDistance);
        cbLowPrice = findViewById(R.id.cbLowPrice);

        rgCarFuel = findViewById(R.id.rgCarFuel);

        stationInformations = new Bundle();

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cardNear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(stPositionX,stPositionY,stPhoto,stationName,stInfo);
            }
        });

        btnFindStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioButtonId = rgCarFuel.getCheckedRadioButtonId();

                rb = findViewById(radioButtonId);

                if(rb == null){
                    Toast.makeText(FindStationActivity.this, "Aracınızın akaryakıt türünü boş bırakamazsınız!", Toast.LENGTH_SHORT).show();
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

    public void intent(double infoStPositionX, double infoStPositionY, String infoStPhoto, String infoStName, String infoStation){

        stationInformations.putDouble("infoStPositionX",infoStPositionX);
        stationInformations.putDouble("infoStPositionY",infoStPositionY);
        stationInformations.putString("infoStPhoto",infoStPhoto);
        stationInformations.putString("infoStName",infoStName);
        stationInformations.putString("infoStation",infoStation);

        intentInfoSt.putExtras(stationInformations);

        startActivity(intentInfoSt);
    }

    ValueEventListener nearStationListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot listSnap : dataSnapshot.getChildren()) {
                    StationModel value = listSnap.getValue(StationModel.class);

                    stationName = value.getStationName();
                    stCleanToilet = value.getStCleanToilet();
                    stPrice = value.getStationPrice();
                    stLPG = value.getStLPG();
                    stDiesel = value.getStDiesel();
                    stGasoline = value.getStGasoline();
                    stPositionX = value.getStPositionX();
                    stPositionY = value.getStPositionY();
                    stInfo = value.getStationInfo();
                    stPhoto = value.getStPhoto();
                    stLogo = value.getStationLogo();

                    stPrice = stGasoline;

                    gps = new GPSTracker(FindStationActivity.this);

                    if (gps.canGetLocation()) {
                        userLatitude = gps.getLatitude();
                        userLongitude = gps.getLongitude();
                    }

                    distanceNumber = CalculationByDistance(userLatitude,userLongitude,stPositionX,stPositionY);

                    //burada mesafeyi kısıtlıyorum
                    DecimalFormat precision = new DecimalFormat("0.0");

                    goodStDistance = "Mesafe: " + precision.format(distanceNumber) + " km";

                    stMPrice = "Fiyat: " + String.valueOf(stPrice);

                    tvNearPrice.setText(stMPrice);
                    tvNearName.setText(stationName);
                    tvNearDistance.setText(goodStDistance);
                    Picasso.get().load(stLogo).into(imgNearLogo);

                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    //mesafe hesaplama fonksiyonu

    public double CalculationByDistance(double userLatitude , double userLongitude,double stationLatitude,double stationLongtitude) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = userLatitude;
        double lat2 = stationLatitude;
        double lon1 = userLongitude;
        double lon2 = stationLongtitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        //int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        //int meterInDec = Integer.valueOf(newFormat.format(meter));
        //Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
        //        + " Meter   " + meterInDec);

        return Radius * c;
    }
}
