package com.example.cansiz.recyclerexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends Activity {
    private EditText email,password;
    private CheckBox rememberMe;
    private String user,pass;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        rememberMe = (CheckBox)findViewById(R.id.checkBox1);

        Button registerBtn = (Button) findViewById(R.id.registerBtn);
        Button loginBtn = (Button) findViewById(R.id.loginBtn);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this,RegisterPage.class);
                startActivity(intent);
            }
        });

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            email.setText(loginPreferences.getString("username", ""));
            password.setText(loginPreferences.getString("password", ""));
            rememberMe.setChecked(true);
            HomePageDirectory();
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            final Database db = new Database(LoginPage.this);
            @Override
            public void onClick(View view) {
                boolean isExist = db.checkUserExist(email.getText().toString(),password.getText().toString());

                if (isExist){
                    HomePageDirectory();
                    login();
                }
                else{
                    email.setText(null);
                    password.setText(null);
                    Toast.makeText(LoginPage.this,"Lütfen Bilgileri Doğru Giriniz.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void login(){
        if(email.getText().toString().length() != 0 && password.getText().length() != 0){

            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(email.getWindowToken(), 0);

            user = email.getText().toString();
            pass = password.getText().toString();


            if (rememberMe.isChecked()) {
                HomePageDirectory();
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.putString("username", user);
                loginPrefsEditor.putString("password", pass);
                loginPrefsEditor.commit();
            } else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }

            HomePageDirectory();
        }else{
            Toast.makeText(getApplicationContext(),"Lütfen alanları doldurunuz.",Toast.LENGTH_SHORT).show();
        }
    }

    public void HomePageDirectory(){
        Intent intent = new Intent(LoginPage.this,HomePage.class);
        LoginPage.this.startActivity(intent);
        LoginPage.this.finish();
    }
}


