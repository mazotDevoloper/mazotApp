package com.mazotapp.mazotapp.fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.IOUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mazotapp.mazotapp.activities.FindStationActivity;
import com.mazotapp.mazotapp.activities.GPSTracker;
import com.mazotapp.mazotapp.activities.HomeActivity;
import com.mazotapp.mazotapp.activities.InformationStationActivity;
import com.mazotapp.mazotapp.R;
import com.mazotapp.mazotapp.activities.StationListActivity;
import com.mazotapp.mazotapp.adapters.MainAdapter;
import com.mazotapp.mazotapp.adapters.PrivateAdapter;
import com.mazotapp.mazotapp.models.StationModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Delayed;

import static android.content.ContentValues.TAG;
import static android.content.Context.LOCATION_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;


public class HomeFragment extends Fragment {

    Button btnGoFindStation;
    Intent intentInfoSt;
    String stationName,stInfo,stPhoto,stLogo,nearDistance;
    Bundle stationInformations;
    View view;
    double stPositionX,stPositionY,userLatitude,userLongitude,distanceNumber;
    CardView cardNear;
    TextView tvNearName,tvNearDistance,AlertT;
    String cityName,countryName;
    ImageView imgNearLogo,imgCarwash,imgMarket,imgWc,imgOil,imgCafe;

    GPSTracker gps;

    Boolean boolMarket,boolCafe,boolOil,boolCarwash,bool24hours,boolWc;
    DatabaseReference databaseReference;
    FirebaseDatabase database;

    private ListView lvStMain;
    List<StationModel> mostUseList = new ArrayList<StationModel>();
    MainAdapter stMainAdapter;
    Query queryStationList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);

        tvNearDistance = view.findViewById(R.id.tvNearDistance);
        tvNearName = view.findViewById(R.id.tvNearName);
        AlertT = view.findViewById(R.id.AlertT);

        imgCafe = view.findViewById(R.id.imgCafe);
        imgCarwash = view.findViewById(R.id.imgCarwash);
        imgMarket = view.findViewById(R.id.imgMarket);
        imgOil = view.findViewById(R.id.imgOil);
        imgWc = view.findViewById(R.id.imgWc);
        imgNearLogo = view.findViewById(R.id.imgStNear);

        lvStMain = view.findViewById(R.id.lvMostUse);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stMainAdapter = new MainAdapter(getActivity(), mostUseList);

        gps = new GPSTracker(getActivity());

        if(gps.isNetworkEnabled){

            userLatitude = gps.getLatitude();
                    userLongitude = gps.getLongitude();

                    countryName = null;
                    cityName = null;
                    Geocoder gcd = new Geocoder(getActivity().getBaseContext(), Locale.getDefault());
                    List<Address> addresses;
                    try {
                        addresses = gcd.getFromLocation(userLatitude, userLongitude, 1);
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


                    // sık kullanılanlar listesi filtreleme
                    queryStationList = databaseReference.orderByChild("stPositionX").limitToFirst(8);
                    queryStationList.addListenerForSingleValueEvent(mostUseStationsListener);

                    //en yakın istasyon

                    queryStationList = databaseReference;
                    queryStationList.addListenerForSingleValueEvent(nearStationListener);

                    AlertT.setVisibility(View.INVISIBLE);
        }else{
            AlertT.setText("Lütfen internet bağlantınızı kontrol  edin ve yukarıdaki yeniden başlat  butonuna basın");
            AlertT.setVisibility(View.VISIBLE);
        }


        //en yakının blgilerini gösteriyorum

        lvStMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //istasyon bilgilerini Bundle la diğer sayfaya aktarıyorum

                Boolean infoBoolMarket = mostUseList.get(position).getStMarket();
                Boolean infoBoolWc = mostUseList.get(position).getStWc();
                Boolean infoBoolOil = mostUseList.get(position).getStOil();
                Boolean infoBool24hours = mostUseList.get(position).getSt24hour();
                Boolean infoBoolCafe = mostUseList.get(position).getStCafe();
                Boolean infoBoolCarwash = mostUseList.get(position).getStCarwash();

                String infoStName = mostUseList.get(position).getStationName();
                String infoStPhoto = mostUseList.get(position).getStPhoto();

                double infoStPositionX = mostUseList.get(position).getStPositionX();
                double infoStPositionY = mostUseList.get(position).getStPositionY();


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

                Intent stationInformation = new Intent(getActivity(), InformationStationActivity.class);
                stationInformation.putExtras(stationInformations);
                startActivity(stationInformation);
            }
        });

        cardNear = view.findViewById(R.id.card_nearStation);

        btnGoFindStation = view.findViewById(R.id.btnGoFindStation);

        intentInfoSt = new Intent(view.getContext(),InformationStationActivity.class);

        stationInformations = new Bundle();

        cardNear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(stPositionX,stPositionY,stPhoto,stationName,stInfo,boolMarket,boolWc,boolCafe,bool24hours,boolOil,boolCarwash);
            }
        });


        btnGoFindStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFindStation = new Intent(v.getContext(), FindStationActivity.class);
                startActivity(intentFindStation);
            }
        });
    }

    public void intent(double infoStPositionX, double infoStPositionY, String infoStPhoto, String infoStName, String infoStation,Boolean infoMarket, Boolean infoWc, Boolean infoCafe, Boolean info24hours, Boolean infoOil, Boolean infoCarwash){

        stationInformations.putDouble("infoStPositionX",infoStPositionX);
        stationInformations.putDouble("infoStPositionY",infoStPositionY);
        stationInformations.putString("infoStPhoto",infoStPhoto);
        stationInformations.putString("infoStName",infoStName);
        stationInformations.putString("infoStation",infoStation);

        stationInformations.putBoolean("infoBoolMarket",infoMarket);
        stationInformations.putBoolean("infoBoolWc",infoWc);
        stationInformations.putBoolean("infoBoolCafe",infoCafe);
        stationInformations.putBoolean("infoBool24hours",info24hours);
        stationInformations.putBoolean("infoBoolOil",infoOil);
        stationInformations.putBoolean("infoBoolCarwash",infoCarwash);

        intentInfoSt.putExtras(stationInformations);

        startActivity(intentInfoSt);
    }

    ValueEventListener mostUseStationsListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            mostUseList.clear();
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
                    Boolean stTire = value.getStTire();
                    Boolean stMigros = value.getStMigros();
                    Boolean stMescit = value.getStMescit();
                    Boolean stATM = value.getStATM();
                    Boolean stRedBull = value.getStRedBull();
                    Boolean stFood = value.getStFood();



                    mostUseList.add(new StationModel(stLogo,stPhoto,stationName,stPositionX,stPositionY,stBrand,st24hour,stCafe,stCarwash,stOil,stWc,stMarket,stTire,stMigros,stMescit,stATM,stRedBull,stFood));

                    stMainAdapter.notifyDataSetChanged();
                }
                lvStMain.setAdapter(stMainAdapter);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    ValueEventListener nearStationListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot listSnap : dataSnapshot.getChildren()) {
                    StationModel value = listSnap.getValue(StationModel.class);

                    stationName = value.getStationName();
                    stPositionX = value.getStPositionX();
                    stPositionY = value.getStPositionY();
                    stPhoto = value.getStPhoto();
                    stLogo = value.getStationLogo();

                    boolCafe = value.getStCafe();
                    boolCarwash = value.getStCarwash();
                    boolMarket = value.getStMarket();
                    boolOil = value.getStOil();
                    boolWc = value.getStWc();
                    bool24hours = value.getSt24hour();

                    if(boolWc){
                        imgWc.setVisibility(View.VISIBLE);
                    }
                    if(boolOil){
                        imgOil.setVisibility(View.VISIBLE);
                    }
                    if(boolCarwash){
                        imgCarwash.setVisibility(View.VISIBLE);
                    }
                    if(boolMarket){
                        imgMarket.setVisibility(View.VISIBLE);
                    }
                    if(boolCafe){
                        imgCafe.setVisibility(View.VISIBLE);
                    }

                    if(!gps.canGetLocation()){
                        distanceNumber = 0.0;
                    }else{
                        userLatitude = gps.getLatitude();
                        userLongitude = gps.getLongitude();
                        distanceNumber = CalculationByDistance(userLatitude,userLongitude,stPositionX,stPositionY);
                    }

                    //burada mesafeyi kısıtlıyorum(virgülden sonraki 0'dan sonraki kısmını göstermiyorum)
                    DecimalFormat precision = new DecimalFormat("0.0");

                    nearDistance =  precision.format(distanceNumber) + " KM";

                    tvNearName.setText(stationName);
                    tvNearDistance.setText(nearDistance);
                    Picasso.get().load(stLogo).into(imgNearLogo);

                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

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

        return Radius * c;
    }
}





