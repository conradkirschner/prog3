package app.persistence.helper;

import famework.annotation.Service;

import java.beans.PersistenceDelegate;
import java.beans.XMLEncoder;
import java.io.*;
import java.math.BigDecimal;

@Service
public class Store {

    public boolean storeAsJOS(Object obj,String path) {
        File saveFile = new File(path);
        saveFile.delete();
        try {
            saveFile.getParentFile().mkdirs();
            saveFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(saveFile);
        } catch (FileNotFoundException ignored) {}

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(obj);
            out.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean storeAsJBP(Object obj,String path) {

        XMLEncoder encoder = null;

        try
        {
            encoder = new XMLEncoder( new FileOutputStream(path) );

            PersistenceDelegate persistenceDelegate = encoder.getPersistenceDelegate(Integer.class);
            encoder.setPersistenceDelegate(BigDecimal.class, persistenceDelegate);
            encoder.writeObject( obj );
        }
        catch ( IOException e ) {
            e.printStackTrace();
            return false;
        }
        finally {
            if ( encoder != null )
                encoder.close();
        }

        return true;
    }
}
