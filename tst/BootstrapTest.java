import app.App;
import app.Bootstrap;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BootstrapTest {

    /**
     *
     * @throws IOException
     */
    @Test
    void main() throws IOException {
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        Mockito.when(bufferedReader.readLine()).thenReturn(
                ":c",
                "test-kunde",
                ":d",
                "test-kunde",
                ":c",
                "test-kunde",
                ":c",
                "Item test-kunde 3 3 , y y y",
                ":c",
                "LiquidBulkCargo test-kunde 3 3 explosive y y y",
                ":c",
                "UnitisedCargo test-kunde 3 3 explosive,radioactive,flammable y y y",
                ":c",
                "MixedCargoLiquidBulkAndUnitised test-kunde 3 3 , y y y",
                ":r",
                "customer",
                ":r",
                "hazard y",
                ":r",
                "hazard n",
                ":r",
                "cargo MixedCargoLiquidBulkAndUnitised ",
                ":x"
        );
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        App app = Bootstrap.setup(bufferedReader, new PrintStream(bo));
        Bootstrap.run(app, false);
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());
        System.out.print(allWrittenLines);
        assertFalse(allWrittenLines.contains("Fehler"));

    }
}