package com.example.cansiz.recyclerexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends Activity {
    private EditText ad,soyad,mail,pass,passCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        final Database db = new Database(RegisterPage.this);

        ad = (EditText) findViewById(R.id.name);
        soyad = (EditText)findViewById(R.id.surname);
        mail = (EditText) findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.password);
        passCorrect = (EditText)findViewById(R.id.passwordCorrect);

        Button RegButton = (Button) findViewById(R.id.registerBtn);

        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String adValue = ad.getText().toString();
                String soyadValue = soyad.getText().toString();
                String mailValue = mail.getText().toString();
                String passValue = pass.getText().toString();
                String passCorrectValue = passCorrect.getText().toString();

                if (adValue.isEmpty() || soyadValue.isEmpty() || mailValue.isEmpty() || passValue.isEmpty()){
                    Toast.makeText(getApplicationContext(),  "Alanları boş bırakmayınız!" , Toast.LENGTH_SHORT).show();
                }
                else if(passValue.length()<6){
                    Toast.makeText(getApplicationContext(),  "Şifre 6 karakterden az olamaz" , Toast.LENGTH_SHORT).show();
                }
                else if(!passValue.equals(passCorrectValue)){
                    Toast.makeText(getApplicationContext(),  "Lütfen şifre tekrarını doğru giriniz" , Toast.LENGTH_SHORT).show();
                }
                else{
                    db.AddUser(ad.getText().toString(),soyad.getText().toString(),mail.getText().toString(),pass.getText().toString());
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(RegisterPage.this);
                    dlgAlert.setMessage("Kayıt Başarılı");
                    dlgAlert.setTitle("DropStock");
                    dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(RegisterPage.this,LoginPage.class);
                            startActivity(intent); }
                    });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
            }
        });
    }


}
