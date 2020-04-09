package cli.screens;

public class InsertScreen implements Screen {

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Boolean status = false;
    public String error = "";

    @Override
    public void getContent() {
        if (status) {
            System.out.println("> Erfolgreich gespeichert");
            return;
        }
        System.out.println("> Es gab ein Fehler beim hinzufügen");
        System.out.println(error);
    }

    @Override
    public void getUsage() {
        System.out.println("--------------------------------------------");
        System.out.println("[Kundenname] fügt einen Kunden ein");
        System.out.println("");
        System.out.println("[Frachttyp] [Kundenname] [Wert] [Einlagerungsdauer\n" +
                "in Sekunden] [kommaseparierte Gefahrenstoffe,\n" +
                "einzelnes Komma für keine] [[zerbrechlich (y/n)]\n" +
                "[unter Druck (y/n)] [fest (y/n)]]");
        System.out.println("--------------------------------------------");
    }

}
