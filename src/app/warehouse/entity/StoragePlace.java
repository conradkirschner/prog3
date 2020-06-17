package app.warehouse.entity;

import java.math.BigDecimal;
import java.util.ArrayList;

public class StoragePlace {
    private int storageID;
    private ArrayList<Item> items;
    private BigDecimal maxSize;

    public StoragePlace(int storageID, BigDecimal maxSize) {
        this.maxSize = maxSize;
        this.storageID = storageID;
        this.items = new ArrayList<Item>();
    }

    public Item getItem(String type) {
        for(Item item:this.items) {
            if (type == null) return item;
            if (item.type.equals(type)) return item;
        }
        return null;
    }

    public Item getItem(boolean hazardFilter) {
        for(Item item:this.items) {
            if (!hazardFilter == (item.getHazards().size() == 0)) return item;
        }
        return null;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public void setItem(Item item) {

       item.setId("#" + item.getId() );
        this.items.add(item);
    }

    public boolean removeItem(String itemId) {
        int index = -1;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId().equals(itemId)) {
                index = i;
            }
        }
        if (index != -1) {
            this.items.remove(index);
            return true;
        }
        return false;
    }
    public int getStorageID() {
        return this.storageID;
    }

    public BigDecimal getMaxSize() {
        return maxSize;
    }

    public BigDecimal getUsedSize() {
        BigDecimal totalSize = new BigDecimal(0);
        for (Item item : this.items) {
            totalSize = totalSize.add(item.getValue());
        }
        return totalSize;
    }

    public BigDecimal getLeftSpace() {
        return this.maxSize.subtract(this.getUsedSize());
    }


}
