package app.persistence.helper;

import famework.annotation.Service;

import java.io.ByteArrayInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

@Service
public class LoadService {

    public Object loadFromJOS(byte[] byteArray) {
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
        ObjectInput in = null;
        Object obj = null;
        try {
            in = new ObjectInputStream(bis);
            obj = in.readObject();
            in.close();
        } catch (Exception ignored) {

        }

        return obj;
    }
}
