package by.grsu.homepharmacy.db.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;

import by.grsu.homepharmacy.db.converter.FormConverter;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.NO_ACTION;

@Entity
public class Drug implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int drugId;

    @ForeignKey
            (entity = Producer.class,
                    parentColumns = "producerId",
                    childColumns = "producer_id",
                    onDelete = NO_ACTION
            )
    private int producer_id;

    @ColumnInfo(name = "drug_name")
    private String name;

    private String description;

    @TypeConverters({FormConverter.class})
    private Form form;

    private String expirationDate;

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public int getProducer_id() {
        return producer_id;
    }

    public void setProducer_id(int producer_id) {
        this.producer_id = producer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

}
