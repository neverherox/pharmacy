package by.grsu.homepharmacy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import by.grsu.homepharmacy.R;
import by.grsu.homepharmacy.db.entity.Producer;

public class UpdateProducerActivity extends AppCompatActivity {

    private Producer producer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_producer);

        Bundle arguments = getIntent().getExtras();
        producer = (Producer)arguments.getSerializable("producer");
    }
    public void updateProducer(View view)
    {
        EditText producerName = findViewById(R.id.producerName);
        EditText producerCountry = findViewById(R.id.producerCountry);

        producer.setName(producerName.getText().toString());
        producer.setCountry(producerCountry.getText().toString());

        Intent intent = new Intent();
        intent.putExtra("producer", producer);
        setResult(RESULT_OK, intent);
        finish();
    }
}