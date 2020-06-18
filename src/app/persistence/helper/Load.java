package app.persistence.helper;

import famework.annotation.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

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
}
