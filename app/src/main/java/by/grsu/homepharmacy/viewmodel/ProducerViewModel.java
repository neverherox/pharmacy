package by.grsu.homepharmacy.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import by.grsu.homepharmacy.db.relation.ProducerWithDrugs;
import by.grsu.homepharmacy.repository.ProducerRepository;

public class ProducerViewModel extends AndroidViewModel {
    private ProducerRepository producerRepository;
    private LiveData<List<ProducerWithDrugs>> producers;

    public ProducerViewModel(Application application) {
        super(application);
        producerRepository = new ProducerRepository(application);
        producers = producerRepository.getProducers();
    }

    public LiveData<List<ProducerWithDrugs>> getProducers() {
        return producers;
    }
    public void insert(ProducerWithDrugs producer) {
        producerRepository.insert(producer);
    }
    public void delete(ProducerWithDrugs producer) {
        producerRepository.delete(producer);
    }
}
