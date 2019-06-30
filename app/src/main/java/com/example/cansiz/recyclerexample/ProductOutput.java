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

public class ProductOutput extends Activity {
    final Database db = new Database(ProductOutput.this);
    private EditText productOutputText;
    private Button productOutputButton;
    private Spinner spinner_show;
    private List<String> productNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_output);

        productOutputText = (EditText)findViewById(R.id.productOutputText);
        productOutputButton = (Button)findViewById(R.id.productOutput);
        spinner_show = (Spinner) findViewById(R.id.spinner_show_output);

        fetchSpinnerValues();

        productOutputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String valueUrunAd = spinner_show.getSelectedItem().toString();

                    String valueUrunAdet= productOutputText.getText().toString();
                    int finalUrunAdet=Integer.parseInt(valueUrunAdet);

                    db.UrunCikis(valueUrunAd,finalUrunAdet);
                    db.UpdateOutput(valueUrunAd,finalUrunAdet);

                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(ProductOutput.this);
                    dlgAlert.setMessage("Ürün Çıkışı Başarılı");
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
        final Database db = new Database(ProductOutput.this);
        productNames = db.productNameList();
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item_layout, productNames);
        spinner_adapter.setDropDownViewResource(R.layout.spinner_item_layout);
        spinner_show.setAdapter(spinner_adapter);
    }
}
