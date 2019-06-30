package com.example.cansiz.recyclerexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHomePage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        
        Button stokEkle = (Button) findViewById(R.id.stokEkle);
        Button stokListele =  (Button) findViewById(R.id.stokListele);


        stokEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomePage.this,AddStock.class);
                startActivity(intent);
            }
        });

        stokListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomePage.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
