package app.cli.screens;

import famework.annotation.Service;

import java.io.PrintStream;

@Service
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
        this.output.println("[Frachttyp] [Kundenname] [Wert] [Einlagerungsdauer" +
                "in Sekunden] [kommaseparierte Gefahrenstoffe," +
                "einzelnes Komma für keine - radioactive,flammable,explosive] [[zerbrechlich (y/n)]" +
                "[unter Druck (y/n)] [fest (y/n)]]");
        this.output.println("");
        this.output.println("Beispiele: o UnitisedCargo Beispielkunde 2000 86400 , n");
        this.output.println("o MixedCargoLiquidBulkAndUnitised Beispielkunde 40.50 86400 radioactive n y y");
        this.output.println("--------------------------------------------");
    }

}
