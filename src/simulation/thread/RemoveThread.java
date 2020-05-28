package simulation.thread;

import app.EventStream;
import app.events.Event;
import simulation.Simulation;
import warehouse.entity.Item;
import warehouse.model.Warehouse;
import warehouseManager.entity.AllItems;
import warehouseManager.entity.AllWarehouses;

public class RemoveThread extends Thread {
    private EventStream eventStream;
    private final Object monitor;

    public RemoveThread(EventStream eventStream, Object monitor) {
        this.eventStream = eventStream;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        synchronized (this.monitor) {
            while (true) {
                try {
                    this.monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                AllItems items = (AllItems) this.eventStream.pushData("warehouse-manager:get-all-items", "");
                int size = items.getItems().size();
                int deleteItemIndex = (int) ((Math.random() * ((size) + 1))-1);
                this.eventStream.pushData("warehouse:delete-item", items.getItems().get(deleteItemIndex).getId());
                this.monitor.notify();
            }
        }
    }
}
