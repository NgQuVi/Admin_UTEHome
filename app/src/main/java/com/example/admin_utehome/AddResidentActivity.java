package com.example.admin_utehome;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.util.Random;

public class AddResidentActivity extends AppCompatActivity {

    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_resident);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        EditText etPassword = findViewById(R.id.etPassword);
        ImageView btnTogglePwd = findViewById(R.id.btnTogglePwd);
        LinearLayout btnGeneratePwd = findViewById(R.id.btnGeneratePwd);
        MaterialButton btnCreateAccount = findViewById(R.id.btnCreateAccount);

        btnTogglePwd.setOnClickListener(v -> {
            isPasswordVisible = !isPasswordVisible;
            if (isPasswordVisible) {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                btnTogglePwd.setImageResource(R.drawable.ic_eye_outline); // Assumed to exist
            } else {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btnTogglePwd.setImageResource(R.drawable.ic_eye_off_outline);
            }
            etPassword.setSelection(etPassword.getText().length());
        });

        btnGeneratePwd.setOnClickListener(v -> {
            int randomNum = 100000 + new Random().nextInt(900000);
            etPassword.setText("PWD" + randomNum);
            Toast.makeText(this, "Đã tạo mật khẩu ngẫu nhiên", Toast.LENGTH_SHORT).show();
            // Automatically reveal password when generated
            isPasswordVisible = true;
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            btnTogglePwd.setImageResource(R.drawable.ic_eye_outline);
            etPassword.setSelection(etPassword.getText().length());
        });

        btnCreateAccount.setOnClickListener(v -> {
            Toast.makeText(this, "Đã tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
