package by.grsu.homepharmacy.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import by.grsu.homepharmacy.db.entity.Producer;
import by.grsu.homepharmacy.db.relation.ProducerWithDrugs;
import by.grsu.homepharmacy.repository.ProducerRepository;

public class ProducerViewModel extends AndroidViewModel {
    private ProducerRepository producerRepository;

    public ProducerViewModel(Application application) {
        super(application);
        producerRepository = new ProducerRepository(application);
    }

    public LiveData<List<ProducerWithDrugs>> getProducers() {
        return producerRepository.getProducers();
    }
    public void insert(ProducerWithDrugs producer) {
        producerRepository.insert(producer);
    }
    public void delete(ProducerWithDrugs producer) {
        producerRepository.delete(producer);
    }
    public void update(Producer producer) {producerRepository.update(producer);}

}
