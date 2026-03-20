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

public class ApartmentListActivity extends AppCompatActivity {

    private RecyclerView rvApartments;
    private ApartmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_apartment_list);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        FloatingActionButton fabAddApartment = findViewById(R.id.fabAddApartment);
        fabAddApartment.setOnClickListener(v -> {
            Intent intent = new Intent(ApartmentListActivity.this, AddApartmentActivity.class);
            startActivity(intent);
        });

        rvApartments = findViewById(R.id.rvApartments);
        rvApartments.setLayoutManager(new LinearLayoutManager(this));

        // Sample data mimicking the image
        List<Apartment> list = new ArrayList<>();
        list.add(new Apartment("TÒA S1", "P.1205", "Nguyễn Văn An", "75", true));
        list.add(new Apartment("TÒA S1", "P.0812", "", "54", false));
        list.add(new Apartment("TÒA S1", "P.0504", "Trần Thị Bích", "68", true));

        adapter = new ApartmentAdapter(list);
        rvApartments.setAdapter(adapter);
    }
}
