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

import java.util.List;

import by.grsu.homepharmacy.R;
import by.grsu.homepharmacy.adapter.DrugAdapter;
import by.grsu.homepharmacy.db.entity.Drug;
import by.grsu.homepharmacy.viewmodel.DrugViewModel;

public class DrugsActivity extends AppCompatActivity {
    private DrugViewModel drugViewModel;
    private DrugAdapter drugAdapter;
    private ListView drugListView;
    private int producerId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs);
        drugViewModel = new ViewModelProvider(this).get(DrugViewModel.class);

        drugListView = (ListView) findViewById(R.id.drugs);
        registerForContextMenu(drugListView);

        Bundle arguments = getIntent().getExtras();
        producerId = arguments.getInt("producerId");

        drugViewModel.getDrugs(producerId).observe(this, new Observer<List<Drug>>()
        {
            @Override
            public void onChanged(List<Drug> drugs) {
                drugAdapter = new DrugAdapter(DrugsActivity.this, drugs);
                drugListView.setAdapter(drugAdapter);
                drugAdapter.notifyDataSetChanged();
            }
        });

        drugListView.setAdapter(drugAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Drug drug = (Drug) drugListView.getItemAtPosition(info.position);

        menu.add(0,1,0,"delete");
        menu.add(0,2,0,"update");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Drug drug = (Drug) drugListView.getItemAtPosition(info.position);
        switch (item.getItemId())
        {
            case 1: drugViewModel.delete(drug); break;
            case 2: updateDrug(drug); break;

        }
        return super.onContextItemSelected(item);
    }

    public void createDrug(View view)
    {
        Intent intent = new Intent(DrugsActivity.this, NewDrugActivity.class);
        startActivityForResult(intent,1);
    }

    public void updateDrug(Drug drug)
    {
        Intent intent = new Intent(DrugsActivity.this, UpdateDrugActivity.class);
        intent.putExtra("drug", drug);
        startActivityForResult(intent,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Drug drug = (Drug) data.getSerializableExtra("drug");
        switch(requestCode)
        {
            case 1: drug.setProducer_id(producerId);
                    drugViewModel.insert(drug);
                    break;
            case 2: drugViewModel.update(drug);
                    break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}