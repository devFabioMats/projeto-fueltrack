package com.example.fueltrack.data;

import com.example.fueltrack.model.FuelRecord;

import java.util.ArrayList;
import java.util.List;

public class MockFuelDataSource {

    private MockFuelDataSource() {
    }

    public static List<FuelRecord> getSampleRecords() {
        List<FuelRecord> records = new ArrayList<>();

        records.add(new FuelRecord("22/04/2026", "Onix", 41250, 41.2, 256.34));
        records.add(new FuelRecord("13/04/2026", "Onix", 40810, 38.5, 238.90));
        records.add(new FuelRecord("03/04/2026", "Onix", 40120, 40.0, 248.00));

        return records;
    }
}

