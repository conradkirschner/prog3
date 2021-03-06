package app.cli.screens;

import famework.annotation.Service;

import java.io.PrintStream;

@Service
public class DeleteScreen implements Screen {
    public String message = "";

    private PrintStream output;

    public DeleteScreen(PrintStream output) {
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
        this.output.println(" \uF0B7 [Kundenname] löscht den Kunden");
        this.output.println(" \uF0B7 [Lagerposition] löscht das Frachtstück");
        this.output.println("--------------------------------------------");
    }

}
