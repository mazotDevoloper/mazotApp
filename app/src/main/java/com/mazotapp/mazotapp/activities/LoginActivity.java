package com.mazotapp.mazotapp.activities;

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
import com.mazotapp.mazotapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private Button btnLogin;
    private ProgressDialog PD;
    private TextView tRegister;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        PD = new ProgressDialog(this);
        PD.setMessage("Yükleniyor...");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);

        auth = FirebaseAuth.getInstance();

        inputEmail = findViewById(R.id.etEmail);
        inputPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tRegister = findViewById(R.id.tRegister);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                final String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();

                try {

                    if (password.length() > 0 && email.length() > 0) {
                        PD.show();
                        auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(
                                                    LoginActivity.this,
                                                    "Aracınıza giriş yapılamadı :(",
                                                    Toast.LENGTH_LONG).show();
                                        } else {
                                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                            startActivity(intent);
                                            //finish();
                                        }
                                        PD.dismiss();
                                    }
                                });
                    } else {
                        Toast.makeText(
                                LoginActivity.this,
                                "Boş depoyla gidemessin dostum :(",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        tRegister.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
            }
        });
    }

    @Override    protected void onResume() {
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
        super.onResume();
    }
}
