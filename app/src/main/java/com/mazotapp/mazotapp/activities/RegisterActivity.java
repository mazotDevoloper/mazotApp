package com.mazotapp.mazotapp.activities;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mazotapp.mazotapp.R;
import com.mazotapp.mazotapp.models.UserModel;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputPhoneNumber, inputName, inputSurname;
    private FirebaseAuth auth;
    private Button registerBtn;
    private CheckBox cbContract;
    private TextView loginT,contractReadT;
    String id,cityName;

    GPSTracker gps;

    double latitude,longitude;

    DatabaseReference databaseReference;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
            finish();
        }

        gps = new GPSTracker(RegisterActivity.this);
        
        cbContract = findViewById(R.id.cbContract);

        contractReadT = findViewById(R.id.contractReadT);

        inputEmail = findViewById(R.id.etEmail);
        inputPassword = findViewById(R.id.etPassword);
        inputPhoneNumber = findViewById(R.id.etMobileNumber);
        inputName = findViewById(R.id.etName);
        inputSurname = findViewById(R.id.etSurname);
        registerBtn = findViewById(R.id.btnRegister);
        loginT = findViewById(R.id.tLogin);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Users");

        contractReadT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentContract = new Intent(RegisterActivity.this,ContractActivity.class);
                startActivity(intentContract);
            }
        });

            registerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    gps = new GPSTracker(view.getContext());

                    if(gps.isNetworkEnabled){
                                if(cbContract.isChecked()){

                                    latitude = gps.getLatitude();
                                    longitude = gps.getLongitude();

                                    cityName = null;
                                    Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                                    List<Address> addresses;
                                    try {
                                        addresses = gcd.getFromLocation(latitude, longitude, 1);
                                        if (addresses.size() > 0) {
                                            cityName = addresses.get(0).getAdminArea();      //il
                                            //cityName = addresses.get(0).getCountryName();
                                            // cityName = addresses.get(0).getSubLocality();   //mahalle
                                            //cityName = addresses.get(0).getLocality();      //ilçe
                                        }
                                    }
                                    catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    final String email = inputEmail.getText().toString().trim();
                                    final String password = inputPassword.getText().toString().trim();
                                    final String phoneNumber = inputPhoneNumber.getText().toString().trim();
                                    final String name = inputName.getText().toString().trim();
                                    final String surname = inputSurname.getText().toString().trim();

                                    final Bundle userInformation = new Bundle();

                                    userInformation.putString("userPhoneNumber", phoneNumber);

                                    if (!name.isEmpty()) {
                                        if(!surname.isEmpty()){
                                            if(!email.isEmpty()){
                                                if(!password.isEmpty()){
                                                    if(!phoneNumber.isEmpty()){
                                                        auth.createUserWithEmailAndPassword(email, password)
                                                                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                                        if (!task.isSuccessful()) {
                                                                            Toast.makeText(RegisterActivity.this, "Hesabınız oluşturulamadı", Toast.LENGTH_LONG).show();
                                                                            inputEmail.setText("");
                                                                            inputName.setText("");
                                                                            inputSurname.setText("");
                                                                            inputPhoneNumber.setText("");
                                                                            inputPassword.setText("");
                                                                        } else {
                                                                            id = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                                                            UserModel user = new UserModel(name, surname, phoneNumber, email, password,longitude, latitude,cityName);

                                                                            databaseReference.child(id).setValue(user);

                                                                            Intent homeActivity = new Intent(RegisterActivity.this, HomeActivity.class);
                                                                            //verifyPhoneNumber.putExtras(userInformation);
                                                                            //startActivity(verifyPhoneNumber);
                                                                            startActivity(homeActivity);
                                                                            finish();
                                                                        }
                                                                    }
                                                                });
                                                    }else {
                                                        Toast.makeText(RegisterActivity.this, "Lütfen telefon numaranızı giriniz", Toast.LENGTH_LONG).show();
                                                    }
                                                }else {
                                                    Toast.makeText(RegisterActivity.this, "Lütfen parolanızı giriniz", Toast.LENGTH_LONG).show();
                                                }
                                            }else {
                                                Toast.makeText(RegisterActivity.this, "Lütfen e-postanızı giriniz", Toast.LENGTH_LONG).show();
                                            }
                                        }else {
                                            Toast.makeText(RegisterActivity.this, "Lütfen soyisminizi giriniz", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Lütfen isminizi giriniz", Toast.LENGTH_LONG).show();
                                    }

                                } else {
                                    Toast.makeText(RegisterActivity.this, "Sözleşmenin şartlarını kabul etmelisiniz.", Toast.LENGTH_SHORT).show();

                                }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Lütfen internet bağlantınızı kontrol  edin", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            loginT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intentLogin);
                }
            });
        }
    }



