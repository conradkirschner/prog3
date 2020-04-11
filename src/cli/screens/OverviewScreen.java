package cli.screens;

import cli.helper.ShowTable;

import java.util.ArrayList;

public class OverviewScreen implements Screen {
    private String mode;
    public ArrayList<String> rows = null;

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
    }

    private void showCargo() {
        if (rows == null) {
            System.out.println("Keine Fracht gefunden");
            return;
        }
        ShowTable st = new ShowTable();
        //st.setRightAlign(true);//if true then cell text is right aligned
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        st.setHeaders("Frachtnummer");//optional - if not used then there will be no header and horizontal lines
        for (String customer: rows) {
            st.addRow(customer);
        }
        st.print();
    }

    private void showHazard() {
        if (rows == null) {
            System.out.println("Keine Fracht gefunden");
            return;
        }
        ShowTable st = new ShowTable();
        //st.setRightAlign(true);//if true then cell text is right aligned
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        st.setHeaders("Fracht");//optional - if not used then there will be no header and horizontal lines
        for (String fracht: rows) {
            st.addRow(fracht);
        }
        st.print();
    }

    private void showCustomer() {
        if (rows == null) {
            System.out.println("Keine Kunden gefunden");
            return;
        }
        ShowTable st = new ShowTable();
        //st.setRightAlign(true);//if true then cell text is right aligned
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        st.setHeaders("Customer");//optional - if not used then there will be no header and horizontal lines
        for (String customer: rows) {
            st.addRow(customer);
        }
        st.print();
    }

    @Override
    public void getUsage() {
        System.out.println("--------------------------------------------");
        System.out.println("customer Anzeige der Kunden mit der Anzahl eingelagerter Frachtstücke ");
        System.out.println("cargo [[Frachttyp]] Anzeige der Frachtstücke ,ggf. gefiltert nach Typ, mit Lagerposition, Einlagerungsdatum und Datum der letzten Inspektion ");
        System.out.println("hazard [enthalten(i)/nicht enthalten(e)] Anzeige der vorhandenen bzw. nicht vorhandenen Gefahrenstoffe ");
        System.out.println("--------------------------------------------");
    }
}
