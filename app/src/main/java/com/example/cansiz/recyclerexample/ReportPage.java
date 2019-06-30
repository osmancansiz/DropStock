package com.example.cansiz.recyclerexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReportPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_page);

        Button urunRapor = (Button) findViewById(R.id.urunRaporButton);
        Button urunGiris =  (Button) findViewById(R.id.girisRaporButton);
        Button urunCikis = (Button) findViewById(R.id.cikisRaporButton);

        urunRapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportPage.this,ReportProductPage.class);
                startActivity(intent);
            }
        });

        urunGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportPage.this,ReportProductEntryPage.class);
                startActivity(intent);
            }
        });

        urunCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportPage.this,ReportProductOutputPage.class);
                startActivity(intent);
            }
        });

    }
}
