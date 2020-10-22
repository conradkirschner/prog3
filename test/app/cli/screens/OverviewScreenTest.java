package app.cli.screens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.PrintStream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

class OverviewScreenTest {

    @Mock
    private PrintStream mockOutput;

    private OverviewScreen overviewScreenUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        overviewScreenUnderTest = new OverviewScreen(mockOutput);
    }

    @Test
    void testGetContentWithCustomer() {
        overviewScreenUnderTest.setMode("customer");
        String[][] rows = new String[1][];
        rows[0] = new String[1];
        rows[0][0] = "test";

        overviewScreenUnderTest.setRows(rows);
        overviewScreenUnderTest.getContent();

        verify(mockOutput, times(5)).println();
    }
    @Test
    void testGetContentWithNoCustomer() {
        overviewScreenUnderTest.setMode("customer");

        overviewScreenUnderTest.setRows(null);
        overviewScreenUnderTest.getContent();

        verify(mockOutput, times(1)).println("Keine Kunden gefunden");
    }

    @Test
    void testGetContentWithCargo() {
        overviewScreenUnderTest.setMode("cargo");
        String[][] rows = new String[1][];
        rows[0] = new String[1];
        rows[0][0] = "test";

        overviewScreenUnderTest.setRows(rows);
        overviewScreenUnderTest.getContent();

        verify(mockOutput, times(5)).println();
    }
    @Test
    void testGetContentWithNoCargo() {
        overviewScreenUnderTest.setMode("cargo");

        overviewScreenUnderTest.setRows(null);
        overviewScreenUnderTest.getContent();

        verify(mockOutput, times(1)).println("Keine Fracht gefunden");
    }

    @Test
    void testGetContentWithHazard() {
        overviewScreenUnderTest.setMode("hazard");
        String[][] rows = new String[1][];
        rows[0] = new String[7];
        rows[0][0] = "Fracht Nummer";
        rows[0][1] = "Gefahrenstoffe";
        rows[0][2] = "Gewicht";
        rows[0][3] =  "Einlagerung bis";
        rows[0][4] =   "Besitzer";
        rows[0][5] =   "letzte Inspektion";
        rows[0][6] =    "Typ";

        overviewScreenUnderTest.setRows(rows);
        overviewScreenUnderTest.getContent();

        verify(mockOutput, times(5)).println();
    }
    @Test
    void testGetContentWithNoHazard() {
        overviewScreenUnderTest.setMode("hazard");

        overviewScreenUnderTest.setRows(null);
        overviewScreenUnderTest.getContent();

        verify(mockOutput, times(1)).println("Keine Fracht gefunden");
    }

    @Test
    void testGetUsage() {
        
        overviewScreenUnderTest.getUsage();
        
        verify(mockOutput, times(2)).println("--------------------------------------------");
        verify(mockOutput).println("customer Anzeige der Kunden mit der Anzahl eingelagerter Frachtstücke ");
        verify(mockOutput).println("cargo [[Frachttyp]] Anzeige der Frachtstücke ,ggf. gefiltert nach Typ, mit Lagerposition, Einlagerungsdatum und Datum der letzten Inspektion ");
        verify(mockOutput).println("hazard [enthalten(i)/nicht enthalten(e)] Anzeige der vorhandenen bzw. nicht vorhandenen Gefahrenstoffe ");

    }
}
