package com.mazotapp.mazotapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mazotapp.mazotapp.R;

public class InformationStationActivity extends AppCompatActivity {

    TextView tvStationName,tvStationInfo;
    String stationName,stationInfo;
    ImageView imgStation;
    Button btnOpenGoogleMap;
    ImageView backIcon;
    int stPhoto;
    double stPositionX,stPositionY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_station);

        tvStationName = findViewById(R.id.stationName);
        tvStationInfo = findViewById(R.id.stationInfo);

        imgStation = findViewById(R.id.imgStation);

        btnOpenGoogleMap = findViewById(R.id.btnOpenGoogleMap);

        //burada stationlistten gelen istasyon bilgilerni  alıyor ve tanımlıyorum

        Bundle stationInformation = getIntent().getExtras();

        stPositionX = stationInformation.getDouble("infoStPositionX");
        stPositionY = stationInformation.getDouble("infoStPositionY");
        stationName = stationInformation.getString("infoStName");
        stationInfo = stationInformation.getString("infoStation");

        stPhoto = stationInformation.getInt("infoStPhoto");

        //gelen bilgileri yerlerine yazdırıyorum

        imgStation.setImageResource(stPhoto);
        tvStationInfo.setText(stationInfo);
        tvStationName.setText(stationName);

        backIcon = findViewById(R.id.imgBack_icon);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnOpenGoogleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String label = stationName;
                String uriBegin = "geo:" + stPositionX + "," + stPositionY;
                String query = stPositionX + "," + stPositionY +"(" + label + ")";
                String encodedQuery = Uri.encode( query  );
                String uriString = uriBegin + "?q=" + encodedQuery;
                Uri uri = Uri.parse( uriString );
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri );
                startActivity( intent );
            }
        });

    }
}
