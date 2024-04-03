package com.example.vothanhtrung_shop;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference database;

    // Khai báo EditText cho email, password, userName, nameOfRestaurant và roles
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText password_reEditText;
    private EditText userNameEditText;
    private EditText nameOfRestaurantEditText;
    private EditText rolesEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        // Khởi tạo FirebaseAuth
        auth = FirebaseAuth.getInstance();

        // Khởi tạo DatabaseReference
        database = FirebaseDatabase.getInstance().getReference();

        // Ánh xạ các trường nhập liệu từ giao diện XML
        emailEditText = findViewById(R.id.editemail);
        passwordEditText = findViewById(R.id.password);
        password_reEditText = findViewById(R.id.password_re);
        userNameEditText = findViewById(R.id.username);
        rolesEditText = findViewById(R.id.roles); // Ánh xạ EditText của roles

        // Gắn sự kiện click cho nút đăng ký
        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các trường nhập liệu
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String password_re = password_reEditText.getText().toString().trim();
                String userName = userNameEditText.getText().toString().trim();
                String roles = rolesEditText.getText().toString().trim(); // Lấy giá trị của roles từ EditText

                // Kiểm tra xem các trường thông tin đã được nhập đầy đủ chưa
                if (email.isEmpty() || password.isEmpty() || password_re.isEmpty() || userName.isEmpty() || roles.isEmpty()) {
                    showToast("Vui lòng nhập đầy đủ thông tin.");
                    return;
                }

                // Kiểm tra mật khẩu nhập lại
                if (!password.equals(password_re)) {
                    showToast("Mật khẩu nhập lại không chính xác");
                    return;
                }

                // Gọi phương thức để đăng ký người dùng với tham số roles
                registerUser(email, password, userName, roles);
            }
        });

        // Thiết lập sự kiện click cho TextView "Bạn đã có tài khoản Đăng Nhập"
        findViewById(R.id.textView19).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registerUser(String email, String password, String userName, String roles) {
        // Đăng ký người dùng với email và password
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Đăng ký thành công, lấy UID của người dùng
                            String userId = auth.getCurrentUser().getUid();

                            // Tạo một đối tượng User với roles được truyền vào
                            User user = new User(userName, email, Integer.parseInt(roles));

                            // Lưu thông tin người dùng vào Firebase Database với UID làm key
                            database.child("users").child(userId).setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // Hiển thị thông báo đăng ký thành công
                                                showToast("Đăng ký tài khoản thành công");

                                                // Chuyển đến MainActivity
                                                Intent intent = new Intent(SignActivity.this, MainActivity.class);
                                                startActivity(intent);
                                                finish(); // Đóng activity hiện tại
                                            } else {
                                                // Đăng ký thất bại, hiển thị thông báo lỗi
                                                showToast("Đăng ký tài khoản thất bại: " + task.getException().getMessage());
                                            }
                                        }
                                    });
                        } else {
                            // Đăng ký thất bại, hiển thị thông báo lỗi
                            showToast("Đăng ký tài khoản thất bại: " + task.getException().getMessage());
                        }
                    }
                });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
