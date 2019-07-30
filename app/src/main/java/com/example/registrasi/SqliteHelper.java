package com.example.registrasi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SqliteHelper extends SQLiteOpenHelper {

    //nama database
    public static final String DATABASE_NAME = "DBRegistrasi";

    //versi database
    public static final int DATABASE_VERSION = 1;

    //nama tabel
    public static final String TABLE_USERS = "users";

    //ID primaryKey
    public static final String KEY_ID = "id";

    //nama lengkap
    public static final String KEY_NAMA_LENGKAP = "namalengkap";

    //tempat lahir
    public static final String KEY_TEMPAT_LAHIR = "tempatlahir";

    //tanggal lahir
    public static final String KEY_TANGGAL_LAHIR = "tanggallahir";

    //jenis kelamin
    public static final String KEY_JENIS_KELAMIN = "jeniskelamin";

    //nomor identitas
    public static final String KEY_NOMOR_IDENTITAS = "nomoridentitas";

    //alamat
    public static final String KEY_ALAMAT = "alamat";

    //kode pos
    public static final String KEY_KODE_POS = "kodepos";

    //nomor telpon
    public static final String KEY_NOMOR_TELPON = "nomortelpon";

    //email
    public static final String KEY_EMAIL = "email";

    //password
    public static final String KEY_PASSWORD = "password";

    //buat tabel
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_NAMA_LENGKAP + " TEXT, "
            + KEY_TEMPAT_LAHIR + " TEXT, "
            + KEY_TANGGAL_LAHIR + " TEXT, "
            + KEY_JENIS_KELAMIN + " TEXT, "
            + KEY_NOMOR_IDENTITAS + " TEXT, "
            + KEY_ALAMAT + " TEXT, "
            + KEY_KODE_POS + " TEXT, "
            + KEY_NOMOR_TELPON + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT"
            + " ) ";


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //eksekusi pembuatan tabel
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop tabel
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
    }

    //metod tambah user
    public void addUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAMA_LENGKAP, user.namalengkap);
        values.put(KEY_TEMPAT_LAHIR, user.tempatlahir);
        values.put(KEY_TANGGAL_LAHIR, user.tanggallahir);
        values.put(KEY_JENIS_KELAMIN, user.jeniskelamin);
        values.put(KEY_NOMOR_IDENTITAS, user.nomoridentitas);
        values.put(KEY_ALAMAT, user.alamat);
        values.put(KEY_KODE_POS, user.kodepos);
        values.put(KEY_NOMOR_TELPON, user.nomortelpon);
        values.put(KEY_EMAIL, user.email);
        values.put(KEY_PASSWORD, user.password);

        // insert nilai
        long todo_id = db.insert(TABLE_USERS, null, values);
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{KEY_ID, KEY_NAMA_LENGKAP, KEY_TEMPAT_LAHIR, KEY_TANGGAL_LAHIR, KEY_JENIS_KELAMIN, KEY_NOMOR_IDENTITAS, KEY_ALAMAT, KEY_KODE_POS, KEY_NOMOR_TELPON, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{user.email},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));

            //jika user ditemukan
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        //jika user tidak ada
        return null;
    }

    //metod cek email
    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{KEY_ID, KEY_NAMA_LENGKAP, KEY_TEMPAT_LAHIR, KEY_TANGGAL_LAHIR, KEY_JENIS_KELAMIN, KEY_NOMOR_IDENTITAS, KEY_ALAMAT, KEY_KODE_POS, KEY_NOMOR_TELPON, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{email},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //jika email ada
            return true;
        }

        //jika email tidak ditemukan
        return false;
    }
}
