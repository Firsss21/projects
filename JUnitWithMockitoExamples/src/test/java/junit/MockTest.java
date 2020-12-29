package junit;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;


public class MockTest {

    @Test
    public void test()  {
        // создаем мок
        Math mockMath = mock(Math.class);

        // при этом методе определяем фейк ретерн
        when(mockMath.division(5,5)).thenReturn(150);

        // тестим
        assertEquals(mockMath.division(5,5), 150);
    }




    // demonstrates the return of multiple values
    @Test
    public void helloWorldWithMock()  {
        Iterator<String> i= mock(Iterator.class);
        when(i.next()).thenReturn("Hello").thenReturn("world");
        String result= i.next()+" "+i.next();

        assertEquals("Hello world", result);
    }

    // сравнение
    @Test
    public void testStringCompare()  {
        Comparable c = mock(Comparable.class);
        when(c.compareTo("test")).thenReturn(1);
        assertEquals(1, c.compareTo("test"));
    }

// эни инт приведет к -1

    @Test
    public void testAnyIntCompare()  {
        Comparable<Integer> c= mock(Comparable.class);
        when(c.compareTo(anyInt())).thenReturn(-1);

        assertEquals(-1, c.compareTo(9));
    }


}
