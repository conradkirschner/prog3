package cli.events;

import app.events.Event;

public class CloseCliEvent implements Event {
    private int status;

    public CloseCliEvent(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
