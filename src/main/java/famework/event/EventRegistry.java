package famework.event;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;

public class EventRegistry {
    private ArrayList<Listener> listeners;

    public EventRegistry(){
        this.listeners = new ArrayList<>();
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
        System.out.println("Adding Listener");
        this.listeners.sort(new Comparator<Listener>() {
            @Override
            public int compare(Listener o1, Listener o2) {
                if(o1 == null) return -1;
                if(o2 == null) return 1;
                return Integer.compare(o1.getPrio(), o2.getPrio());
            }
        });
    }
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }
    public ArrayList<Listener> getListeners(){
        return this.listeners;
    }
    public Event push(Event event){

        Event eventResponse = event;

        try {
            for(Listener listener: this.listeners) {
                if (listener == null) continue;
                Event response = listener.update(eventResponse);
                if(response != null) {
                    eventResponse = (Event) this.removeReferences(response);
                }
            }
        } catch (ConcurrentModificationException exception) {
            // disable fail-fast from iteration list (repush if fail)
            return push(event);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return eventResponse;
    }

    /**
     * This is the Object Firewall
     *
     * @param obj
     * @return
     * @throws Exception
     */
    private Object removeReferences(Object obj) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream( byteArrayOutputStream );
        outputStream.writeObject(obj);
        outputStream.close();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));

        return objectInputStream.readObject();
    }
}
