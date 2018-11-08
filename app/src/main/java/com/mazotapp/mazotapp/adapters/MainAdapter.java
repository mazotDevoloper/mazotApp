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

public class MainAdapter extends BaseAdapter{

    private LayoutInflater stationLayoutInflater;
    TextView stationNameT,stationPrice,stationDistance;
    ImageView stationImg;

    List<StationModel> stationLst  = new ArrayList<>();
    String fuelPrice;


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


        stationNameT = lineVisibility.findViewById(R.id.tvStName);
        stationPrice = lineVisibility.findViewById(R.id.stPrice);
        stationImg = lineVisibility.findViewById(R.id.imgLogo);
        stationDistance = lineVisibility.findViewById(R.id.stDistance);


        StationModel stationModel = stationLst.get(position);

        fuelPrice = "Fiyat: " + String.valueOf(stationModel.getStationPrice());

        stationDistance.setText("Mesafe: 25.km");
        stationNameT.setText(stationModel.getStationName());
        stationPrice.setText(fuelPrice);
        Picasso.get().load(stationModel.getStationLogo()).into(stationImg);
        //stationDistance.setText(stationModel.getStDistance());


        return lineVisibility;
    }
}
