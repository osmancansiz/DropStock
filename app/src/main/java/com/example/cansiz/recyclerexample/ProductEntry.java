package com.example.cansiz.recyclerexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class ProductEntry extends Activity {

    final Database db = new Database(ProductEntry.this);
    private EditText productEntryText;
    private Button productEntryButton;
    private Spinner spinner_show;
    private List<String> productNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_entry);

        productEntryText = (EditText)findViewById(R.id.productEntryText);
        productEntryButton = (Button)findViewById(R.id.productEntry);
        spinner_show = (Spinner) findViewById(R.id.spinner_show);

        fetchSpinnerValues();

        productEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String valueUrunAd = spinner_show.getSelectedItem().toString();

                    String valueUrunAdet= productEntryText.getText().toString();
                    int finalUrunAdet=Integer.parseInt(valueUrunAdet);

                    db.UrunGiris(valueUrunAd,finalUrunAdet);
                    db.UpdateEntry(valueUrunAd,finalUrunAdet);

                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(ProductEntry.this);
                    dlgAlert.setMessage("Ürün Giriş Başarılı");
                    dlgAlert.setTitle("DropStock");
                    dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(),"Lütfen doğru değer giriniz.",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(),spinner_show.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchSpinnerValues()
    {
        final Database db = new Database(ProductEntry.this);
        productNames = db.productNameList();
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item_layout, productNames);
        spinner_adapter.setDropDownViewResource(R.layout.spinner_item_layout);
        spinner_show.setAdapter(spinner_adapter);
    }
}
