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
import com.mazotapp.mazotapp.activities.RegisterActivity;


public class SettingsFragment extends Fragment {

    Button btnLogOut,btnShareApp,btnFeedback;
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
                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.mazotapp.mazotapp&hl=tr");
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
                Intent intentRegister = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intentRegister);
            }
        });


    }
}
