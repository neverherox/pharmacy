package by.grsu.homepharmacy.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import by.grsu.homepharmacy.db.dao.DrugDao;
import by.grsu.homepharmacy.db.dao.ProducerDao;
import by.grsu.homepharmacy.db.PharmacyDataBase;
import by.grsu.homepharmacy.db.entity.Drug;
import by.grsu.homepharmacy.db.entity.Producer;
import by.grsu.homepharmacy.db.relation.ProducerWithDrugs;

public class ProducerRepository {
    private PharmacyDataBase pharmacyDataBase;
    private ProducerDao producerDao;
    private LiveData<List<ProducerWithDrugs>> producers;

    public ProducerRepository(Application application) {
        pharmacyDataBase = PharmacyDataBase.getInstance(application);
        producerDao = pharmacyDataBase.producerDao();
        producers = producerDao.getAll();
    }

    public LiveData<List<ProducerWithDrugs>> getProducers() {
        return producers;
    }

    public LiveData<ProducerWithDrugs> getById(int id) {
        return producerDao.getById(id);
    }

    public void insert(Producer producer) {
        PharmacyDataBase.databaseWriteExecutor.execute(() -> {
            long index = producerDao.insert(producer);
        });
    }
    public void delete(Producer producer) {
        PharmacyDataBase.databaseWriteExecutor.execute(() -> {
        producerDao.delete(producer);
        });
    }
    public void update(Producer producer) {
        PharmacyDataBase.databaseWriteExecutor.execute(() -> {
            producerDao.update(producer);
        });
    }
}
