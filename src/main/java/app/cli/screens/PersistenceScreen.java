package app.cli.screens;


import app.cli.screens.Screen;
import famework.annotation.Service;

import java.io.PrintStream;

@Service
public class PersistenceScreen implements Screen {
    private PrintStream output;

    public PersistenceScreen(PrintStream output) {
        this.output = output;
    }

    @Override
    public void getContent() {

    }

    @Override
    public void getUsage() {
        this.output.println("--------------------------------------------");
        this.output.println("[y] Speichern als JOS");
        this.output.println("[x] Speichern als JBP");
        this.output.println("--------------------------------------------");
    }
}
