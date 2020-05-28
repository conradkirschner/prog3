package warehouse.thread;

import warehouse.model.Warehouse;

public class RemoveThread extends Thread {
    private Warehouse warehouse;

    public RemoveThread(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void run() {

    }
}
