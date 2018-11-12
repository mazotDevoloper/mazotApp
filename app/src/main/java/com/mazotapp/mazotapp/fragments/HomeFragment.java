package com.mazotapp.mazotapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mazotapp.mazotapp.activities.FindStationActivity;
import com.mazotapp.mazotapp.activities.HomeActivity;
import com.mazotapp.mazotapp.activities.InformationStationActivity;
import com.mazotapp.mazotapp.R;
import com.mazotapp.mazotapp.activities.StationListActivity;
import com.mazotapp.mazotapp.adapters.MainAdapter;
import com.mazotapp.mazotapp.adapters.PrivateAdapter;
import com.mazotapp.mazotapp.models.StationModel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    Button btnGoFindStation;
    Intent intentInfoSt;
    String stationName,stInfo,stPhoto,stLogo,stMPrice;
    Bundle stationInformations;
    int stCleanToilet;
    View view;
    double stPrice,stLPG,stDiesel,stGasoline,stPositionX,stPositionY;
    CardView cardNear;

    TextView tvNearName,tvNearPrice,tvNearDistance;

    ImageView imgNearLogo;

    private ListView lvStMain;
    DatabaseReference databaseReference;
    FirebaseDatabase database;

    List<StationModel> mostUseList = new ArrayList<StationModel>();
    MainAdapter stMainAdapter;
    Query queryStationList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);

        tvNearDistance = view.findViewById(R.id.tvNearDistance);
        tvNearName = view.findViewById(R.id.tvNearName);
        tvNearPrice = view.findViewById(R.id.tvNearPrice);

        imgNearLogo = view.findViewById(R.id.imgStNear);

        lvStMain = view.findViewById(R.id.lvMostUse);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stMainAdapter = new MainAdapter(getActivity(), mostUseList);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Stations");

        // sık kullanılanlar listesi filtreleme
        queryStationList = databaseReference.orderByChild("stGasoline").limitToFirst(4);
        queryStationList.addListenerForSingleValueEvent(mostUseStationsListener);

        //en yakın istasyon

        queryStationList = databaseReference;
        queryStationList.addListenerForSingleValueEvent(nearStationListener);

        //en yakının blgilerini gösteriyorum


        lvStMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //istasyon bilgilerini Bundle la diğer sayfaya aktarıyorum

                String infoStation = mostUseList.get(position).getStationInfo();
                String infoStName = mostUseList.get(position).getStationName();
                String infoStPhoto = mostUseList.get(position).getStPhoto();

                double infoStPrice = mostUseList.get(position).getStationPrice();
                double infoStPositionX = mostUseList.get(position).getStPositionX();
                double infoStPositionY = mostUseList.get(position).getStPositionY();

                Bundle stationInformations = new Bundle();
                stationInformations.putDouble("infoStPositionX", infoStPositionX);
                stationInformations.putDouble("infoStPositionY", infoStPositionY);
                stationInformations.putString("infoStPhoto", infoStPhoto);
                stationInformations.putString("infoStName", infoStName);
                stationInformations.putDouble("infoStPrice", infoStPrice);
                stationInformations.putString("infoStation", infoStation);

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
                intent(stPositionX,stPositionY,stPhoto,stationName,stInfo);
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

    public void intent(double infoStPositionX, double infoStPositionY, String infoStPhoto, String infoStName, String infoStation){

        stationInformations.putDouble("infoStPositionX",infoStPositionX);
        stationInformations.putDouble("infoStPositionY",infoStPositionY);
        stationInformations.putString("infoStPhoto",infoStPhoto);
        stationInformations.putString("infoStName",infoStName);
        stationInformations.putString("infoStation",infoStation);

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
                    int stCleanToilet = value.getStCleanToilet();
                    double stPrice = value.getStationPrice();
                    double stLPG = value.getStLPG();
                    double stDiesel = value.getStDiesel();
                    double stGasoline = value.getStGasoline();
                    Double stPositionX = value.getStPositionX();
                    Double stPositionY = value.getStPositionY();
                    String stInfo = value.getStationInfo();
                    String stPhoto = value.getStPhoto();
                    String stLogo = value.getStationLogo();

                    stPrice = stGasoline;

                    mostUseList.add(new StationModel(stLogo,stPhoto,stationName,stCleanToilet,stPrice,stDiesel,stGasoline,stLPG,stInfo,stPositionX,stPositionY));

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

                    stMPrice = "Fiyat: " + String.valueOf(stPrice);

                    tvNearPrice.setText(stMPrice);
                    tvNearName.setText(stationName);
                    tvNearDistance.setText("Mesafe: 1.5km");
                    Picasso.get().load(stLogo).into(imgNearLogo);

                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


}

