package com.example.admin_utehome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InvoiceManagementActivity extends AppCompatActivity {

    private List<Invoice> allInvoices = new ArrayList<>();
    private InvoiceAdapter adapter;
    private TextView chipAll, chipUnpaid, chipPaid;
    private TextView tvListTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_management);

        initViews();
        setupListeners();
        loadMockData();
        setupRecyclerView();
        
        // Initial state
        selectChip(chipAll);
        updateListTitle(allInvoices.size());
    }

    private void initViews() {
        chipAll = findViewById(R.id.chipAll);
        chipUnpaid = findViewById(R.id.chipUnpaid);
        chipPaid = findViewById(R.id.chipPaid);
        tvListTitle = findViewById(R.id.tvListTitle);
    }

    private void setupListeners() {
        // Back
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        // Search
        findViewById(R.id.btnSearch).setOnClickListener(v -> 
            Toast.makeText(this, "Tính năng tìm kiếm", Toast.LENGTH_SHORT).show()
        );

        // Chips
        chipAll.setOnClickListener(v -> filterInvoices(-1, chipAll));
        chipUnpaid.setOnClickListener(v -> filterInvoices(Invoice.STATUS_UNPAID, chipUnpaid));
        chipPaid.setOnClickListener(v -> filterInvoices(Invoice.STATUS_PAID, chipPaid));

        // FAB
        findViewById(R.id.fabAdd).setOnClickListener(v -> {
            Intent intent = new Intent(this, ComposeInvoiceActivity.class);
            startActivity(intent);
        });
    }

    private void loadMockData() {
        allInvoices.add(new Invoice("P.1205", "#HD102301", "Hạn thanh toán: 15/10/2023", Invoice.STATUS_UNPAID, "2.500.000đ"));
        allInvoices.add(new Invoice("P.0812", "#HD102302", "Hạn thanh toán: 10/10/2023", Invoice.STATUS_PAID, "1.250.000đ"));
        allInvoices.add(new Invoice("P.0304", "#HD102303", "Hạn thanh toán: 01/10/2023", Invoice.STATUS_OVERDUE, "3.800.000đ"));
        allInvoices.add(new Invoice("P.2210", "#HD102304", "Hạn thanh toán: 15/10/2023", Invoice.STATUS_UNPAID, "4.200.000đ"));
    }

    private void setupRecyclerView() {
        RecyclerView rv = findViewById(R.id.rvInvoices);
        adapter = new InvoiceAdapter(new ArrayList<>(allInvoices));
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    private void filterInvoices(int status, TextView selectedChip) {
        selectChip(selectedChip);
        
        if (status == -1) {
            adapter.updateList(new ArrayList<>(allInvoices));
            updateListTitle(allInvoices.size());
            return;
        }

        List<Invoice> filtered = new ArrayList<>();
        for (Invoice inv : allInvoices) {
            if (inv.getStatus() == status) {
                filtered.add(inv);
            }
        }
        adapter.updateList(filtered);
        updateListTitle(filtered.size());
    }

    private void selectChip(TextView activeChip) {
        chipAll.setSelected(false);
        chipUnpaid.setSelected(false);
        chipPaid.setSelected(false);
        activeChip.setSelected(true);
    }

    private void updateListTitle(int size) {
        tvListTitle.setText("DANH SÁCH HÓA ĐƠN (" + size + ")");
    }
}
