package by.grsu.homepharmacy.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import by.grsu.homepharmacy.db.entity.Drug;
import by.grsu.homepharmacy.repository.DrugRepository;

public class DrugViewModel extends AndroidViewModel {

    private DrugRepository drugRepository;
    private LiveData<List<Drug>> drugs;


    public DrugViewModel(Application application) {
        super(application);

        drugRepository = new DrugRepository(application);
        drugs = drugRepository.getDrugs();
    }

    public LiveData<List<Drug>> getDrugs() {
        return drugs;
    }

    public void insert(Drug drug) {
            drugRepository.insert(drug);
    }
}
