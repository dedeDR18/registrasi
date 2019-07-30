package com.example.registrasi;

public class User {
    public String id;
    public String namalengkap;
    public String tempatlahir;
    public String tanggallahir;
    public String jeniskelamin;
    public String nomoridentitas;
    public String alamat;
    public String kodepos;
    public String nomortelpon;
    public String email;
    public String password;


    public User(String id, String namalengkap, String tempatlahir, String tanggallahir, String jeniskelamin, String nomoridentitas, String alamat, String kodepos, String nomortelpon, String email, String password) {
        this.id = id;
        this.namalengkap = namalengkap;
        this.tempatlahir = tempatlahir;
        this.tanggallahir = tanggallahir;
        this.jeniskelamin = jeniskelamin;
        this.nomoridentitas = nomoridentitas;
        this.alamat = alamat;
        this.kodepos = kodepos;
        this.nomortelpon = nomortelpon;
        this.email = email;
        this.password = password;
    }

}
