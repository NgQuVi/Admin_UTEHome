package com.example.admin_utehome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InvoiceDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_detail);

        // Actions
        ImageView btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        LinearLayout btnEdit = findViewById(R.id.btnEdit);
        if (btnEdit != null) {
            btnEdit.setOnClickListener(v -> {
                Intent compIntent = new Intent(this, ComposeInvoiceActivity.class);
                compIntent.putExtra("invoiceId", "HD102301"); // using a fixed ID assuming this detail page represents it
                startActivity(compIntent);
            });
        }

        LinearLayout btnDelete = findViewById(R.id.btnDelete);
        if (btnDelete != null) {
            btnDelete.setOnClickListener(v -> 
                Toast.makeText(this, "Chức năng xóa hóa đơn đang phát triển", Toast.LENGTH_SHORT).show()
            );
        }
    }
}
