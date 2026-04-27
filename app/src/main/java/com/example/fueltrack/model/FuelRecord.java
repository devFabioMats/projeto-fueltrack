package com.example.fueltrack.model;

public class FuelRecord {
    private final String date;
    private final String vehicle;
    private final String liters;
    private final String totalCost;

    public FuelRecord(String date, String vehicle, String liters, String totalCost) {
        this.date = date;
        this.vehicle = vehicle;
        this.liters = liters;
        this.totalCost = totalCost;
    }

    public String toDisplayLine() {
        return date + " - " + vehicle + "\n" + liters + " L | R$ " + totalCost;
    }
}

