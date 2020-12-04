package by.grsu.homepharmacy.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Producer implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int producerId;

    @ColumnInfo(name = "producer_name")
    private String name;

    private String country;

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
