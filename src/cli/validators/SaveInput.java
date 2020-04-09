package cli.validators;

import java.util.ArrayList;

public class SaveInput implements Validator {
    public String error;

    public Boolean isValid(String[] input) {
        return null;
    }

    public String getMessage() {
        return this.error;
    }
}
