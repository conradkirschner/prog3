package app.cli.helper;


import java.io.BufferedReader;

public class CliInput  {
    public BufferedReader input;

    public CliInput(BufferedReader input) {
        this.input = input;
    }

    public String getName() {
        return "cli-input";
    }
}
