package com.example.fueltrack.ui.fuel;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fueltrack.data.FuelRepository;
import com.example.fueltrack.model.FuelRecord;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import com.example.fueltrack.R;

import android.text.Editable;
import android.text.TextWatcher;

public class FuelEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_entry);

        TextInputLayout layoutDate = findViewById(R.id.layout_date);
        TextInputLayout layoutVehicle = findViewById(R.id.layout_vehicle);
        TextInputLayout layoutOdometer = findViewById(R.id.layout_odometer);
        TextInputLayout layoutLiters = findViewById(R.id.layout_liters);
        TextInputLayout layoutTotalCost = findViewById(R.id.layout_total_cost);

        TextInputEditText inputDate = findViewById(R.id.input_date);
        TextInputEditText inputVehicle = findViewById(R.id.input_vehicle);
        TextInputEditText inputOdometer = findViewById(R.id.input_odometer);
        TextInputEditText inputLiters = findViewById(R.id.input_liters);
        TextInputEditText inputTotalCost = findViewById(R.id.input_total_cost);

        applyDateMask(inputDate);

        FuelEntryValidator validator = new FuelEntryValidator();
        FuelRepository repository = FuelRepository.getInstance(this);
        Button buttonSaveFueling = findViewById(R.id.button_save_fueling);

        // Edit mode: if an existing record id is passed, preload fields and update on save
        long editingId = getIntent().getLongExtra("record_id", 0L);
        final boolean[] isEditMode = {false};
        if (editingId > 0L) {
            isEditMode[0] = true;
            buttonSaveFueling.setText(R.string.update_fueling);
            repository.getRecordById(editingId, record -> {
                if (record != null) {
                    inputDate.setText(record.getDate());
                    inputVehicle.setText(record.getVehicle());
                    inputOdometer.setText(String.valueOf(record.getOdometer()));
                    inputLiters.setText(String.valueOf(record.getLiters()));
                    inputTotalCost.setText(String.valueOf(record.getTotalCost()));
                }
            });
        }

        buttonSaveFueling.setOnClickListener(v -> {
            clearErrors(layoutDate, layoutVehicle, layoutOdometer, layoutLiters, layoutTotalCost);

            FuelEntryValidator.ValidationResult result = validator.validate(
                getText(inputDate),
                getText(inputVehicle),
                getText(inputOdometer),
                getText(inputLiters),
                getText(inputTotalCost)
            );

            if (!result.isValid()) {
                applyError(layoutDate, result.getDateError());
                applyError(layoutVehicle, result.getVehicleError());
                applyError(layoutOdometer, result.getOdometerError());
                applyError(layoutLiters, result.getLitersError());
                applyError(layoutTotalCost, result.getTotalCostError());
                Toast.makeText(this, getString(R.string.fuel_entry_invalid_message), Toast.LENGTH_SHORT).show();
                return;
            }

            if (isEditMode[0] && editingId > 0L) {
                FuelRecord record = new FuelRecord(
                    editingId,
                    getText(inputDate),
                    getText(inputVehicle),
                    result.getOdometer(),
                    result.getLiters(),
                    result.getTotalCost(),
                    System.currentTimeMillis()
                );
                repository.updateRecord(record, () -> {
                    Toast.makeText(this, getString(R.string.fueling_updated_message), Toast.LENGTH_SHORT).show();
                    finish();
                });
            } else {
                FuelRecord record = new FuelRecord(
                    getText(inputDate),
                    getText(inputVehicle),
                    result.getOdometer(),
                    result.getLiters(),
                    result.getTotalCost()
                );

                repository.saveRecord(record, () -> {
                    Toast.makeText(this, getString(R.string.fueling_saved_message), Toast.LENGTH_SHORT).show();
                    finish();
                });
            }
        });
    }

    private void clearErrors(TextInputLayout... layouts) {
        for (TextInputLayout layout : layouts) {
            layout.setError(null);
        }
    }

    private void applyError(TextInputLayout layout, FuelEntryValidator.FieldError error) {
        if (error == null) {
            layout.setError(null);
            return;
        }
        int messageRes;
        switch (error) {
            case REQUIRED:
                messageRes = R.string.error_required_field;
                break;
            case INVALID_NUMBER:
                messageRes = R.string.error_invalid_number;
                break;
            case NON_POSITIVE:
            default:
                messageRes = R.string.error_positive_number;
                break;
        }
        layout.setError(getString(messageRes));
    }

    private String getText(TextInputEditText editText) {
        return editText.getText() == null ? "" : editText.getText().toString();
    }

    private void applyDateMask(TextInputEditText inputDate) {
        inputDate.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // no-op
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // no-op
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (isUpdating) {
                    return;
                }
                isUpdating = true;
                String digits = editable.toString().replaceAll("\\D", "");
                if (digits.length() > 8) {
                    digits = digits.substring(0, 8);
                }
                StringBuilder formatted = new StringBuilder();
                for (int i = 0; i < digits.length(); i++) {
                    if (i == 2 || i == 4) {
                        formatted.append('/');
                    }
                    formatted.append(digits.charAt(i));
                }
                inputDate.setText(formatted.toString());
                inputDate.setSelection(formatted.length());
                isUpdating = false;
            }
        });
    }
}
