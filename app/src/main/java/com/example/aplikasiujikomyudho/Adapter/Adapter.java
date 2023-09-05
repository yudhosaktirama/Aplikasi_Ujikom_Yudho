package com.example.aplikasiujikomyudho.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiujikomyudho.Database.DatabaseHelper;
import com.example.aplikasiujikomyudho.Activity.DetailActivity;
import com.example.aplikasiujikomyudho.Model.ModelClass;
import com.example.aplikasiujikomyudho.R;
import com.example.aplikasiujikomyudho.Activity.UpdateDataActivity;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context konteks;
    private List<ModelClass> modelClassList;



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final ModelClass modelku = modelClassList.get(position);
        holder.nama.setText(modelku.getNama());
        holder.nim.setText(modelku.getNIM());

        if (position %2 == 0){
            holder.nama.setTextColor(Color.WHITE);
            holder.nim.setTextColor(Color.WHITE);
            holder.layouting.setBackgroundColor(Color.BLACK);

        }else{
            holder.nama.setTextColor(Color.WHITE);
            holder.nim.setTextColor(Color.WHITE);
            holder.layouting.setBackgroundColor(Color.GRAY);
        }

        holder.tombolPilihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialogku = new Dialog(konteks);
                dialogku.setTitle("Silahkan Pilih Opsi");
                dialogku.setContentView(R.layout.dialog_layout);
                TextView teksDetail;
                TextView teksUpdate;
                TextView teksDelete;
                teksDetail = dialogku.findViewById(R.id.detailData);
                teksUpdate = dialogku.findViewById(R.id.updateData);
                teksDelete = dialogku.findViewById(R.id.hapusData);

                DisplayMetrics metrics = konteks.getResources().getDisplayMetrics();
                int width = metrics.widthPixels;
                dialogku.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialogku.create();
                dialogku.show();

                teksDetail.setOnClickListener(view1 -> {
                    Intent pindahDetail = new Intent(konteks, DetailActivity.class);
                    pindahDetail.putExtra("nama",modelku.getNama());
                    pindahDetail.putExtra("nim",modelku.getNIM());
                    pindahDetail.putExtra("gender",modelku.getGender());
                    pindahDetail.putExtra("ttl",modelku.getTTL());
                    pindahDetail.putExtra("id", modelku.getID());
                    konteks.startActivity(pindahDetail);
                });

                teksUpdate.setOnClickListener(view12 -> {
                    Intent pindahUpdate = new Intent(konteks, UpdateDataActivity.class);
                    pindahUpdate.putExtra("nama",modelku.getNama());
                    pindahUpdate.putExtra("nim",modelku.getNIM());
                    pindahUpdate.putExtra("gender",modelku.getGender());
                    pindahUpdate.putExtra("ttl",modelku.getTTL());
                    pindahUpdate.putExtra("id", modelku.getID());
                    konteks.startActivity(pindahUpdate);

                });

                teksDelete.setOnClickListener(view13 -> {
                    DatabaseHelper databaseHelper = new DatabaseHelper(konteks);



                    Integer integer = new Integer(modelku.getID());
                    showDialogDelete(integer.toString());
                    notifyDataSetChanged();



                });


            }
        });



    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    public Adapter(Context context, List<ModelClass> modelClassList){
        this.konteks = context;
        this.modelClassList = modelClassList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nama;
        public TextView nim;

        public Button tombolPilihan;

        public LinearLayout layouting;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.teksnama);
            nim = itemView.findViewById(R.id.teksnim);
            tombolPilihan = itemView.findViewById(R.id.buttonpilihan);
            layouting = itemView.findViewById(R.id.layputing);

        }
    }

    private void showDialogDelete(String idku){
        DatabaseHelper databaseHelper = new DatabaseHelper(konteks);
        final AlertDialog.Builder dialogDelete=new AlertDialog.Builder(konteks);
        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure to delete?");
        // final EditText idText=findViewById(R.id.idET);
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    databaseHelper.hapusMahasiswa(idku);

                    Toast.makeText(konteks,"Deleted data",Toast.LENGTH_SHORT).show();


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
