package app.cli.validators;

import famework.annotation.Service;

/**
 * There is no real validation required!
 */
@Service
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
