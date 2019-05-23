package com.example.cansiz.recyclerexample;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;




public class Database extends SQLiteOpenHelper {
    private static final String DB_PATH = "//data/data/com.example.cansiz.recyclerexample/databases/";
    private static final int DB_VERSION = 3;
    private static final String DB_NAME="dropstockdb.db";
    private static final String TABLE_NAME="Kullanicilar";

    private static final String name = "ad";
    private static final String surname = "soyad";
    private static final String email = "email";
    private static final String password = "password";
    private static final String user_type = "user_type";


    public Database(Context context){
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT,"+name+" TEXT,"+surname+" TEXT,"
                    +email+" TEXT,"+password+" TEXT,"+user_type+" INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void veriEkle(String name, String surname, String email, String password){
        SQLiteDatabase db=this.getWritableDatabase();//SQLiteDatabase sınıfında yazılabilir bağlantı açıyoruz.
        ContentValues cv=new ContentValues();//contentValues sınıfından bir nesne oluşturuyoruz.ContentValues değerleri tutmamızı sağlıyor.

        cv.put(this.name,name.trim());//Ad alıyoruz.
        cv.put(this.surname,surname.trim());//Soyad alıyoruz.
        cv.put(this.email,email.trim());
        cv.put(this.password,password.trim());
        cv.put(this.user_type,1);
        long r=db.insert(TABLE_NAME,null,cv);//Tabloya ekleme yaptığımız fonksiyon.
        if(r>-1)
            Log.i("tag","İşlem Başarılı");//ekleme olması durumunda bu çıktıyı verir.
        else
            Log.e("tag","İşlem Başarısız");//ekleme olmaması durumunda bu çıktıyı verir.
        db.close();//Veritabanı kapatma işlemi
    }

    public boolean checkUserExist(String email, String password){
        String[] columns = {"email"};
        SQLiteDatabase db=this.getReadableDatabase();

        String selection = "email=? and password = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();
        close();

        if(count > 0){
            return true;
        } else {
            return false;
        }
    }


}
