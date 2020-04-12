package cli.validators;

import java.util.ArrayList;

public class SearchInput implements Validator {
    public String error;
    private String type;

    public String getType() {
        return type;
    }

    public Boolean isValid(String[] input) {
        if (input[0].equals("customer")) {
            this.type = "customer";
            return true;
        }
        if (input[0].equals("cargo") && input.length  == 2 ) {
            this.type = "cargo";
            return true;
        }
        if (input[0].equals("hazard") && input.length == 2 && (input[1].equals("y") || input[1].equals("n")) ) {
            this.type = "hazard:" + input[1];
            return true;
        }
        return false;
    }

    public String getMessage() {
        return this.error;
    }
}
