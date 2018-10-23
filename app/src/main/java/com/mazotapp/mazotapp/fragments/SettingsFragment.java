package com.mazotapp.mazotapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mazotapp.mazotapp.activities.FeedbackActivity;
import com.mazotapp.mazotapp.activities.FindStationActivity;
import com.mazotapp.mazotapp.activities.LoginActivity;
import com.mazotapp.mazotapp.R;


public class SettingsFragment extends Fragment {

    Button btnLogOut,btnShareApp,btnFeedback,btnCallServices;
    FirebaseAuth auth;
    FirebaseUser user;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnLogOut = view.findViewById(R.id.btnLogOut);
        btnShareApp = view.findViewById(R.id.btnShareApp);
        btnFeedback = view.findViewById(R.id.btnFeedback);
        btnCallServices =view.findViewById(R.id.btnCallServices);

        btnCallServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumber = "+905393819792";
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));

            }
        });

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFeedback = new Intent(v.getContext(), FeedbackActivity.class);
                startActivity(intentFeedback);
            }
        });

        btnShareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "http://mazotapp.com");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth = FirebaseAuth.getInstance();
                user = auth.getCurrentUser();
                auth.signOut();
                Intent intentLogin = new Intent(getActivity(), LoginActivity.class);
                startActivity(intentLogin);
            }
        });


    }
}
