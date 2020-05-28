package warehouse.thread;

import warehouse.entity.Item;
import warehouse.model.Warehouse;

public class InsertThread extends Thread {
    private Warehouse warehouse;
    private Item item;
    public boolean success;

    public InsertThread(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.item = null;
        this.success = true;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public synchronized  void run() {
        while (success) {
            try {
                wait();
                if (this.item != null) {
                    int result = this.warehouse.store(item);
                    if (result == -1) { // Notify auslagerungsthread
                        success = false;
                    } else {
                        success = true;
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
