package famework.event;

import java.util.ArrayList;
import java.util.Comparator;

public class EventRegistry {
    private ArrayList<Listener> listeners;

    public EventRegistry(){
        this.listeners = new ArrayList<>();
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }
    public ArrayList<Listener> getListeners(){
        return this.listeners;
    }
    public Event push(Event event){
        this.listeners.sort(new Comparator<Listener>() {
            @Override
            public int compare(Listener o1, Listener o2) {
                if(o1 == null) return -1;
                if(o2 == null) return 1;
                return Integer.compare(o1.getPrio(), o2.getPrio());
            }
        });
        Event eventResponse = event;

        for(Listener listener: this.listeners) {
            if (listener == null) continue;
            eventResponse = listener.update(event);
        }
        return eventResponse;
    }
}
