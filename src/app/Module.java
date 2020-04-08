package app;

public class Module implements app.events.Module {
    public EventStream eventStream;
    private App app;

    public Module(App app) {
        this.app = app;
        this.eventStream =  new EventStream(app);
    }

    @Override
    public String getName() {
        return "event-stream";
    }
}
