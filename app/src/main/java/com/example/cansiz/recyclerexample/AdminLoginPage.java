package com.example.cansiz.recyclerexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginPage extends Activity {
    final Database db = new Database(AdminLoginPage.this);
    private EditText email,password;
    private String user,pass;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_page);

        if (!db.checkAdminExist("admin","admin",2)){
            db.AddAdmin("admin","admin","admin","admin");
        }

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        Button loginBtn = (Button) findViewById(R.id.adminLoginButton);

        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                boolean checkAdminExist = db.checkAdminExist(email.getText().toString(),password.getText().toString(),2);
                if (checkAdminExist){
                    HomePageDirectory();
                }
                else{
                    email.setText(null);
                    password.setText(null);
                    Toast.makeText(AdminLoginPage.this,"Lütfen Bilgileri Doğru Giriniz.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void HomePageDirectory(){
        Intent intent = new Intent(AdminLoginPage.this,AdminHomePage.class);
        AdminLoginPage.this.startActivity(intent);
        AdminLoginPage.this.finish();
    }

    public void forgotClick(View view) {
        Intent intent = new Intent(AdminLoginPage.this,ForgotPassword.class);
        AdminLoginPage.this.startActivity(intent);
    }
}

