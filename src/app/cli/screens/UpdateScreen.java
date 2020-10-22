package app.cli.screens;

import famework.annotation.Service;

import java.io.PrintStream;

@Service
public class UpdateScreen implements Screen {
    public String message = "";

    private PrintStream output;

    public UpdateScreen(PrintStream output) {
        this.output = output;
    }

    @Override
    public void getContent() {
        this.output.println(message);
        this.message = "";
    }

    @Override
    public void getUsage() {
        this.output.println("--------------------------------------------");
        this.output.println(" \uF0B7 [Lagerposition] setzt das Inspektionsdatum auf den aktuellen Zeitpunkt");
        this.output.println("--------------------------------------------");
    }

}
