package by.grsu.homepharmacy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import by.grsu.homepharmacy.R;
import by.grsu.homepharmacy.adapter.DrugAdapter;
import by.grsu.homepharmacy.db.entity.Drug;
import by.grsu.homepharmacy.db.relation.ProducerWithDrugs;

public class
DrugsActivity extends AppCompatActivity {
    private ListView drugListView;
    private List<Drug> drugs;
    private DrugAdapter drugAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs);
        drugListView = (ListView) findViewById(R.id.drugs);

        Bundle arguments = getIntent().getExtras();
        drugs = (ArrayList<Drug>)getIntent().getSerializableExtra("drugs");

        drugAdapter = new DrugAdapter(this, drugs);

        drugListView.setAdapter(drugAdapter);
    }
}