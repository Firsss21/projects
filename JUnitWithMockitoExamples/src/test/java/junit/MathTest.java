package junit;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MathTest {

      int a, b, result;

      public MathTest(int a, int b, int result) {
          this.a = a;
          this.b = b;
          this.result = result;
      }
        private Math testMath = new Math();

        @Parameterized.Parameters
        public static Collection numbers() {
            return Arrays.asList(new Object[][] {{1,2,3}, {3,5,8}, {Integer.MIN_VALUE, Integer.MAX_VALUE, -1}, {6,6,12}});
        }

        @Test
        @Ignore
        public void testSubtraction() {
            assertEquals(testMath.subtraction(a,b), -15);
        }

        @Test
        @Ignore
        public void testMultiplication() {
            assertEquals(testMath.multiplication(a,b), 0);
        }

        @Test(timeout = 100)
        public void testAddition() {
            assertEquals(result, testMath.addition(a,b));
        }

        // ??
        @Test(expected = ArithmeticException.class)
        @Ignore
        public void testDivision() throws InterruptedException{
            int result = testMath.division(b,a);
            assertEquals(0,result);
        }

        @Before
        public void setUp() throws Exception {
            System.out.println("Before");
        }

        @After
        public void tearDown() throws Exception {
            System.out.println("After");
        }
}