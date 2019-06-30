package com.example.cansiz.recyclerexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePassword extends Activity {
    final Database db = new Database(UpdatePassword.this);
    private Button newPassButton;
    private EditText newPass,newPassCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        Bundle extras = getIntent().getExtras();
        final String email = extras.getString("email");

        newPassButton = (Button) findViewById(R.id.newPasswordButton);
        newPass = (EditText) findViewById(R.id.newPassword);
        newPassCorrect = (EditText) findViewById(R.id.newPasswordCorrect);

        newPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = newPass.getText().toString();
                String passwordCorrect = newPassCorrect.getText().toString();

                if (!password.equals(passwordCorrect)){
                    Toast.makeText(getApplicationContext(),  "Lütfen şifre tekrarını doğru giriniz" , Toast.LENGTH_SHORT).show();
                }
                else{
                    db.UpdatePassword(email,password);
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(UpdatePassword.this);
                    dlgAlert.setMessage("Şifre Değişimi Başarılı");
                    dlgAlert.setTitle("DropStock");
                    dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(UpdatePassword.this,LoginPage.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
            }
        });
    }
}
