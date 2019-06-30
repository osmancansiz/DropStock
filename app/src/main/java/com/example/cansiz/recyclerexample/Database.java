package com.example.cansiz.recyclerexample;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class Database extends SQLiteOpenHelper {
    private static final String DB_PATH = "//data/data/com.example.cansiz.recyclerexample/databases/";
    private static final int DB_VERSION = 3;
    private static final String DB_NAME="dropstockdb.db";
    private static final String TABLE_KULLANICI="Kullanicilar";
    private static final String TABLE_URUN="Urunler";
    private static final String TABLE_GIRIS="Giris";
    private static final String TABLE_CIKIS="Cikis";

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

        db.execSQL("CREATE TABLE "+TABLE_GIRIS+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT,"+urun_adi+" TEXT,"+urun_adedi+" INTEGER);");

        db.execSQL("CREATE TABLE "+TABLE_CIKIS+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT,"+urun_adi+" TEXT,"+urun_adedi+" INTEGER);");

        //db.execSQL("INSERT or replace INTO Kullanicilar (name,surname,email,password,user_type) VALUES('admin','admin','admin','admin',2)");
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

    public boolean checkAdminExist(String email, String password,int userType) {
        String[] columns = {"email"};
        SQLiteDatabase db=this.getReadableDatabase();

        String selection = "email=? and password = ? and user_type=?";
        String[] selectionArgs = {email, password, String.valueOf(userType)};

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

    public boolean checkMailExist(String email){
        String[] columns = {"email"};
        SQLiteDatabase db=this.getReadableDatabase();

        String selection = "email=?";
        String[] selectionArgs = {email};

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

    public void AddAdmin(String name, String surname, String email, String password){
        SQLiteDatabase db=this.getWritableDatabase();//SQLiteDatabase sınıfında yazılabilir bağlantı açıyoruz.
        //ContentValues cv=new ContentValues();//contentValues sınıfından bir nesne oluşturuyoruz.ContentValues değerleri tutmamızı sağlıyor.
        String query = "REPLACE INTO Kullanicilar (ad, soyad, email, password, user_type) VALUES ('"+name+"', '"+surname+"', '"+email+"', '"+password+"', 2)";

        //cv.put(this.name,name.trim());//Ad alıyoruz.
        //cv.put(this.surname,surname.trim());//Soyad alıyoruz.
        //cv.put(this.email,email.trim());
        //cv.put(this.password,password.trim());
        //cv.put(this.user_type,2);

        //long r=db.insert(TABLE_KULLANICI,null,cv);//Tabloya ekleme yaptığımız fonksiyon.
        db.execSQL(query);
        /*if(r>-1)
            Log.i("tag","İşlem Başarılı");//ekleme olması durumunda bu çıktıyı verir.
        else
            Log.e("tag","İşlem Başarısız");//ekleme olmaması durumunda bu çıktıyı verir.*/
        db.close();//Veritabanı kapatma işlemi
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

    public void UrunGiris(String urun_adi,int urun_adedi){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(this.urun_adi,urun_adi.trim());
        cv.put(this.urun_adedi,urun_adedi);

        long r=db.insert(TABLE_GIRIS,null,cv);

        if(r>-1)
            Log.i("tag","İşlem Başarılı");
        else
            Log.e("tag","İşlem Başarısız");
        db.close();
    }

    public void UrunCikis(String urun_adi,int urun_adedi){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(this.urun_adi,urun_adi.trim());
        cv.put(this.urun_adedi,urun_adedi);

        long r=db.insert(TABLE_CIKIS,null,cv);

        if(r>-1)
            Log.i("tag","İşlem Başarılı");
        else
            Log.e("tag","İşlem Başarısız");
        db.close();
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

    public void UpdateEntry(String urun_adi,int urun_adedi){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="UPDATE Urunler SET urun_adedi = urun_adedi + ? WHERE urun_adi = ?";
        String[] selections={String.valueOf(urun_adedi), urun_adi};
        Cursor cursor=db.rawQuery(query, selections);

        if (cursor.moveToFirst()) {
            do {
                Log.i("tag","İşlem Başarılı");
            } while (cursor.moveToNext());
        }
        db.close();
    }

    public void UpdateOutput(String urun_adi,int urun_adedi){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="UPDATE Urunler SET urun_adedi = urun_adedi - ? WHERE urun_adi = ?";
        String[] selections={String.valueOf(urun_adedi), urun_adi};
        Cursor cursor=db.rawQuery(query, selections);

        if (cursor.moveToFirst()) {
            do {
                Log.i("tag","İşlem Başarılı");
            } while (cursor.moveToNext());
        }
        db.close();
    }

    public void UpdatePassword(String email,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="UPDATE Kullanicilar SET password = ? WHERE email = ?";
        String[] selections={password,email};
        Cursor cursor=db.rawQuery(query, selections);

        if (cursor.moveToFirst()) {
            do {
                Log.i("tag","İşlem Başarılı");
            } while (cursor.moveToNext());
        }
        db.close();
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

    public List<String> productNameList(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_URUN;
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<String> productNames = new ArrayList<String>();

        if (cursor.moveToFirst()) {
            do {
                productNames.add(cursor.getString(1));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return productNames;
    }

    public List<ReportModel> reportEntryListItems() {
        List<ReportModel> reportModels = new ArrayList<>();
        String selectQuery = "SELECT  * FROM Giris";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ReportModel reportModel = new ReportModel();
                reportModel.setId(cursor.getInt(0));
                reportModel.seturunAdi(cursor.getString(1));
                reportModel.seturunAdedi(cursor.getString(2));
                reportModels.add(reportModel);
            } while (cursor.moveToNext());
        }

        db.close();
        return reportModels;
    }

    public List<ReportModel> reportOutputListItems() {
        List<ReportModel> reportModels = new ArrayList<>();
        String selectQuery = "SELECT  * FROM Cikis";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ReportModel reportModel = new ReportModel();
                reportModel.setId(cursor.getInt(0));
                reportModel.seturunAdi(cursor.getString(1));
                reportModel.seturunAdedi(cursor.getString(2));
                reportModels.add(reportModel);
            } while (cursor.moveToNext());
        }

        db.close();
        return reportModels;
    }


}
