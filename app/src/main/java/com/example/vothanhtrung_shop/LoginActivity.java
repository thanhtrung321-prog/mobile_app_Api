package com.example.vothanhtrung_shop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final int DELAY_MILLIS = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailEditText = findViewById(R.id.loginemailAdmin);
        EditText passwordEditText = findViewById(R.id.loginPasswordadmin);
        Button loginButton = findViewById(R.id.login_button_admin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Kiểm tra xem email và mật khẩu có được nhập hay không
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    showToast("Vui lòng nhập đầy đủ thông tin đăng nhập");
                    return;
                }

                // Ở đây bạn có thể thêm mã để xác thực người dùng với CSDL hoặc bất kỳ loại xác thực nào khác

                // Tạm thời chuyển đến MainActivity
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Đóng LoginActivity
                    }
                }, DELAY_MILLIS);
            }
        });

        View signUpButton = findViewById(R.id.signup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showToast(String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
