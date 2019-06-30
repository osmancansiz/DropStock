package com.example.cansiz.recyclerexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserHomePage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);

        Button urunGiris = (Button) findViewById(R.id.urunGiris);
        Button urunCikis =  (Button) findViewById(R.id.urunCikis);
        Button urunRapor = (Button) findViewById(R.id.urunRapor);


        urunGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomePage.this,ProductEntry.class);
                startActivity(intent);
            }
        });

        urunCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomePage.this,ProductOutput.class);
                startActivity(intent);
            }
        });

        urunRapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomePage.this,ReportPage.class);
                startActivity(intent);
            }
        });
    }

    public void adminClick(View view) {
        Intent intent = new Intent(UserHomePage.this,LoginPage.class);
        startActivity(intent);
        finish();
    }
}
