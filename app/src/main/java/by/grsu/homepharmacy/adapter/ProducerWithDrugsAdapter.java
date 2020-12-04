package by.grsu.homepharmacy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import by.grsu.homepharmacy.R;
import by.grsu.homepharmacy.db.relation.ProducerWithDrugs;

public class ProducerWithDrugsAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<ProducerWithDrugs> producers;

    public ProducerWithDrugsAdapter(Context context, List<ProducerWithDrugs> producers) {
        ctx = context;
        this.producers = producers;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return producers.size();
    }

    @Override
    public Object getItem(int position) {
        return producers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.producer_item, parent, false);
        }

        TextView producerName = (TextView) view.findViewById(R.id.producerName);
        TextView producerCountry = (TextView) view.findViewById(R.id.producerCountry);

        ProducerWithDrugs producer = producers.get(position);

        producerName.setText(producer.getProducer().getName());
        producerCountry.setText(producer.getProducer().getCountry());

        return view;
    }

}
