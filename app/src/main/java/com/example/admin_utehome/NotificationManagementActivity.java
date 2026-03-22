package com.example.admin_utehome;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotificationManagementActivity extends AppCompatActivity {

    private List<Notification> allNotifications = new ArrayList<>();
    private NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_management);

        // Back button
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Dữ liệu mẫu
        allNotifications.add(new Notification(
                Notification.TYPE_MAINTENANCE,
                "Thông báo bảo trì thang máy tòa S1",
                "10:30 - 24/10/2023",
                "Toàn bộ cư dân tòa S1"
        ));
        allNotifications.add(new Notification(
                Notification.TYPE_EVENT,
                "Chương trình Trung Thu 2023 tại sân chung",
                "08:15 - 23/10/2023",
                "Toàn bộ cư dân"
        ));
        allNotifications.add(new Notification(
                Notification.TYPE_URGENT,
                "Cảnh báo vệ sinh khu vực hầm gửi xe B2",
                "Hôm qua",
                "Cư dân có ô tô"
        ));

        // RecyclerView
        RecyclerView rv = findViewById(R.id.rvNotifications);
        adapter = new NotificationAdapter(new ArrayList<>(allNotifications));
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        // Search
        EditText etSearch = findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterNotifications(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // FAB
        findViewById(R.id.fabCreateNotif).setOnClickListener(v -> {
            Intent intent = new Intent(this, ComposeNotificationActivity.class);
            startActivity(intent);
        });
    }

    private void filterNotifications(String query) {
        List<Notification> filtered = new ArrayList<>();
        for (Notification n : allNotifications) {
            if (n.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(n);
            }
        }
        adapter.updateList(filtered);
    }
}
