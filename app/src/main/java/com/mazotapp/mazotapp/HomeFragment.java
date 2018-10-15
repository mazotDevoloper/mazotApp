package com.mazotapp.mazotapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class HomeFragment extends Fragment {

    Button btnSignOut, btnGoFindStation;
    FirebaseAuth auth;
    FirebaseUser user;
    Intent intentInfoSt;
    String infoStation,infoStName;
    Bundle stationInformations;
    int infoStPhoto;
    double infoStPositionX,infoStPositionY;
    ImageView imgLogout;
    CardView cardNear,cardMostUse1,cardMostUse2,cardMostUse3;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home,container,false);

        cardNear = view.findViewById(R.id.card_nearStation);
        cardMostUse1 = view.findViewById(R.id.card_mostUseStations1);
        cardMostUse2 = view.findViewById(R.id.card_mostUseStations2);
        cardMostUse3 = view.findViewById(R.id.card_mostUseStations3);

        imgLogout = view.findViewById(R.id.imgLogout);
        btnGoFindStation = view.findViewById(R.id.btnGoFindStation);

        intentInfoSt = new Intent(view.getContext(),InformationStationActivity.class);

        stationInformations = new Bundle();


        cardNear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoStation = "OPET Şişli akaryakıt istasyonu, 24 saat hizmet vermektedir. Akaryakıt istasyonunda market, hava ve su makinesi, tuvalet, engelli tuvaleti, bebek bakım ünitesi bulunmaktadır.";
                infoStName = "OPET  GÜRCAN Petrol";
                infoStPositionX = 41.048434;
                infoStPositionY = 28.984981;
                infoStPhoto = R.drawable.opet_gurcan_photo;
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
                infoStPhoto = R.drawable.shell_shell_photo;
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
                infoStPhoto = R.drawable.petrolofisi_orsay_photo;
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
                infoStPhoto = R.drawable.aytemiz_sisli_photo;
                intent(infoStPositionX,infoStPositionY,infoStPhoto,infoStName,infoStation);

            }
        });

        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth = FirebaseAuth.getInstance();
                user = auth.getCurrentUser();
                auth.signOut();
                if (auth.getCurrentUser() == null) {
                    Intent intentLogin = new Intent(view.getContext(), LoginActivity.class);
                    startActivity(intentLogin);
                }
            }
        });

        btnGoFindStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFindStation = new Intent(v.getContext(), FindStationActivity.class);
                startActivity(intentFindStation);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
     //               + " must implement OnFragmentInteractionListener");
       }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void intent(double infoStPositionX,double infoStPositionY,int infoStPhoto,String infoStName,String infoStation){

        stationInformations.putDouble("infoStPositionX",infoStPositionX);
        stationInformations.putDouble("infoStPositionY",infoStPositionY);
        stationInformations.putInt("infoStPhoto",infoStPhoto);
        stationInformations.putString("infoStName",infoStName);
        stationInformations.putString("infoStation",infoStation);

        intentInfoSt.putExtras(stationInformations);

        startActivity(intentInfoSt);
    }
    
}

