package app.events;

import app.EventStream;

public interface Connectable extends RegisterModuleEvent {
    public void connectToStream(EventStream eventStream);
}
