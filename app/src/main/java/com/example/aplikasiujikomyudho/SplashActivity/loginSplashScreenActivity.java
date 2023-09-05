package com.example.aplikasiujikomyudho.SplashActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.aplikasiujikomyudho.Activity.HomeActivity;
import com.example.aplikasiujikomyudho.R;

public class loginSplashScreenActivity extends AppCompatActivity {
    private TextView teksNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_splash_screen);

        teksNama = findViewById(R.id.namaPengguna);

        teksNama.setText(getIntent().getStringExtra("userName"));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(loginSplashScreenActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }


}