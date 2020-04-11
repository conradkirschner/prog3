package cli.screens;

import java.io.PrintStream;

public class InsertScreen implements Screen {
    public Boolean status = false;
    public String message = "";

    private PrintStream output;

    public InsertScreen(PrintStream output) {
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
        this.output.println("[Kundenname] fügt einen Kunden ein");
        this.output.println("");
        this.output.println("[Frachttyp] [Kundenname] [Wert] [Einlagerungsdauer\n" +
                "in Sekunden] [kommaseparierte Gefahrenstoffe,\n" +
                "einzelnes Komma für keine] [[zerbrechlich (y/n)]\n" +
                "[unter Druck (y/n)] [fest (y/n)]]");
        this.output.println("--------------------------------------------");
    }

}
