package famework.di;

public class DI {
    private Registry registry;

    public DI() {
        this.registry = new Registry();
    }

    public Registry getRegistry() {
        return registry;
    }
}
