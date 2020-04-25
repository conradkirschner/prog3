package app;

public class Module implements app.events.Module {
    public EventStream eventStream;
    private App app;

    public Module(App app) {
        this.app = app;
        this.eventStream =  new EventStream(app);
    }
    public EventStream getModule() {
        return this.eventStream;
    }
    @Override
    public String getName() {
        return "event-stream";
    }
}
