package com.example.admin_utehome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ComposeInvoiceActivity extends AppCompatActivity {

    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_invoice);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("invoiceId")) {
            isEditMode = true;
        }

        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        if (isEditMode) {
            tvHeaderTitle.setText("Chỉnh sửa hóa đơn");
        } else {
            tvHeaderTitle.setText("Tạo hóa đơn mới");
        }

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        RelativeLayout btnPickDate = findViewById(R.id.btnPickDate);
        if (btnPickDate != null) {
            btnPickDate.setOnClickListener(v -> 
                Toast.makeText(this, "Chọn hạn thanh toán", Toast.LENGTH_SHORT).show()
            );
        }

        TextView btnSaveInvoice = findViewById(R.id.btnSaveInvoice);
        if (btnSaveInvoice != null) {
            btnSaveInvoice.setOnClickListener(v -> {
                Toast.makeText(this, "Đã lưu hóa đơn thành công!", Toast.LENGTH_SHORT).show();
                finish();
            });
        }
    }
}
