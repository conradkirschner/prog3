package app.cli.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class ShowTableTest {

    @Mock
    private PrintStream mockOutput;

    private ShowTable showTableUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        showTableUnderTest = new ShowTable(mockOutput);
    }

    @Test
    void testSetShowVerticalLines() {
        showTableUnderTest.setShowVerticalLines(false);
    }

    @Test
    void testAddRow() {
        showTableUnderTest.addRow("cells");
    }

    @Test
    void testPrint() throws Exception {
        showTableUnderTest.addRow("cells");
        showTableUnderTest.setShowVerticalLines(false);
        showTableUnderTest.setHeaders("A");

        final PrintStream spyPrintStream = spy(new PrintStream("s"));

        when(mockOutput.printf(anyString())).thenReturn(spyPrintStream);

        showTableUnderTest.print();

        verify(mockOutput, times(5)).println();
    }
    @Test
    void testPrintWithException() throws Exception {
        showTableUnderTest.addRow("cells","cells");
        showTableUnderTest.setShowVerticalLines(false);
        String[] header = new String[1];
        header[0] = "test";

        showTableUnderTest.setHeaders(header);

        final PrintStream spyPrintStream = spy(new PrintStream("s"));

        when(mockOutput.printf(anyString())).thenReturn(spyPrintStream);


        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            showTableUnderTest.print();
        });

        String expectedMessage = "Number of row-cells and headers should be consistent";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testPrintWithNoHeaderSet() throws Exception {
        showTableUnderTest.addRow("cells","cells");
        showTableUnderTest.setShowVerticalLines(false);

        showTableUnderTest.setHeaders(null);

        final PrintStream spyPrintStream = spy(new PrintStream("s"));

        when(mockOutput.printf(anyString())).thenReturn(spyPrintStream);


        showTableUnderTest.print();

        verify(mockOutput, times(1)).println();

    }
    @Test
    void testPrintVertical() throws Exception {
        showTableUnderTest.addRow("cells");
        showTableUnderTest.setShowVerticalLines(true);
        showTableUnderTest.setHeaders("A");

        final PrintStream spyPrintStream = spy(new PrintStream("s"));

        when(mockOutput.printf(anyString())).thenReturn(spyPrintStream);

        showTableUnderTest.print();

        verify(mockOutput, times(5)).println();
    }
}
