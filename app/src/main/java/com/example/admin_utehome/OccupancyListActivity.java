package com.example.admin_utehome;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OccupancyListActivity extends AppCompatActivity {

    private RecyclerView rvApartments;
    private OccupancyAdapter adapter;
    private TextView tabAll, tabOccupied, tabEmpty;
    private List<Apartment> fullList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_occupancy_list);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        rvApartments = findViewById(R.id.rvApartments);
        rvApartments.setLayoutManager(new LinearLayoutManager(this));

        tabAll = findViewById(R.id.tabAll);
        tabOccupied = findViewById(R.id.tabOccupied);
        tabEmpty = findViewById(R.id.tabEmpty);

        tabAll.setOnClickListener(v -> {
            updateTabs(tabAll);
            filterData("ALL");
        });
        tabOccupied.setOnClickListener(v -> {
            updateTabs(tabOccupied);
            filterData("OCCUPIED");
        });
        tabEmpty.setOnClickListener(v -> {
            updateTabs(tabEmpty);
            filterData("EMPTY");
        });

        // Sample data mimicking the image
        fullList = new ArrayList<>();
        fullList.add(new Apartment("TÒA S1", "P.1205", "Nguyễn Văn An", "75", true));
        fullList.add(new Apartment("TÒA S1", "P.0812", "", "54", false));
        fullList.add(new Apartment("TÒA S1", "P.0504", "Trần Thị Bích", "68", true));

        adapter = new OccupancyAdapter(fullList);
        rvApartments.setAdapter(adapter);

        updateTabs(tabAll);
    }

    private void filterData(String type) {
        List<Apartment> filtered = new ArrayList<>();
        for (Apartment a : fullList) {
            if ("ALL".equals(type)) {
                filtered.add(a);
            } else if ("OCCUPIED".equals(type) && a.isOccupied()) {
                filtered.add(a);
            } else if ("EMPTY".equals(type) && !a.isOccupied()) {
                filtered.add(a);
            }
        }
        adapter.updateList(filtered);
    }

    private void updateTabs(TextView selectedTab) {
        TextView[] tabs = {tabAll, tabOccupied, tabEmpty};
        for (TextView tab : tabs) {
            if (tab == selectedTab) {
                tab.setBackgroundResource(R.drawable.bg_chip_selected);
                tab.setTextColor(android.graphics.Color.WHITE);
            } else {
                tab.setBackgroundResource(R.drawable.bg_chip_unselected);
                tab.setTextColor(android.graphics.Color.parseColor("#8E8E8E")); // text_secondary
            }
        }
    }
}
