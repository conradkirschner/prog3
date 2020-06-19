package famework.configReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    public Properties getConfig(Class clazz) {
        String[] packageName = clazz.getName().split("\\.");

        String path = resolveClassLocation(clazz) + "config/" + packageName[1] + ".properties";
        Properties config = new Properties();
        try {
            InputStream input = new FileInputStream(path);
            config.load(input);
        } catch (IOException e) {
            return mergeWithSysProperties(new Properties());

        }
        return mergeWithSysProperties(config);
    }

    private String resolveClassLocation(Class clazz) {
        return clazz.getProtectionDomain().getCodeSource().getLocation().getPath();
    }
    private Properties mergeWithSysProperties(Properties fileProperties){
        Properties merged = new Properties();
        Properties system = System.getProperties();

        merged.putAll(fileProperties);
        merged.putAll(system);
        return merged;
    }
}
