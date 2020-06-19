package famework.event;

public interface Listener {
    public Event update(Event event);
    public int getPrio();
}
