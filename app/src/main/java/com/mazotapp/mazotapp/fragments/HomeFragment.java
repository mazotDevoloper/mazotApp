package com.mazotapp.mazotapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mazotapp.mazotapp.activities.FindStationActivity;
import com.mazotapp.mazotapp.activities.InformationStationActivity;
import com.mazotapp.mazotapp.R;


public class HomeFragment extends Fragment {

    Button btnGoFindStation;
    Intent intentInfoSt;
    String infoStation,infoStName;
    Bundle stationInformations;
    int infoStPhoto;
    View view;
    double infoStPositionX,infoStPositionY;
    CardView cardNear,cardMostUse1,cardMostUse2,cardMostUse3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardNear = view.findViewById(R.id.card_nearStation);
        cardMostUse1 = view.findViewById(R.id.card_mostUseStations1);
        cardMostUse2 = view.findViewById(R.id.card_mostUseStations2);
        cardMostUse3 = view.findViewById(R.id.card_mostUseStations3);

        btnGoFindStation = view.findViewById(R.id.btnGoFindStation);

        intentInfoSt = new Intent(view.getContext(),InformationStationActivity.class);

        stationInformations = new Bundle();


        cardNear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoStation = "OPET Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, tuvalet, engelli tuvaleti, bebek bakım ünitesi bulunmaktadır.";
                infoStName = "OPET İstanbul-Şişli GÜRCAN";
                infoStPositionX = 41.048434;
                infoStPositionY = 28.984981;
                infoStPhoto = 0;
                intent(infoStPositionX,infoStPositionY,infoStPhoto,infoStName,infoStation);
            }
        });

        cardMostUse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                infoStation = "Shell Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, para çekebileceğiniz bir ATM ve tuvalet bulunmaktadır.";
                infoStName = "Shell Şişli Petrol";
                infoStPositionX = 41.069317;
                infoStPositionY = 29.004395;
                infoStPhoto = 0;
                intent(infoStPositionX,infoStPositionY,infoStPhoto,infoStName,infoStation);

            }
        });

        cardMostUse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                infoStation = "Petrol Ofisi Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda yıkama makinesi, hava ve su makinesi, tuvalet bulunmaktadır.";
                infoStName = "Petrol Ofisi ORSAY";
                infoStPositionX = 41.047798;
                infoStPositionY = 28.968614;
                infoStPhoto = 0;
                intent(infoStPositionX,infoStPositionY,infoStPhoto,infoStName,infoStation);


            }
        });

        cardMostUse3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                infoStation = "AYTEMİZ Şişli akaryakıt istasyonunda market, oto yıkama, hava ve su makinesi, tuvalet bulunmaktadır.";
                infoStName = "AYTEMİZ   ŞİŞLİ Petrol ";
                infoStPositionX = 41.058522;
                infoStPositionY = 28.965656;
                infoStPhoto = 0;
                intent(infoStPositionX,infoStPositionY,infoStPhoto,infoStName,infoStation);

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

    public void intent(double infoStPositionX, double infoStPositionY, int infoStPhoto, String infoStName, String infoStation){

        stationInformations.putDouble("infoStPositionX",infoStPositionX);
        stationInformations.putDouble("infoStPositionY",infoStPositionY);
        stationInformations.putInt("infoStPhoto",infoStPhoto);
        stationInformations.putString("infoStName",infoStName);
        stationInformations.putString("infoStation",infoStation);

        intentInfoSt.putExtras(stationInformations);

        startActivity(intentInfoSt);
    }
    
}

