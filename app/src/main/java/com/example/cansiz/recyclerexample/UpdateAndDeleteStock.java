package com.example.cansiz.recyclerexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateAndDeleteStock extends Activity {
    private EditText ad,adet,fiyat;
    final Database db = new Database(UpdateAndDeleteStock.this);
    Button updateButton,deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete_stock);
        Bundle extras = getIntent().getExtras();

        final int id = extras.getInt("index");
        String exName = extras.getString("urun_Adi");
        String exNumbers = extras.getString("urun_Adedi");
        String exPrice = extras.getString("urun_Fiyati");

        ad = (EditText) findViewById(R.id.exName);
        adet = (EditText)findViewById(R.id.exNumbers);
        fiyat = (EditText) findViewById(R.id.exPrice);

        updateButton = (Button) findViewById(R.id.updateStockButton);
        deleteButton = (Button) findViewById(R.id.deleteStockButton);

        ad.setText(exName);
        adet.setText(exNumbers);
        fiyat.setText(exPrice);


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String valueUrunAd = ad.getText().toString();//ad
                    String valueUrunAdet= adet.getText().toString();//adet convert
                    int finalUrunAdet=Integer.parseInt(valueUrunAdet);
                    String valueUrunFiyat= fiyat.getText().toString();//fiyat convert
                    int finalUrunFiyat=Integer.parseInt(valueUrunFiyat);

                    db.UpdateStock(id,valueUrunAd,finalUrunAdet,finalUrunFiyat);

                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(UpdateAndDeleteStock.this);
                    dlgAlert.setMessage("Güncelleme Başarılı");
                    dlgAlert.setTitle("DropStock");
                    dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent i = new Intent(UpdateAndDeleteStock.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(),"Lütfen doğru değer giriniz.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(UpdateAndDeleteStock.this);
                dlgAlert.setMessage("Ürünü silmek istediğinizden emin misiniz?");
                dlgAlert.setTitle("DropStock");
                dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int i) {
                        db.DeleteStock(id);
                        Intent intent = new Intent(UpdateAndDeleteStock.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }
        });

    }
}
