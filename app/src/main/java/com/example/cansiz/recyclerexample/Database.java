package com.example.cansiz.recyclerexample;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class Database extends SQLiteOpenHelper {
    private static final String DB_PATH = "//data/data/com.example.cansiz.recyclerexample/databases/";
    private static final int DB_VERSION = 3;
    private static final String DB_NAME="dropstockdb.db";
    private static final String TABLE_KULLANICI="Kullanicilar";
    private static final String TABLE_URUN="Urunler";

    private static final String name = "ad";
    private static final String surname = "soyad";
    private static final String email = "email";
    private static final String password = "password";
    private static final String user_type = "user_type";

    private static String urun_adi="urun_adi";
    private static String urun_adedi="urun_adedi";
    private static String urun_fiyati="urun_fiyati";


    public Database(Context context){
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_KULLANICI+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT,"+name+" TEXT,"+surname+" TEXT,"
                    +email+" TEXT,"+password+" TEXT,"+user_type+" INTEGER);");

        db.execSQL("CREATE TABLE "+TABLE_URUN+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT,"+urun_adi+" TEXT,"+urun_adedi+" INTEGER,"
                +urun_fiyati+" REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean checkUserExist(String email, String password){
        String[] columns = {"email"};
        SQLiteDatabase db=this.getReadableDatabase();

        String selection = "email=? and password = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_KULLANICI, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();
        close();

        if(count > 0){
            return true;
        } else {
            return false;
        }
    }

    public void AddUser(String name, String surname, String email, String password){
        SQLiteDatabase db=this.getWritableDatabase();//SQLiteDatabase sınıfında yazılabilir bağlantı açıyoruz.
        ContentValues cv=new ContentValues();//contentValues sınıfından bir nesne oluşturuyoruz.ContentValues değerleri tutmamızı sağlıyor.

        cv.put(this.name,name.trim());//Ad alıyoruz.
        cv.put(this.surname,surname.trim());//Soyad alıyoruz.
        cv.put(this.email,email.trim());
        cv.put(this.password,password.trim());
        cv.put(this.user_type,1);
        long r=db.insert(TABLE_KULLANICI,null,cv);//Tabloya ekleme yaptığımız fonksiyon.
        if(r>-1)
            Log.i("tag","İşlem Başarılı");//ekleme olması durumunda bu çıktıyı verir.
        else
            Log.e("tag","İşlem Başarısız");//ekleme olmaması durumunda bu çıktıyı verir.
        db.close();//Veritabanı kapatma işlemi
    }

    public void AddStock(String urun_adi, int urun_adedi, int urun_fiyati){
        SQLiteDatabase db=this.getWritableDatabase();//SQLiteDatabase sınıfında yazılabilir bağlantı açıyoruz.
        ContentValues cv=new ContentValues();//contentValues sınıfından bir nesne oluşturuyoruz.ContentValues değerleri tutmamızı sağlıyor.

        cv.put(this.urun_adi,urun_adi.trim());
        cv.put(this.urun_adedi,urun_adedi);
        cv.put(this.urun_fiyati,urun_fiyati);

        long r=db.insert(TABLE_URUN,null,cv);//Tabloya ekleme yaptığımız fonksiyon.

        if(r>-1)
            Log.i("tag","İşlem Başarılı");//ekleme olması durumunda bu çıktıyı verir.
        else
            Log.e("tag","İşlem Başarısız");//ekleme olmaması durumunda bu çıktıyı verir.
        db.close();//Veritabanı kapatma işlemi
    }

    public void UpdateStock(int id , String urun_adi, int urun_adedi, int urun_fiyati){
        SQLiteDatabase db=this.getWritableDatabase();//SQLiteDatabase sınıfında yazılabilir bağlantı açıyoruz.
        ContentValues cv=new ContentValues();//contentValues sınıfından bir nesne oluşturuyoruz.ContentValues değerleri tutmamızı sağlıyor.

        cv.put(this.urun_adi,urun_adi.trim());
        cv.put(this.urun_adedi,urun_adedi);
        cv.put(this.urun_fiyati,urun_fiyati);

        long r=db.update(TABLE_URUN, cv, "id" + "=" + id, null);

        if(r>-1)
            Log.i("tag","İşlem Başarılı");//ekleme olması durumunda bu çıktıyı verir.
        else
            Log.e("tag","İşlem Başarısız");//ekleme olmaması durumunda bu çıktıyı verir.
        db.close();//Veritabanı kapatma işlemi
    }

    public void DeleteStock(long id) {//sil metodu
        SQLiteDatabase db=this.getReadableDatabase();//sqlite veritabani nesnesi
        long r = db.delete(TABLE_URUN, "id" + "=" + id, null);//id degerine göre

        if(r>-1)
            Log.i("tag","İşlem Başarılı");
        else
            Log.e("tag","İşlem Başarısız");
        db.close();//Veritabanı kapatma işlemi
    }

    public List<Product> productItems() {
        List<Product> products = new ArrayList<>();
        String selectQuery = "SELECT  * FROM Urunler";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getInt(0));
                product.setProductName(cursor.getString(1));
                product.setProductNumbers(cursor.getString(2));
                product.setProductPrice(cursor.getString(3));
                products.add(product);
            } while (cursor.moveToNext());
        }

        db.close();
        return products;
    }



}
