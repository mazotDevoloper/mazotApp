package com.mazotapp.mazotapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mazotapp.mazotapp.R;
import com.mazotapp.mazotapp.models.StationModel;
import com.mazotapp.mazotapp.models.UserModel;

public class AdminActivity extends AppCompatActivity {

    EditText edtName,edtDiesel,edtLPG,edtGasoline,edtToilet,edtPositionX,edtPositionY,edtLogo,edtPhoto,edtInfo;
    Button btnAddStation;

    Double positionX,positionY,diesel,gasoline,LPG;

    int toilet;

    DatabaseReference databaseReference;
    FirebaseDatabase database;

    String logoURL,photoURL,name,info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Stations");

        edtDiesel = findViewById(R.id.adDieselPrice);
        edtGasoline = findViewById(R.id.adGasolinePrice);
        edtLPG = findViewById(R.id.adLPGPrice);

        edtInfo = findViewById(R.id.adInfo);
        edtLogo = findViewById(R.id.adLogo);
        edtPhoto = findViewById(R.id.adPhoto);
        edtName = findViewById(R.id.adName);
        edtToilet = findViewById(R.id.adToilet);
        edtPositionX = findViewById(R.id.adPositionX);
        edtPositionY = findViewById(R.id.adPositionY);

        btnAddStation = findViewById(R.id.btnAddStation);

        btnAddStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //verileri edittextlerden çekiyorum

                name = edtName.getText().toString();
                photoURL = edtPhoto.getText().toString();
                logoURL = edtLogo.getText().toString();
                info = edtInfo.getText().toString();

                positionX = Double.valueOf(edtPositionX.getText().toString());
                positionY = Double.valueOf(edtPositionY.getText().toString());

                diesel = Double.valueOf(edtDiesel.getText().toString());
                gasoline = Double.valueOf(edtGasoline.getText().toString());
                LPG = Double.valueOf(edtLPG.getText().toString());

                toilet = Integer.valueOf(edtToilet.getText().toString());

                StationModel addStation = new StationModel(logoURL,photoURL,name,toilet,0.0,diesel,gasoline,LPG,info,positionX,positionY);

                databaseReference.child(name).setValue(addStation);

            }
        });



    }
}
