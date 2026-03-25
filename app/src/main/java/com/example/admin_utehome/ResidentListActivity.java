package com.example.admin_utehome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ResidentListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewResidents;
    private ResidentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resident_list);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        recyclerViewResidents = findViewById(R.id.recyclerViewResidents);
        recyclerViewResidents.setLayoutManager(new LinearLayoutManager(this));

        // Sample data mimicking the image
        List<Resident> residentList = new ArrayList<>();
        residentList.add(new Resident("Nguyễn Văn An", "P.1205 - Tòa S1"));
        residentList.add(new Resident("Lê Thị Mai", "P.0502 - Tòa S1"));
        residentList.add(new Resident("Trần Quốc Bảo", "P.1812 - Tòa S2"));
        residentList.add(new Resident("Phạm Minh Hiếu", "P.0910 - Tòa S2"));

        adapter = new ResidentAdapter(residentList);
        recyclerViewResidents.setAdapter(adapter);

        FloatingActionButton fabAddResident = findViewById(R.id.fabAddResident);
        if (fabAddResident != null) {
            fabAddResident.setOnClickListener(v -> {
                Intent intent = new Intent(ResidentListActivity.this, AddResidentActivity.class);
                startActivity(intent);
            });
        }
    }
}
