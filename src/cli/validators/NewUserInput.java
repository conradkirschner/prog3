package cli.validators;

import java.util.ArrayList;

/**
 * There is no real validation required!
 */
public class NewUserInput implements Validator {
    public String input;
    public String error;
    private String username;

    public Boolean isValid(String[] input) {
        this.username = input[0];
        return input.length == 1;
    }

    public String getMessage() {
        return this.error;
    }

    public String get() {
        return this.username;
    }
}
