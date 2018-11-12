package com.mazotapp.mazotapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mazotapp.mazotapp.R;
import com.mazotapp.mazotapp.activities.GPSTracker;
import com.mazotapp.mazotapp.activities.RegisterActivity;
import com.mazotapp.mazotapp.models.StationModel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends BaseAdapter{

    private LayoutInflater stationLayoutInflater;
    TextView stationNameT,stationPrice,stationDistance;
    ImageView stationImg;

    List<StationModel> stationLst  = new ArrayList<>();
    GPSTracker gps;
    String fuelPrice,distance;

    double userLatitude,userLongitude,distanceNumber;

    public MainAdapter(Activity activity, List<StationModel> stationList){
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

        View lineVisibility = stationLayoutInflater.inflate(R.layout.cardview_station,null);

        gps = new GPSTracker(lineVisibility.getContext());

        if (gps.canGetLocation()) {
            userLatitude = gps.getLatitude();
            userLongitude = gps.getLongitude();
        }

        stationNameT = lineVisibility.findViewById(R.id.tvStName);
        stationPrice = lineVisibility.findViewById(R.id.stPrice);
        stationImg = lineVisibility.findViewById(R.id.imgLogo);
        stationDistance = lineVisibility.findViewById(R.id.stDistance);


        StationModel stationModel = stationLst.get(position);

        distanceNumber = CalculationByDistance(userLatitude,userLongitude,stationModel.getStPositionX(),stationModel.getStPositionY());

        //burada mesafeyi kısıtlıyorum
        DecimalFormat precision = new DecimalFormat("0.0");

        distance = "Mesafe: " + precision.format(distanceNumber) + " km";

        fuelPrice = "Fiyat: " + String.valueOf(stationModel.getStationPrice());

        stationDistance.setText(distance);
        stationNameT.setText(stationModel.getStationName());
        stationPrice.setText(fuelPrice);
        Picasso.get().load(stationModel.getStationLogo()).into(stationImg);

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
