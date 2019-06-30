package com.example.cansiz.recyclerexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class ForgotPassword extends Activity {
    final Database db = new Database(ForgotPassword.this);
    private EditText editTextEmail,checkCorrect;
    private Button buttonSend,checkCorrectButton;
    int result=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        editTextEmail = (EditText) findViewById(R.id.emailCorrect);
        buttonSend = (Button) findViewById(R.id.forgotPassButton);
        checkCorrect = (EditText) findViewById(R.id.checkCorrect);
        checkCorrectButton = (Button) findViewById(R.id.checkCorrectButton);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isMailExist = db.checkMailExist(editTextEmail.getText().toString());
                if (isMailExist){
                    sendEmail();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Lütfen kayıtlı olduğunuz maili giriniz.",Toast.LENGTH_LONG).show();
                }
            }
        });

        checkCorrectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valueCheckCorrect = checkCorrect.getText().toString();
                final int finalCheckCorrect=Integer.parseInt(valueCheckCorrect);
                if(result == finalCheckCorrect){
                    Intent intent = new Intent(ForgotPassword.this,UpdatePassword.class);
                    intent.putExtra("email",editTextEmail.getText().toString());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Lütfen Kodu Doğru Giriniz",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void sendEmail(){
        String email = editTextEmail.getText().toString().trim();
        Random r = new Random();
        int low = 1000;
        int high = 9999;
        result = r.nextInt(high-low) + low;

        SendMail sm = new SendMail(this,email,"DropStock Şifre Yenileme",result);
        sm.execute();
    }

}
