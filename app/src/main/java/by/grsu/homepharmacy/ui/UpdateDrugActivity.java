package by.grsu.homepharmacy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import by.grsu.homepharmacy.R;
import by.grsu.homepharmacy.db.entity.Drug;
import by.grsu.homepharmacy.db.entity.Form;
import by.grsu.homepharmacy.db.entity.Producer;

public class UpdateDrugActivity extends AppCompatActivity {

    private Drug drug;
    private String[] forms = {Form.SOFT.toString(), Form.SOLID.toString(), Form.GASEOUS.toString(), Form.LIQUID.toString()};
    private String form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_drug);


        Bundle arguments = getIntent().getExtras();
        drug = (Drug)arguments.getSerializable("drug");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, forms);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Drug form");
        spinner.setSelection(2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                form = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
    public void updateDrug(View view)
    {
        EditText drugName = findViewById(R.id.drugName);
        EditText drugDescription = findViewById(R.id.drugDescription);
        EditText drugExpirationDate = findViewById(R.id.drugExpirationDate);

        drug.setName(drugName.getText().toString());
        drug.setDescription(drugDescription.getText().toString());
        drug.setExpirationDate(drugExpirationDate.getText().toString());

        drug.setForm(Form.valueOf(form));

        Intent intent = new Intent();
        intent.putExtra("drug", drug);
        setResult(RESULT_OK, intent);
        finish();
    }
}