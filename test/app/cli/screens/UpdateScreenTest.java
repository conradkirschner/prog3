package app.cli.screens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.PrintStream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

class UpdateScreenTest {

    @Mock
    private PrintStream mockOutput;

    private UpdateScreen updateScreenUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        updateScreenUnderTest = new UpdateScreen(mockOutput);
    }

    @Test
    void testGetContent() {



        updateScreenUnderTest.getContent();


        verify(mockOutput).println("");
    }

    @Test
    void testGetUsage() {



        updateScreenUnderTest.getUsage();


        verify(mockOutput).println(" \uF0B7 [Lagerposition] setzt das Inspektionsdatum auf den aktuellen Zeitpunkt");
        verify(mockOutput, times(2)).println("--------------------------------------------");

    }
}
