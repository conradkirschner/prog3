package famework;

import famework.annotation.AutoloadListener;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.di.DI;
import famework.di.Registry;
import famework.event.EventHandler;
import famework.event.Listener;
import famework.event.Subscriber;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Kernel {
    DI di;
    EventHandler eventHandler;
    public EventHandler run(ArrayList<Object> imports) {
        this.eventHandler = new EventHandler();
        imports.add(this.eventHandler);
        this.di = new DI();
        imports.add(this.di);
        imports.add(this.di.getRegistry());
        Registry registry = this.di.getRegistry();
        registry.add(imports);
        File directory = new File("./src/app/");

        Class[] classes = null;
        try {
            classes = getClasses("app");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadServices(classes,0);
        loadClasses(classes,0);

        return eventHandler;
    }
    private ArrayList<Class> loadServices(Class[] classes, int retries) {
        boolean failure = false;

        ArrayList<Class> classArrayList = new ArrayList<>();
        for(Class obj : classes) {
            try {
                resolve(obj);
            } catch (Exception e){
                classArrayList.add(obj);
                failure=true;
                e.printStackTrace();
            }
        }
        if(failure){
            return  loadServices(classArrayList.toArray(new Class[classArrayList.size()]),retries+1);
        }
      return null;
    }
    private ArrayList<Class> loadClasses(Class[] classList, int retries) {
        if (retries > 10) {
            System.out.println("Error in Service Annotation found!");
            System.exit(-1);
        }
        System.out.println("Loaded Class");
        ArrayList<Class> waitingLine = new ArrayList<>();
        for(Class clazz : classList) {
            try {
                inject(clazz);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                // not loaded
                waitingLine.add(clazz);
            }
        }
        if (waitingLine.size() != 0) {
            loadClasses(waitingLine.toArray(new Class[waitingLine.size()]), retries+1);
        }
        return null;

    }
    private void inject(Class obj) throws NoSuchFieldException, IllegalAccessException {

        Field[] fields = obj.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                field.set(this.di.getRegistry().getRegistered(obj.getName()), this.di.getRegistry().getRegistered(field.getType().getName()));
            }
        }
    }

    private void addService(Class obj){
        this.di.getRegistry().add(obj);
    }

    private boolean resolve(Class obj) throws InstantiationException, IllegalAccessException {
            Annotation[] c = obj.getAnnotations();
            if (c == null) return true;
            for(Annotation annotation: c) {
                if(annotation instanceof Service) {
                    addService(obj);
                }
                if(annotation instanceof AutoloadSubscriber) {
                        addSubscriber(obj);
                }
                if(annotation instanceof AutoloadListener) {
                    addListener(obj);
                }
            }
        return false;
    }
    private void addSubscriber(Class object) throws IllegalAccessException, InstantiationException {
        this.eventHandler.registerSubscriber((Subscriber) this.di.getRegistry().getRegistered(object.getName()));

    }
    private void addListener(Class object) throws IllegalAccessException, InstantiationException {
        this.eventHandler.registerListener((Listener) this.di.getRegistry().getRegistered(object.getName()));
    }

    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @source http://stackoverflow.com/questions/520328/can-you-find-all-classes-in-a-package-using-reflection
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private static Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @source http://stackoverflow.com/questions/520328/can-you-find-all-classes-in-a-package-using-reflection
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
