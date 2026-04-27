package com.example.fueltrack.ui.fuel;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fueltrack.R;

public class FuelEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_entry);

        Button buttonSaveFueling = findViewById(R.id.button_save_fueling);
        buttonSaveFueling.setOnClickListener(v -> {
            Toast.makeText(this, getString(R.string.fueling_saved_message), Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}

