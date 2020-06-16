package app.cli;

import famework.annotation.Service;
import famework.configReader.ConfigBag;

@Service
public class CliManager {
    ConfigBag configBag;

    public CliManager(ConfigBag configBag) {
        this.configBag = configBag;
        ClassLoader classLoader = CliManager.class.getClassLoader();
        try {
            Class aClass = classLoader.loadClass("com.jenkov.MyClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(System.out.getClass());
        String output = configBag.props.getProperty("output");
        String input = configBag.props.getProperty("input");
    }

    public int run() {

        return 0;
    }
}
