package app.warehouse.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class StoragePlace implements Serializable {
    private int storageID;
    private ArrayList<app.warehouse.entity.Item> items;
    private BigDecimal maxSize;

    public StoragePlace() {
    }

    public void setStorageID(int storageID) {
        this.storageID = storageID;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setMaxSize(BigDecimal maxSize) {
        this.maxSize = maxSize;
    }

    public StoragePlace(int storageID, BigDecimal maxSize) {
        this.maxSize = maxSize;
        this.storageID = storageID;
        this.items = new ArrayList<app.warehouse.entity.Item>();
    }

    public app.warehouse.entity.Item getItemById(String itemId) {
        for(app.warehouse.entity.Item item:this.items) {
            if (itemId == null) return item;
            if (item.getId().equals(itemId)) return item;
        }
        return null;
    }
    public app.warehouse.entity.Item getItemByType(String type) {
        for(app.warehouse.entity.Item item:this.items) {
            if (type == null) return item;
            if (item.type.equals(type)) return item;
        }
        return null;
    }

    public app.warehouse.entity.Item getItem(boolean hazardFilter) {
        for(app.warehouse.entity.Item item:this.items) {
            if (!hazardFilter == (item.getHazards().size() == 0)) return item;
        }
        return null;
    }

    public ArrayList<app.warehouse.entity.Item> getItems() {
        return this.items;
    }

    public void setItem(app.warehouse.entity.Item item) {
        if (item.getId() != null) {
            if (item.getId().charAt(0) == '#') {
                item.setId("#" + item.getId() );
            }
        } else {
            item.setId("#" + item.getId() );
        }
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
