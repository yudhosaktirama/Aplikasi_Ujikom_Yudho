package com.example.aplikasiujikomyudho.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplikasiujikomyudho.Database.DatabaseHelper;
import com.example.aplikasiujikomyudho.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UpdateDataActivity extends AppCompatActivity {
    private EditText etNama;
    private EditText etNIM;
    private EditText etGender;
    private EditText etTTL;

    private FloatingActionButton btnKembali;

    private FloatingActionButton btnUpdate;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        etNama = findViewById(R.id.updateNama);
        etNIM =findViewById(R.id.updateNIM);
        etGender = findViewById(R.id.updateGender);
        etTTL = findViewById(R.id.updateTTL);
        btnKembali = findViewById(R.id.floatingBackUpdate);
        btnUpdate = findViewById(R.id.floatingUpdateData);

        dbHelper = new DatabaseHelper(this);

        setTeksAwal();

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(UpdateDataActivity.this, LihatDataActivity.class);
                startActivity(kembali);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validasi();
            }
        });


    }


    void setTeksAwal(){
        etNama.setText(getIntent().getStringExtra("nama"));
        etNIM.setText(getIntent().getStringExtra("nim"));
        etGender.setText(getIntent().getStringExtra("gender"));
        etTTL.setText(getIntent().getStringExtra("ttl"));
    }

    void Validasi(){
        if (etNama.getText().toString().isEmpty()){
            etNama.setError("Masukkan Nama");
            return;
        }
        if (etNIM.getText().toString().isEmpty()){
            etNIM.setError("Masukkan NIM");
            return;
        }
        if (etGender.getText().toString().isEmpty()){
            etGender.setError("Masukkan Gender");
            return;
        }
        if (etTTL.getText().toString().isEmpty()){
            etTTL.setError("Masukkan TTL");
            return;
        }
        int idku = getIntent().getIntExtra("id",1);
        Integer coba = new Integer(idku);
        String idjadi = coba.toString();
        String nama = etNama.getText().toString();
        String NIM = etNIM.getText().toString();
        String gender = etGender.getText().toString();
        String ttl = etTTL.getText().toString();
        dbHelper.updateMahasiswa(idjadi,nama,NIM,gender,ttl);
        Toast.makeText(UpdateDataActivity.this, "Update Data Berhasil", Toast.LENGTH_SHORT).show();

    }




}