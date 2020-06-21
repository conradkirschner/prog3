package famework.di;

import famework.configReader.ConfigBag;
import famework.configReader.ConfigReader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Properties;

public class Registry {
    private ArrayList<Class> registered;
    private ArrayList<Object> registeredInstances;


    public Registry() {
        this.registered = new ArrayList<Class>();
        this.registeredInstances = new ArrayList<Object>();
    }

    public ArrayList<Class> getRegistered() {
        return registered;
    }
    public Object getRegistered(String name) {
        for(Object registeredInsanze: registeredInstances){
            if(name.equals(registeredInsanze.getClass().getName())){
                return registeredInsanze;
            }
        }
        return null;
    }

    public Registry add(Object obj) {
        this.registeredInstances.add(obj);
        return this;
    }
    public Registry add(ArrayList<Object> obj) {
        for (Object newObject: obj) {
            add(newObject);
        }
        return this;
    }
    public Registry add(Class obj) {
        ConfigReader configReader = new ConfigReader();
        Properties properties = configReader.getConfig(obj);

        ArrayList<Object> parameters = new ArrayList<>();

        Constructor[] ctor = null;
        ctor = obj.getConstructors();
        for(Constructor currentConstructor: ctor) {

            parameters = new ArrayList<>();
            for(Parameter parameter :currentConstructor.getParameters()){
                if (parameter.getType().equals(ConfigBag.class)){
                    parameters.add(new ConfigBag(properties));
                } else {
                    Object registeredClass = getRegistered(parameter.getType().getName());
                    if (registeredClass != null) {
                        parameters.add(registeredClass);
                    } else
                    {
                        // todo: wait until class is loaded!
                    }
                }
            }

        }

        Object object = null;
        try {
            if(ctor == null) {
                object = obj.newInstance();
            } else {
                try {
                    Object[] parameter = parameters.toArray();
                    object = ctor[0].newInstance(parameter);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        this.registeredInstances.add(object);
        return this;
    }
}
