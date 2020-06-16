package app.cli.validators;

public class DeleteInput implements Validator {
    public String error;

    public Boolean isValid(String[] input) {
        return null;
    }

    public String getMessage() {
        return this.error;
    }
}
