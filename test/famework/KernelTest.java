package famework;

import famework.di.DI;
import famework.di.Registry;
import famework.event.EventHandler;
import famework.event.Listener;
import famework.event.Subscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        ArrayList<Object> imports = new ArrayList<>();
        imports.add(System.in);
        imports.add(System.out);

        when(kernelUnderTest.di.getRegistry()).thenReturn(new Registry());


        final EventHandler result = kernelUnderTest.run(imports);

        // checks that every module could be loaded, increment when you add new subscriber
        verify(kernelUnderTest.eventHandler, times(26)).registerSubscriber(any(Subscriber.class));
        verify(kernelUnderTest.eventHandler, times(1)).registerListener(any(Listener.class));
    }
}
