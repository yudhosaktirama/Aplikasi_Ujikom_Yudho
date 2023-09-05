package com.example.aplikasiujikomyudho.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aplikasiujikomyudho.R;
import com.example.aplikasiujikomyudho.SplashActivity.loginSplashScreenActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText editEmail;
    private EditText editPassword;
    private EditText editNama;

    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editTextTextEmailAddress);
        editPassword = findViewById(R.id.editTextTextPassword);
        editNama = findViewById(R.id.loginMasukkanNama);
        buttonLogin = findViewById(R.id.button);

        Validasi();




    }

    String tangkapNama(){
        String simpanNama = editNama.getText().toString();

        return  simpanNama;
    }

    void Validasi(){
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editEmail.getText().toString().isEmpty()){
                    editEmail.setError("Silahkan Masukkan Email Anda");
                    return;
                }

                if (editNama.getText().toString().isEmpty()){
                    editNama.setError("Silahkan Masukkan Nama Anda");
                    return;
                }

                if (editPassword.getText().toString().isEmpty()){
                    editPassword.setError("Silahkan Masukkan Password Anda");
                    return;
                }



                Intent pindahLogin = new Intent(LoginActivity.this, loginSplashScreenActivity.class);
                pindahLogin.putExtra("userName", tangkapNama());
                startActivity(pindahLogin);

            }
        });


    }
}