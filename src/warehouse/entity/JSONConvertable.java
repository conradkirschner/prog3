package warehouse.entity;

import java.text.ParseException;

public interface JSONConvertable {
    public String getJson();
    public void restoreFromJson(String json) throws ParseException;
}
