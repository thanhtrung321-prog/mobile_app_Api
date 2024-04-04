package com.example.vothanhtrung_shop;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

public class SignActivity extends AppCompatActivity {

    public EditText emailEditText;
    public EditText passwordEditText;
    public EditText password_reEditText;
    public EditText userNameEditText;
    public Button registerButton;
    public ApiCaller apiCaller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        emailEditText = findViewById(R.id.editemail);
        passwordEditText = findViewById(R.id.password);
        password_reEditText = findViewById(R.id.password_re);
        userNameEditText = findViewById(R.id.username);

        registerButton = findViewById(R.id.register_button);
        apiCaller = ApiCaller.getInstance(this);
        Log.d("avav","okokok");

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy giá trị từ các EditText
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String userName = userNameEditText.getText().toString();// Lấy số điện thoại nếu cần

                // Tạo mới đối tượng User với các giá trị từ các EditText
                User newUser = new User(email, password,"null", userName, "null");

                // Gọi phương thức addUser từ apiCaller để thêm người dùng mới
                apiCaller.addUser(newUser, new ApiCaller.ApiResponseListener<User>() {
                    @Override
                    public void onSuccess(User response) {
                        showToast("Đăng ký thành công người dùng");
                        Log.d("avav", "Đăng kí người dùng thành công. Username: " + response.getUsername());
                    }

                    @Override
                    public void onError(String errorMessage) {
                        showToast("Đăng kí thất bại " + errorMessage);
                        Log.d("avav", "Đăng kí người dùng không thành công " + errorMessage);
                    }
                });
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}