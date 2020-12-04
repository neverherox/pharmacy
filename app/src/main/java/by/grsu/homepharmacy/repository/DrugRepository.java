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
    private LiveData<List<Drug>> drugs;

    public DrugRepository(Application application) {
        pharmacyDataBase = PharmacyDataBase.getInstance(application);
        drugDao = pharmacyDataBase.drugDao();
        drugs = drugDao.getAll();
    }

    public LiveData<List<Drug>> getDrugs() {
        return drugs;
    }

    public LiveData<Drug> getById(int id) {
        return drugDao.getById(id);
    }

    public void insert(Drug drug) {
        PharmacyDataBase.databaseWriteExecutor.execute(() -> {
            drugDao.insert(drug);
        });
    }
}
