package com.mazotapp.mazotapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword,inputAgainPassword,inputPhoneNumber,inputNameSurname;
    private FirebaseAuth auth;
    private Button registerBtn;
    private TextView loginT;
    private ProgressDialog PD;
    private static final String TAG = "AddToDatabase";



    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        PD = new ProgressDialog(this);
        PD.setMessage("Yükleniyor...");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
            finish();
        }

        inputEmail = findViewById(R.id.etEmail);
        inputPassword = findViewById(R.id.etPassword);
        inputAgainPassword = findViewById(R.id.etAgainPassword);
        inputPhoneNumber = findViewById(R.id.etMobileNumber);
        inputNameSurname = findViewById(R.id.etNameAndSurname);
        registerBtn =  findViewById(R.id.btnRegister);
        loginT = findViewById(R.id.tLogin);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override  public void onClick(View view) {
                final String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();
                final String againPassword = inputAgainPassword.getText().toString().trim();
                final String phoneNumber = inputPhoneNumber.getText().toString().trim();
                final String nameAndSurname = inputNameSurname.getText().toString().trim();

                final Bundle userInformation = new Bundle();

                userInformation.putString("userPhoneNumber",phoneNumber);


                try {
                    if (!password.isEmpty() && !againPassword.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty() && !nameAndSurname.isEmpty()) {
                        if (password.equals(againPassword)) {

                            PD.show();
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
                                                inputAgainPassword.setText("");
                                                inputNameSurname.setText("");
                                                inputPhoneNumber.setText("");
                                                inputPassword.setText("");
                                            } else {
                                                Intent verifyPhoneNumber = new Intent(RegisterActivity.this,VerifyPhoneNumberActivity.class);
                                                verifyPhoneNumber.putExtras(userInformation);
                                                startActivity(verifyPhoneNumber);
                                                finish();
                                            }
                                            PD.dismiss();
                                        }
                                    });
                        }else{
                            Toast.makeText(RegisterActivity.this, "Şifre ve şifre tekrarı uyuşmamaktadır.Lütfen tekrar deneyiniz.", Toast.LENGTH_SHORT).show();
                            inputPassword.setText("");
                            inputAgainPassword.setText("");
                        }

                        } else {
                            Toast.makeText(
                                    RegisterActivity.this,
                                    "Boş depoyla gidemessin dostum :(",
                                    Toast.LENGTH_LONG).show();
                        }
                    } catch(Exception e){
                        e.printStackTrace();
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

