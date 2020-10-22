package app.cli.validators;

import app.cli.validators.Validator;
import famework.annotation.Service;

@Service
public class SearchInput implements Validator {
    public String error;
    private String type;
    private String arg2;
    private boolean hazardFilter;

    public String getType() {
        return type;
    }

    public String getArg2() {
        return arg2;
    }

    public boolean getHazardFilter() {
        return hazardFilter;
    }

    public Boolean isValid(String[] input) {
        if (input[0].equals("customer")) {
            this.type = "customer";
            return true;
        }
        if (input[0].equals("cargo") && input.length  == 2 ) {
            this.type = "cargo";
            this.arg2 = input[1];
            return true;
        }
        if (input[0].equals("hazard") && input.length == 2) {
            this.type = "hazard";
            if(input[1].equals("y")) {
                this.hazardFilter = true;
            } else {
                this.hazardFilter = false;
            }
            return true;
        }
        return false;
    }

    public String getMessage() {
        return this.error;
    }
}
