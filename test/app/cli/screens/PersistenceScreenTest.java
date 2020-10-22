package app.cli.screens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.PrintStream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

class PersistenceScreenTest {

    @Mock
    private PrintStream mockOutput;

    private PersistenceScreen persistenceScreenUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        persistenceScreenUnderTest = new PersistenceScreen(mockOutput);
    }

    @Test
    void testGetContent() {



        persistenceScreenUnderTest.getContent();


        verify(mockOutput).println("");

    }

    @Test
    void testGetUsage() {



        persistenceScreenUnderTest.getUsage();


         verify(mockOutput, times(2)).println("--------------------------------------------");

         verify(mockOutput).println("[y] Speichern als JOS");
         verify(mockOutput).println("[y itemID] Speichert Item als JOS");

    }
}
