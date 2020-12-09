package by.grsu.homepharmacy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import by.grsu.homepharmacy.R;
import by.grsu.homepharmacy.db.entity.Producer;
import by.grsu.homepharmacy.db.relation.ProducerWithDrugs;

public class NewProducerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_producer);
        Button submit = (Button) findViewById(R.id.submit);
        submit.setText("create");
    }
    public void createProducer(View view)
    {
        EditText producerName = findViewById(R.id.producerName);
        EditText producerCountry = findViewById(R.id.producerCountry);

        Producer producer = new Producer();
        producer.setName(producerName.getText().toString());
        producer.setCountry(producerCountry.getText().toString());

        Intent intent = new Intent();
        intent.putExtra("producer", producer);
        setResult(RESULT_OK, intent);
        finish();
    }
}