package com.example.vothanhtrung_shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText loginemailAdmin;
    private EditText loginPasswordadmin;
    private Button buttonlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        View register=findViewById(R.id.signup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignActivity.class);
                startActivity(intent);
            }
        });
        loginemailAdmin=findViewById(R.id.loginemailAdmin);
        loginPasswordadmin=findViewById(R.id.loginPasswordadmin);
        buttonlogin=findViewById(R.id.login_button_admin);
        ApiCaller.getInstance(this);
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=loginemailAdmin.getText().toString();
                String password=loginPasswordadmin.getText().toString();
                LoginUser(email,password);
            }
        });
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=loginemailAdmin.getText().toString();
                String password=loginPasswordadmin.getText().toString();
            }
        });
    }
    private void LoginUser(String email,String password){

    }
}
