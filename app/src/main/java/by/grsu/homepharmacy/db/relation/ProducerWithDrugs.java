package by.grsu.homepharmacy.db.relation;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

import by.grsu.homepharmacy.db.entity.Drug;
import by.grsu.homepharmacy.db.entity.Producer;

public class ProducerWithDrugs {
    @Embedded
    private Producer producer;
    @Relation(
            parentColumn = "producerId",
            entityColumn = "producer_id",
            entity = Drug.class
    )
    private List<Drug> drugs = new ArrayList<>();

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }
}
