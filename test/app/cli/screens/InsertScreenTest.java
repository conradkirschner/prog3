package app.cli.screens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.PrintStream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

class InsertScreenTest {

    @Mock
    private PrintStream mockOutput;

    private InsertScreen insertScreenUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        insertScreenUnderTest = new InsertScreen(mockOutput);
    }

    @Test
    void testGetContent() {
        insertScreenUnderTest.getContent();

        verify(mockOutput).println("");
    }

    @Test
    void testGetUsage() {
        insertScreenUnderTest.getUsage();

         verify(mockOutput, times(2)).println("--------------------------------------------");
         verify(mockOutput).println("[Kundenname] fügt einen Kunden ein");
         verify(mockOutput, times(2)).println("");
         verify(mockOutput).println("[Frachttyp] [Kundenname] [Wert] [Einlagerungsdauer" +
                "in Sekunden] [kommaseparierte Gefahrenstoffe," +
                "einzelnes Komma für keine - radioactive,flammable,explosive] [[zerbrechlich (y/n)]" +
                "[unter Druck (y/n)] [fest (y/n)]]");
         verify(mockOutput).println("Beispiele: o UnitisedCargo Beispielkunde 2000 86400 , n");
         verify(mockOutput).println("o MixedCargoLiquidBulkAndUnitised Beispielkunde 40.50 86400 radioactive n y y");

    }
}
