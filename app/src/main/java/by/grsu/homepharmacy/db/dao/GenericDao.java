package by.grsu.homepharmacy.db.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import java.util.List;

import by.grsu.homepharmacy.db.entity.Drug;

public interface GenericDao<T> {
    @Insert
    long insert(T item);

    @Insert
    void insert(List<T> items);

    @Update
    void update(T item);

    @Delete
    void delete(T item);

    @Delete
    void delete(List<T> item);
}
