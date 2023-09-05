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

public class InputDataActivity extends AppCompatActivity {

    private DatabaseHelper databaseku;
    private EditText editId;
    private EditText editNama;
    private EditText editNIM;
    private EditText editGender;
    private EditText editTTL;
    private FloatingActionButton btnSimpan;

    private FloatingActionButton btnKembali;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        editId = findViewById(R.id.idMahasiswa);
        editNama = findViewById(R.id.namaMahasiswa);
        editNIM = findViewById(R.id.nimMahasiswa);
        editGender = findViewById(R.id.jenisKelamin);
        editTTL = findViewById(R.id.ttlMahasiswa);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnKembali = findViewById(R.id.buttonBackInputData);

        databaseku = new DatabaseHelper(this);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Validasi();

            }
        });

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(InputDataActivity.this, HomeActivity.class);
                startActivity(kembali);
            }
        });




    }

    void Validasi(){
        if (editId.getText().toString().isEmpty()){
            editId.setError("Masukkan ID");
            return;
        }
        if (editNama.getText().toString().isEmpty()){
            editNama.setError("Masukkan Nama");
            return;
        }
        if (editNIM.getText().toString().isEmpty()){
            editNIM.setError("Masukkan NIM");
            return;
        }
        if (editGender.getText().toString().isEmpty()){
            editGender.setError("Masukkan Gender");
            return;
        }
        if (editTTL.getText().toString().isEmpty()){
            editTTL.setError("Masukkan TTL");
            return;
        }
        int idku = Integer.parseInt(editId.getText().toString());
        String nama = editNama.getText().toString();
        String NIM = editNIM.getText().toString();
        String gender = editGender.getText().toString();
        String ttl = editTTL.getText().toString();
        databaseku.tambahkanMahasiswa(idku,nama,NIM,gender,ttl);
        Toast.makeText(InputDataActivity.this, "Tambah Data Berhasil", Toast.LENGTH_SHORT).show();

    }
}