package by.grsu.homepharmacy.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import by.grsu.homepharmacy.db.entity.Drug;
import by.grsu.homepharmacy.repository.DrugRepository;

public class DrugViewModel extends AndroidViewModel {

    private DrugRepository drugRepository;


    public DrugViewModel(Application application) {
        super(application);

        drugRepository = new DrugRepository(application);
    }

    public LiveData<List<Drug>> getDrugs(int producerId) {
        return drugRepository.getDrugs(producerId);
    }

    public void insert(Drug drug) {
            drugRepository.insert(drug);
    }
    public void delete(Drug drug) {
        drugRepository.delete(drug);
    }
}
