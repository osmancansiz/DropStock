/*package com.example.cansiz.recyclerexample;

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

public class StockDatabase extends SQLiteOpenHelper{

    private static final int DB_VERSION = 3;
    private static final String DB_NAME="dropstockdb.db";
    private static final String TABLE_NAME="Urunler";

    private static String urun_adi="urun_adi";
    private static String urun_adedi="urun_adedi";
    private static String urun_fiyati="urun_fiyati";

    public StockDatabase(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT,"+urun_adi+" TEXT,"+urun_adedi+" INTEGER,"
                +urun_fiyati+" REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void AddStock(String urun_adi, int urun_adedi, int urun_fiyati){
        SQLiteDatabase db=this.getWritableDatabase();//SQLiteDatabase sınıfında yazılabilir bağlantı açıyoruz.
        ContentValues cv=new ContentValues();//contentValues sınıfından bir nesne oluşturuyoruz.ContentValues değerleri tutmamızı sağlıyor.

        cv.put(this.urun_adi,urun_adi.trim());
        cv.put(this.urun_adedi,urun_adedi);
        cv.put(this.urun_fiyati,urun_fiyati);

        long r=db.insert(TABLE_NAME,null,cv);//Tabloya ekleme yaptığımız fonksiyon.

        if(r>-1)
            Log.i("tag","İşlem Başarılı");//ekleme olması durumunda bu çıktıyı verir.
        else
            Log.e("tag","İşlem Başarısız");//ekleme olmaması durumunda bu çıktıyı verir.
        db.close();//Veritabanı kapatma işlemi
    }
}
*/