package com.example.admin_utehome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        LinearLayout cardTotalResidents = findViewById(R.id.cardTotalResidents);
        if (cardTotalResidents != null) {
            cardTotalResidents.setOnClickListener(v -> {
                Intent intent = new Intent(DashboardActivity.this, ResidentListActivity.class);
                startActivity(intent);
            });
        }

        LinearLayout cardTotalApartments = findViewById(R.id.cardTotalApartments);
        if (cardTotalApartments != null) {
            cardTotalApartments.setOnClickListener(v -> {
                Intent intent = new Intent(DashboardActivity.this, ApartmentListActivity.class);
                startActivity(intent);
            });
        }
        LinearLayout cardOccupancy = findViewById(R.id.cardOccupancy);
        if (cardOccupancy != null) {
            cardOccupancy.setOnClickListener(v -> {
                Intent intent = new Intent(DashboardActivity.this, OccupancyListActivity.class);
                startActivity(intent);
            });
        }
    }
}