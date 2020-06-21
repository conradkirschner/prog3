package app.persistence.helper;

import famework.annotation.Service;

import java.beans.XMLEncoder;
import java.io.*;

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

    public boolean storeAsJOB(Object obj,String path) {
        File saveFile = new File(path );
        saveFile.delete();
        try {
            saveFile.getParentFile().mkdirs();
            saveFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        XMLEncoder enc = null;

        try
        {
            enc = new XMLEncoder( new FileOutputStream(path) );
            enc.writeObject(obj );
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
        finally {
            if ( enc != null )
                enc.close();
        }
        return true;
    }
}
