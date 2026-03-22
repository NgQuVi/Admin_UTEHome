package com.example.admin_utehome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VehicleDetailActivity extends AppCompatActivity {

    private TextView tvStatusTitle;
    private TextView tvDetailType, tvDetailPlate, tvDetailDesc;
    private TextView tvDetailOwner, tvDetailApt, tvDetailPhone;
    private TextView btnReject, btnApprove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);

        initViews();
        loadDataFromIntent();
        setupListeners();
    }

    private void initViews() {
        tvStatusTitle = findViewById(R.id.tvStatusTitle);
        tvDetailType = findViewById(R.id.tvDetailType);
        tvDetailPlate = findViewById(R.id.tvDetailPlate);
        tvDetailDesc = findViewById(R.id.tvDetailDesc);
        
        tvDetailOwner = findViewById(R.id.tvDetailOwner);
        tvDetailApt = findViewById(R.id.tvDetailApt);
        tvDetailPhone = findViewById(R.id.tvDetailPhone);

        btnReject = findViewById(R.id.btnReject);
        btnApprove = findViewById(R.id.btnApprove);
    }

    private void loadDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            String plate = intent.getStringExtra("plate");
            String desc = intent.getStringExtra("desc");
            String owner = intent.getStringExtra("owner");
            String apt = intent.getStringExtra("apt");
            String type = intent.getStringExtra("type");
            String status = intent.getStringExtra("status");

            if (plate != null) tvDetailPlate.setText(plate);
            if (desc != null) tvDetailDesc.setText(desc);
            if (owner != null) tvDetailOwner.setText(owner);
            if (apt != null) tvDetailApt.setText(apt);
            if (type != null) tvDetailType.setText(type);

            if (Vehicle.STATUS_APPROVED.equals(status)) {
                tvStatusTitle.setText("Đã duyệt");
                tvStatusTitle.setTextColor(0xFF28A745); // Green
                
                // Ẩn cụm nút phê duyệt phía dưới
                android.view.View bottomAction = findViewById(R.id.bottomAction);
                if (bottomAction != null) {
                    bottomAction.setVisibility(android.view.View.GONE);
                }
            } else {
                tvStatusTitle.setText("Đang chờ duyệt");
            }
        }
    }

    private void setupListeners() {
        ImageView btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        if (btnReject != null) {
            btnReject.setOnClickListener(v -> {
                Toast.makeText(this, "Đã từ chối phương tiện", Toast.LENGTH_SHORT).show();
                finish();
            });
        }

        if (btnApprove != null) {
            btnApprove.setOnClickListener(v -> {
                Toast.makeText(this, "Duyệt phương tiện thành công", Toast.LENGTH_SHORT).show();
                finish();
            });
        }
    }
}
