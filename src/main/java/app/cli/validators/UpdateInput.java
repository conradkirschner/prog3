package app.cli.validators;

import app.cli.validators.Validator;
import famework.annotation.Service;

@Service
public class UpdateInput implements Validator {
    public String error;
    private String id;

    public Boolean isValid(String[] input) {
        boolean allowed =  (!input[0].equals("") && input.length == 1  && input[0].charAt(0) == '#');
        if (allowed) {
            id = input[0];
            return true;
        }
        return false;
    }

    public String getMessage() {
        return this.error;
    }

    public String getId() {
        return id;
    }
}
