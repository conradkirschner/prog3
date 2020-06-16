package app.cli.screens;


import app.cli.helper.ShowTable;
import famework.annotation.Service;

import java.io.PrintStream;

@Service
public class OverviewScreen implements Screen {
    private String mode;
    public String[][] rows = new String[10][];
    private PrintStream output;

    public OverviewScreen(PrintStream output) {
        this.output = output;
        this.mode = "";
    }

    public OverviewScreen setRows(String[][] rows) {
        this.rows = rows;
        return this;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public void getContent() {
        switch (this.mode) {
            case "customer":
                this.showCustomer();
                break;
            case "cargo":
                this.showCargo();
                break;
            case "hazard":
                this.showHazard();
                break;
            default:
                return;
        }
        this.setMode("");
    }

    private void showCargo() {
        if (rows == null) {
            this.output.println("Keine Fracht gefunden");
            return;
        }
        ShowTable st = new ShowTable(this.output);
        //st.setRightAlign(true);//if true then cell text is right aligned
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        st.setHeaders("Frachtnummer");//optional - if not used then there will be no header and horizontal lines
        for (String[] cargo: rows) {
            st.addRow(cargo[0]);
        }
        st.print();
    }

    private void showHazard() {
        if (rows == null) {
            this.output.println("Keine Fracht gefunden");
            return;
        }
        ShowTable st = new ShowTable(this.output);
        //st.setRightAlign(true);//if true then cell text is right aligned
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        st.setHeaders("Fracht Nummer", "Gefahrenstoffe", "Gewicht", "Einlagerung bis", "Besitzer", "letzte Inspektion", "Typ");
        for (String[] fracht: rows) {
            st.addRow(fracht[0], fracht[1], fracht[2], fracht[3], fracht[4], fracht[5], fracht[6]);
        }
        st.print();
    }

    private void showCustomer() {
        if (rows == null) {
            this.output.println("Keine Kunden gefunden");
            return;
        }
        ShowTable st = new ShowTable(this.output);
        //st.setRightAlign(true);//if true then cell text is right aligned
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        st.setHeaders("Customer");//optional - if not used then there will be no header and horizontal lines
        st.setHeaders("Customer");//optional - if not used then there will be no header and horizontal lines
        for (String[] customer: rows) {
            st.addRow(customer[0]);
        }
        st.print();
    }

    @Override
    public void getUsage() {
        this.output.println("--------------------------------------------");
        this.output.println("customer Anzeige der Kunden mit der Anzahl eingelagerter Frachtstücke ");
        this.output.println("cargo [[Frachttyp]] Anzeige der Frachtstücke ,ggf. gefiltert nach Typ, mit Lagerposition, Einlagerungsdatum und Datum der letzten Inspektion ");
        this.output.println("hazard [enthalten(i)/nicht enthalten(e)] Anzeige der vorhandenen bzw. nicht vorhandenen Gefahrenstoffe ");
        this.output.println("--------------------------------------------");
    }
}
