package app.cli.screens;


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
        this.output.println("");
    }

    @Override
    public void getUsage() {
        this.output.println("--------------------------------------------");

        this.output.println("[y] Speichern als JOS");
        this.output.println("[y itemID] Speichert Item als JOS");

        this.output.println("--------------------------------------------");
    }
}
