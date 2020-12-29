package junit;

import junit.framework.TestCase;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class MainTest {
    @Rule
    public TestRule timeout = new Timeout(350);

//    @Test(timeout = 100)
    @Test
    public void threadSleep() throws InterruptedException {
        Main.threadSleep();
    }

    @Test
    public void iteratorWillReturnMockNumbers() {
        System.out.println("iteratorWillReturnMockNumbers()");
        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn(5d).thenReturn(6d).thenReturn(15.d);
        System.out.println(i.next());
        System.out.println(i.next());
        System.out.println(i.next());
    }

    @Test
    public void with_arguments() {
        Comparable c = mock(Comparable.class);
        when(c.compareTo("comparetomock")).thenReturn(1);
        assertEquals(1, c.compareTo("comparetomock"));
    }


    @Test
    public void with_specified_arguments() {
        Comparable c = mock(Comparable.class);
        when(c.compareTo(15)).thenReturn(-1);
        assertEquals(0, c.compareTo(5));
    }

    @Test
    public void with_unspecified_arguments() {
        Comparable c = mock(Comparable.class);
        when(c.compareTo(anyInt())).thenReturn(-1);
        assertEquals(-1, c.compareTo(1));
    }
}
