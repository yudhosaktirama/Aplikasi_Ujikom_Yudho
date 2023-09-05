package com.example.aplikasiujikomyudho.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasiujikomyudho.Database.DatabaseHelper;
import com.example.aplikasiujikomyudho.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailActivity extends AppCompatActivity {
    private TextView tvNama;

    private TextView cobaGan;
    private TextView tvNIM;
    private TextView tvGender;
    private TextView tvTTL;
    private FloatingActionButton btnKembali;
    private FloatingActionButton btnHapus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNama = findViewById(R.id.tvNama);
        tvNIM = findViewById(R.id.tvNim);
        tvGender = findViewById(R.id.tvGender);
        tvTTL = findViewById(R.id.tvTTL);
        btnKembali = findViewById(R.id.floatingActionButton2);
        btnHapus = findViewById(R.id.floatingActionButton3);

        LoadData();



        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindahLihatData = new Intent(DetailActivity.this, LihatDataActivity.class);
                startActivity(pindahLihatData);
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int coba = getIntent().getIntExtra("id",1);
                Integer integer = new Integer(coba);
                showDialogDelete(integer.toString());

            }
        });
    }

    void LoadData(){
        tvNama.setText(getIntent().getStringExtra("nama"));
        tvNIM.setText(getIntent().getStringExtra("nim"));
        tvGender.setText(getIntent().getStringExtra("gender"));
        tvTTL.setText(getIntent().getStringExtra("ttl"));
    }

    private void showDialogDelete(String idku){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        final AlertDialog.Builder dialogDelete=new AlertDialog.Builder(this);
        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure to delete?");
        // final EditText idText=findViewById(R.id.idET);
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    databaseHelper.hapusMahasiswa(idku);
                    Toast.makeText(DetailActivity.this,"Deleted data",Toast.LENGTH_SHORT).show();
                    Intent pindahLIhatData = new Intent(DetailActivity.this,LihatDataActivity.class);
                    pindahLIhatData.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(pindahLIhatData);


                }catch (Exception e){
                    Log.e("error",e.getMessage());

                }

            }
        });
        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogDelete.show();
    }
}