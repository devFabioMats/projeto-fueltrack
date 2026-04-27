package com.example.fueltrack.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fueltrack.R;
import com.example.fueltrack.ui.fuel.FuelEntryActivity;
import com.example.fueltrack.ui.history.HistoryActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button buttonNewFueling = findViewById(R.id.button_new_fueling);
        Button buttonHistory = findViewById(R.id.button_history);

        buttonNewFueling.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, FuelEntryActivity.class);
            startActivity(intent);
        });

        buttonHistory.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
            startActivity(intent);
        });
    }
}

