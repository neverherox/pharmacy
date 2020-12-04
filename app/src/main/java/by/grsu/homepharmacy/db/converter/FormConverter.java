package by.grsu.homepharmacy.db.converter;

import androidx.room.TypeConverter;

import by.grsu.homepharmacy.db.entity.Form;

public class FormConverter {
    @TypeConverter
    public String fromForm(Form form) {
        return form.toString();
    }

    @TypeConverter
    public Form toForm(String data) {
        return Form.valueOf(data);
    }
}
