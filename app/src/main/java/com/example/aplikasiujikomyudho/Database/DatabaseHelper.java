package com.example.aplikasiujikomyudho.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aplikasiujikomyudho.Model.ModelClass;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mahasiswa.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "biodata";
    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_NIM = "nim";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_TTL = "ttl";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE biodata(id integer primary key, nama text, nim text,gender text,ttl text);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS biodata;");
        onCreate(sqLiteDatabase);
    }

    public long tambahkanMahasiswa(int id, String nama, String nim, String gender, String ttl){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        contentValues.put(KEY_ID, id);
        contentValues.put(KEY_NAMA, nama);
        contentValues.put(KEY_NIM, nim);
        contentValues.put(KEY_GENDER, gender);
        contentValues.put(KEY_TTL, ttl);
        long tambah = db.insert("biodata",null,contentValues);
        return  tambah;
    }

    public void hapusMahasiswa(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE,"id=?",new String[]{id});
        db.close();
    }

    public boolean updateMahasiswa(String id, String nama, String nim, String gender, String ttl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAMA,nama);
        contentValues.put(KEY_NIM,nim);
        contentValues.put(KEY_GENDER,gender);
        contentValues.put(KEY_TTL,ttl);
        db.update(TABLE,contentValues, "id = ?",new String[]{id});
        return true;
    }



    @SuppressLint("Range")
    public List<ModelClass> getSemuaMahasiswa(){
        List<ModelClass> mahasiswa = new ArrayList<>();
        String sql = "SELECT * FROM biodata";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = this.getWritableDatabase().rawQuery(sql,null);

        if (cursor.moveToFirst()){
            do {
                mahasiswa.add(new ModelClass(
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))),
                        cursor.getString(cursor.getColumnIndex(KEY_NAMA)),
                        cursor.getString(cursor.getColumnIndex(KEY_NIM)),
                        cursor.getString(cursor.getColumnIndex(KEY_GENDER)),
                        cursor.getString(cursor.getColumnIndex(KEY_TTL))
                ));
            }while (cursor.moveToNext());
        }
        db.close();
        return mahasiswa;
    }
}
