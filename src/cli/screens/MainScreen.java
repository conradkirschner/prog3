package cli.screens;

import java.io.PrintStream;

public class MainScreen implements Screen {
    private PrintStream output;

    public MainScreen(PrintStream output) {
        this.output = output;
    }

    @Override
    public void getContent() {
        this.output.println("--------------------------------------------");
        this.output.println("##--PROG-3--####--Warehouse-v0.0.1--######");
        this.output.println(":c - Einfügemodus");
        this.output.println(":d - Löschmodus");
        this.output.println(":r - Anzeigemodus");
        this.output.println(":u - Änderungsmodus");
        this.output.println(":p - Persistenzmodus");
        this.output.println(":config - Konfigurationsmodus");
        this.output.println("--------------------------------------------");
    }

    @Override
    public void getUsage() {

    }
}
