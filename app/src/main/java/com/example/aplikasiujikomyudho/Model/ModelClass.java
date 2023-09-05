package com.example.aplikasiujikomyudho.Model;

public class ModelClass {
    public int idku;
    public String Nama;
    public String NIM;
    public String Gender;
    public String TTL;

    public ModelClass(int idsaya, String nama, String nim, String gender,String ttl){
        this.idku = idsaya;
        this.Nama = nama;
        this.NIM = nim;
        this.Gender = gender;
        this.TTL = ttl;
    }

    public int getID(){
        return idku;
    }

    public String getNama(){
        return Nama;
    }

    public String getNIM(){
        return NIM;
    }
    public String getGender(){
        return Gender;
    }
    public String getTTL(){
        return TTL;
    }





}
