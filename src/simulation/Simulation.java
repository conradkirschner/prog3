package simulation;

import app.EventStream;
import simulation.thread.InsertThread;
import simulation.thread.RemoveThread;
import storageContract.cargo.Hazard;
import user.entity.Customer;
import warehouse.entity.Item;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import static storageContract.cargo.Hazard.explosive;

public class Simulation {
    private EventStream eventStream;
    private Item item;

    public RemoveThread removeThread = null;
    public InsertThread insertThread = null;
    private final Object object = new Object();

    public Simulation(EventStream eventStream) {
        this.eventStream = eventStream;
        ArrayList<Hazard> hazards = new ArrayList<Hazard>();
        hazards.add(explosive);
        Customer customer = (Customer) this.eventStream.pushData("user:new", "simulatorUser");

        this.item = new Item(new BigDecimal("50"), customer, hazards,  ZonedDateTime.now());
    }

    public void start() {
        this.removeThread = new RemoveThread(this.eventStream, this.object);
        this.insertThread = new InsertThread(this.eventStream, this.item, this.object);
        removeThread.start();
        insertThread.start();


    }
}
