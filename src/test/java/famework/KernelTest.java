package famework;

import famework.di.DI;
import famework.di.Registry;
import famework.event.EventHandler;
import famework.event.Listener;
import famework.event.Subscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class KernelTest {

    private Kernel kernelUnderTest;

    @BeforeEach
    void setUp() {
        kernelUnderTest = new Kernel();
        kernelUnderTest.di = mock(DI.class);
        kernelUnderTest.eventHandler = mock(EventHandler.class);
    }

    @Test
    void testRun() {
        // Setup
        final ArrayList<Object> imports = new ArrayList<>(Arrays.asList("value"));
        when(kernelUnderTest.di.getRegistry()).thenReturn(new Registry());

        // Run the test
        final EventHandler result = kernelUnderTest.run(imports);

        // Verify the results
        verify(kernelUnderTest.eventHandler).registerSubscriber(any(Subscriber.class));
        verify(kernelUnderTest.eventHandler).registerListener(any(Listener.class));
    }
}
