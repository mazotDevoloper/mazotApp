package com.mazotapp.mazotapp.activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.mazotapp.mazotapp.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class InformationStationActivity extends AppCompatActivity {

    TextView tvStationName,tvAddress;
    String stationName,stPhoto,stLocation,stAddress;
    ImageView imgStation,backIcon,infoMarket,infoOil,infoWc,infoCafe,infoCarwash,info24hours;
    Button btnOpenGoogleMap;
    Boolean infoBoolMarket,infoBoolWc,infoBoolOil,infoBool24hours,infoBoolCafe,infoBoolCarwash;
    double stPositionX,stPositionY;
    private WebView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_station);

        tvStationName = findViewById(R.id.stationName);
        tvAddress = findViewById(R.id.tvAddress);

        imgStation = findViewById(R.id.imgStation);

        infoCafe = findViewById(R.id.infoCafe);
        infoCarwash = findViewById(R.id.infoCarwash);
        infoMarket = findViewById(R.id.infoMarket);
        infoOil = findViewById(R.id.infoOil);
        infoWc = findViewById(R.id.infoWc);
        info24hours = findViewById(R.id.info24hours);

        btnOpenGoogleMap = findViewById(R.id.btnOpenGoogleMap);

        //burada stationlistten gelen istasyon bilgilerni  alıyor ve tanımlıyorum sonrada başka bundle koyuyorum

        final Bundle stationInformation = getIntent().getExtras();

        stPositionX = stationInformation.getDouble("infoStPositionX");
        stPositionY = stationInformation.getDouble("infoStPositionY");
        stationName = stationInformation.getString("infoStName");
        stPhoto = stationInformation.getString("infoStPhoto");

        infoBoolMarket = stationInformation.getBoolean("infoBoolMarket");
        infoBoolWc = stationInformation.getBoolean("infoBoolWc");
        infoBoolOil = stationInformation.getBoolean("infoBoolOil");
        infoBool24hours = stationInformation.getBoolean("infoBool24hours");
        infoBoolCafe = stationInformation.getBoolean("infoBoolCafe");
        infoBoolCarwash = stationInformation.getBoolean("infoBoolCarwash");

        // istasyon adresinin yazılması

        stAddress = null;

        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(stPositionX, stPositionY, 1);
            if (addresses.size() > 0) {
                // addresses.get(0).getAdminArea();      //il
                //stAddress = addresses.get(0).getCountryName()+" "+ addresses.get(0).getAdminArea()+ "/" + addresses.get(0).getLocality()+ " " + addresses.get(0).getSubAdminArea();

                stAddress = addresses.get(0).getThoroughfare()+ "  " + addresses.get(0).getSubLocality() + " " + addresses.get(0).getLocality()+ "/" + addresses.get(0).getAdminArea() + " " + addresses.get(0).getCountryName();

                if(addresses.get(0).getLocality() == null){
                    stAddress = addresses.get(0).getThoroughfare()+ "  " + addresses.get(0).getSubLocality() + " " + addresses.get(0).getSubAdminArea()+ "/" + addresses.get(0).getAdminArea() + " " + addresses.get(0).getCountryName();
                }

                if(addresses.get(0).getThoroughfare() == null && addresses.get(0).getSubLocality() == null){
                    stAddress = addresses.get(0).getSubAdminArea()+ "/" + addresses.get(0).getAdminArea() + " " + addresses.get(0).getCountryName();
                }

                if(addresses.get(0).getSubLocality() == null && addresses.get(0).getThoroughfare() != null){
                    stAddress = addresses.get(0).getThoroughfare() + " " + addresses.get(0).getSubAdminArea()+ "/" + addresses.get(0).getAdminArea() + " " + addresses.get(0).getCountryName();
                }

                // addresses.get(0).getSubLocality();   //mahalle
                // addresses.get(0).getLocality();      //ilçe
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        tvAddress.setText(stAddress);

        if(infoBool24hours){
            info24hours.setVisibility(View.VISIBLE);
        }
        if(infoBoolCafe){
            infoCafe.setVisibility(View.VISIBLE);
        }
        if(infoBoolCarwash){
            infoCarwash.setVisibility(View.VISIBLE);
        }
        if(infoBoolMarket){
            infoMarket.setVisibility(View.VISIBLE);
        }
        if(infoBoolOil){
            infoOil.setVisibility(View.VISIBLE);
        }
        if(infoBoolWc){
            infoWc.setVisibility(View.VISIBLE);
        }

        stLocation = String.valueOf(stPositionX) + "," + String.valueOf(stPositionY);

        stationInformation.putString("stLocation", stLocation);
        stationInformation.putString("stationName", stationName);

        //gelen bilgileri yerlerine yazdırıyorum

        Picasso.get().load(stPhoto).into(imgStation);


        //imgStation.setImageResource(stPhoto);
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


                /*
                Intent map = new Intent(InformationStationActivity.this,MapActivity.class);
                map.putExtras(stationInformation);
                startActivity(map);
                */
            }
        });

    }
}
