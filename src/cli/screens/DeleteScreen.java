package cli.screens;

import java.io.PrintStream;

public class DeleteScreen implements Screen {
    public Boolean status = false;
    public String message = "";

    private PrintStream output;

    public DeleteScreen(PrintStream output) {
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
        this.output.println(" \uF0B7 [Kundenname] löscht den Kunden");
        this.output.println(" \uF0B7 [Lagerposition] löscht das Frachtstüc");
        this.output.println("--------------------------------------------");
    }

}
