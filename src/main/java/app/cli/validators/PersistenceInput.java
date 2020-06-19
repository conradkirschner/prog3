package app.cli.validators;

import app.cli.validators.Validator;
import famework.annotation.Service;

@Service
public class PersistenceInput implements Validator {
    public String error;

    private String type;

    public Boolean isValid(String[] input) {
        if (input[0].equals("y")) {
            type = "JOS";
            return true;
        }
        if (input[0].equals("x")) {
            type = "JBP";
            return true;
        }
        return true;
    }

    public String getMessage() {
        return this.error;
    }

    public String getType() {
        return type;
    }
}
