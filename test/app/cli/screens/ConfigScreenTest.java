package app.cli.screens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.PrintStream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

class ConfigScreenTest {

    @Mock
    private PrintStream mockOutput;

    private ConfigScreen configScreenUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        configScreenUnderTest = new ConfigScreen(mockOutput);
    }

    @Test
    void testGetContent() {



        configScreenUnderTest.getContent();


        verify(mockOutput).println("");
    }

    @Test
    void testGetUsage() {



        configScreenUnderTest.getUsage();


        verify(mockOutput, times(2)).println("--------------------------------------------");
        verify(mockOutput).println(" \uF0B7 [activate] activate SubscriberName - aktiviert einen Subscriber");
        verify(mockOutput).println(" \uF0B7 [addStoragePlace] place StorageName Size StorageNumber - Erstellt und aktiviert ein neues Lager");
        verify(mockOutput).println(" \uF0B7 [storage] StorageName StorageName ...  - Erstellt und aktiviert ein neues Lager");
        verify(mockOutput).println(" \uF0B7  example: storage 0");
        verify(mockOutput).println(" \uF0B7  :x back");

    }
}
