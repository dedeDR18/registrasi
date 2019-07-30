package com.example.registrasi;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;


public class registerActivity extends AppCompatActivity {

    //edit Text
    EditText editTextNamaLengkap;
    EditText editTextTempatLahir;
    EditText editTextTanggalLahir;
    EditText editTextJenisKelamin;
    EditText editTextNomorIdentitas;
    EditText editTextAlamat;
    EditText editTextKodePos;
    EditText editTextNomorTelpon;
    EditText editTextEmail;
    EditText editTextPassword;

    //text input layout
    TextInputLayout textInputLayoutNamaLengkap;
    TextInputLayout textInputLayoutTempatLahir;
    TextInputLayout textInputLayoutTanggalLahir;
    TextInputLayout textInputLayoutJenisKelamin;
    TextInputLayout textInputLayoutNomorIdentitas;
    TextInputLayout textInputLayoutAlamat;
    TextInputLayout textInputLayoutKodePos;
    TextInputLayout textInputLayoutNomorTelpon;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    //button
    Button buttonRegister;

    //sqlite helper
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sqliteHelper = new SqliteHelper(this);
        initTextViewLogin();
        initViews();
        Log.i("test", "okokok");
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String NamaLengkap = editTextNamaLengkap.getText().toString();
                    String TempatLahir = editTextTempatLahir.getText().toString();
                    String TanggalLahir = editTextTanggalLahir.getText().toString();
                    String JenisKelamin = editTextJenisKelamin.getText().toString();
                    String NomorIdentitas = editTextNomorIdentitas.getText().toString();
                    String Alamat = editTextAlamat.getText().toString();
                    String KodePos = editTextKodePos.getText().toString();
                    String NomorTelpon = editTextNomorTelpon.getText().toString();
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    Log.i("test", "email =" + Email);
                    //Cek email sudah ada atau belum
                    if (!sqliteHelper.isEmailExists(Email)) {

                        //simpan data ke database
                        sqliteHelper.addUser(new User(null, NamaLengkap, TempatLahir, TanggalLahir, JenisKelamin, NomorIdentitas, Alamat, KodePos, NomorTelpon, Email, Password));
                        Log.i("test", "eksekusi");
                        Snackbar.make(buttonRegister, "Data Berhasil Dimasukan ", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);
                    }else {

                        //pesan kesalahan
                        Snackbar.make(buttonRegister, "Email Sudah terdaftar ", Snackbar.LENGTH_LONG).show();
                    }


                }
            }
        });
    }

    //login
    private void initTextViewLogin() {
        TextView textViewLogin = (TextView) findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //inisialisasi
    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextNamaLengkap = (EditText) findViewById(R.id.editTextNamaLengkap);
        editTextTempatLahir = (EditText) findViewById(R.id.editTextTempatLahir);
        editTextTanggalLahir = (EditText) findViewById(R.id.editTextTanggalLahir);
        editTextJenisKelamin = (EditText) findViewById(R.id.editTextJenisKelamin);
        editTextNomorIdentitas = (EditText) findViewById(R.id.editTextNomorIdentitas);
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);
        editTextKodePos = (EditText) findViewById(R.id.editTextKodePos);
        editTextNomorTelpon = (EditText) findViewById(R.id.editTextNomorTelpon);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutNamaLengkap = (TextInputLayout) findViewById(R.id.textInputLayoutNamaLengkap);
        textInputLayoutTempatLahir = (TextInputLayout) findViewById(R.id.textInputLayoutTempatLahir);
        textInputLayoutTanggalLahir = (TextInputLayout) findViewById(R.id.textInputLayoutTanggalLahir);
        textInputLayoutJenisKelamin = (TextInputLayout) findViewById(R.id.textInputLayoutJenisKelamin);
        textInputLayoutNomorIdentitas = (TextInputLayout) findViewById(R.id.textInputLayoutNomorIdentitas);
        textInputLayoutAlamat = (TextInputLayout) findViewById(R.id.textInputLayoutAlamat);
        textInputLayoutKodePos = (TextInputLayout) findViewById(R.id.textInputLayoutKodePos);
        textInputLayoutNomorTelpon = (TextInputLayout) findViewById(R.id.textInputLayoutNomorTelpon);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

    }

    //validasi masukan dari user
    public boolean validate() {
        boolean valid = false;

        //mengambil nilai dari edittext
        String NamaLengkap = editTextNamaLengkap.getText().toString();
        String TempatLahir = editTextTempatLahir.getText().toString();
        String TanggalLahir = editTextTanggalLahir.getText().toString();
        String JenisKelamin = editTextJenisKelamin.getText().toString();
        String NomorIdentitas = editTextNomorIdentitas.getText().toString();
        String Alamat = editTextAlamat.getText().toString();
        String KodePos = editTextKodePos.getText().toString();
        String NomorTelpon = editTextNomorTelpon.getText().toString();
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        //validasi nama lengkap
        if (NamaLengkap.isEmpty()) {
            valid = false;
            textInputLayoutNamaLengkap.setError("Masukan Data Dengan Benar");
        } else {
            if (NamaLengkap.length() > 5) {
                valid = true;
                textInputLayoutNamaLengkap.setError(null);
            } else {
                valid = false;
                textInputLayoutNamaLengkap.setError("Data Masukan terlalu Pendek!");
            }
        }

        //validasi Tempat Lahir
        if (TempatLahir.isEmpty()) {
            valid = false;
            textInputLayoutTempatLahir.setError("Data Tidak Boleh Kosong");
        } else {
            if (TempatLahir.length() > 15) {
                valid = false;
                textInputLayoutTempatLahir.setError("Nama Tempat Lahir terlalu Panjang");
            } else {
                valid = true;
                textInputLayoutTempatLahir.setError(null);
            }
        }

        //validasi Tanggal Lahir
        if (TanggalLahir.isEmpty()) {
            valid = false;
            textInputLayoutTanggalLahir.setError("Data Tidak Boleh Kosong");
        } else {
            if (TempatLahir.length() > 10) {
                valid = false;
                textInputLayoutTanggalLahir.setError("contoh Penulisan : 18/12/1995");
            } else {
                valid = true;
                textInputLayoutTanggalLahir.setError(null);
            }
        }

        //validasi Jenis Kelamin
        if (JenisKelamin.isEmpty()) {
            valid = false;
            textInputLayoutTanggalLahir.setError("Data Tidak Boleh Kosong");
        } else {
            if (JenisKelamin == "pria" || JenisKelamin == "Pria" || JenisKelamin == "Wanita" || JenisKelamin == "wanita") {
                valid = true;
                textInputLayoutJenisKelamin.setError(null);
            } else {
                valid = false;
                textInputLayoutJenisKelamin.setError("Jenis Kelamin Pria/Wanita");
            }
        }

        //validasi Kode Pos
        if (KodePos.isEmpty()) {
            valid = false;
            textInputLayoutKodePos.setError("Data Tidak Boleh Kosong");
        } else {
            if (KodePos.length() <= 5) {
                valid = true;
                textInputLayoutKodePos.setError(null);
            } else {
                valid = false;
                textInputLayoutKodePos.setError("Kode Pos Salah");
            }
        }

        //validasi nomor telpon
        if (NomorTelpon.isEmpty()) {
            valid = false;
            textInputLayoutKodePos.setError("Data Tidak Boleh Kosong");
        } else {
            if (NomorTelpon.length() <= 12) {
                valid = true;
                textInputLayoutNomorTelpon.setError(null);
            } else {
                valid = false;
                textInputLayoutNomorTelpon.setError("nomor telpon maximal 12 digit");
            }
        }
        //validasi Nomor Identitas
        if (NomorIdentitas.isEmpty()) {
            valid = false;
            textInputLayoutNomorIdentitas.setError("Data Tidak Boleh Kosong");
        } else {
            if (NomorIdentitas.length() == 16) {
                valid = true;
                textInputLayoutNomorIdentitas.setError(null);
            } else {
                valid = false;
                textInputLayoutNomorIdentitas.setError("Masukan Nomor Identitas Dengan Benar");
            }
        }

        //validasi Alamat
        if (Alamat.isEmpty()) {
            valid = false;
            textInputLayoutAlamat.setError("Data Tidak Boleh Kosong");
        } else {
            if (Alamat.length() > 50) {
                valid = false;
                textInputLayoutAlamat.setError("Data Alamat Terlalu Panjang");
            } else {
                valid = true;
                textInputLayoutAlamat.setError(null);
            }
        }

        //validasi email
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Masukan Email Dengan Benar!");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }

        //validasi password
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Masukan Password Dengan Benar!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password Terlalu Pendek!");
            }
        }


        return valid;
    }
}
