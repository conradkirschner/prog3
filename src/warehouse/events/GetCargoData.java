package warehouse.events;

import app.events.Event;
import warehouse.entity.Item;

import java.util.ArrayList;

public class GetCargoData implements Event {
    private ArrayList<Item> item;

    public GetCargoData(ArrayList<Item> item) {
        this.item = item;
    }

    public ArrayList<Item> getItems() {
        return item;
    }

    public GetCargoData addItems(ArrayList<Item> item) {
        this.item.addAll(item);
        return this;
    }
}
