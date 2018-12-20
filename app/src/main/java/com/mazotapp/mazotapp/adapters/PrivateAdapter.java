package com.mazotapp.mazotapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mazotapp.mazotapp.R;
import com.mazotapp.mazotapp.activities.GPSTracker;
import com.mazotapp.mazotapp.models.StationModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class PrivateAdapter extends BaseAdapter{

    private LayoutInflater stationLayoutInflater;
    TextView stationNameT,stationDistance,stPositionT;
    ImageView stationImg,imgCafe,imgMarket,imgWc,imgOil,imgCarwash;

    GPSTracker gps;

    double userLatitude,userLongitude,distanceNumber;

    List<StationModel> stationLst  = new ArrayList<>();
     String distance;


    public PrivateAdapter(Activity activity, List<StationModel> stationList){
        stationLayoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.stationLst = stationList;
    }

    //gönderilen listenin kaç elemanlı oldugunu bildirir ve ona göre ekrandan layout çizer.

    @Override
    public int getCount() {
        return stationLst.size();
    }

    @Override
    public Object getItem(int position) {
        return stationLst.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View lineVisibility = stationLayoutInflater.inflate(R.layout.station,null);

        stPositionT = lineVisibility.findViewById(R.id.tvPosition);
        stationNameT = lineVisibility.findViewById(R.id.tStationName);
        stationImg = lineVisibility.findViewById(R.id.imgStationPhoto);
        stationDistance = lineVisibility.findViewById(R.id.tStDistance);

        imgCafe = lineVisibility.findViewById(R.id.imgCafe);
        imgCarwash = lineVisibility.findViewById(R.id.imgCarwash);
        imgMarket = lineVisibility.findViewById(R.id.imgMarket);
        imgOil =  lineVisibility.findViewById(R.id.imgOil);
        imgWc = lineVisibility.findViewById(R.id.imgWc);

        StationModel stationModel = stationLst.get(position);

        gps = new GPSTracker(lineVisibility.getContext());

        if(!gps.canGetLocation()){
            distanceNumber = 0.0;
        }else{
            userLatitude = gps.getLatitude();
            userLongitude = gps.getLongitude();
            distanceNumber = CalculationByDistance(userLatitude,userLongitude,stationModel.getStPositionX(),stationModel.getStPositionY());
        }

        Boolean boolCafe,boolCarwash,boolMarket,boolOil,boolWc;

        boolCafe = stationModel.getStCafe();
        boolCarwash = stationModel.getStCarwash();
        boolMarket = stationModel.getStMarket();
        boolOil = stationModel.getStOil();
        boolWc = stationModel.getStWc();

        if(boolCafe){
            imgCafe.setVisibility(View.VISIBLE);
        }
        if(boolCarwash){
            imgCarwash.setVisibility(View.VISIBLE);
        }
        if(boolMarket){
            imgMarket.setVisibility(View.VISIBLE);
        }
        if(boolOil){
            imgOil.setVisibility(View.VISIBLE);
        }
        if(boolWc){
            imgWc.setVisibility(View.VISIBLE);
        }

        //burada mesafeyi kısıtlıyorum
        DecimalFormat precision = new DecimalFormat("0.0");

        distance = precision.format(distanceNumber) + " KM";

        stationNameT.setText(stationModel.getStationName());
        stPositionT.setText(String.valueOf(position + 1 ));
        Picasso.get().load(stationModel.getStationLogo()).into(stationImg);
        stationDistance.setText(distance);


        return lineVisibility;
    }

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
