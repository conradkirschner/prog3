package cli.screens;

public class MainScreen implements Screen {

    @Override
    public void getContent() {
        System.out.println("--------------------------------------------");
        System.out.println("##--PROG-3--####--Warehouse-v0.0.1--######");
        System.out.println(":c - Einfügemodus");
        System.out.println(":d - Löschmodus");
        System.out.println(":r - Anzeigemodus");
        System.out.println(":u - Änderungsmodus");
        System.out.println(":p - Persistenzmodus");
        System.out.println(":config - Konfigurationsmodus");
        System.out.println("--------------------------------------------");
    }

    @Override
    public void getUsage() {

    }
}
