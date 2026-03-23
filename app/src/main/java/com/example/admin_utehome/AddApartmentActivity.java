package com.example.admin_utehome;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class AddApartmentActivity extends AppCompatActivity {

    private TextView btnStatusEmpty, btnStatusOccupied, btnStatusHandover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_apartment);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        btnStatusEmpty = findViewById(R.id.btnStatusEmpty);
        btnStatusOccupied = findViewById(R.id.btnStatusOccupied);
        btnStatusHandover = findViewById(R.id.btnStatusHandover);

        // Simple toggle logic mimicking a radio group
        btnStatusEmpty.setOnClickListener(v -> selectStatus(btnStatusEmpty));
        btnStatusOccupied.setOnClickListener(v -> selectStatus(btnStatusOccupied));
        btnStatusHandover.setOnClickListener(v -> selectStatus(btnStatusHandover));

        MaterialButton btnCreateApartment = findViewById(R.id.btnCreateApartment);
        btnCreateApartment.setOnClickListener(v -> {
            Toast.makeText(this, "Đã tạo căn hộ mới thành công", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void selectStatus(TextView selected) {
        // Reset all
        btnStatusEmpty.setBackgroundResource(R.drawable.bg_status_toggle_unselected);
        btnStatusEmpty.setTextColor(Color.parseColor("#8E8E8E")); // text_secondary

        btnStatusOccupied.setBackgroundResource(R.drawable.bg_status_toggle_unselected);
        btnStatusOccupied.setTextColor(Color.parseColor("#8E8E8E"));

        btnStatusHandover.setBackgroundResource(R.drawable.bg_status_toggle_unselected);
        btnStatusHandover.setTextColor(Color.parseColor("#8E8E8E"));

        // Highlight selected
        selected.setBackgroundResource(R.drawable.bg_status_toggle_selected);
        selected.setTextColor(Color.parseColor("#C05030")); // Match orange text
    }
}
