package com.example.cansiz.recyclerexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStock extends Activity {
    Context context;
    final Database db = new Database(AddStock.this);
    private EditText urun_Ad,urun_Adet,urun_Fiyat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);

        urun_Ad = (EditText) findViewById(R.id.productName);
        urun_Adet = (EditText)findViewById(R.id.productNumbers);
        urun_Fiyat = (EditText) findViewById(R.id.productPrice);

        Button addStockButton = (Button) findViewById(R.id.addStockBtn);

        addStockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String valueUrunAd = urun_Ad.getText().toString();

                    String valueUrunAdet= urun_Adet.getText().toString();
                    int finalUrunAdet=Integer.parseInt(valueUrunAdet);

                    String valueUrunFiyat= urun_Fiyat.getText().toString();
                    int finalUrunFiyat=Integer.parseInt(valueUrunFiyat);

                    db.AddStock(valueUrunAd,finalUrunFiyat,finalUrunAdet);

                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(AddStock.this);
                    dlgAlert.setMessage("Kayıt Başarılı");
                    dlgAlert.setTitle("DropStock");
                    dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            urun_Ad.setText(null);
                            urun_Adet.setText(null);
                            urun_Fiyat.setText(null);
                        }
                    });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
                catch (Exception ex){
                    Toast.makeText(context,"Lütfen doğru değer giriniz.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
