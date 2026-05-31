package com.example.fueltrack.data;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.fueltrack.data.local.AppDatabase;
import com.example.fueltrack.data.local.FuelRecordDao;
import com.example.fueltrack.model.FuelRecord;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FuelRepository {

    public interface RecordsCallback {
        void onLoaded(List<FuelRecord> records);
    }

    public interface RecordCallback {
        void onLoaded(FuelRecord record);
    }

    public interface SaveCallback {
        void onSaved();
    }

    public interface DeleteCallback {
        void onDeleted();
    }

    private static volatile FuelRepository instance;
    private final FuelRecordDao fuelRecordDao;
    private final ExecutorService executor;
    private final Handler mainHandler;

    private FuelRepository(Context context) {
        fuelRecordDao = AppDatabase.getInstance(context).fuelRecordDao();
        executor = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());
    }

    public static FuelRepository getInstance(Context context) {
        if (instance == null) {
            synchronized (FuelRepository.class) {
                if (instance == null) {
                    instance = new FuelRepository(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    public void saveRecord(FuelRecord record, SaveCallback callback) {
        executor.execute(() -> {
            fuelRecordDao.insert(record);
            if (callback != null) {
                mainHandler.post(callback::onSaved);
            }
        });
    }

    public void updateRecord(FuelRecord record, SaveCallback callback) {
        executor.execute(() -> {
            fuelRecordDao.update(record);
            if (callback != null) {
                mainHandler.post(callback::onSaved);
            }
        });
    }

    public void deleteRecord(FuelRecord record, DeleteCallback callback) {
        executor.execute(() -> {
            fuelRecordDao.delete(record);
            if (callback != null) {
                mainHandler.post(callback::onDeleted);
            }
        });
    }

    public void deleteRecordById(long id, DeleteCallback callback) {
        executor.execute(() -> {
            fuelRecordDao.deleteById(id);
            if (callback != null) {
                mainHandler.post(callback::onDeleted);
            }
        });
    }

    public void getRecordById(long id, RecordCallback callback) {
        executor.execute(() -> {
            FuelRecord record = fuelRecordDao.getById(id);
            if (callback != null) {
                mainHandler.post(() -> callback.onLoaded(record));
            }
        });
    }

    public void loadAllRecords(RecordsCallback callback) {
        executor.execute(() -> {
            List<FuelRecord> records = fuelRecordDao.getAll();
            if (callback != null) {
                mainHandler.post(() -> callback.onLoaded(records));
            }
        });
    }
}

