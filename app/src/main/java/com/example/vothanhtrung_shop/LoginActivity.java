package com.example.vothanhtrung_shop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private static final int DELAY_MILLIS = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Khởi tạo FirebaseAuth
        auth = FirebaseAuth.getInstance();

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

                // Thực hiện xác minh thông tin đăng nhập với Firebase Authentication
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Đăng nhập thành công, hiển thị thông báo
                                    showToast("Đăng nhập thành công");

                                    // Chờ 1 giây trước khi chuyển đến MainActivity
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish(); // Đóng LoginActivity
                                        }
                                    }, DELAY_MILLIS);
                                } else {
                                    // Đăng nhập thất bại, hiển thị thông báo lỗi
                                    showToast("Sai tài khoản hoặc mật khẩu ");
                                }
                            }
                        });
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
