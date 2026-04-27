package com.example.fueltrack.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fueltrack.R;
import com.example.fueltrack.data.MockFuelDataSource;
import com.example.fueltrack.model.FuelRecord;
import com.example.fueltrack.ui.fuel.FuelEntryActivity;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        TextView textHistoryItems = findViewById(R.id.text_history_items);
        Button buttonAddFromHistory = findViewById(R.id.button_add_from_history);

        List<FuelRecord> records = MockFuelDataSource.getSampleRecords();
        StringBuilder historyText = new StringBuilder();

        for (FuelRecord record : records) {
            historyText.append(record.toDisplayLine()).append("\n\n");
        }

        textHistoryItems.setText(historyText.toString().trim());

        buttonAddFromHistory.setOnClickListener(v -> {
            Intent intent = new Intent(HistoryActivity.this, FuelEntryActivity.class);
            startActivity(intent);
        });
    }
}

