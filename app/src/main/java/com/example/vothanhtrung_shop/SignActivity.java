package com.example.vothanhtrung_shop;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText password_reEditText;
    private EditText userNameEditText;
    private Button registerButton;
    private ApiCaller apiCaller;

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
        // kiem tra lổi
//        Log.d("avav", "okokok");

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy giá trị từ các EditText
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String userName = userNameEditText.getText().toString();

                // Kiểm tra xem các trường đã được điền đầy đủ không
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(userName)) {
                    showToast("vui lòng nhập trường bắt buộc");
                    return;
                }

                // Kiểm tra mật khẩu có đủ độ dài và chứa các yếu tố cần thiết không
                if (!isValidPassword(password)) {
                    showToast("mật khẩu của bạn phải bằng hoặc trên 6 ký tự và phải chứa ký tự đặc biệt");
                    return;
                }

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

    // Hàm kiểm tra mật khẩu có đủ độ dài và chứa các yếu tố cần thiết không
    private boolean isValidPassword(String password) {
        String passwordPattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{6,}";
        return password.matches(passwordPattern);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
