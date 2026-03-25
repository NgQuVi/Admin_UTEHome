package com.example.admin_utehome;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class VehicleManagementActivity extends AppCompatActivity {

    private RecyclerView rvVehicles;
    private VehicleAdapter adapter;
    private List<Vehicle> allVehicles;
    private List<Vehicle> displayedVehicles;

    private TextView tvListTitle;
    private TextView chipAll, chipCar, chipBike, chipPending;
    private EditText edtSearch;

    private String currentSelectedFilter = "ALL"; // ALL, CAR, BIKE, PENDING

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_management);

        initViews();
        setupListeners();
        loadMockData();

        rvVehicles.setLayoutManager(new LinearLayoutManager(this));
        displayedVehicles = new ArrayList<>(allVehicles);
        adapter = new VehicleAdapter(displayedVehicles);
        rvVehicles.setAdapter(adapter);

        // Intial state
        selectChip(chipAll);
        applyFilters();
    }

    private void initViews() {
        rvVehicles = findViewById(R.id.rvVehicles);
        tvListTitle = findViewById(R.id.tvListTitle);
        chipAll = findViewById(R.id.chipAll);
        chipCar = findViewById(R.id.chipCar);
        chipBike = findViewById(R.id.chipBike);
        chipPending = findViewById(R.id.chipPending);
        edtSearch = findViewById(R.id.edtSearchVehicle);
    }

    private void setupListeners() {
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        chipAll.setOnClickListener(v -> {
            currentSelectedFilter = "ALL";
            selectChip(chipAll);
            applyFilters();
        });

        chipCar.setOnClickListener(v -> {
            currentSelectedFilter = "CAR";
            selectChip(chipCar);
            applyFilters();
        });

        chipBike.setOnClickListener(v -> {
            currentSelectedFilter = "BIKE";
            selectChip(chipBike);
            applyFilters();
        });

        chipPending.setOnClickListener(v -> {
            currentSelectedFilter = "PENDING";
            selectChip(chipPending);
            applyFilters();
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                applyFilters();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void applyFilters() {
        String query = edtSearch.getText().toString().toLowerCase().trim();
        List<Vehicle> filteredList = new ArrayList<>();

        for (Vehicle v : allVehicles) {
            boolean matchesSearch = v.getLicensePlate().toLowerCase().contains(query) ||
                                  v.getOwnerName().toLowerCase().contains(query);

            boolean matchesStatus = false;
            if (currentSelectedFilter.equals("ALL") && v.getStatus().equals(Vehicle.STATUS_APPROVED)) {
                matchesStatus = true;
            } else if (currentSelectedFilter.equals("CAR") && v.getStatus().equals(Vehicle.STATUS_APPROVED) && v.getType().equals(Vehicle.TYPE_CAR)) {
                matchesStatus = true;
            } else if (currentSelectedFilter.equals("BIKE") && v.getStatus().equals(Vehicle.STATUS_APPROVED) && v.getType().equals(Vehicle.TYPE_MOTORBIKE)) {
                matchesStatus = true;
            } else if (currentSelectedFilter.equals("PENDING") && v.getStatus().equals(Vehicle.STATUS_PENDING)) {
                matchesStatus = true;
            }

            if (matchesSearch && matchesStatus) {
                filteredList.add(v);
            }
        }

        displayedVehicles = filteredList;
        adapter.updateList(displayedVehicles);

        if (currentSelectedFilter.equals("PENDING")) {
            tvListTitle.setText("DANH SÁCH CHỜ DUYỆT (" + displayedVehicles.size() + ")");
        } else {
            tvListTitle.setText("DANH SÁCH ĐÃ DUYỆT (" + displayedVehicles.size() + ")");
        }
    }

    private void selectChip(TextView selectedChip) {
        TextView[] chips = {chipAll, chipCar, chipBike, chipPending};
        for (TextView chip : chips) {
            chip.setSelected(chip == selectedChip);
        }
    }

    private void loadMockData() {
        allVehicles = new ArrayList<>();
        allVehicles.add(new Vehicle("51H - 123.45", "Toyota Camry - Trắng", "Nguyễn Văn A", "P.1205", Vehicle.TYPE_CAR, Vehicle.STATUS_APPROVED));
        allVehicles.add(new Vehicle("29A1 - 999.99", "Honda SH 150i - Đen", "Trần Thị B", "P.0812", Vehicle.TYPE_MOTORBIKE, Vehicle.STATUS_APPROVED));
        allVehicles.add(new Vehicle("30G - 888.88", "VinFast VF8 - Xanh", "Lê Hoàng C", "P.1501", Vehicle.TYPE_CAR, Vehicle.STATUS_APPROVED));
        allVehicles.add(new Vehicle("51F - 456.78", "Mazda CX-5 - Đỏ", "Phạm Minh D", "P.0404", Vehicle.TYPE_CAR, Vehicle.STATUS_APPROVED));
        allVehicles.add(new Vehicle("60B1 - 122.33", "Yamaha Exciter 155", "Ngô Thanh E", "P.1606", Vehicle.TYPE_MOTORBIKE, Vehicle.STATUS_APPROVED));
        
        allVehicles.add(new Vehicle("59P1 - 555.55", "Honda Vision 2023", "Bùi Trí Khang", "P.1002", Vehicle.TYPE_MOTORBIKE, Vehicle.STATUS_PENDING));
        allVehicles.add(new Vehicle("43A - 111.11", "Mercedes C300", "Lâm Trường", "P.2001", Vehicle.TYPE_CAR, Vehicle.STATUS_PENDING));
    }
}
