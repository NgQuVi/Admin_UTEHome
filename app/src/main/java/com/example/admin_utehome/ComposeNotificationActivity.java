package com.example.admin_utehome;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import androidx.appcompat.widget.SwitchCompat;

import androidx.appcompat.app.AppCompatActivity;

public class ComposeNotificationActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextView tvHeaderTitle;
    private TextView chipMaintenance, chipEvent, chipUrgent, chipUtility;
    private SwitchCompat switchSchedule;
    private EditText etDate, etTime;
    private LinearLayout btnPublish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_notification);

        initViews();
        setupListeners();
        
        // Default selection
        selectChip(chipMaintenance);
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        tvHeaderTitle = findViewById(R.id.tvHeaderTitle);

        chipMaintenance = findViewById(R.id.chipMaintenance);
        chipEvent = findViewById(R.id.chipEvent);
        chipUrgent = findViewById(R.id.chipUrgent);
        chipUtility = findViewById(R.id.chipUtility);

        switchSchedule = findViewById(R.id.switchSchedule);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);

        btnPublish = findViewById(R.id.btnPublish);
        
        // Initial state for date and time fields
        toggleScheduleFields(switchSchedule.isChecked());
        
        // Check if editing
        if (getIntent().getBooleanExtra("isEdit", false)) {
            tvHeaderTitle.setText("Chỉnh sửa thông báo");
            btnPublish.getChildAt(0).setTag("Cập nhật");
            ((TextView) btnPublish.getChildAt(0)).setText("Cập nhật");
        }
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());

        View.OnClickListener chipListener = v -> selectChip((TextView) v);
        chipMaintenance.setOnClickListener(chipListener);
        chipEvent.setOnClickListener(chipListener);
        chipUrgent.setOnClickListener(chipListener);
        chipUtility.setOnClickListener(chipListener);

        switchSchedule.setOnCheckedChangeListener((buttonView, isChecked) -> toggleScheduleFields(isChecked));

        btnPublish.setOnClickListener(v -> {
            String title = ((EditText) findViewById(R.id.etTitle)).getText().toString();
            if (title.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tiêu đề", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Phát hành thành công!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void selectChip(TextView selectedChip) {
        chipMaintenance.setSelected(false);
        chipEvent.setSelected(false);
        chipUrgent.setSelected(false);
        chipUtility.setSelected(false);

        selectedChip.setSelected(true);
    }

    private void toggleScheduleFields(boolean isEnabled) {
        etDate.setEnabled(isEnabled);
        etTime.setEnabled(isEnabled);
        
        if (isEnabled) {
            etDate.setAlpha(1.0f);
            etTime.setAlpha(1.0f);
        } else {
            etDate.setAlpha(0.5f);
            etTime.setAlpha(0.5f);
        }
    }
}
