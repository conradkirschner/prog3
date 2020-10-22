package app.example;

import famework.event.Event;

public class TestEvent implements Event {
    static String name = TestEvent.class.getName();
    @Override
    public String getName() {
        return name;
    }
    public static TestEvent create(){
        return new TestEvent();
    }
}
