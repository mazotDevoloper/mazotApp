package com.mazotapp.mazotapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PrivateAdapter extends BaseAdapter{

    private LayoutInflater stationLayoutInflater;
    TextView stationNameT,stationPrGasoline;
    ImageView stationImg;

     List<StationModel> stationLst  = new ArrayList<>();



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

        stationNameT = lineVisibility.findViewById(R.id.tStationName);
        stationPrGasoline = lineVisibility.findViewById(R.id.tStPrGasoline);
        stationImg = lineVisibility.findViewById(R.id.imgStationPhoto);


        StationModel stationModel = stationLst.get(position);

        stationNameT.setText(stationModel.getStationName());
        stationPrGasoline.setText(stationModel.getStationPrGasoline());
        stationImg.setImageResource(stationModel.getStationLogo());


        return lineVisibility;
    }
}
