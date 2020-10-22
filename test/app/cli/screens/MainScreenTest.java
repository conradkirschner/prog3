package app.cli.screens;

import famework.configReader.ConfigBag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.PrintStream;
import java.util.Properties;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MainScreenTest {

    @Mock
    private PrintStream mockOutput;
    @Mock
    private ConfigBag mockConfigBag;

    private MainScreen mainScreenUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        Properties properties = mock(Properties.class);
        when(properties.getProperty("version")).thenReturn("2.0.1");

        mockConfigBag.props = properties;

        mainScreenUnderTest = new MainScreen(mockOutput, mockConfigBag);
    }

    @Test
    void testGetContent() {


        mainScreenUnderTest.getContent();


        verify(mockOutput, times(2)).println("--------------------------------------------");

        verify(mockOutput).println("##--PROG-3--####--Warehouse-v2.0.1--######");
        verify(mockOutput).println(":c - Einfügemodus");
        verify(mockOutput).println(":d - Löschmodus");
        verify(mockOutput).println(":r - Anzeigemodus");
        verify(mockOutput).println(":u - Änderungsmodus");
        verify(mockOutput).println(":p - Persistenzmodus");
        verify(mockOutput).println(":config - Konfigurationsmodus");

    }

    @Test
    void testGetUsage() {



        mainScreenUnderTest.getUsage();


    }
}
