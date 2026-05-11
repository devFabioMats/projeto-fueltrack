package com.example.fueltrack.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "fuel_records")
public class FuelRecord {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private String date;
    @NonNull
    private String vehicle;
    private int odometer;
    private double liters;
    private double totalCost;
    private long createdAt;

    public FuelRecord(long id, @NonNull String date, @NonNull String vehicle, int odometer, double liters, double totalCost, long createdAt) {
        this.id = id;
        this.date = date;
        this.vehicle = vehicle;
        this.odometer = odometer;
        this.liters = liters;
        this.totalCost = totalCost;
        this.createdAt = createdAt;
    }

    @Ignore
    public FuelRecord(@NonNull String date, @NonNull String vehicle, int odometer, double liters, double totalCost) {
        this(0, date, vehicle, odometer, liters, totalCost, System.currentTimeMillis());
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    @NonNull
    public String getVehicle() {
        return vehicle;
    }

    public int getOdometer() {
        return odometer;
    }

    public double getLiters() {
        return liters;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}

