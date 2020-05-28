package simulation.thread;

import app.EventStream;
import app.events.Event;
import simulation.Simulation;
import warehouse.entity.Item;
import warehouse.model.Warehouse;
import warehouseManager.entity.AllWarehouses;

public class InsertThread extends Thread {
    private EventStream eventStream;
    private Item item;
    private final Object monitor;

    public InsertThread(EventStream eventStream, Item item, Object monitor) {
        this.eventStream = eventStream;
        this.item = item;
        this.monitor = monitor;

    }

    @Override
    public void run(){
        while(true){
        synchronized (this.monitor) {
            Error error = null;
            while (error == null) {
                AllWarehouses warehouses = (AllWarehouses) this.eventStream.pushData("warehouse-manager:get-all", "");
                for (Warehouse warehouse: warehouses.getWarehouses()) {
                    Event event = this.eventStream.pushData("warehouse:store-item", warehouse.getWarehouseName() + "$$" + this.item.getJson());
                    if (!(event instanceof Error)) { // no error means success
                        error = null;
                        break;
                    } else {
                        error = (Error) event;
                    }
                }
            }
            // it seems currently all warehouses are full
            try {
                this.monitor.notify();
                this.monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    }
}
