package app.persistence.helper;

import famework.annotation.Service;

import javax.xml.bind.JAXB;
import java.io.*;

@Service
public class Load {

    public Object loadFromJOS(byte[] byteArray) {
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
        ObjectInput in = null;
        Object obj = null;
        try {
            in = new ObjectInputStream(bis);
            obj = in.readObject();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }

        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException ignored) {
        }
        return obj;
    }
    public Object loadFromJBP(String path, Class clazz) {
        File saveFile = new File(path);
        Object objClass = JAXB.unmarshal(saveFile, clazz);
        return objClass;
    }
}
