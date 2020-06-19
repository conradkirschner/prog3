package app.cli.validators;

import app.cli.validators.Validator;
import famework.annotation.Service;

@Service
public class DeleteInput implements Validator {
    public String error;

    private String username;
    private String position;

    public Boolean isValid(String[] input) {
        if (input[0].equals("")) return false;

        if(input[0].charAt(0) == '#') {
            position = input[0];
            return true;
        }
        username = input[0];
        return true;
    }

    public String getMessage() {
        return this.error;
    }

    public String getUsername() {
        String tmp = username;
        username = null;
        return tmp;
    }

    public String getPosition() {
        String tmp = position;
        position = null;
        return tmp;
    }
}
