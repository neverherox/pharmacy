package by.grsu.homepharmacy.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import by.grsu.homepharmacy.R;
import by.grsu.homepharmacy.adapter.DrugAdapter;
import by.grsu.homepharmacy.adapter.ProducerWithDrugsAdapter;
import by.grsu.homepharmacy.db.entity.Drug;
import by.grsu.homepharmacy.db.entity.Form;
import by.grsu.homepharmacy.db.entity.Producer;
import by.grsu.homepharmacy.db.relation.ProducerWithDrugs;
import by.grsu.homepharmacy.viewmodel.DrugViewModel;
import by.grsu.homepharmacy.viewmodel.ProducerViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProducerWithDrugsAdapter producerWithDrugsAdapter;
    private ProducerViewModel producerViewModel;
    private  ListView producerListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        producerViewModel = new ViewModelProvider(this).get(ProducerViewModel.class);
        producerListView = (ListView) findViewById(R.id.producers);
        registerForContextMenu(producerListView);
        setOnItemClickListener();


        producerViewModel.getProducers().observe(this, new Observer<List<ProducerWithDrugs>>()
        {
            @Override
            public void onChanged(List<ProducerWithDrugs> producerWithDrugs) {
                producerWithDrugsAdapter = new ProducerWithDrugsAdapter(MainActivity.this, producerWithDrugs);
                producerListView.setAdapter(producerWithDrugsAdapter);
                producerWithDrugsAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            ProducerWithDrugs producer = (ProducerWithDrugs) producerListView.getItemAtPosition(info.position);

            menu.add("Delete");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ProducerWithDrugs producer = (ProducerWithDrugs) producerListView.getItemAtPosition(info.position);
        producerViewModel.delete(producer);
        return super.onContextItemSelected(item);
    }

    public void setOnItemClickListener()
    {
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                ProducerWithDrugs producer = (ProducerWithDrugs) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, DrugsActivity.class);
                intent.putExtra("drugs",  (ArrayList<Drug>)producer.getDrugs());
                startActivity(intent);
            }
        };
        producerListView.setOnItemClickListener(itemListener);
    }

    public void add(View view)
    {
        Drug drug = new Drug();
        drug.setName("123");
        drug.setDescription("123");
        drug.setExpirationDate("123");
        drug.setForm(Form.GASEOUS);


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