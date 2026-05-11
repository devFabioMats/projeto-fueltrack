package com.example.fueltrack.ui.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fueltrack.R;
import com.example.fueltrack.model.FuelRecord;

import java.util.ArrayList;
import java.util.List;

public class FuelRecordAdapter extends RecyclerView.Adapter<FuelRecordAdapter.RecordViewHolder> {

    private final List<FuelRecord> records = new ArrayList<>();

    public void submitList(List<FuelRecord> items) {
        records.clear();
        if (items != null) {
            records.addAll(items);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_fuel_record, parent, false);
        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        FuelRecord record = records.get(position);
        holder.bind(record);
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    static class RecordViewHolder extends RecyclerView.ViewHolder {
        private final TextView textDateVehicle;
        private final TextView textOdometer;
        private final TextView textLiters;
        private final TextView textTotalCost;

        RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            textDateVehicle = itemView.findViewById(R.id.text_date_vehicle);
            textOdometer = itemView.findViewById(R.id.text_odometer);
            textLiters = itemView.findViewById(R.id.text_liters);
            textTotalCost = itemView.findViewById(R.id.text_total_cost);
        }

        void bind(FuelRecord record) {
            textDateVehicle.setText(itemView.getContext().getString(
                R.string.history_item_title,
                record.getDate(),
                record.getVehicle()
            ));
            textOdometer.setText(itemView.getContext().getString(
                R.string.history_item_odometer,
                record.getOdometer()
            ));
            textLiters.setText(itemView.getContext().getString(
                R.string.history_item_liters,
                record.getLiters()
            ));
            textTotalCost.setText(itemView.getContext().getString(
                R.string.history_item_total_cost,
                record.getTotalCost()
            ));
        }
    }
}


