package by.grsu.homepharmacy.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import by.grsu.homepharmacy.db.entity.Drug;

@Dao
public interface DrugDao {

    @Transaction
    @Query("SELECT * FROM Drug")
    LiveData<List<Drug>> getAll();

    @Transaction
    @Query("SELECT * FROM Drug WHERE drugId = :id")
    LiveData<Drug> getById(int id);

    @Insert
    long insert(Drug drug);

    @Update
    void update(Drug drug);

    @Delete
    void delete(Drug drug);
}
