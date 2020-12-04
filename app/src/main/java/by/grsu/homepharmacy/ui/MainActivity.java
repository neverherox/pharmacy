package by.grsu.homepharmacy.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

            menu.add("delete");
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
                intent.putExtra("producerId",producer.getProducer().getProducerId());
                startActivity(intent);
            }
        };
        producerListView.setOnItemClickListener(itemListener);
    }

    public void createProducer(View view)
    {
        Intent intent = new Intent(MainActivity.this, NewProducerActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Producer producer = (Producer) data.getSerializableExtra("producer");
        ProducerWithDrugs newProducer = new ProducerWithDrugs();

        newProducer.setProducer(producer);
        producerViewModel.insert(newProducer);

        super.onActivityResult(requestCode, resultCode, data);
    }
}