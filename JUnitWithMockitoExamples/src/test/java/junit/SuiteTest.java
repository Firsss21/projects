package junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({MainTest.class, MathTest.class, MockTest.class})
public class SuiteTest {
}
