package B1;

import static org.junit.Assert.*;
import org.junit.*;

public class MathFuncTest {
    private MathFunc math;

    @Before
    public void init() {
        math = new MathFunc();
    }

    @After
    public void tearDown() {
        math = null;
    }

    @Test
    public void calls() {
        assertEquals(0, math.getCalls());

        math.factorial(1);
        assertEquals(1, math.getCalls());

        math.factorial(1);
        assertEquals(2, math.getCalls());
    }

    @Test
    public void factorial() {
        assertEquals(1, math.factorial(0));
        assertEquals(1, math.factorial(1));
        assertEquals(120, math.factorial(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void factorialNegative() {
        math.factorial(-1);
    }

    @Ignore
    @Test
    public void todo() {
        assertTrue(math.plus(1, 1) == 3);
    }
}
