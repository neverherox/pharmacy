package by.grsu.homepharmacy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import by.grsu.homepharmacy.R;
import by.grsu.homepharmacy.adapter.DrugAdapter;
import by.grsu.homepharmacy.adapter.ProducerWithDrugsAdapter;
import by.grsu.homepharmacy.db.entity.Drug;
import by.grsu.homepharmacy.db.entity.Form;
import by.grsu.homepharmacy.db.entity.Producer;
import by.grsu.homepharmacy.db.relation.ProducerWithDrugs;
import by.grsu.homepharmacy.viewmodel.DrugViewModel;
import by.grsu.homepharmacy.viewmodel.ProducerViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProducerWithDrugsAdapter producerWithDrugsAdapter;
    private ProducerViewModel producerViewModel;
    private DrugViewModel drugViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drugViewModel = new ViewModelProvider(this).get(DrugViewModel.class);
        producerViewModel = new ViewModelProvider(this).get(ProducerViewModel.class);

        ListView countriesList = (ListView) findViewById(R.id.countriesList);

        drugViewModel.getDrugs().observe(this, new Observer<List<Drug>>() {
            @Override
            public void onChanged(List<Drug> drugWithProducers) {
                DrugAdapter drugAdapter = new DrugAdapter(MainActivity.this, drugWithProducers);
                //countriesList.setAdapter(drugAdapter);
                //drugAdapter.notifyDataSetChanged();
            }
        });

        producerViewModel.getProducers().observe(this, new Observer<List<ProducerWithDrugs>>()
        {

            @Override
            public void onChanged(List<ProducerWithDrugs> producerWithDrugs) {
                producerWithDrugsAdapter = new ProducerWithDrugsAdapter(MainActivity.this, producerWithDrugs);
                countriesList.setAdapter(producerWithDrugsAdapter);
                producerWithDrugsAdapter.notifyDataSetChanged();
            }
        });
    }
    public void add(View view)
    {
        Drug drug = new Drug();
        drug.setName("123");
        drug.setDescription("123");
        drug.setExpirationDate("123");
        drug.setForm(Form.GASEOUS);

        drugViewModel.insert(drug);

        Producer producer = new Producer();
        producer.setName("----");
        producer.setCountry("----");

        ProducerWithDrugs producerWithDrugs = new ProducerWithDrugs();
        producerWithDrugs.getDrugs().add(drug);
        producerWithDrugs.getDrugs().add(drug);

        producerWithDrugs.setProducer(producer);
        producerViewModel.insert(producerWithDrugs);

    }
}