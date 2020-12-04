package by.grsu.homepharmacy.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import by.grsu.homepharmacy.db.dao.DrugDao;
import by.grsu.homepharmacy.db.dao.ProducerDao;
import by.grsu.homepharmacy.db.PharmacyDataBase;
import by.grsu.homepharmacy.db.entity.Drug;
import by.grsu.homepharmacy.db.relation.ProducerWithDrugs;

public class ProducerRepository {
    private PharmacyDataBase pharmacyDataBase;
    private ProducerDao producerDao;
    private DrugDao drugDao;
    private LiveData<List<ProducerWithDrugs>> producers;

    public ProducerRepository(Application application) {
        pharmacyDataBase = PharmacyDataBase.getInstance(application);
        producerDao = pharmacyDataBase.producerDao();
        drugDao = pharmacyDataBase.drugDao();
        producers = producerDao.getAll();
    }

    public LiveData<List<ProducerWithDrugs>> getProducers() {
        return producers;
    }

    public LiveData<ProducerWithDrugs> getById(int id) {
        return producerDao.getById(id);
    }

    public void insert(ProducerWithDrugs producer) {
        PharmacyDataBase.databaseWriteExecutor.execute(() -> {
            long index = producerDao.insert(producer.getProducer());
            for(Drug drug : producer.getDrugs()) {
                drug.setProducer_id((int)index);
            }
            drugDao.insert(producer.getDrugs());
        });
    }
    public void delete(ProducerWithDrugs producer) {
        PharmacyDataBase.databaseWriteExecutor.execute(() -> {
        producerDao.delete(producer.getProducer());
        drugDao.delete(producer.getDrugs());
        });
    }
}
