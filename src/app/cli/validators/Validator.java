package app.cli.validators;

public interface Validator  {
    Boolean isValid(String[] input);
    String getMessage();
}
