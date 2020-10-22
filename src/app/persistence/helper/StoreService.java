package app.persistence.helper;

import famework.annotation.Service;

import java.io.*;

@Service
public class StoreService {

    public boolean storeAsJOS(Object obj,String path) {
        try {
            File saveFile = new File(path);
            saveFile.delete();
            if ( saveFile.getParentFile() != null) {
                saveFile.getParentFile().mkdirs();
            }
            saveFile.createNewFile();

            FileOutputStream fileOutputStream = null;

            fileOutputStream = new FileOutputStream(saveFile);

            ObjectOutputStream out = null;

            out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(obj);
            out.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
