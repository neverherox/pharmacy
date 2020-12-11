package by.grsu.homepharmacy.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

import by.grsu.homepharmacy.R;
import by.grsu.homepharmacy.db.entity.Drug;

public class DrugAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<Drug> drugs;

    public DrugAdapter(Context context, List<Drug> drugs) {
        ctx = context;
        this.drugs = drugs;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return drugs.size();
    }

    @Override
    public Object getItem(int position) {
        return drugs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.drug_item, parent, false);
        }

        TextView drugName = (TextView) view.findViewById(R.id.drugName);
        TextView drugDescription = (TextView) view.findViewById(R.id.drugDescription);
        TextView drugExpirationDate = (TextView) view.findViewById(R.id.drugExpirationDate);
        TextView drugForm = (TextView) view.findViewById(R.id.drugForm);
        ImageView drugImage = (ImageView) view.findViewById(R.id.drugImage);

        Drug drug = drugs.get(position);

        drugName.setText(drug.getName());
        drugDescription.setText(drug.getDescription());
        drugExpirationDate.setText(drug.getExpirationDate());
        drugForm.setText(drug.getForm().toString());
        Uri uri = Uri.parse(drug.getImageUri());
        drugImage.setImageURI(uri);
        return view;
    }
}
