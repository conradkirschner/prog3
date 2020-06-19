package app.cli.screens;

import app.cli.screens.Screen;
import famework.annotation.Service;

import java.io.PrintStream;

@Service
public class UpdateScreen implements Screen {
    public Boolean status = false;
    public String message = "";

    private PrintStream output;

    public UpdateScreen(PrintStream output) {
        this.output = output;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
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
