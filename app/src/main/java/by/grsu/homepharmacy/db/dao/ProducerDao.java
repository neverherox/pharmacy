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
import by.grsu.homepharmacy.db.entity.Producer;
import by.grsu.homepharmacy.db.relation.ProducerWithDrugs;

@Dao
public interface ProducerDao {

    @Transaction
    @Query("SELECT * FROM Producer")
    LiveData<List<ProducerWithDrugs>> getAll();

    @Transaction
    @Query("SELECT * FROM Producer WHERE producerId = :id")
    LiveData<ProducerWithDrugs> getById(int id);

    @Transaction
    @Insert
    long insert(Producer producer);

    @Insert
    void insert(List<Drug> drugs);

    @Update
    void update(Producer producer);

    @Delete
    void delete(Producer producer);

}
