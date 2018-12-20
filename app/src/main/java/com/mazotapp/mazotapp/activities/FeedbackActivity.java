package com.mazotapp.mazotapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mazotapp.mazotapp.R;
import com.mazotapp.mazotapp.models.StationModel;
import com.mazotapp.mazotapp.models.UserModel;

public class FeedbackActivity extends AppCompatActivity {

    FirebaseDatabase database;
    EditText etEmail,etMessage;
    Button btnFeedback;
    ImageView backIcon;
    String id,email;


    GPSTracker gps;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        etEmail =findViewById(R.id.etEmail);
        etMessage = findViewById(R.id.etMessage);

        btnFeedback = findViewById(R.id.btnFeedback);


        backIcon = findViewById(R.id.imgBack_icon);


        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        etEmail.setText(email);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gps = new GPSTracker(FeedbackActivity.this);

                if(gps.isNetworkEnabled){
                    database = FirebaseDatabase.getInstance();
                    databaseReference = database.getReference("Feedbacks");

                    id = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    DatabaseReference referenceYeni = databaseReference.child(id);

                    referenceYeni.child("Email").setValue(etEmail.getText().toString());
                    referenceYeni.child("Message").setValue(etMessage.getText().toString());
                    Toast.makeText(FeedbackActivity.this,"Geri bildiriminiz için teşekkür ederiz", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(FeedbackActivity.this,HomeActivity.class));
                    finish();
                }else{
                    Toast.makeText(FeedbackActivity.this, "Lütfen internet bağlantınızı kontrol edin", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
