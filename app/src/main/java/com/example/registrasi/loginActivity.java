package com.example.registrasi;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;


public class loginActivity extends AppCompatActivity {

    //edit teks
    EditText editTextEmail;
    EditText editTextPassword;

    //text input layout
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    //button
    Button buttonLogin;

    // sqlitehelper
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sqliteHelper = new SqliteHelper(this);
        initCreateAccountTextView();
        initViews();

        //klik event login
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //cek inputan user
                if (validate()) {

                    //ambil nilai edit teks
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    //autentifikasi user
                    User currentUser = sqliteHelper.Authenticate(new User(null, null, null, null, null, null, null, null, null, Email, Password));

                    //cek berhasil autentifikasi
                    if (currentUser != null) {
                        Snackbar.make(buttonLogin, "Berhasil Masuk!", Snackbar.LENGTH_LONG).show();

                        //user berhasil login
                        Intent intent=new Intent(loginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {

                        //user gagal login
                        Snackbar.make(buttonLogin, "Gagal masuk, coba lagi!", Snackbar.LENGTH_LONG).show();

                    }
                }
            }
        });


    }

    //text view registrasi
    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#ffffff'> </font><font color='#0c0099'>registrasi</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this, registerActivity.class);
                startActivity(intent);
            }
        });
    }

    //inisialisasi view
    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

    }


    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    //validasi masukan user
    public boolean validate() {
        boolean valid = false;

        //ambil nilai masukan
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        //validasi email
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Email yang dimasukan tidak valid!");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }

        //validasi password
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Masukan Password dengan Benar!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password Terlalu Pendek");
            }
        }

        return valid;
    }


}



