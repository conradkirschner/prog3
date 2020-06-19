package app.cli.validators;

import java.util.ArrayList;

public interface Validator  {
    Boolean isValid(String[] input);
    String getMessage();
}
