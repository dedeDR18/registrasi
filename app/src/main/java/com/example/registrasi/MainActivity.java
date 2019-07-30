package com.example.registrasi;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private SqliteHelper sqliteHelper;
    TextView tampilNamaLengkap;
    TextView tampilTempatLahir;
    TextView tampilTanggalLahir;
    TextView tampilJenisKelamin;
    TextView tampilNomorIdentitas;
    TextView tampilAlamat;
    TextView tampilKodePos;
    TextView tampilNomorTelpon;
    TextView tampilEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampildata);
        initViews();
        getData();
    }


    //inisialisasi
    private void initViews() {
        tampilNamaLengkap = (TextView) findViewById(R.id.TextViewNamaLengkap);
        tampilTempatLahir = (TextView) findViewById(R.id.TextViewTempatLahir);
        tampilTanggalLahir = (TextView) findViewById(R.id.TextViewTanggalLahir);
        tampilJenisKelamin = (TextView) findViewById(R.id.TextViewJenisKelamin);
        tampilNomorIdentitas = (TextView) findViewById(R.id.TextViewNomorIdentitas);
        tampilAlamat = (TextView) findViewById(R.id.TextViewAlamat);
        tampilKodePos = (TextView) findViewById(R.id.TextViewKodePos);
        tampilNomorTelpon = (TextView) findViewById(R.id.TextViewNomorTelpon);
        tampilEmail = (TextView) findViewById(R.id.TextViewEmail);

        sqliteHelper = new SqliteHelper(this);
    }

    //Mengambil data terkahir yang dimasukan
    private void getData(){
        SQLiteDatabase database = sqliteHelper.getReadableDatabase();
        String sql="SELECT * FROM "+ SqliteHelper.TABLE_USERS;

        //MEMBUAT KURSOR UNTUK MEMBUKA DATABASE
        Cursor cursor = database.rawQuery(sql,null);
        cursor.getCount();
        cursor.moveToLast();

        tampilNamaLengkap.setText(cursor.getString(1));
        tampilTempatLahir.setText(cursor.getString(2));
        tampilTanggalLahir.setText(cursor.getString(3));
        tampilJenisKelamin.setText(cursor.getString(4));
        tampilNomorIdentitas.setText(cursor.getString(5));
        tampilAlamat.setText(cursor.getString(6));
        tampilKodePos.setText(cursor.getString(7));
        tampilNomorTelpon.setText(cursor.getString(8));
        tampilEmail.setText(cursor.getString(9));
    }



}

