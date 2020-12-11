package by.grsu.homepharmacy.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import by.grsu.homepharmacy.db.dao.DrugDao;
import by.grsu.homepharmacy.db.PharmacyDataBase;
import by.grsu.homepharmacy.db.entity.Drug;

public class DrugRepository {
    private PharmacyDataBase pharmacyDataBase;
    private DrugDao drugDao;

    public DrugRepository(Application application) {
        pharmacyDataBase = PharmacyDataBase.getInstance(application);
        drugDao = pharmacyDataBase.drugDao();
    }

    public LiveData<List<Drug>> getDrugs(int producerId) {
        return drugDao.getAll(producerId);
    }

    public LiveData<List<Drug>> getDrugs(String name) {return drugDao.getAll(name);}
    public void insert(Drug drug) {
        PharmacyDataBase.databaseWriteExecutor.execute(() -> {
            drugDao.insert(drug);
        });
    }

    public void delete(Drug drug) {
        PharmacyDataBase.databaseWriteExecutor.execute(() -> {
            drugDao.delete(drug);
        });
    }
    public void update(Drug drug)
    {
        PharmacyDataBase.databaseWriteExecutor.execute(() -> {
            drugDao.update(drug);
        });
    }
}
