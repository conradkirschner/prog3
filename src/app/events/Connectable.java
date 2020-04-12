package app.events;

import app.EventStream;

public interface Connectable {
    public void connectToStream(EventStream eventStream);
}
