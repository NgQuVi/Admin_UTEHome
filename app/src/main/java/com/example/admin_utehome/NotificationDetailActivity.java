package com.example.admin_utehome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);

        // Back
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Nhận dữ liệu từ Intent
        int type = getIntent().getIntExtra("notif_type", Notification.TYPE_MAINTENANCE);
        String title = getIntent().getStringExtra("notif_title");
        String time = getIntent().getStringExtra("notif_time");
        String recipient = getIntent().getStringExtra("notif_recipient");

        // Cập nhật UI
        TextView tvDetailTitle = findViewById(R.id.tvDetailTitle);
        TextView tvDetailTag = findViewById(R.id.tvDetailTag);
        TextView tvDetailDate = findViewById(R.id.tvDetailDate);
        TextView tvDetailTime = findViewById(R.id.tvDetailTime);
        TextView tvImpact = findViewById(R.id.tvImpact);

        if (title != null) tvDetailTitle.setText(title);
        if (recipient != null) tvImpact.setText(recipient);

        // Parse time dạng "HH:MM - DD/MM/YYYY"
        if (time != null) {
            String[] parts = time.split(" - ");
            if (parts.length == 2) {
                tvDetailTime.setText(parts[0]);
                tvDetailDate.setText(parts[1]);
            } else {
                tvDetailDate.setText(time);
            }
        }

        // Tag style theo loại
        switch (type) {
            case Notification.TYPE_MAINTENANCE:
                tvDetailTag.setText("BẢO TRÌ");
                tvDetailTag.setBackgroundResource(R.drawable.bg_tag_maintenance);
                tvDetailTag.setTextColor(getResources().getColor(android.R.color.holo_blue_dark, null));
                break;
            case Notification.TYPE_EVENT:
                tvDetailTag.setText("SỰ KIỆN");
                tvDetailTag.setBackgroundResource(R.drawable.bg_tag_event);
                tvDetailTag.setTextColor(0xFF7C4DFF);
                break;
            case Notification.TYPE_URGENT:
                tvDetailTag.setText("THÔNG BÁO KHẨN");
                tvDetailTag.setBackgroundResource(R.drawable.bg_tag_urgent);
                tvDetailTag.setTextColor(0xFFFF6D00);
                break;
        }

        // Nút Chỉnh sửa
        LinearLayout btnEdit = findViewById(R.id.btnEditNotif);
        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(this, ComposeNotificationActivity.class);
            intent.putExtra("isEdit", true);
            startActivity(intent);
        });
    }
}
