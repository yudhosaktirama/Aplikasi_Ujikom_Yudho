package com.example.aplikasiujikomyudho.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.aplikasiujikomyudho.R;

public class HomeActivity extends AppCompatActivity {
    private ImageView lihatData;
    private ImageView inputData;
    private ImageView informasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lihatData = findViewById(R.id.lihatData);
        inputData = findViewById(R.id.inputData);
        informasi = findViewById(R.id.informasi);


        informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindahInformasi = new Intent(HomeActivity.this, InformasiActivity.class);
                startActivity(pindahInformasi);
            }
        });

        inputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindahInputData = new Intent(HomeActivity.this, InputDataActivity.class);
                startActivity(pindahInputData);
            }
        });

        lihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindahLihatData = new Intent(HomeActivity.this, LihatDataActivity.class);
                startActivity(pindahLihatData);
            }
        });


    }
}