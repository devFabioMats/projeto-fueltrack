package com.example.fueltrack.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.fueltrack.model.FuelRecord;

import java.util.List;

@Dao
public interface FuelRecordDao {

    @Insert
    long insert(FuelRecord record);

    @Update
    void update(FuelRecord record);

    @Delete
    void delete(FuelRecord record);

    @Query("DELETE FROM fuel_records WHERE id = :id")
    void deleteById(long id);

    @Query("SELECT * FROM fuel_records WHERE id = :id LIMIT 1")
    FuelRecord getById(long id);

    @Query("SELECT * FROM fuel_records ORDER BY createdAt DESC")
    List<FuelRecord> getAll();
}

