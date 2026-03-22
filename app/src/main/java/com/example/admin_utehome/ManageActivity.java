package com.example.admin_utehome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ManageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Setup Card Clicks
        LinearLayout cardManageVehicles = findViewById(R.id.cardManageVehicles);
        if (cardManageVehicles != null) {
            cardManageVehicles.setOnClickListener(v -> {
                Intent intent = new Intent(this, VehicleManagementActivity.class);
                startActivity(intent);
            });
        }

        LinearLayout cardManageUtilities = findViewById(R.id.cardManageUtilities);
        if (cardManageUtilities != null) {
            cardManageUtilities.setOnClickListener(v -> 
                Toast.makeText(this, "Chức năng quản lý tiện ích đang phát triển", Toast.LENGTH_SHORT).show()
            );
        }

        // Setup Bottom Navigation
        LinearLayout navDashboard = findViewById(R.id.navDashboard);
        if (navDashboard != null) {
            navDashboard.setOnClickListener(v -> {
                // Return to dashboard
                finish();
            });
        }
        
        LinearLayout navSettings = findViewById(R.id.navSettings);
        if (navSettings != null) {
            navSettings.setOnClickListener(v -> 
                Toast.makeText(this, "Cài đặt đang phát triển", Toast.LENGTH_SHORT).show()
            );
        }
    }
}
