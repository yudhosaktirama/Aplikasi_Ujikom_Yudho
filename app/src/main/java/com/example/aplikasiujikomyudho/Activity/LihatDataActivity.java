package com.example.aplikasiujikomyudho.Activity;

import static android.widget.LinearLayout.VERTICAL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aplikasiujikomyudho.Adapter.Adapter;
import com.example.aplikasiujikomyudho.Database.DatabaseHelper;
import com.example.aplikasiujikomyudho.Model.ModelClass;
import com.example.aplikasiujikomyudho.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LihatDataActivity extends AppCompatActivity {

    private DatabaseHelper db_controller;
    private List<ModelClass> modelClassList = new ArrayList<ModelClass>();
    private RecyclerView recyclerView;
    private Adapter adapter;

    private FloatingActionButton btnKembali;
    private FloatingActionButton btnTambah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        db_controller = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerView);
        btnKembali = findViewById(R.id.btnBackLihatData);
        btnTambah = findViewById(R.id.btnInsertLihatData);


        modelClassList.addAll(db_controller.getSemuaMahasiswa());

        adapter = new Adapter(this,modelClassList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindahHome = new Intent(LihatDataActivity.this, HomeActivity.class);
                startActivity(pindahHome);
            }
        });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindahInsert = new Intent(LihatDataActivity.this, InputDataActivity.class);
                startActivity(pindahInsert);
            }
        });


    }
}