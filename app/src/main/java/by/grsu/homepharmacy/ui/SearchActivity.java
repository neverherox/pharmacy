package by.grsu.homepharmacy.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import by.grsu.homepharmacy.R;
import by.grsu.homepharmacy.adapter.DrugAdapter;
import by.grsu.homepharmacy.db.entity.Drug;
import by.grsu.homepharmacy.viewmodel.DrugViewModel;

public class SearchActivity extends AppCompatActivity {
    private DrugAdapter drugAdapter;
    private DrugViewModel drugViewModel;
    private ListView drugListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        drugListView = (ListView) findViewById(R.id.drugs);
        drugViewModel = new ViewModelProvider(this).get(DrugViewModel.class);
        EditText drugName = (EditText) findViewById(R.id.drugName);
        registerForContextMenu(drugListView);

        drugName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                drugViewModel.getDrugs(drugName.getText().toString()).observe(SearchActivity.this, new Observer<List<Drug>>()
                {
                    @Override
                    public void onChanged(List<Drug> drugs) {
                        drugAdapter = new DrugAdapter(SearchActivity.this, drugs);
                        drugListView.setAdapter(drugAdapter);
                        drugAdapter.notifyDataSetChanged();
                    }
                });
                drugListView.setAdapter(drugAdapter);
            }
        });
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
    public void updateDrug(Drug drug)
    {
        Intent intent = new Intent(SearchActivity.this, UpdateDrugActivity.class);
        intent.putExtra("drug", drug);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Drug drug = (Drug) data.getSerializableExtra("drug");
        drugViewModel.update(drug);
        super.onActivityResult(requestCode, resultCode, data);
    }
}