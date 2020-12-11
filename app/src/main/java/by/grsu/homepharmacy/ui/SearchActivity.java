package by.grsu.homepharmacy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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
}