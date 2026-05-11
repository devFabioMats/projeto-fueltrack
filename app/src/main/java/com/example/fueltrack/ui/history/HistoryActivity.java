package com.example.fueltrack.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fueltrack.R;
import com.example.fueltrack.data.FuelRepository;
import com.example.fueltrack.ui.fuel.FuelEntryActivity;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        TextView textHistoryEmpty = findViewById(R.id.text_history_empty);
        RecyclerView recyclerHistory = findViewById(R.id.recycler_history);
        Button buttonAddFromHistory = findViewById(R.id.button_add_from_history);

        FuelRecordAdapter adapter = new FuelRecordAdapter();
        recyclerHistory.setLayoutManager(new LinearLayoutManager(this));
        recyclerHistory.setAdapter(adapter);

        FuelRepository repository = FuelRepository.getInstance(this);
        loadRecords(repository, adapter, textHistoryEmpty);

        buttonAddFromHistory.setOnClickListener(v -> {
            Intent intent = new Intent(HistoryActivity.this, FuelEntryActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        FuelRepository repository = FuelRepository.getInstance(this);
        FuelRecordAdapter adapter = (FuelRecordAdapter) ((RecyclerView) findViewById(R.id.recycler_history)).getAdapter();
        TextView textHistoryEmpty = findViewById(R.id.text_history_empty);
        if (adapter != null) {
            loadRecords(repository, adapter, textHistoryEmpty);
        }
    }

    private void loadRecords(FuelRepository repository, FuelRecordAdapter adapter, TextView textHistoryEmpty) {
        repository.loadAllRecords(records -> {
            adapter.submitList(records);
            boolean isEmpty = records == null || records.isEmpty();
            textHistoryEmpty.setVisibility(isEmpty ? TextView.VISIBLE : TextView.GONE);
        });
    }
}

