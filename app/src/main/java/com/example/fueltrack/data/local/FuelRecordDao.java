package com.example.fueltrack.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fueltrack.model.FuelRecord;

import java.util.List;

@Dao
public interface FuelRecordDao {

    @Insert
    long insert(FuelRecord record);

    @Query("SELECT * FROM fuel_records ORDER BY createdAt DESC")
    List<FuelRecord> getAll();
}

