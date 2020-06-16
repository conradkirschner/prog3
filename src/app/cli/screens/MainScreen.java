package app.cli.screens;

import famework.annotation.Service;
import famework.configReader.ConfigBag;

import java.io.PrintStream;

@Service
public class MainScreen implements Screen {

    private PrintStream output;
    String version ="";

    public MainScreen(PrintStream output, ConfigBag configBag) {
        this.output = output;
        version = configBag.props.getProperty("version");
    }

    @Override
    public void getContent() {
        this.output.println("--------------------------------------------");
        this.output.println("##--PROG-3--####--Warehouse-v"+ this.version +"--######");
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
