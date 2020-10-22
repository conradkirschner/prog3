package app.cli.screens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.PrintStream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

class DeleteScreenTest {

    @Mock
    private PrintStream mockOutput;

    private DeleteScreen deleteScreenUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        deleteScreenUnderTest = new DeleteScreen(mockOutput);
    }

    @Test
    void testGetContent() {



        deleteScreenUnderTest.getContent();


        verify(mockOutput).println("");
    }

    @Test
    void testGetUsage() {



        deleteScreenUnderTest.getUsage();


        verify(mockOutput, times(2)).println("--------------------------------------------");
        verify(mockOutput).println(" \uF0B7 [Kundenname] löscht den Kunden");
        verify(mockOutput).println(" \uF0B7 [Lagerposition] löscht das Frachtstück");

    }
}
