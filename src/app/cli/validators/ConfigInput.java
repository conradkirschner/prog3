package app.cli.validators;

import famework.annotation.Service;

import java.util.Arrays;

import static java.lang.Integer.parseInt;

@Service
public class ConfigInput implements Validator {
    public String error;
    private String type;
    private String[] storageNames;
    private String storageName;
    private int size;
    private int storageNumber;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getStorageNames() {
        return storageNames;
    }


    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStorageNumber() {
        return storageNumber;
    }

    public String getType() {
        return type;
    }

    public Boolean isValid(String input) {
        String[] inputs = input.split(" ");
        if (inputs[0].equals("storage")) {
            this.type = "storage";
            this.storageNames = Arrays.copyOfRange(inputs, 1, inputs.length);
            return true;
        }
        if (inputs[0].equals("place") && inputs.length  == 4 ) {
            this.type = "place";
            this.storageName = inputs[1];
            this.size = parseInt(inputs[2]);
            this.storageNumber = parseInt(inputs[3]);
            return true;
        }
        if (inputs[0].equals("activate") && inputs.length  == 2 ) {
            this.type = "activate";
            this.storageName = inputs[1];
            return true;
        }
        if (inputs[0].equals("deactivate") && inputs.length  == 2 ) {
            this.type = "deactivate";
            this.storageName = inputs[1];
            return true;
        }
        return false;
    }

    @Override
    public Boolean isValid(String[] input) {
        StringBuilder builder = new StringBuilder();
        for(String s : input) {
            builder.append(s).append(" ");
        }
        String str = builder.toString();
        return this.isValid(str);
    }

    public String getMessage() {
        return this.error;
    }
}
