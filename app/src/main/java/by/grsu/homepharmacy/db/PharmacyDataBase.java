package by.grsu.homepharmacy.db;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import by.grsu.homepharmacy.db.dao.DrugDao;
import by.grsu.homepharmacy.db.dao.ProducerDao;
import by.grsu.homepharmacy.db.entity.Drug;
import by.grsu.homepharmacy.db.entity.Producer;


@Database(entities = {Drug.class, Producer.class}, version = 1)
public abstract class PharmacyDataBase extends RoomDatabase {
    private static final String DB_NAME = "pharmacy.db";
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public abstract DrugDao drugDao();
    public abstract ProducerDao producerDao();

    private static volatile PharmacyDataBase INSTANCE;

    public static PharmacyDataBase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (PharmacyDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PharmacyDataBase.class, DB_NAME).fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
