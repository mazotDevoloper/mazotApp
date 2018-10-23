package com.mazotapp.mazotapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mazotapp.mazotapp.R;
import com.mazotapp.mazotapp.models.UserModelRegister;

public class RegisterActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword,inputPhoneNumber,inputName,inputSurname;
    private FirebaseAuth auth;
    private Button registerBtn;
    private TextView loginT;
    private static final String TAG = "AddToDatabase";
    String id;

    DatabaseReference databaseReference;
    FirebaseDatabase database;




    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
            finish();
        }

        inputEmail = findViewById(R.id.etEmail);
        inputPassword = findViewById(R.id.etPassword);
        inputPhoneNumber = findViewById(R.id.etMobileNumber);
        inputName = findViewById(R.id.etName);
        inputSurname = findViewById(R.id.etSurname);
        registerBtn =  findViewById(R.id.btnRegister);
        loginT = findViewById(R.id.tLogin);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Users");


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override  public void onClick(View view) {
                 final String email = inputEmail.getText().toString().trim();
                 final String password = inputPassword.getText().toString().trim();
                 final String phoneNumber = inputPhoneNumber.getText().toString().trim();
                 final String name = inputName.getText().toString().trim();
                 final String surname = inputSurname.getText().toString().trim();

                final Bundle userInformation = new Bundle();

                userInformation.putString("userPhoneNumber",phoneNumber);

                if (!password.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty() && !name.isEmpty() && !surname.isEmpty()) {
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(
                                                RegisterActivity.this,
                                                "Plakanız oluşturulamadı :(",
                                                Toast.LENGTH_LONG).show();
                                        inputEmail.setText("");
                                        inputName.setText("");
                                        inputSurname.setText("");
                                        inputPhoneNumber.setText("");
                                        inputPassword.setText("");
                                    } else {
                                        id = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                        UserModelRegister registeredUser = new UserModelRegister(name,surname,phoneNumber,email,password);

                                        databaseReference.child(id).setValue(registeredUser);

                                        Intent verifyPhoneNumber = new Intent(RegisterActivity.this,VerifyPhoneNumberActivity.class);
                                        verifyPhoneNumber.putExtras(userInformation);
                                        startActivity(verifyPhoneNumber);
                                        finish();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(
                            RegisterActivity.this,
                            "Boş depoyla gidemessin dostum :(",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        loginT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intentLogin);
            }
        });
    }
}

