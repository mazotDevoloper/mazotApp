package com.mazotapp.mazotapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mazotapp.mazotapp.R;
import com.mazotapp.mazotapp.models.StationModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PrivateAdapter extends BaseAdapter{

    private LayoutInflater stationLayoutInflater;
    TextView stationNameT,stationPrice,stationDistance,stPositionT;
    ImageView stationImg;

     List<StationModel> stationLst  = new ArrayList<>();
     String fuelPrice;


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
        stationPrice = lineVisibility.findViewById(R.id.tStPrice);
        stationImg = lineVisibility.findViewById(R.id.imgStationPhoto);
        stationDistance = lineVisibility.findViewById(R.id.tStDistance);


        StationModel stationModel = stationLst.get(position);

        fuelPrice = "Fiyatı: " + String.valueOf(stationModel.getStationPrice()) ;

        stationNameT.setText(stationModel.getStationName());
        stPositionT.setText(String.valueOf(position + 1 ));
        stationPrice.setText(fuelPrice);
        Picasso.get().load(stationModel.getStationLogo()).into(stationImg);
        //stationDistance.setText(stationModel.getStDistance());


        return lineVisibility;
    }
}
